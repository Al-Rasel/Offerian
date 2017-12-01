package com.bynotech.offerian.models;

/**
 * Created by jacosrasel on 11/29/2017.
 */

public class SignUpPayLoad {


    int status;

    int session_id;

    public SignUpPayLoad(int status, int session_id) {
        this.status = status;
        this.session_id = session_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        return "SignUpPayLoad{" +
                "status=" + status +
                ", session_id=" + session_id +
                '}';
    }
}
