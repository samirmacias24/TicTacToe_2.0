package com.example.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameBoardTwoPlayer extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private ImageButton homeButton; // home button
    private Drawable playerOneImage; // contains player one's character
    private Drawable playerTwoImage; // contains player two's character

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board_two_player);

        Bundle extras = getIntent().getExtras();
        byte [] byteArray = extras.getByteArray("CHARACTER1");
        // converting byteArray to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView newImage = (ImageView) findViewById(R.id.playerImageOne);
        newImage.setImageBitmap(bitmap);

        // converting the image selected by player1 into a drawable object
        // this is done so that it can be used to show the users choice (within the game)
        Drawable d = new BitmapDrawable((getResources()), bitmap);
        playerOneImage = d;

        byte [] byteArray2 = extras.getByteArray("CHARACTER2");
        // converting byteArray to Bitmap
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
        ImageView newImage2 = (ImageView) findViewById(R.id.playerImageTwo);
        newImage2.setImageBitmap(bitmap2);

        // converting the image selected by player1 into a drawable object
        // this is done so that it can be used to show the users choice (within the game)
        Drawable d1 = new BitmapDrawable((getResources()), bitmap2);
        playerTwoImage = d1;


        // returns to main menu when the home button clicked
        homeButton = findViewById(R.id.returnHomeButton2);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameBoardTwoPlayer.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // setting up changing background color
        ConstraintLayout cLayout = findViewById(R.id.constLayout);
        AnimationDrawable aDraw = (AnimationDrawable) cLayout.getBackground();
        aDraw.setEnterFadeDuration(2000);
        aDraw.setExitFadeDuration(4000);
        aDraw.start();

    }
    int counter = 0; // its counts the number of spots selected (once 9 is reached, a tie dialog is shown)
    boolean type = false; // if "type" is false, display "X"
                          // if "type" is true, display "O"
    int player1Score = 0;
    int player2Score = 0;
    boolean winner = false; // will become true if there is a winner
    String winnerType = ""; // did 'X' win or 'O'
    int position = 0; // corresponds to which type of win it was(8 types)
    int winningPosition = 0; // the position and or line that won
    int onlyOneWinner = 0;  // helps avoid any bugs of multiple winners in a single game

    public void buttonClicked(final View v)
    {
        // the button available
        button1 = findViewById(R.id.one_button_);
        button2 = findViewById(R.id.two_button_);
        button3 = findViewById(R.id.three_button_);
        button4 = findViewById(R.id.four_button_);
        button5 = findViewById(R.id.five_button_);
        button6 = findViewById(R.id.six_button_);
        button7 = findViewById(R.id.seven_button_);
        button8 = findViewById(R.id.eight_button_);
        button9 = findViewById(R.id.nine_button_);
        // places 'X' or 'O' into button
        // ensures that once a value is printed on the button, it cannot be changed
        TextView buttonText = (TextView) v;
        String text = buttonText.getText().toString();
        if(type == false && text == "")
        {
            counter++;
            // this section sets the buttons width to a specfic size, stopping the original image from expanding
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button1.getLayoutParams();
            params.height = 285;
            buttonText.setLayoutParams(params);
            buttonText.setBackground(playerOneImage);

            buttonText.setText("X");
            type = true;
        }
        else if(type == true && text == "")
        {
            counter++;
            // this section sets the buttons width to a specfic size, stopping the original image from expanding
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button1.getLayoutParams();
            params.height = 285;
            buttonText.setLayoutParams(params);
            buttonText.setBackground(playerTwoImage);

            buttonText.setText("O");
            type = false;
        }
        String val = "";

        // this loop check every single possibility for a "win" to occur
        // winner is set to true when a winner is found
        for(int i = 1 ; i < 9; i++)
        {
            switch (i) {
                case 1:
                    val = ((TextView) button1).getText().toString() + ((TextView) button2).getText().toString()
                            + ((TextView) button3).getText().toString();
                    position = 1;
                    winner = isWinner(val, winner,position);
                    break;
                case 2:
                    val = ((TextView) button4).getText().toString() + ((TextView) button5).getText().toString()
                            + ((TextView) button6).getText().toString();
                    position = 2;
                    winner = isWinner(val,winner,position);
                    break;
                case 3:
                    val = ((TextView) button7).getText().toString() + ((TextView) button8).getText().toString()
                            + ((TextView) button9).getText().toString();
                    position = 3;
                    winner = isWinner(val,winner,position);
                    break;
                case 4:
                    val = ((TextView) button1).getText().toString() + ((TextView) button4).getText().toString()
                            + ((TextView) button7).getText().toString();
                    position = 4;
                    winner = isWinner(val,winner,position);
                    break;
                case 5:
                    val = ((TextView) button2).getText().toString() + ((TextView) button5).getText().toString()
                            + ((TextView) button8).getText().toString();
                    position = 5;
                    winner = isWinner(val,winner,position);
                    break;
                case 6:
                    val = ((TextView) button3).getText().toString() + ((TextView) button6).getText().toString()
                            + ((TextView) button9).getText().toString();
                    position = 6;
                    winner = isWinner(val,winner,position);
                    break;
                case 7:
                    val = ((TextView) button1).getText().toString() + ((TextView) button5).getText().toString()
                            + ((TextView) button9).getText().toString();
                    position = 7;
                    winner = isWinner(val,winner,position);
                    break;
                case 8:
                    val = ((TextView) button3).getText().toString() + ((TextView) button5).getText().toString()
                            + ((TextView) button7).getText().toString();
                    position = 8;
                    winner = isWinner(val,winner,position);
                    break;
            }
        }
        // if there is a winner we want to cross a line by the winning section
        // Dialogs are used to display a message on who won.
        if(winner)
        {
            if(winnerType.equals("X"))
            {
                WinDialogFragment frag = new WinDialogFragment(findViewById(R.id.playerImageOne));
                frag.show(getSupportFragmentManager(), "WIN1");
            }
            else if(winnerType.equals("O"))
            {
                WinDialogFragment frag = new WinDialogFragment(findViewById(R.id.playerImageTwo));
                frag.show(getSupportFragmentManager(), "WIN2");
            }

            button1.setClickable(false);
            button2.setClickable(false);
            button3.setClickable(false);
            button4.setClickable(false);
            button5.setClickable(false);
            button6.setClickable(false);
            button7.setClickable(false);
            button8.setClickable(false);
            button9.setClickable(false);

            (new Handler()).postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    theNewGame(v);
                }
            }, 1500);
        }
        // if there is a tie, then display the tie dialog
        if(counter == 9 && !winner)
        {
            TieDialogFragment tie = new TieDialogFragment(findViewById(R.id.playerImageOne), findViewById(R.id.playerImageTwo));
            tie.show(getSupportFragmentManager(), "TIE2");
            (new Handler()).postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    theNewGame(v);
                }
            }, 1500);
        }

    }
    public void theNewGame(View obj)
    {
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
        button5.setClickable(true);
        button6.setClickable(true);
        button7.setClickable(true);
        button8.setClickable(true);
        button9.setClickable(true);
        // reseting all the buttons
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        // reseting all the backgrounds
        button1.setBackgroundColor(Color.TRANSPARENT);
        button2.setBackgroundColor(Color.TRANSPARENT);
        button3.setBackgroundColor(Color.TRANSPARENT);
        button4.setBackgroundColor(Color.TRANSPARENT);
        button5.setBackgroundColor(Color.TRANSPARENT);
        button6.setBackgroundColor(Color.TRANSPARENT);
        button7.setBackgroundColor(Color.TRANSPARENT);
        button8.setBackgroundColor(Color.TRANSPARENT);
        button9.setBackgroundColor(Color.TRANSPARENT);

        counter = 0;
        onlyOneWinner = 0;
        winner = false;
    }
    /**
     * Checks if there is a winner. If the "val" variable contains three repeating letters,
     * such as "XXX", if so then a winner has been reached and winner = true, and
     * winner is them returned.
     * EXTRA: winnerType contains whether the winner was using "X" or "O"
     * @param val string containing x's and o's
     * @param winner true if someone has won, if not then false
     * @param position an integer that represents the line that won
     * @return a boolean whether there is a winner or not
     */
    public boolean isWinner(String val, boolean winner, int position)
    {
        // if x is a winner
        if(val.equals("XXX"))
        {
            onlyOneWinner++;
            // increase the score only one time
            if(onlyOneWinner == 1) {
                player1Score++;
                ((TextView) findViewById(R.id.scoreTextP1_)).setText(String.valueOf(player1Score));
                winner = true;
                winnerType = "X";
                winningPosition = position;
            }
        }
        // if o is a winner
        if(val.equals("OOO"))
        {
            onlyOneWinner++;
            // increase the score only one time
            if(onlyOneWinner == 1)
            {
                player2Score++;
                ((TextView) findViewById(R.id.scoreTextP2_)).setText(String.valueOf(player2Score));
                winner = true;
                winnerType = "O";
                winningPosition = position;
            }
        }
        return winner;
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