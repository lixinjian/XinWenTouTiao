package com.xinjian.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinjian.R;
import com.xinjian.api.NewsService;
import com.xinjian.base.BaseFragment;
import com.xinjian.bean.news.KeJiBean;
import com.xinjian.utils.RetrofitFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lxj on 2018/1/12 0012.
 */

public class KeJiFragment extends BaseFragment {


    private static KeJiFragment instance = null;

    private TextView move;

    public static KeJiFragment getInstance() {
        if (instance == null) {
            instance = new KeJiFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keji, container, false);

        move = view.findViewById(R.id.move);

        getMovie();

        return view;
    }

    //进行网络请求
    private void getMovie() {
        showLoading();
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        NewsService movieService = retrofit.create(NewsService.class);
        //   iid=12507202490
// &device_id=37487219424
// &refer=1&
// count=1&
// aid=13&
// category=news_tech
// &max_behot_time=1515726654

        Call<ResponseBody> call = movieService.getKeJi(0, 0, 1, 1, 13, "news_tech", 1515726654);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissLoading();
                try {
                    String s = response.body().string();

                    KeJiBean bean = new Gson().fromJson(s, KeJiBean.class);

                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < bean.data.size(); i++) {
                        text.append(bean.data.get(i).content).append("\n\r")
                                .append("\n\r");
                    }
                    move.setText(text.toString());

                    move.setText(text);

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
