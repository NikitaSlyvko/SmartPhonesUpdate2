package com.example.nikita.smartphonesupdate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
    private Button alertButtonMusicList;
    private Button alertButtonVersion;
    private Button alertButtonAbout;
    private Button alertButtonExit;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        alertButtonMusicList = (Button) view.findViewById(R.id.aler_button_music);
        alertButtonVersion = (Button) view.findViewById(R.id.alert_button_version);
        alertButtonAbout = (Button) view.findViewById(R.id.alert_button_about);
        alertButtonExit = (Button) view.findViewById(R.id.alert_button_exit);

        alertButtonExit.setOnClickListener(buttonClickListener);
        alertButtonVersion.setOnClickListener(buttonClickListener);
        alertButtonAbout.setOnClickListener(buttonClickListener);
        alertButtonMusicList.setOnClickListener(buttonClickListener);

        mediaPlayer = new MediaPlayer();
        return view;
    }

    private Button.OnClickListener buttonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            switch (v.getId()) {
                case R.id.alert_button_exit: showDialog((AlertDialog) createAlertDialogExit(builder)); break;
                case R.id.alert_button_version: showDialog((AlertDialog) createAlertDialogVersion(builder)); break;
                case R.id.alert_button_about: showDialog((AlertDialog) createAlertDialogAbout(builder)); break;
                case R.id.aler_button_music: showDialog((AlertDialog) createAlertDialogSounds(builder)); break;
            }
        }
    };

    private void showDialog(AlertDialog alertDialog){
        alertDialog.show();
    }

    private Dialog createAlertDialogExit(AlertDialog.Builder builder) {
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), Container.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("LOGOUT", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    private Dialog createAlertDialogVersion(AlertDialog.Builder builder) {
        builder.setTitle("Version");
        builder.setMessage("SmartPhone application demo version 1.0.1");
        return builder.create();
    }

    private Dialog createAlertDialogAbout(AlertDialog.Builder builder) {
        View layout = getCustomDialog();
        builder.setView(layout);
        builder.setTitle("About me");
        return builder.create();
    }

    private View getCustomDialog() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.about_me, (ViewGroup)getActivity().findViewById(R.id.about_me));
    }

    private Dialog createAlertDialogSounds(AlertDialog.Builder builder) {
        final String[] sounds = {"disco", "rock"};
        builder.setTitle("Music");
        builder.setItems(sounds, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if(position == 0) startMusic(R.raw.disco);
                else  startMusic(R.raw.rock);
            }
        }).setPositiveButton("Play", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startMusic(R.raw.disco);
            }
        }).setNegativeButton("Stop", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopMusic();
            }
        });
        return builder.create();
    }

    private void startMusic(int soundId) {
        if(mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(getActivity(), soundId);
        mediaPlayer.start();
    }

    private  void stopMusic() {
        mediaPlayer.stop();
    }
}
