package org.yagna.samples.jwt.model;

/**
 * Created by asish on 4/2/16.
 */
public class AuthStatus {

    private String status;

    public AuthStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
