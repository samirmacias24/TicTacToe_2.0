package com.example.tictactoe2;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class BackgroundMusic extends Thread {

    public static BackgroundMusic obj;
    public static MediaPlayer mp;
    private Context theContext;
    private boolean musicStopped;

    public boolean isMusicStopped() {
        return musicStopped;
    }

    public void setMusicStopped(boolean musicStopped) {
        this.musicStopped = musicStopped;
    }

    // only allows for a single instance of this class to be created
    private BackgroundMusic(Context context)
    {
        theContext = context.getApplicationContext();
        if(mp == null) {
            mp = MediaPlayer.create(theContext, R.raw.song1);
        }
        mp.setLooping(true);
        mp.start();
    }

    // retrieves the BackgroundMusic object
    public static BackgroundMusic getInstance(Context context)
    {
        if(obj == null)
        {
            obj = new BackgroundMusic(context);
            return obj;
        }
        else
        {
            return obj;
        }
    }

    // releasing the mediaplayer
    public void release()
    {
        if(mp != null)
        {
            mp.release();
            mp = null;
        }
    }
}
