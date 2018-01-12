package com.xinjian.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lxj on 2018/1/10 0010.
 */

public interface NewsService {

    //    String baseUrl = "https://api.douban.com/v2/movie/";
    String baseUrl = "http://lf.snssdk.com/api/news/";
//    String baseUrl = "http://lf.snssdk.com/api/news/?iid=12507202490&device_id=37487219424&refer=1&count=1&aid=13&category=news_tech&max_behot_time=1515726654";



    @GET("top250")
    Call<ResponseBody> getTopMovie(@Query("start") int start, @Query("count") int count);




    @GET("feed/v62/")
    Call<ResponseBody> getKeJi(
            @Query("iid") long iid
            , @Query("device_id") long device_id
            , @Query("refer") int refer
            , @Query("count") int count
            , @Query("aid") int aid
            , @Query("category") String category
            , @Query("max_behot_time") long max_behot_time

    );

}