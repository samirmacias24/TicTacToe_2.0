package com.example.tictactoe2;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

public class SettingDialogFragment extends DialogFragment {

    private ImageView settings;
    private Switch a_switch;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_settings,null);

        final Dialog a = new Dialog(getContext());
        a.setContentView(v);

        settings = v.findViewById(R.id.settingsLogo);
        a_switch = v.findViewById(R.id.musicSwitch);

        settings.setColorFilter(ContextCompat.getColor(getContext(),R.color.whiteColor), android.graphics.PorterDuff.Mode.SRC_IN);

        // "turn" it on if there is music playing already
        if(BackgroundMusic.obj.isMusicStopped() == false) {
            a_switch.setChecked(true);
        }

        a_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if(!on){
                    BackgroundMusic.mp.pause();
                    BackgroundMusic.obj.setMusicStopped(true);
                }
                // if switch is turned on
                else {
                    BackgroundMusic.obj.setMusicStopped(false);
                    BackgroundMusic.mp.start();
                }
            }
        });

        a.create();
        a.show();
        return a;

    }
}
