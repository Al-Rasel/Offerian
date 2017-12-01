package com.bynotech.offerian.interfaces;

import com.bynotech.offerian.models.DemoOtpCheck;
import com.bynotech.offerian.models.DemoOtpPayLoad;
import com.bynotech.offerian.models.DemoSignUpRequestBody;
import com.bynotech.offerian.models.DistrictPayLoad;
import com.bynotech.offerian.models.SignUpPayLoad;
import com.bynotech.offerian.models.SignupRequestBody;
import com.bynotech.offerian.models.UserSignInRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by jacosrasel on 11/28/2017.
 */

public interface UserAuthRetrofitInterface {


    @GET("api/apps/getalldistricts")
    Call<List<DistrictPayLoad>> getAllDistrict();


    @POST("api/apps/signup")
    Call<SignUpPayLoad> signUpNetworkCall(@Body SignupRequestBody signupRequestBody);

    @POST("api/apps/demosignup")
    Call<SignUpPayLoad> demoSignUp(@Body DemoSignUpRequestBody signupRequestBody);

    @POST("api/apps/demootpcheck")
    Call<DemoOtpPayLoad> demoOtpCheck(@Body DemoOtpCheck demoOtpCheck);


    @POST("api/apps/login ")
    Call<SignUpPayLoad> userSignInNetworkCall(@Body UserSignInRequestBody signupRequestBody);

}
