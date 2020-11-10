package com.dou361.jjdxm_ijkplayer.module;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/3/7
 * <p/>
 * 描 述：网络接口api
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public interface ApiService {

    /**
     * 直播相关接口
     */
    @FormUrlEncoded
    @POST("/kkgame/entrance")
    Call<String> live(@Field("parameter") String params);


}
