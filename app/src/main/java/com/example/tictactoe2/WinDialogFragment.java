package com.example.tictactoe2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class WinDialogFragment extends DialogFragment {

    private Button tryAgain;
    private Button mainMenu;
    private ImageView playerImage; // its the character selected by the player, used in the dialog
    private ImageView playerImage2; // contains the character selected by the player

    public WinDialogFragment(View v)
    {
        playerImage2 = (ImageView) v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_popup_win, null);

        // create the new dialog
        final Dialog a = new Dialog(getContext(), R.style.newTheme); // "newTheme" is the background for the dialog
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setCancelable(true);
        a.setContentView(v);

        playerImage = v.findViewById(R.id.enemyPic);
        tryAgain = v.findViewById(R.id.dialog_cancel);
        mainMenu = v.findViewById(R.id.dialog_ok);

        // give dialog imageview(playerImage) the characters image
        playerImage.setImageDrawable(playerImage2.getDrawable());

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
