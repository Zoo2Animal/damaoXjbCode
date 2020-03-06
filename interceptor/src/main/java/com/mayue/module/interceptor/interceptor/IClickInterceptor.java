package com.mayue.module.interceptor.interceptor;

import android.view.View;

/**
 *  created by matthew , 2020-03-05
 *
 *  点击拦截器主要业务的抽象接口，方便拓展
 */
public interface IClickInterceptor {

    boolean isIntercepted(View v);



}
