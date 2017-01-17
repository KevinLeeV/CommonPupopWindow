package com.kevinlee.commonpopupwindowlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevinlee.compressbitmaplibrary.CompressBitmapFromPath;

/**
 * ClassName:
 * Description:
 * Author:KevinLee
 * Date:2016/12/19 0019
 * Time:上午 11:50
 * Email:KevinLeeV@163.com
 */
public class CommonPopupWindow extends PopupWindow {

    private Context mContext;
    private View view;
    private static final int DEFAULT_BGCOLOR = 0x88000000;
    private static final int DEFAULT_WIDTH = WindowManager.LayoutParams.MATCH_PARENT;
    private static final int DEFAULT_HEIGHT = WindowManager.LayoutParams.MATCH_PARENT;
    private View.OnClickListener mListener;

    public CommonPopupWindow(Context context, int layoutId, int animationStyle) {
        super(context);
        view = LayoutInflater.from(context).inflate(layoutId, null);
        this.setContentView(view);
        this.mContext = context;
        //设置宽和高
        this.setWidth(DEFAULT_WIDTH);
        this.setHeight(DEFAULT_HEIGHT);
        //设置可获得焦点
        this.setFocusable(true);
        //设置背景色
        ColorDrawable dw = new ColorDrawable(DEFAULT_BGCOLOR);
        this.setBackgroundDrawable(dw);
        //设置动画
        this.setAnimationStyle(animationStyle);
    }

    /**
     * 设置字体
     *
     * @param view
     * @param typeface 字体
     * @return
     */
    public void setTypeFaceToTextView(TextView view, Typeface typeface) {
        view.setTypeface(typeface);
    }

    /**
     * 设置背景色
     *
     * @param bgColor 背景色
     * @return
     */
    public CommonPopupWindow setBgColor(int bgColor) {
        ColorDrawable dw = new ColorDrawable(bgColor);
        this.setBackgroundDrawable(dw);
        return this;
    }

    /**
     * 设置Window的宽度
     *
     * @param width
     * @return
     */
    public CommonPopupWindow setWindowWidth(int width) {
        this.setWidth(width);
        return this;
    }

    /**
     * 设置Window的高度
     *
     * @param height
     * @return
     */
    public CommonPopupWindow setWindowHeight(int height) {
        this.setHeight(height);
        return this;
    }

    /**
     * 设置点击事件
     * @param listener
     * @return
     */
    public CommonPopupWindow setOnClickListener(View.OnClickListener listener){
        this.mListener = listener;
        return this;
    }

    /**
     * 设置点击window边缘时，window消失
     *
     * @param viewId 参照view的id
     * @return
     */
    public CommonPopupWindow setOnTouchDismissListener(final int viewId) {
        //设置触摸事件,即当点击PopupWindow的外面时,弹窗消失
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                View viewById = v.findViewById(viewId);
                int top = viewById.getTop();
                int left = viewById.getLeft();
                int right = viewById.getRight();
                int bottom = viewById.getBottom();
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (x < left || x > right || y < top || y > bottom) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        return this;
    }

    /**
     * 通过id获取到View
     *
     * @param viewId id
     * @param <T>    泛型
     * @return
     */
    public <T extends View> T getView(int viewId, boolean setClickListener) {
        View v = view.findViewById(viewId);
        if (mListener != null && setClickListener)
            v.setOnClickListener(mListener);
        return (T) v;
    }

    /**
     * 通过id获取到View
     *
     * @param viewId id
     * @param <T>    泛型
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View v = getView(viewId, false);
        return (T) v;
    }

    /**
     * 为View设置点击事件
     *
     * @param viewId
     * @return
     */
    public CommonPopupWindow setClickListener(int viewId) {
        getView(viewId, true);
        return this;
    }

    /**
     * 为TextView设置文本
     *
     * @param viewId view的id
     * @param text   文本信息
     * @return
     */
    public CommonPopupWindow setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为View设置背景图
     *
     * @param viewId view的id
     * @param resId  资源Id
     * @return 
     */
    public CommonPopupWindow setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 为View设置背景色
     *
     * @param viewId view的id
     * @param color  颜色
     * @return 
     */
    public CommonPopupWindow setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为ImageView设置前景图
     *
     * @param viewId view的id
     * @param url    图片地址
     * @return 
     */
    public CommonPopupWindow setImageUrl(int viewId, String url) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(url).skipMemoryCache(true).into(view);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId view的id
     * @param resId  资源Id
     * @return 
     */
    public CommonPopupWindow setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId view的id
     * @param bitmap
     * @return 
     */
    public CommonPopupWindow setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId   view的id
     * @param filePath 图片路径
     * @return 
     */
    public CommonPopupWindow setImageBitmap(int viewId, String filePath) {
        ImageView view = getView(viewId);
        Bitmap bitmap = CompressBitmapFromPath.getInstance().decodeSampledBitmapFromPath(filePath, view);
        view.setImageBitmap(bitmap);
        return this;
    }

}
