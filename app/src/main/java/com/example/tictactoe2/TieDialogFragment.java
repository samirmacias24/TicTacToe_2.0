package com.example.tictactoe2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.TimeUnit;

public class TieDialogFragment extends DialogFragment {

    private Button tryAgain;
    private Button mainMenu;
    private ImageView playerImage; // its the character selected by the player, used in the dialog
    private ImageView playerImage_; // its the character selected by player two, used in the dialog

    private ImageView playerImage2; // contains the character selected by the player
    private ImageView playerImage3; // contains the second character selected by the player

    private boolean isTwoPlayers = false;

    // passing the image of the character in order to use it for the dialog
    public TieDialogFragment(View a)
    {
        playerImage2 = (ImageView) a;
    }

    public TieDialogFragment(View a, View b)
    {
        playerImage2 = (ImageView) a;
        playerImage3 = (ImageView) b;
        isTwoPlayers = true;
    }


    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_popup_tie, null);

        // create the new dialog
        final Dialog a = new Dialog(getContext(), R.style.newTheme); // "newTheme" is the background for the dialog
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setCancelable(true);
        a.setContentView(v);

        playerImage = v.findViewById(R.id.playerPic);
        playerImage_ = v.findViewById(R.id.enemyPic);
        tryAgain = v.findViewById(R.id.tryAgainButton);
        mainMenu = v.findViewById(R.id.mainMenuButton);

        // give dialog imageview(playerImage) the characters image
        playerImage.setImageDrawable(playerImage2.getDrawable());

        // sets up the image for a second player (for two player mode)
        if(isTwoPlayers)
        {
            playerImage_.setImageDrawable(playerImage3.getDrawable());
        }
        // if the user selected "Try Again" simply dismiss
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.dismiss();
            }
        });
        // if user selects "Main Menu" return home
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        a.create();
        a.show();
        return a;
    }
}
