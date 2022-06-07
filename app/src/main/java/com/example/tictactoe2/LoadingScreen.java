package com.example.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {

    ProgressBar bar;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bar = findViewById(R.id.progressBar);
        text = findViewById(R.id.loadingText);
        bar.setMax(100);
        bar.setScaleX(3f);

        progressAnimation();
    }

    public void progressAnimation(){
        ProgressBarAnimation animation = new ProgressBarAnimation(this, bar,text, 0f, 100f);
        animation.setDuration(8000);
        bar.setAnimation(animation);
        bar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    }
}