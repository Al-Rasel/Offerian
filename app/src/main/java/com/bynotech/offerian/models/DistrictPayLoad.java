package com.bynotech.offerian.models;

/**
 * Created by jacosrasel on 11/28/2017.
 */

public class DistrictPayLoad {
    String bd_district_id;
    String name_en;

    public DistrictPayLoad(String bd_district_id, String name_en) {
        this.bd_district_id = bd_district_id;
        this.name_en = name_en;
    }

    public String getBd_district_id() {
        return bd_district_id;
    }

    public void setBd_district_id(String bd_district_id) {
        this.bd_district_id = bd_district_id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    @Override
    public String toString() {
        return "DistrictPayLoad{" +
                "bd_district_id='" + bd_district_id + '\'' +
                ", name_en='" + name_en + '\'' +
                '}';
    }
}
