package com.squad.auth.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseWrapper {

    private Object data, metaData;
    private List<ErrorMessage> errorMessages;

    public ResponseWrapper(Object data , Object metaData , List<ErrorMessage> errorMessages) {
        super();
        this.data = data;
        this.metaData = metaData;
        this.errorMessages = errorMessages;
    }
}
