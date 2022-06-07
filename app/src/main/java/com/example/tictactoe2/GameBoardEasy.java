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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class GameBoardEasy extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private ImageButton homeButton;

    private ImageView thePlayerImage;  //contains the player's character
    private Drawable userImageSelected; // drawable image of the user's character
    private Drawable userImageSelected2; // drawable image of the enemy character
    private ImageView theEnemyImage;   // contains the red "enemy" character

    private int loopVal = 2; // will be used to determine whether the user or ai makes the first move
    private boolean hasWon = false;
    private boolean haveWinner = false;
    private int player1Score = 0;
    private int aiScore = 0;
    private boolean whoWon = false;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board_easy);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("CHARACTER_EASY");
        // converting byteArray to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView newImage = (ImageView) findViewById(R.id.playerImage);
        newImage.setImageBitmap(bitmap);

        // converting the image selected by user into a drawable object
        // this is done so that it can be used to show the users choice
        Drawable d = new BitmapDrawable((getResources()), bitmap);
        userImageSelected = d;

        // getting the image of the AI ("enemy")
        // and converting it to a drawable
        theEnemyImage = findViewById(R.id.enemyImage);
        userImageSelected2 = theEnemyImage.getDrawable();

        // getting the player's image
        thePlayerImage = findViewById(R.id.playerImage);

        // setting up changing background color
        ConstraintLayout cLayout = findViewById(R.id.constLayout);
        AnimationDrawable aDraw = (AnimationDrawable) cLayout.getBackground();
        aDraw.setEnterFadeDuration(2000);
        aDraw.setExitFadeDuration(4000);
        aDraw.start();

        // the buttons available
        button1 = findViewById(R.id.one_button);
        button2 = findViewById(R.id.two_button);
        button3 = findViewById(R.id.three_button);
        button4 = findViewById(R.id.four_button);
        button5 = findViewById(R.id.five_button);
        button6 = findViewById(R.id.six_button);
        button7 = findViewById(R.id.seven_button);
        button8 = findViewById(R.id.eight_button);
        button9 = findViewById(R.id.nine_button);

        // allows the home button to work
        homeButton = findViewById(R.id.returnHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameBoardEasy.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void buttonClicked(final View view)
    {
        // need "AI" to decide its next move
        if (!haveWinner)
        {
            Button buttonText = (Button) view;
            String text = buttonText.getText().toString();
            // user clicking empty button
            if (text.equals("")) {
                // this section sets the buttons width to a specfic size, stopping the original image from expanding
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button1.getLayoutParams();
                params.height = 285;
                buttonText.setLayoutParams(params);
                buttonText.setBackground(userImageSelected);
                buttonText.setText("X");
            } else {
                return;
            }

            int val = findNextMove(); // return most optimal position
            int winnerLine = checkIfWinner();  // hasWon = true; if winner is found

            if (!hasWon) {
                switch (val) {
                    case 1:
                        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) button1.getLayoutParams();
                        params1.height = 285;
                        button1.setLayoutParams(params1);
                        button1.setBackground(userImageSelected2);
                        button1.setText("O");
                        break;
                    case 2:
                        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) button2.getLayoutParams();
                        params2.height = 285;
                        button2.setLayoutParams(params2);
                        button2.setBackground(userImageSelected2);
                        button2.setText("O");
                        break;
                    case 3:
                        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) button3.getLayoutParams();
                        params3.height = 285;
                        button3.setLayoutParams(params3);
                        button3.setBackground(userImageSelected2);
                        button3.setText("O");
                        break;
                    case 4:
                        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) button4.getLayoutParams();
                        params4.height = 285;
                        button4.setLayoutParams(params4);
                        button4.setBackground(userImageSelected2);
                        button4.setText("O");
                        break;
                    case 5:
                        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                        params5.height = 285;
                        button5.setLayoutParams(params5);
                        button5.setBackground(userImageSelected2);
                        button5.setText("O");
                        break;
                    case 6:
                        LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                        params6.height = 285;
                        button6.setLayoutParams(params6);
                        button6.setBackground(userImageSelected2);
                        button6.setText("O");
                        break;
                    case 7:
                        LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) button7.getLayoutParams();
                        params7.height = 285;
                        button7.setLayoutParams(params7);
                        button7.setBackground(userImageSelected2);
                        button7.setText("O");
                        break;
                    case 8:
                        LinearLayout.LayoutParams params8 = (LinearLayout.LayoutParams) button8.getLayoutParams();
                        params8.height = 285;
                        button8.setLayoutParams(params8);
                        button8.setBackground(userImageSelected2);
                        button8.setText("O");
                        break;
                    case 9:
                        LinearLayout.LayoutParams params9 = (LinearLayout.LayoutParams) button9.getLayoutParams();
                        params9.height = 285;
                        button9.setLayoutParams(params9);
                        button9.setBackground(userImageSelected2);
                        button9.setText("O");
                        break;
                }

                // if AI wins
                int val2 = checkIfWinner();
                if (val2 != 0)
                {
                    whoWon = true;
                    haveWinner = true;
                    conclusion(view, val2, whoWon);
                }
                // if its a tie game then output a dialog and start new game
                if (!anySpacesLeft() && !hasWon)
                {
                    button1.setClickable(false);
                    button2.setClickable(false);
                    button3.setClickable(false);
                    button4.setClickable(false);
                    button5.setClickable(false);
                    button6.setClickable(false);
                    button7.setClickable(false);
                    button8.setClickable(false);
                    button9.setClickable(false);
                    TieDialogFragment frag = new TieDialogFragment(findViewById(R.id.playerImage));
                    frag.show(getSupportFragmentManager(), "TIE");

                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            theNewGame(view);
                        }
                    }, 1000);
                }
            }
            // if user wins
            else {
                conclusion(view, winnerLine, whoWon);
            }
        }
    }
    /**
     * Checks if there is a winner, if a winner is found, it will
     * return an integer value that represents the line of buttons that
     * won.
     * @return an integer that represents a line of buttons that won, or 0 if no winner
     */
    public int checkIfWinner()
    {
        String value1 = ((TextView) button1).getText().toString() + ((TextView) button2).getText().toString()
                + ((TextView) button3).getText().toString();
        if (value1.equals("XXX") || value1.equals("OOO")) {
            hasWon = true;
            return 1;
        }
        String value2 = ((TextView) button4).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button6).getText().toString();
        if (value2.equals("XXX") || value2.equals("OOO")) {
            hasWon = true;
            return 2;
        }
        String value3 = ((TextView) button7).getText().toString() + ((TextView) button8).getText().toString()
                + ((TextView) button9).getText().toString();
        if (value3.equals("XXX") || value3.equals("OOO")) {
            hasWon = true;
            return 3;
        }
        String value4 = ((TextView) button1).getText().toString() + ((TextView) button4).getText().toString()
                + ((TextView) button7).getText().toString();
        if (value4.equals("XXX") || value4.equals("OOO")) {
            hasWon = true;
            return 4;
        }
        String value5 = ((TextView) button2).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button8).getText().toString();
        if (value5.equals("XXX") || value5.equals("OOO")) {
            hasWon = true;
            return 5;
        }
        String value6 = ((TextView) button3).getText().toString() + ((TextView) button6).getText().toString()
                + ((TextView) button9).getText().toString();
        if (value6.equals("XXX") || value6.equals("OOO")) {
            hasWon = true;
            return 6;
        }
        String value7 = ((TextView) button1).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button9).getText().toString();
        if (value7.equals("XXX") || value7.equals("OOO")) {
            hasWon = true;
            return 7;
        }
        String value8 = ((TextView) button3).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button7).getText().toString();
        if (value8.equals("XXX") || value8.equals("OOO")) {
            hasWon = true;
            return 8;
        }
        return 0;
    }
    /**
     * Returns a random position, as the AI's choice
     * @return integer will be the desired position (1-9)
     */
    public int findNextMove()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        // must check available spots
        arr = avaliableSpaces();

        //choose one of those available spots randomly
        int index = (int)( Math.random() * arr.size());
        int position;
        // if not empty return value, else return 0
        if(!arr.isEmpty())
        {
            position = arr.get(index);
            return position;
        }
        else {
            return 0;
        }
    }
    /**
     * Checks for any available spaces left on the board
     * @return an array contains the index of the buttons left (1-9)
     */
    public ArrayList<Integer> avaliableSpaces()
    {
        ArrayList<Integer> arr = new ArrayList<>();

        if(button1.getText().toString() == "")
        {
            arr.add(1);
        }
        if(button2.getText().toString() == "")
        {
            arr.add(2);
        }
        if(button3.getText().toString() == "")
        {
            arr.add(3);
        }
        if(button4.getText().toString() == "")
        {
            arr.add(4);
        }
        if(button5.getText().toString() == "")
        {
            arr.add(5);
        }
        if(button6.getText().toString() == "")
        {
            arr.add(6);
        }
        if(button7.getText().toString() == "")
        {
            arr.add(7);
        }
        if(button8.getText().toString() == "")
        {
            arr.add(8);
        }
        if(button9.getText().toString() == "")
        {
            arr.add(9);
        }
        return arr;
    }
    /**
     * Restarts the game, clears all the buttons of any text and images
     * @param obj is the button clicked
     */
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

        // will have the AI select a place
        if(loopVal % 2 == 0)
        {
            // getting a random value between 1- 5
            Random random = new Random();
            int val = random.nextInt(5) + 1;
            switch(val) {
                case 1:
                    LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) button1.getLayoutParams();
                    params1.height = 285;
                    button1.setLayoutParams(params1);
                    button1.setBackground(userImageSelected2);
                    button1.setText("O");
                    break;
                case 2:
                    LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) button3.getLayoutParams();
                    params3.height = 285;
                    button3.setLayoutParams(params3);
                    button3.setBackground(userImageSelected2);
                    button3.setText("O");
                    break;
                case 3:
                    LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                    params5.height = 285;
                    button5.setLayoutParams(params5);
                    button5.setBackground(userImageSelected2);
                    button5.setText("O");
                    break;
                case 4:
                    LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) button7.getLayoutParams();
                    params7.height = 285;
                    button7.setLayoutParams(params7);
                    button7.setBackground(userImageSelected2);
                    button7.setText("O");
                    break;
                case 5:
                    LinearLayout.LayoutParams params9 = (LinearLayout.LayoutParams) button9.getLayoutParams();
                    params9.height = 285;
                    button9.setLayoutParams(params9);
                    button9.setBackground(userImageSelected2);
                    button9.setText("O");
                    break;
            }
        }
        loopVal++;
        // reseting values
        hasWon = false;
        whoWon = false;
        haveWinner = false;
        count = 0;
    }

    /**
     * Checks whether there are any open spaces left
     * @return a boolean value if true or false
     */
    public boolean anySpacesLeft()
    {
        if(button1.getText().toString() == "")
        {
            return true;
        }
        else if(button2.getText().toString() == "")
        {
            return true;
        }
        else if(button3.getText().toString() == "")
        {
            return true;
        }
        else if(button4.getText().toString() == "")
        {
            return true;
        }
        else if(button5.getText().toString() == "")
        {
            return true;
        }
        else if(button6.getText().toString() == "")
        {
            return true;
        }
        else if(button7.getText().toString() == "")
        {
            return true;
        }
        else if(button8.getText().toString() == "")
        {
            return true;
        }
        else if(button9.getText().toString() == "")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Converts the winning line of buttons to watermelons.
     * @param obj is the newGame button
     * @param val is the integer that represents the line of buttons that won
     * @param whoWon is the boolean value that is used to dertemine if 'x' or 'o' won
     */
    public void conclusion(final View obj, int val, boolean whoWon)
    {
        if(hasWon) {
            button1.setClickable(false);
            button2.setClickable(false);
            button3.setClickable(false);
            button4.setClickable(false);
            button5.setClickable(false);
            button6.setClickable(false);
            button7.setClickable(false);
            button8.setClickable(false);
            button9.setClickable(false);

            if(whoWon)
                {
                    aiScore++;
                    ((TextView)findViewById(R.id.scoreText2)).setText(String.valueOf(aiScore));
                    // if user loses
                    LoseDialogFragment frag = new LoseDialogFragment();
                    frag.show(getSupportFragmentManager(),"LOSE_");
                }
            else
                {
                    player1Score++;
                TextView score  = findViewById(R.id.scoreText1);
                score.setText(String.valueOf(player1Score));
                // if user wins
                WinDialogFragment fragWin = new WinDialogFragment(findViewById(R.id.playerImage));
                fragWin.show(getSupportFragmentManager(), "WINNER_");
            }
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    theNewGame(obj);
                }
            }, 1000);
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