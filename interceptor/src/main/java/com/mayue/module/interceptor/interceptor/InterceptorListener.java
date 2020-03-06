package com.mayue.module.interceptor.interceptor;

import android.view.View;

/**
 *  created by matthew , 2020-03-05
 *
 *  点击拦截器结果业务的抽象接口，需要使用者实现
 */
public interface InterceptorListener {

    void onIntercepted(View v);

    void notIntercepted(View v);
}
