package com.mubaracktahir.wishes.ui.main;

import android.app.Activity;
import android.app.WallpaperInfo;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;

public class Tool {
    public static void setSystemBarColor() {
        setSystemBarColor();
    }

    public static void setSystemBarColor(Activity art, @ColorRes int color){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = art.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(art.getResources().getColor(color));
        }
    }
}
