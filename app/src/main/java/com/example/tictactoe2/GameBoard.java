package com.example.tictactoe2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameBoard extends AppCompatActivity {

    private int loopVal = 2; // will be used to determine whether the user or ai makes the first move
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private ImageButton home;
    private int count = 0;
    private Drawable userImageSelected; // drawable image of the user's character
    private Drawable userImageSelected2; // drawable image of the enemy character
    private ImageView theEnemyImage;   // contains the red "enemy" character
    private ImageView thePlayerImage;  //contains the player's character

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

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_board);

        Bundle extras = getIntent().getExtras();
        byte [] byteArray = extras.getByteArray("CHARACTER");
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
        theEnemyImage  = findViewById(R.id.enemyImage);
        userImageSelected2 = theEnemyImage.getDrawable();

        // getting the player's image
        thePlayerImage = findViewById(R.id.playerImage);

        // setting up changing background color
        ConstraintLayout cLayout = findViewById(R.id.constLayout);
        AnimationDrawable aDraw = (AnimationDrawable) cLayout.getBackground();
        aDraw.setEnterFadeDuration(2000);
        aDraw.setExitFadeDuration(4000);
        aDraw.start();

        home = findViewById(R.id.returnHomeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameBoard.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    String ai = "O";
    String player = "X";
    boolean hasWon = false;
    int player1Score = 0;
    int aiScore = 0;
    boolean whoWon = false;
    int counter = 0; // this counter will be used to avoid calling a method when the program begins
    boolean haveWinner = false;

    // this method is called when any of the nine buttons is clicked
    public void buttonClicked(final View view)
    {
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

        // need "AI" to decide its next move
        if(!haveWinner) {
            Button buttonText = (Button) view;
            String text = buttonText.getText().toString();
            if (text.equals("")) {
                // this section sets the buttons width to a specfic size, stopping the original image from expanding
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button1.getLayoutParams();
                params.height = 285;
                buttonText.setLayoutParams(params);
                buttonText.setBackground(userImageSelected);

                buttonText.setText("X");
            }
            else {
                return;
            }

            int val = findBestMove(view,count); // return most optimal position
            int winnerLine = checkIfWinner();  // hasWon = true; if winner is found

            if (!hasWon)
            {
                switch (val)
                {
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
                    displayResults(view, val2, whoWon);
                }
                // if its a tie game then output a dialog and start new game
                if(!anySpacesLeft() && !hasWon)
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
                    frag.show(getSupportFragmentManager(),"TIE");

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
                displayResults(view, winnerLine, whoWon);
            }
        }
    }

    /**
     * Find the best posible move for the AI
     * @return the index of the button that is the most optimal move
     */
    public int findBestMove(View a, int val)
    {
        Button button = (Button) a;
        if(val == 0 && button.equals(button5) && button1.getText().toString().equals(""))
        {
            count++;
            return 1;

        }
        if(val == 0 && !button.equals(button5) && button5.getText().toString().equals(""))
        {
            count++;
            return 5;
        }

        int bestVal = -1000;
        int bestMoveButton = 0;
        // traversing through all the buttons
        // if button has not been clicked yet
        if(button1.getText().toString().equals(""))
        {
            button1.setText(ai);
            int value = miniMax(0, false);
            button1.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 1;
                bestVal = value;
            }
        }
        if(button2.getText().toString().equals(""))
        {
            button2.setText(ai);
            int value = miniMax(0, false);
            button2.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 2;
                bestVal = value;
            }
        }
        if(button3.getText().toString().equals(""))
        {
            button3.setText(ai);
            int value = miniMax(0, false);
            button3.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 3;
                bestVal = value;
            }
        }
        if(button4.getText().toString().equals(""))
        {
            button4.setText(ai);
            int value = miniMax(0, false);
            button4.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 4;
                bestVal = value;
            }
        }
        if(button5.getText().toString().equals(""))
        {
            button5.setText(ai);
            int value = miniMax(0, false);
            button5.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 5;
                bestVal = value;
            }
        }
        if(button6.getText().toString().equals(""))
        {
            button6.setText(ai);
            int value = miniMax(0, false);
            button6.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 6;
                bestVal = value;
            }
        }
        if(button7.getText().toString().equals(""))
        {
            button7.setText(ai);
            int value = miniMax(0, false);
            button7.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 7;
                bestVal = value;
            }
        }
        if(button8.getText().toString().equals(""))
        {
            button8.setText(ai);
            int value = miniMax(0, false);
            button8.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 8;
                bestVal = value;
            }
        }
        if(button9.getText().toString().equals(""))
        {
            button9.setText(ai);
            int value = miniMax(0, false);
            button9.setText("");
            if(value > bestVal)
            {
                bestMoveButton = 9;
                bestVal = value;
            }
        }
        return bestMoveButton;
    }

    /**
     * Recursively called and is the minimax algorithm. There the most optimal move is found.
     * @param depth is the depth of the recursive call
     * @param isMax changes between true (for maximizer) and or false (for minimizer).
     * @return whether the maximizer(10) and or minimizer(-10) won or there is no more spaces(0)
     */
    public int miniMax(int depth, boolean isMax)
    {
        int val = calculateIfWinner();
        if(val == 10)  // maximizer won
        {
            return val;
        }
        if(val == -10) // minimizer won
        {
            return val;
        }
        if(!anySpacesLeft()) // check if theres any moves left
        {
            return 0;
        }
        if(isMax) // maximizer
        {
            int best = -1000;
            if(button1.getText().toString().equals(""))
            {
                button1.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button1.setText("");
            }
            if(button2.getText().toString().equals(""))
            {
                button2.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button2.setText("");
            }
            if(button3.getText().toString().equals(""))
            {
                button3.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button3.setText("");
            }
            if(button4.getText().toString().equals(""))
            {
                button4.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button4.setText("");
            }
            if(button5.getText().toString().equals(""))
            {
                button5.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button5.setText("");
            }
            if(button6.getText().toString().equals(""))
            {
                button6.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button6.setText("");
            }
            if(button7.getText().toString().equals(""))
            {
                button7.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button7.setText("");
            }
            if(button8.getText().toString().equals(""))
            {
                button8.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button8.setText("");
            }
            if(button9.getText().toString().equals(""))
            {
                button9.setText(ai);
                best = Math.max(best, miniMax(depth+1, false)); // recursive call (returns largest val)
                button9.setText("");
            }
            return best;
        }
        else // minimizer
        {
            int best = 1000;
            if(button1.getText().toString().equals(""))
            {
                button1.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button1.setText("");
            }
            if(button2.getText().toString().equals(""))
            {
                button2.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button2.setText("");
            }
            if(button3.getText().toString().equals(""))
            {
                button3.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button3.setText("");
            }
            if(button4.getText().toString().equals(""))
            {
                button4.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button4.setText("");
            }
            if(button5.getText().toString().equals(""))
            {
                button5.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button5.setText("");
            }
            if(button6.getText().toString().equals(""))
            {
                button6.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button6.setText("");
            }
            if(button7.getText().toString().equals(""))
            {
                button7.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button7.setText("");
            }
            if(button8.getText().toString().equals(""))
            {
                button8.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button8.setText("");
            }
            if(button9.getText().toString().equals(""))
            {
                button9.setText(player);
                best = Math.min(best, miniMax(depth+1, true)); // recursive call (returns largest val)
                button9.setText("");
            }
            return best;
        }
    }

    /**
     * Checks if there is a winner. Returns -10 for 'x' and 10 for 'o'.
     * @return 10 or -10 depending on who won
     */
    public int calculateIfWinner()
    {
        //must check for winner (analyze all 8 winning directions)
        String value1 = ((TextView) button1).getText().toString() + ((TextView) button2).getText().toString()
                + ((TextView) button3).getText().toString();
        if (value1.equals("XXX")) {
            return -10;
        }
        if (value1.equals("OOO")) {
            return 10;
        }

        String value2 = ((TextView) button4).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button6).getText().toString();
        if (value2.equals("XXX")) {
            return -10;
        }
        if (value2.equals("OOO")) {
            return 10;
        }

        String value3 = ((TextView) button7).getText().toString() + ((TextView) button8).getText().toString()
                + ((TextView) button9).getText().toString();
        if (value3.equals("XXX")) {
            return -10;
        }
        if (value3.equals("OOO")) {
            return 10;
        }

        String value4 = ((TextView) button1).getText().toString() + ((TextView) button4).getText().toString()
                + ((TextView) button7).getText().toString();
        if (value4.equals("XXX")) {
            return -10;
        }
        if (value4.equals("OOO")) {
            return 10;
        }

        String value5 = ((TextView) button2).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button8).getText().toString();
        if (value5.equals("XXX")) {
            return -10;
        }
        if (value5.equals("OOO")) {
            return 10;
        }

        String value6 = ((TextView) button3).getText().toString() + ((TextView) button6).getText().toString()
                + ((TextView) button9).getText().toString();
        if (value6.equals("XXX")) {
            return -10;
        }
        if (value6.equals("OOO")) {
            return 10;
        }

        String value7 = ((TextView) button1).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button9).getText().toString();
        if (value7.equals("XXX")) {
            return -10;
        }
        if (value7.equals("OOO")) {
            return 10;
        }

        String value8 = ((TextView) button3).getText().toString() + ((TextView) button5).getText().toString()
                + ((TextView) button7).getText().toString();
        if (value8.equals("XXX")) {
            return -10;
        }
        if (value8.equals("OOO")) {
            return 10;
        }
        // if none of them have won then just return 0
        return 0;
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
     * Converts the winning line of buttons to watermelons.
     * @param obj is the newGame button
     * @param val is the integer that represents the line of buttons that won
     * @param whoWon is the boolean value that is used to dertemine if 'x' or 'o' won
     */
    public void displayResults(final View obj, int val, boolean whoWon)
    {
        if(hasWon) {
            if(whoWon)
            {
                aiScore++;
                ((TextView)findViewById(R.id.scoreText2)).setText(String.valueOf(aiScore));
                LoseDialogFragment frag = new LoseDialogFragment();
                frag.show(getSupportFragmentManager(),"LOSE");
            }
            else
            {
                player1Score++;
                ((TextView)findViewById(R.id.scoreText1)).setText(player1Score);
            }
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    theNewGame(obj);
                }
            }, 1000);
        }
    }
    /**
     * Restarts the game, clears all the buttons of any text.
     * The score is not changed, typically used for ties.
     * @param obj is the button clicked
     */
    public void theNewGame(View obj)
    {
        // reseting values
        hasWon = false;
        whoWon = false;
        haveWinner = false;
        count = 0;

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
    }

    void openNewDialog()
    {
        final Dialog a = new Dialog(this,R.style.newTheme);
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setCancelable(true);
        a.setContentView(R.layout.dialog_popup_lose);

        final ImageView aImage = findViewById(R.id.enemyPic);
        aImage.setAlpha(0.25f);
        a.show();
    }
}