package com.weibangbang.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/19 14:35
 * 功能描述：
 */
public interface ApiService {
    String OFFICIAL_WEB = "http://weibangbang.dazhu-ltd.cn/";
    String BASE_URL = OFFICIAL_WEB+"index.php/index/index/";

    @FormUrlEncoded
    @POST("banner.html")
    Call<ResponseBody> postBanners(@Field("banner_id") String banner_id );

    @POST("notice.html")
    Call<ResponseBody> postNotice();
}
