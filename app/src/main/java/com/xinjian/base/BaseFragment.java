package com.xinjian.base;

import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.xinjian.R;

/**
 * Created by lxj on 2018/1/11 0011.
 */

public class BaseFragment extends Fragment {

    private PopupWindow mPopupWindow;
    private boolean flag;



    public void onResume() {
        super.onResume();
        flag = true;
    }



    public void showLoading() {

        if(flag) {
            showPop();
        } else {
            getActivity().findViewById(android.R.id.content).post(new Runnable() {
                @Override
                public void run() {
                    showPop();
                }
            });
        }
    }


    private void showPop() {
        if (mPopupWindow == null) {
            View popLayout = getLayoutInflater().inflate(R.layout.loading_page, null);
            mPopupWindow = new PopupWindow(popLayout, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT, false);
            mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setClippingEnabled(false);
        }

        mPopupWindow.showAtLocation(getActivity().findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }

    public void dismissLoading() {
        if(!getActivity().isFinishing() && mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public boolean isShowLoading() {
        return !getActivity().isFinishing() && mPopupWindow != null && mPopupWindow.isShowing();
    }

}
