package com.example.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class OptionsMenuTwoPlayer extends AppCompatActivity {

    private ImageView topRight;
    private ImageView topLeft;
    private ImageView buttomLeft;
    private ImageView buttomRight;

    private ImageView imageSelected1Section;
    private ImageView imageSelected2Section;
    private ImageView imageSelected1;
    private ImageView imageSelected2;

    private boolean characterSelected1 = false;
    private boolean characterSelected2 = false;
    private boolean canClickAnymore = true;

    private Button continue_Button;
    private ImageButton resetButton;
    private int count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu_two_player);

        continue_Button = findViewById(R.id.continueButton);
        continue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(characterSelected1 && characterSelected2)
                {
                  Intent intent = new Intent(OptionsMenuTwoPlayer.this,GameBoardTwoPlayer.class );
                    // want to pass the selected image to the gameboard activity

                    // convert first character selected
                    // convert imageView to a bitmap
                    BitmapDrawable a = (BitmapDrawable) imageSelected1.getDrawable();
                    Bitmap bitmap  = a.getBitmap();
                    // convert bitmap to byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
                    byte[] byteArray = stream.toByteArray();
                    // passing bute array to an intent extra
                    intent.putExtra("CHARACTER1", byteArray);

                    // convert second character selected
                    // convert imageView to a bitmap
                    BitmapDrawable bit = (BitmapDrawable) imageSelected2.getDrawable();
                    Bitmap bitmap2  = bit.getBitmap();
                    // convert bitmap to byte array
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.PNG,100, stream2);
                    byte[] byteArray2 = stream2.toByteArray();

                    // passing bute array to an intent extra
                    intent.putExtra("CHARACTER2", byteArray2);
                    startActivity(intent);
                }
            }
        });

        resetButton = findViewById(R.id.resetButtonP2);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if two options have been clicked already
                if(!canClickAnymore)
                {
                    ImageView a = findViewById(R.id.playerOnePic);// getting the imageView1 where we will place the image selected
                    ImageView b = findViewById(R.id.playerTwoPic);// getting the imageView2 where we will place the image selected
                    // remove the original images
                    a.setImageDrawable(null);
                    b.setImageDrawable(null);
                    // set character selected to false and allow the user to click options again
                    characterSelected1 = false;
                    characterSelected2 = false;
                    canClickAnymore = true;
                    // reseting all the buttons
                    topRight = findViewById(R.id.greenCharacter);
                    topRight.setBackgroundColor(0);
                    topRight.setAlpha(1f);
                    topRight.setEnabled(true);
                    topLeft = findViewById(R.id.pinkCharacter);
                    topLeft.setBackgroundColor(0);
                    topLeft.setAlpha(1f);
                    topLeft.setEnabled(true);
                    buttomLeft = findViewById(R.id.purpleCharacter);
                    buttomLeft.setBackgroundColor(0);
                    buttomLeft.setAlpha(1f);
                    buttomLeft.setEnabled(true);
                    buttomRight = findViewById(R.id.blueCharacter);
                    buttomRight.setBackgroundColor(0);
                    buttomRight.setAlpha(1f);
                    buttomRight.setEnabled(true);
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

        // will only allow the users to choose two characters
        if(canClickAnymore) {
            // selecting first character
            if (count % 2 == 0) {

                count++;
                // which ever image is clicked on get a background to show it was picked
                ImageView image = (ImageView) v;
                image.setBackgroundColor(Color.parseColor("#9A8E8E"));
                image.setAlpha(0.25f);
                image.setEnabled(false);

                // now we can set characterSelected to true
                characterSelected1 = true;

                imageSelected1Section = findViewById(R.id.playerOnePic); // getting the imageView where we will place the image selected
                imageSelected1 = ((ImageView) v); // getting the ImageView selected by the user2
                imageSelected1Section.setImageDrawable(imageSelected1.getDrawable());
            }
            // selecting second character
            else {
                count++;
                // which ever image is clicked on get a background to show it was picked
                ImageView image = (ImageView) v;
                image.setBackgroundColor(Color.parseColor("#9A8E8E"));
                image.setAlpha(0.25f);
                image.setEnabled(false);

                // now we can set characterSelected to true
                characterSelected2 = true;
                imageSelected2Section = findViewById(R.id.playerTwoPic);// getting the imageView where we will place the image selected
                imageSelected2 = ((ImageView) v); // getting the ImageView selected by the user2
                imageSelected2Section.setImageDrawable(imageSelected2.getDrawable()); // setting image
                canClickAnymore = false;
            }
        }
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
}