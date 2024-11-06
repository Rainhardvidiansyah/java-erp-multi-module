package com.mini.erp.product.dto.response;

public class HttpResponse <T>{

    private T status;

    private T responseMessage;

    private T statusResponse;

    public HttpResponse(T status, T responseMessage, T data) {
        this.status = status;
        this.responseMessage = responseMessage;
        this.statusResponse = data;
    }

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    public T getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(T responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(T statusResponse) {
        this.statusResponse = statusResponse;
    }
}
