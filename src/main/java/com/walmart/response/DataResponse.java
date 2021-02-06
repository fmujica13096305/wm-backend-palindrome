package com.walmart.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.walmart.utils.PageState;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class DataResponse extends BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ITEMS_ELEMENT = "items";
    private static final String PAGE_STATE = "pageState";
    @JsonProperty
    protected Object data;

    public DataResponse() {
    }

    public void setReturnData(Object value) {
        this.data = value;
    }

    public void setReturnList(Object list) {
        this.setReturnList(list, (PageState) null);
    }

    public void setReturnList(Object list, PageState pageState) {
        Map<String, Object> items = new HashMap();
        items.put("items", list);
        if (pageState != null) {
            items.put("pageState", pageState);
        }

        this.data = items;
    }

    public Object getData() {
        return this.data;
    }

    public String toString() {
        return "DataResponse [success=" + this.getSuccess() + ", status=" + this.getStatus() + ", message=" + this.getMessage() + ", data =" + this.data + "]";
    }
}
