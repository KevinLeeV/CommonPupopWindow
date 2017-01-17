package com.kevinlee.commonpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.kevinlee.commonpopupwindowlibrary.CommonPopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHeadIcon, btnSex, btnShare, btnClearData;
    private CommonPopupWindow headIconWindow, sexWindow, shareWindow, clearDataWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        btnHeadIcon = (Button) findViewById(R.id.btn_headicon);
        btnSex = (Button) findViewById(R.id.btn_sex);
        btnShare = (Button) findViewById(R.id.btn_share);
        btnClearData = (Button) findViewById(R.id.btn_clear_data);

        btnClearData.setOnClickListener(this);
        btnHeadIcon.setOnClickListener(this);
        btnSex.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear_data:

                break;
            case R.id.btn_headicon:
                if (headIconWindow == null)
                    headIconWindow = new CommonPopupWindow(this, R.layout.photo_crop_window_layout, R.style.ShareWindowAnim)
                            .setOnClickListener(photoCropListener)
                            .setClickListener(R.id.tv_cancel)
                            .setClickListener(R.id.tv_gallery)
                            .setOnTouchDismissListener(R.id.ll_photo_crop)
                            .setClickListener(R.id.tv_tack_photo);
                if (headIconWindow != null)
                    headIconWindow.showAtLocation(btnHeadIcon, Gravity.CENTER, 0, 0);
                break;
            case R.id.btn_sex:
                if (sexWindow == null)
                    sexWindow = new CommonPopupWindow(this, R.layout.choose_sex_window_layout, R.style.AdvertisementWindowAnim)
                            .setOnClickListener(chooseSexListener)
                            .setClickListener(R.id.tv_man)
                            .setClickListener(R.id.tv_woman)
                            .setClickListener(R.id.tv_cancel)
                            .setOnTouchDismissListener(R.id.ll_choose_sex)
                            .setBgColor(0x44FF0000);
                if (sexWindow != null) {
                    sexWindow.showAtLocation(btnSex, Gravity.CENTER, 0, 0);
                }
                break;
            case R.id.btn_share:
                if (shareWindow == null)
                    shareWindow = new CommonPopupWindow(this, R.layout.share_window_layout, R.style.ShareWindowAnim)
                            .setOnClickListener(shareListener)
                            .setClickListener(R.id.tv_cancel)
                            .setClickListener(R.id.ll_share_qq)
                            .setClickListener(R.id.ll_share_wechat)
                            .setClickListener(R.id.ll_share_wechat_pyq)
                            .setOnTouchDismissListener(R.id.ll_share)
                            .setBgColor(0x4400FF00);
                if (shareWindow != null)
                    shareWindow.showAtLocation(btnShare, Gravity.CENTER, 0, 0);
                break;
        }
    }

    /**
     * 选择头像的弹窗的点击事件
     */
    private View.OnClickListener photoCropListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_tack_photo:
                case R.id.tv_gallery:
                case R.id.tv_cancel:
                    headIconWindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 选择性别的弹窗的点击事件
     */
    private View.OnClickListener chooseSexListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_man:
                case R.id.tv_woman:
                case R.id.tv_cancel:
                    sexWindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    // 弹窗点击事件
    private View.OnClickListener shareListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_share_qq:
                case R.id.ll_share_wechat:
                case R.id.ll_share_wechat_pyq:
                case R.id.tv_cancel:
                    shareWindow.dismiss();
                    break;

                default:
                    break;
            }
        }
    };

}
