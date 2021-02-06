package com.walmart.response;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String subcode;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String subcode, String message) {
        this.subcode = subcode;
        this.message = message;
    }

    public String getSubcode() {
        return this.subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
