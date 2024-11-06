package com.mini.erp.user.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserErrorMessage {


    @JsonProperty("field name")
    private String fieldName;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status code")
    private Object status;

    @JsonProperty("url")
    private String url;


    public UserErrorMessage(String fieldName, String message, Object status, String url) {
        this.fieldName = fieldName;
        this.message = message;
        this.status = status;
        this.url = url;
    }

    public UserErrorMessage(String message, Object status, String url) {
        this.message = message;
        this.status = status;
        this.url = url;
    }

    public UserErrorMessage() {}


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
