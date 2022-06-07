package com.example.tictactoe2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class LoseDialogFragment extends DialogFragment {
    private Button tryAgain;
    private Button mainMenu;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_popup_lose, null);

        final Dialog a = new Dialog(getContext(),R.style.newTheme);
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setCancelable(true);
        a.setContentView(v);

        tryAgain = v.findViewById(R.id.dialog_cancel);
        mainMenu = v.findViewById(R.id.dialog_ok);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.dismiss();
            }
        });
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
