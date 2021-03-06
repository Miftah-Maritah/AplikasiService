package com.example.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button mSetBtn;
    private Button mUnsetBtn;
    private RadioButton mMenitRadio;
    private RadioButton mlimaRadio;
    private RadioButton mTigaPuluhRadio;
    private RadioButton mJamRadio;
    private RadioGroup mTimeRadioGroup;
    public int mChangeTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSetBtn = (Button) findViewById(R.id.btnSet);
        mUnsetBtn = (Button) findViewById(R.id.btnUnset);
        mMenitRadio = (RadioButton) findViewById(R.id.radio0);
        mlimaRadio = (RadioButton) findViewById(R.id.radio1);
        mTigaPuluhRadio = (RadioButton) findViewById(R.id.radio2);
        mJamRadio = (RadioButton) findViewById(R.id.radio3);
        mTimeRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mUnsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View y) {
                Intent mDisable = new Intent(MainActivity.this, WallpaperChangesService.class);
                stopService(mDisable);
                finish();
            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioID = mTimeRadioGroup.getCheckedRadioButtonId();
                if (mMenitRadio.getId() == mRadioID) {
                    mChangeTime = 60;
                } else if (mlimaRadio.getId() == mRadioID) {
                    mChangeTime = 5 * 60;
                } else if (mTigaPuluhRadio.getId() == mRadioID) {
                    mChangeTime = 30 * 60;
                } else if (mJamRadio.getId() == mRadioID) {
                    mChangeTime = 60 * 60;
                }

                Intent mService = new Intent(MainActivity.this, WallpaperChangesService.class);
                /* membuat bundle dan menyimpan pasangan nilai dengan kuncinya.*/
                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi", mChangeTime);
                /*Menaruh bundle kedalam intent */
                mService.putExtras(mBundleTime);
                /* memulai service */
                startService(mService);
                /* mengakhiri activity */
                finish();
            }
        });
    }
}
