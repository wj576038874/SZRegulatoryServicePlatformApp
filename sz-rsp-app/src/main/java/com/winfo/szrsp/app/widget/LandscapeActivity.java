package com.winfo.szrsp.app.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.DateFormatUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandscapeActivity extends Activity {
    @BindView(R.id.view)
    LinePathView pathView;
    @BindView(R.id.clear1)
    Button mClear;
    @BindView(R.id.save1)
    Button mSave;
    @BindView(R.id.ll)
    LinearLayout ll;

    private String qm_paths = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/szrsp/" + File.separator + DateFormatUtils.getPVAFormatDate()+"qm.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hand_write);
        final String path = getIntent().getStringExtra("path");
        ButterKnife.bind(this);
        setResult(50);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (pathView.getTouched())
            {
                try {
                    if (path !=null){
                        pathView.save(path,false,10);
                        Intent intent = new Intent();
                        intent.putExtra("path",path);
                        setResult(101,intent);
                        finish();
                    }else{
                        pathView.save(qm_paths,false,10);
                        Intent intent = new Intent();
                        intent.putExtra("path",qm_paths);
                        setResult(101,intent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(LandscapeActivity.this,"您没有签名~", Toast.LENGTH_SHORT).show();
            }
        }});
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.clear();
            }
        });
    }



}
