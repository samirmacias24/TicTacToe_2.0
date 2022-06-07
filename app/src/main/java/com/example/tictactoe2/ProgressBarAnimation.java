package com.example.tictactoe2;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar bar;
    private TextView text;
    private float from;
    private float to;

    public ProgressBarAnimation(Context context2, ProgressBar bar2, TextView text2, float from2, float to2){
        this.context = context2;
        this.bar = bar2;
        this.text = text2;
        this.from = from2;
        this.to = to2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        bar.setProgress((int) value);
        text.setText((int) value+" %");

        if(value == to)
        {
            context.startActivity(new Intent(context, MainActivity.class));
        }

    }
}
