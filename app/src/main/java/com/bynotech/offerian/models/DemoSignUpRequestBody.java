package com.bynotech.offerian.models;

/**
 * Created by jacosrasel on 11/30/2017.
 */

public class DemoSignUpRequestBody {
    String mobile;

    public DemoSignUpRequestBody(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "DemoSignUpRequestBody{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
