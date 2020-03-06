package com.mayue.module.interceptor.interceptor;

import android.util.Log;
import android.view.View;

import com.mayue.module.interceptor.R;

import static com.mayue.module.interceptor.interceptor.Constants.*;

/**
 * created by matthew, 2020-03-05
 *
 * 功能：实现一个 View 在一段时间（filterInterval）内不能重复点击
 * 使用: View.OnClickListener(new TimeFilterInterceptor(...))
 *
 * 成员说明：
 * ID:                      向拦截的 View 对象存储数据时，使用的唯一标识，具体可以参考 View.setTag() 方法
 * filterInterval:          拦截点击的间隔时间，默认 2S ，可以通过构造器重载设置；
 * InterceptedListener:     用户回调接口，此参数必需
 */
public class TimeFilterInterceptor implements IClickInterceptor, View.OnClickListener {

    private static final String TAG = "TimeFilterInterceptor";

    // view 拦截器向 view 对象存储数据时使用的 标识
    private static final int ID = R.id.click_interceptor_id;

    // 用户回调接口
    private final InterceptorListener listener;

    // 拦截点击的间隔时间
    private final long filterInterval;


    public TimeFilterInterceptor(InterceptorListener listener) {
        this(listener,DEFAULT_INTERVAL);
    }


    public TimeFilterInterceptor(InterceptorListener listener, long filterInterval) {
        this.listener = listener;
        this.filterInterval = filterInterval;
    }


    /**
     * View.OnClickListener 接口的 onClick（） 的封装
     * 其它可以参考  isIntercepted(View v) 方法
     * @param v 需要拦截的 View 对象
     */
    @Override
    public final void onClick(View v) {
        if (isIntercepted(v)) {
            listener.onIntercepted(v);
            Log.e(TAG, "onIntercepted: ");
        } else {
            listener.notIntercepted(v);
            Log.e(TAG, "notIntercepted: ");
        }
    }

    /**
     * 点击时先查看对应的 View 对象是否有该拦截器的 标记，
     * 如果没有直接返回 false；
     * 如果有会通过标记获取上一次点击的时间与 间隔时间 进行比较， 大于则返回 false，反之 true。
     * @param v 需要拦截的 View 对象
     * @return  false 不拦截，会回调 InterceptedListener.notIntercepted()
     *          true 拦截，会回调 InterceptedListener.onIntercepted()
     */
    @Override
    public boolean isIntercepted(View v) {
        long currentClick = System.currentTimeMillis();
        Object tag = v.getTag(ID);
        if (tag == null) {
            v.setTag(ID, currentClick);
            return false;
        }

        long lastClick = (long) v.getTag(ID);
        if ((currentClick - lastClick) > filterInterval) {
            v.setTag(ID,currentClick);
            return false;
        }

        return true;
    }
}
