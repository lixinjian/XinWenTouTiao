package com.xinjian.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinjian.R;
import com.xinjian.api.NewsService;
import com.xinjian.base.BaseFragment;
import com.xinjian.bean.Top250Bean;
import com.xinjian.utils.RetrofitFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lxj on 2018/1/10 0010.
 */

public class NewsFragment extends BaseFragment {

    private static NewsFragment instance = null;
    private TextView move;

    public static NewsFragment getInstance() {
        if (instance == null) {
            instance = new NewsFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        move = view.findViewById(R.id.move);

        getMovie();

        return view;
    }


    //进行网络请求
    private void getMovie() {
        showLoading();
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        NewsService movieService = retrofit.create(NewsService.class);
        Call<ResponseBody> call = movieService.getTopMovie(0, 250);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissLoading();
                try {
                    String s = response.body().string();

                    Top250Bean bean = new Gson().fromJson(s, Top250Bean.class);

                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < bean.subjects.size(); i++) {
                        text.append(bean.subjects.get(i).title).append("\n\r")
                                .append(bean.subjects.get(i).year).append("\n\r")
                                .append(bean.subjects.get(i).rating.average).append("\n\r")
                                .append("\n\r");
                    }
                    move.setText(text.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
