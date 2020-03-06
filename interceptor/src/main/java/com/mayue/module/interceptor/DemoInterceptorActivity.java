package com.mayue.module.interceptor;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mayue.module.interceptor.interceptor.InterceptorListener;
import com.mayue.module.interceptor.interceptor.TimeFilterInterceptor;

/**
 * created by  matthew , 2020-03-05
 *
 * 主要作为 Interceptor 工具类的 demoActivity，如何使用
 */
public class DemoInterceptorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor_demo);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new TimeFilterInterceptor(new InterceptorListener() {
            @Override
            public void onIntercepted(View v) {
                Toast.makeText(DemoInterceptorActivity.this, "请稍后再试...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void notIntercepted(View v) {
                Toast.makeText(DemoInterceptorActivity.this, "你点到我了！",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
