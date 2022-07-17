package com.authorizationservice.handler;


public class JarvisException extends RuntimeException {

    private static final long serialVersionUID = 8018712198999585399L;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JarvisException() {
        super();
    }

    public JarvisException(String message) {
        super(message);
    }

    public JarvisException(String message, int code) {
        super(message);
        this.code = code;
    }

    public JarvisException(String message, Throwable cause) {
        super(message, cause);
    }

    public JarvisException(Throwable cause) {
        super(cause);
    }

    protected JarvisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
