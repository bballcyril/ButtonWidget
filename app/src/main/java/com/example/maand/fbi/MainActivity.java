package com.example.maand.fbi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    MediaPlayer sound = MediaPlayer.create(this,R.raw.fbi_sound);
    Button button = (Button) findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final MediaPlayer sound = MediaPlayer.create(this,R.raw.fbi_sound);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                sound.start();
            }
        });
    }
}
