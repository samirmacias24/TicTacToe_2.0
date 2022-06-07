package com.example.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button player1;
    private Button player2;
    private ImageView setting;

    // causes the app to exit when the user clicks the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(BackgroundMusic.mp != null && BackgroundMusic.obj.isMusicStopped() == false) {
            BackgroundMusic.mp.pause();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(BackgroundMusic.mp != null && BackgroundMusic.obj.isMusicStopped() == false) {
            BackgroundMusic.mp.start();
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // starts the music when you start the app
        BackgroundMusic bmusic = BackgroundMusic.getInstance(this);

        ConstraintLayout cLayout = findViewById(R.id.mainActivity_constLayout);
        AnimationDrawable aDraw = (AnimationDrawable) cLayout.getBackground();
        aDraw.setEnterFadeDuration(2000);
        aDraw.setExitFadeDuration(4000);
        aDraw.start();

        player1 = findViewById(R.id.button2);
        player2 = findViewById(R.id.button3);
        setting = findViewById(R.id.settings);
        setting.setColorFilter(ContextCompat.getColor(this,R.color.settingsColor), android.graphics.PorterDuff.Mode.SRC_IN);

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, OptionsMenu.class);
                startActivity(intent);
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OptionsMenuTwoPlayer.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingDialogFragment frag = new SettingDialogFragment();
                frag.show(getSupportFragmentManager(), "SETTINGS");
            }
        });
    }



}