package com.rongxianren.lsn37_highui_uidrawtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.rongxianren.lsn37_highui_uidrawtest.custom_view.CustomViewActivity;
import com.rongxianren.lsn37_highui_uidrawtest.flow_cutomview.FlowCustomViewActivity;

/**
 * @hide
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }
    
    public void customView(View view) {
        Intent intent = new Intent(this, CustomViewActivity.class);
        startActivity(intent);
    }

    public void flowCustomView(View view) {
        Intent intent = new Intent(this, FlowCustomViewActivity.class);
        startActivity(intent);
    }
}
