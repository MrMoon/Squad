package com.squad.auth.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.squad.auth.authentication.AWSClientProviderBuilder;
import com.squad.auth.exception.CognitoException;
import com.squad.auth.model.*;
import com.squad.auth.security.model.SpringSecurityUser;
import com.squad.auth.util.AWSConfiguration;
import com.squad.auth.util.ValidationHelper;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CognitoAuthenticationService {

    private static final String NEW_PASS_WORD = "NEW_PASSWORD";
    private static final String NEW_PASS_WORD_REQUIRED = "NEW_PASSWORD_REQUIRED";
    private static final String PASS_WORD = "PASSWORD";
    private static final String USERNAME = "USERNAME";


    private final AWSClientProviderBuilder cognitoBuilder;
    private final AWSConfiguration cognitoConfig;

    @Autowired
    public CognitoAuthenticationService(AWSClientProviderBuilder cognitoBuilder , AWSConfiguration cognitoConfig) {
        this.cognitoBuilder = cognitoBuilder;
        this.cognitoConfig = cognitoConfig;
    }

    private AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
        return cognitoBuilder.getAWSCognitoIdentityClient();
    }

    public SpringSecurityUser authenticate(AuthenticationRequest authenticationRequest) {

        AuthenticationResultType authenticationResult = null;
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        try {
            final Map<String, String> authParams = new HashMap<>();
            authParams.put(USERNAME , authenticationRequest.getUsername());
            authParams.put(PASS_WORD , authenticationRequest.getPassword());

            final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
            authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                    .withClientId(cognitoConfig.getClientId())
                    .withUserPoolId(cognitoConfig.getPoolId())
                    .withAuthParameters(authParams);
            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);

            if (StringUtils.isNotBlank(result.getChallengeName())) {
                if (NEW_PASS_WORD_REQUIRED.equals(result.getChallengeName())) {

                    if (null == authenticationRequest.getNewPassword())
                        throw new CognitoException("User must provide a new password" , CognitoException.USER_MUST_CHANGE_PASS_WORD_EXCEPTION_CODE , result.getChallengeName());
                    else {

                        final Map<String, String> challengeResponses = new HashMap<>();
                        challengeResponses.put(USERNAME , authenticationRequest.getUsername());
                        challengeResponses.put(PASS_WORD , authenticationRequest.getPassword());
                        challengeResponses.put(NEW_PASS_WORD , authenticationRequest.getNewPassword());

                        final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest();
                        request.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                                .withChallengeResponses(challengeResponses)
                                .withClientId(cognitoConfig.getClientId())
                                .withUserPoolId(cognitoConfig.getPoolId())
                                .withSession(result.getSession());

                        AdminRespondToAuthChallengeResult resultChallenge = cognitoClient.adminRespondToAuthChallenge(request);
                        authenticationResult = resultChallenge.getAuthenticationResult();

                    }
                } else
                    throw new CognitoException(result.getChallengeName() , CognitoException.USER_MUST_DO_ANOTHER_CHALLENGE , result.getChallengeName());
            } else authenticationResult = result.getAuthenticationResult();
            SpringSecurityUser springSecurityUser = new SpringSecurityUser();
            springSecurityUser.setUsername(authenticationRequest.getUsername());
            springSecurityUser.setPassword(authenticationRequest.getPassword());
            springSecurityUser.setAccessToken(authenticationResult.getAccessToken());
            springSecurityUser.setExpiresIn(authenticationResult.getExpiresIn());
            springSecurityUser.setTokenType(authenticationResult.getTokenType());
            springSecurityUser.setRefreshToken(authenticationResult.getRefreshToken());
            springSecurityUser.setIdToken(authenticationResult.getIdToken());
            return springSecurityUser;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (CognitoException cognitoException) {
            throw cognitoException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public UserResponse getUserInfo(String accessToken) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        try {
            if (StringUtils.isBlank(accessToken))
                throw new CognitoException("User must provide an access token" , CognitoException.INVALID_ACCESS_TOKEN_EXCEPTION , "User must provide an access token");

            GetUserRequest userRequest = new GetUserRequest().withAccessToken(accessToken);
            GetUserResult userResult = cognitoClient.getUser(userRequest);
            List<AttributeType> userAttributes = userResult.getUserAttributes();
            return getUserAttributesData(userAttributes , userResult.getUsername());
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    private @NotNull UserResponse getUserAttributesData(@NotNull List<AttributeType> userAttributes , String username) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(username);
        for (AttributeType attribute : userAttributes) {
            switch (attribute.getName()) {
                case "email":
                    userResponse.setEmail(attribute.getValue());
                    break;
                case "name":
                    userResponse.setFirstName(attribute.getValue());
                    break;
                case "family_name":
                    userResponse.setLastName(attribute.getValue());
                    break;
            }
        }
        return userResponse;
    }

    public UserSignUpResponse signUp(@NotNull UserSignUpRequest signUpRequest) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        if (StringUtils.isEmpty(signUpRequest.getUsername())) signUpRequest.setUsername(signUpRequest.getEmail());

        try {
            AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
                    .withUserPoolId(cognitoConfig.getPoolId())
                    .withUsername(signUpRequest.getUsername())
                    .withUserAttributes(
                            new AttributeType()
                                    .withName("email")
                                    .withValue(signUpRequest.getEmail()) ,
                            new AttributeType()
                                    .withName("name")
                                    .withValue(signUpRequest.getFirstName()) ,
                            new AttributeType()
                                    .withName("family_name")
                                    .withValue(signUpRequest.getLastName()) ,
                            new AttributeType()
                                    .withName("custom:brokerID")
                                    .withValue(signUpRequest.getBrokerId()) ,
                            new AttributeType()
                                    .withName("email_verified")
                                    .withValue("true"))
                    .withTemporaryPassword("QuhxmE472.")
                    .withMessageAction("SUPPRESS")
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                    .withForceAliasCreation(Boolean.FALSE);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(cognitoRequest);
            UserSignUpResponse userResult = new UserSignUpResponse();
            UserType cognitoUser = createUserResult.getUser();
            userResult.setUsername(signUpRequest.getUsername());
            userResult.setEmail(signUpRequest.getEmail());
            userResult.setEnabled(cognitoUser.getEnabled());
            userResult.setUserStatus(cognitoUser.getUserStatus());
            userResult.setLastModifiedDate(ValidationHelper.convertDateToString(cognitoUser.getUserLastModifiedDate() , "MM-dd-yyyy"));
            userResult.setUserCreationDate(ValidationHelper.convertDateToString(cognitoUser.getUserCreateDate() , "MM-dd-yyyy"));
            userResult.setBrokerId(signUpRequest.getBrokerId());
            return userResult;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (CognitoException cognitoException) {
            throw cognitoException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public void signUpConfirmation(@NotNull UserSignUpRequest signUpRequest) {
        String temporaryPassword = "QuhxmE472.";
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        if (StringUtils.isBlank(signUpRequest.getEmail()))
            throw new CognitoException("Invalid email" , CognitoException.EMAIL_MISSING_EXCEPTION , "Invalid email");

        if (StringUtils.isBlank(signUpRequest.getPassword()))
            throw new CognitoException("Invalid Password" , CognitoException.INVALID_PASS_WORD_EXCEPTION , "Invalid password");

        try {
            Map<String, String> initialParams = new HashMap<>();
            initialParams.put(USERNAME , signUpRequest.getUsername());
            initialParams.put(PASS_WORD , temporaryPassword);

            AdminInitiateAuthRequest initialRequest = new AdminInitiateAuthRequest()
                    .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                    .withAuthParameters(initialParams)
                    .withClientId(cognitoConfig.getClientId())
                    .withUserPoolId(cognitoConfig.getPoolId());

            AdminInitiateAuthResult initialResponse = cognitoClient.adminInitiateAuth(initialRequest);

            if (!ChallengeNameType.NEW_PASSWORD_REQUIRED.name().equals(initialResponse.getChallengeName()))
                throw new CognitoException(initialResponse.getChallengeName() , CognitoException.USER_MUST_DO_ANOTHER_CHALLENGE , initialResponse.getChallengeName());

            Map<String, String> challengeResponses = new HashMap<>();
            challengeResponses.put(USERNAME , signUpRequest.getUsername());
            challengeResponses.put(PASS_WORD , temporaryPassword);
            challengeResponses.put(NEW_PASS_WORD , signUpRequest.getPassword());

            AdminRespondToAuthChallengeRequest finalRequest = new AdminRespondToAuthChallengeRequest()
                    .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                    .withChallengeResponses(challengeResponses)
                    .withClientId(cognitoConfig.getClientId())
                    .withUserPoolId(cognitoConfig.getPoolId())
                    .withSession(initialResponse.getSession());

            AdminRespondToAuthChallengeResult challengeResponse = cognitoClient.adminRespondToAuthChallenge(finalRequest);

            if (!StringUtils.isBlank(challengeResponse.getChallengeName()))
                throw new CognitoException(challengeResponse.getChallengeName() , CognitoException.USER_MUST_DO_ANOTHER_CHALLENGE , challengeResponse.getChallengeName());

            addUserToGroup(signUpRequest.getUsername() , cognitoConfig.getDeveloperGroup());
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (CognitoException cognitoException) {
            throw cognitoException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public void addUserToGroup(String username , String groupname) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        try {
            AdminAddUserToGroupRequest addUserToGroupRequest = new AdminAddUserToGroupRequest()
                    .withGroupName(groupname)
                    .withUserPoolId(cognitoConfig.getPoolId())
                    .withUsername(username);
            cognitoClient.adminAddUserToGroup(addUserToGroupRequest);
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public void deleteUser(String user) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        try {
            AdminDeleteUserRequest deleteAdminUserRequest = new AdminDeleteUserRequest()
                    .withUserPoolId(cognitoConfig.getPoolId())
                    .withUsername(user);
            cognitoClient.adminDeleteUser(deleteAdminUserRequest);
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public String signOut(String accessToken , String username) {
        String resultMessage = null;
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        try {
            if (null != accessToken) {
                GlobalSignOutRequest globalSignOutRequest = new GlobalSignOutRequest().withAccessToken(accessToken);
                cognitoClient.globalSignOut(globalSignOutRequest);
                resultMessage = "SUCCESS";
                return resultMessage;
            } else
                throw new CognitoException("Missing access token" , CognitoException.ACCESS_TOKEN_MISSING_EXCEPTION , "Missing access token");
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (CognitoException cognitoException) {
            throw cognitoException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public PasswordResponse resetPassword(@NotNull PasswordRequest passwordRequest) {
        String username = passwordRequest.getUsername();
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        try {
            if (StringUtils.isBlank(username))
                throw new CognitoException("Invalid username" , CognitoException.INVALID_USERNAME_EXCEPTION , "Invalid username");
            ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest().withClientId(cognitoConfig.getClientId()).withUsername(username);
            ForgotPasswordResult forgotPasswordResult = cognitoClient.forgotPassword(forgotPasswordRequest);
            PasswordResponse passwordResponse = new PasswordResponse();
            passwordResponse.setDestination(forgotPasswordResult.getCodeDeliveryDetails().getDestination());
            passwordResponse.setDeliveryMedium(forgotPasswordResult.getCodeDeliveryDetails().getDeliveryMedium());
            passwordResponse.setUsername(username);
            passwordResponse.setMessage("SUCCESS");
            return passwordResponse;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (CognitoException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

    public PasswordResponse confirmResetPassword(@NotNull PasswordRequest passwordRequest) {
        String username = passwordRequest.getUsername();
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        try {
            ConfirmForgotPasswordRequest forgotPasswordRequest = new ConfirmForgotPasswordRequest()
                    .withClientId(cognitoConfig.getClientId())
                    .withUsername(username)
                    .withPassword(passwordRequest.getPassword())
                    .withConfirmationCode(passwordRequest.getConfirmationCode());

            cognitoClient.confirmForgotPassword(forgotPasswordRequest);

            PasswordResponse passwordResponse = new PasswordResponse();
            passwordResponse.setUsername(username);
            passwordResponse.setMessage("SUCCESS");

            return passwordResponse;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , e.getErrorCode() , e.getMessage() + e.getErrorCode());
        } catch (CognitoException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CognitoException(e.getMessage() , CognitoException.GENERIC_EXCEPTION_CODE , e.getMessage());
        }
    }

}
