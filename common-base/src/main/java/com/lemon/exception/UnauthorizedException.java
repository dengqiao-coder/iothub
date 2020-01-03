package com.lemon.exception;

/**
 * 未授权异常
 */
public class UnauthorizedException extends Exception{
    public UnauthorizedException(String message)
    {
        super(message);
    }


    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
