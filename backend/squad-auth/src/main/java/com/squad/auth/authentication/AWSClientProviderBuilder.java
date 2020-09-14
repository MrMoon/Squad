package com.squad.auth.authentication;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.squad.auth.util.AWSConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AWSClientProviderBuilder {

    private final AWSConfiguration awsConfiguration;
    private AWSCognitoIdentityProvider cognitoIdentityProvider;
    //private AmazonS3 s3Client;
    private ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider;
    private String region;

    @Autowired
    public AWSClientProviderBuilder(AWSConfiguration awsConfiguration) {
        this.awsConfiguration = awsConfiguration;
    }

    private void initCommonInfo() {
        if (propertiesFileCredentialsProvider == null)
            propertiesFileCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
        if (region == null) region = awsConfiguration.getRegion();
    }

    public AWSCognitoIdentityProvider getAWSCognitoIdentityClient() {
        if (cognitoIdentityProvider == null) {
            initCommonInfo();
            cognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder.standard()
                    .withCredentials(propertiesFileCredentialsProvider)
                    .withRegion(region)
                    .build();
        }
        return cognitoIdentityProvider;
    }

    /*public AmazonS3 getAWSS3Client() {
        if (s3Client == null) {
            initCommonInfo();
            s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(propertiesFileCredentialsProvider)
                    .withRegion(region)
                    .build();
        }
        return s3Client;
    }*/
}
