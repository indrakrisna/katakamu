package edu.unikom.katakamu;

import com.google.gson.annotations.SerializedName;

public class WebResponse<T> {

    @SerializedName("data")
    private T data;

    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    public T getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
