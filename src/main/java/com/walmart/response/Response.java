package com.walmart.response;


import java.io.Serializable;
import java.util.List;

public interface Response extends Serializable {
    boolean getSuccess();

    void setSuccess(boolean var1);

    Integer getStatus();

    void setStatus(Integer var1);

    void addError(ErrorResponse var1);

    List<ErrorResponse> getErrors();

    String getMessage();

    void setMessage(String var1);

    Long getTimestamp();

    void setTimestamp(Long var1);
}
