package com.mini.erp.product.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

    @JsonProperty("field name")
    private String fieldName;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status code")
    private Object status;

    @JsonProperty("url")
    private String url;


    public ErrorMessage(String fieldName, String message, Object status, String url) {
        this.fieldName = fieldName;
        this.message = message;
        this.status = status;
        this.url = url;
    }

    public ErrorMessage(String message, Object status, String url) {
        this.message = message;
        this.status = status;
        this.url = url;
    }


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
