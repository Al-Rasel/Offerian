package com.bynotech.offerian.models;

/**
 * Created by jacosrasel on 11/29/2017.
 */

public class SignupRequestBody {

    String full_name;
    String mobile;
    int bd_district_id;

    int gender;

    String password;

    String reffered_by;

    String ip_address;
    String os;
    String os_version;
    String band_name;
    String divice_name;
    String model;
    String imei;
    String fcm_token;
    String operator;

    String screen_size;


    public SignupRequestBody(String full_name, String mobile, int bd_district_id, int gender, String password, String reffered_by, String ip_address, String os, String os_version, String band_name, String divice_name, String model, String imei, String fcm_token, String operator, String screen_size) {
        this.full_name = full_name;
        this.mobile = mobile;
        this.bd_district_id = bd_district_id;
        this.gender = gender;
        this.password = password;
        this.reffered_by = reffered_by;
        this.ip_address = ip_address;
        this.os = os;
        this.os_version = os_version;
        this.band_name = band_name;
        this.divice_name = divice_name;
        this.model = model;
        this.imei = imei;
        this.fcm_token = fcm_token;
        this.operator = operator;
        this.screen_size = screen_size;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBd_district_id() {
        return bd_district_id;
    }

    public void setBd_district_id(int bd_district_id) {
        this.bd_district_id = bd_district_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReffered_by() {
        return reffered_by;
    }

    public void setReffered_by(String reffered_by) {
        this.reffered_by = reffered_by;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }

    public String getDivice_name() {
        return divice_name;
    }

    public void setDivice_name(String divice_name) {
        this.divice_name = divice_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(String screen_size) {
        this.screen_size = screen_size;
    }

    @Override
    public String toString() {
        return "SignupRequestBody{" +
                "full_name='" + full_name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bd_district_id=" + bd_district_id +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", reffered_by='" + reffered_by + '\'' +
                ", ip_address='" + ip_address + '\'' +
                ", os='" + os + '\'' +
                ", os_version='" + os_version + '\'' +
                ", band_name='" + band_name + '\'' +
                ", divice_name='" + divice_name + '\'' +
                ", model='" + model + '\'' +
                ", imei='" + imei + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                ", operator='" + operator + '\'' +
                ", screen_size='" + screen_size + '\'' +
                '}';
    }
}
