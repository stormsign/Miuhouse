package com.hyphenate.easeuisimpledemo;

import android.app.Application;

import com.hyphenate.easeui.controller.EaseUI;

public class DemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        EaseUI.getInstance().init(this);
    }
    
}
