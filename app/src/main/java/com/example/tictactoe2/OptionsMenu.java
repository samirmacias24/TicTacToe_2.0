package com.example.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class OptionsMenu extends AppCompatActivity {

    private Button easy;
    private Button medium;
    private Button hard;
    private ImageView topRight;
    private ImageView topLeft;
    private ImageView buttomLeft;
    private ImageView buttomRight;
    private ImageView imageSelected;

    private boolean characterSelected = false;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        easy = findViewById(R.id.easy_level);
        medium = findViewById(R.id.medium_level);
        hard = findViewById(R.id.hard_level);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(characterSelected)
                {
                    Intent intent = new Intent(OptionsMenu.this, GameBoardEasy.class);

                    // want to pass the selected image to the gameboard activity
                    // convert imageView to a bitmap
                    BitmapDrawable a = (BitmapDrawable) imageSelected.getDrawable();
                    Bitmap bitmap  = a.getBitmap();
                    // convert bitmap to byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
                    byte[] byteArray = stream.toByteArray();

                    // passing bute array to an intent extra
                    intent.putExtra("CHARACTER_EASY", byteArray);
                    startActivity(intent);
                }
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(characterSelected) {
                    Intent intent = new Intent(OptionsMenu.this, GameBoardMid.class);

                    // want to pass the selected image to the gameboard activity
                    // convert imageView to a bitmap
                    BitmapDrawable a = (BitmapDrawable) imageSelected.getDrawable();
                    Bitmap bitmap = a.getBitmap();
                    // convert bitmap to byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    // passing bute array to an intent extra
                    intent.putExtra("CHARACTER_MID", byteArray);
                    startActivity(intent);
                }
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(characterSelected)
                {
                    Intent intent = new Intent(OptionsMenu.this, GameBoard.class);

                    // want to pass the selected image to the gameboard activity
                    // convert imageView to a bitmap
                    BitmapDrawable a = (BitmapDrawable) imageSelected.getDrawable();
                    Bitmap bitmap  = a.getBitmap();
                    // convert bitmap to byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
                    byte[] byteArray = stream.toByteArray();

                    // passing bute array to an intent extra
                    intent.putExtra("CHARACTER", byteArray);
                    startActivity(intent);
                }
            }
        });
    }

    public void imageSelected(View v)
    {
        topRight = findViewById(R.id.greenCharacter);
        topLeft = findViewById(R.id.pinkCharacter);
        buttomLeft = findViewById(R.id.purpleCharacter);
        buttomRight = findViewById(R.id.blueCharacter);

        // sets all the images' background color back to transparent
        topRight.setBackgroundColor(0);
        topLeft.setBackgroundColor(0);
        buttomLeft.setBackgroundColor(0);
        buttomRight.setBackgroundColor(0);

       // which ever image is clicked on get a background to show it was picked
        ImageView image = (ImageView) v;
        image.setBackgroundColor(Color.parseColor("#7E9BE4E4"));

        // now we can set characterSelected to true
        characterSelected = true;
        imageSelected = (ImageView) v;
    }
}