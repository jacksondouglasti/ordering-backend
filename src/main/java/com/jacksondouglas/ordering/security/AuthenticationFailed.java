package com.jacksondouglas.ordering.security;

public class AuthenticationFailed {

    private Integer status;
    private String message;
    private Long timestamp;
    private String error;
    private String path;

    public AuthenticationFailed() {
    }

    public AuthenticationFailed(Integer status, String message, Long timestamp, String error, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
