package com.walmart.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BaseResponse implements Response, Serializable {
    private static final long serialVersionUID = -3086475621942934987L;
    protected boolean isSuccessful = true;
    protected Integer status = 200;
    protected List<ErrorResponse> errors;
    protected String message;
    protected Long timestamp;

    public BaseResponse() {
    }

    @JsonProperty
    public boolean getSuccess() {
        return this.isSuccessful;
    }

    public void setSuccess(boolean success) {
        this.isSuccessful = success;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addError(ErrorResponse error) {
        if (this.errors == null) {
            this.errors = new ArrayList();
        }

        this.errors.add(error);
    }

    public List<ErrorResponse> getErrors() {
        return this.errors;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

