package org.yagna.samples.jwt.exception;

/**
 * Created by asish on 3/28/16.
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg) {
        super(msg);
    }
}
