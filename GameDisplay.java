package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private LibraryHunt tic;
    private Button stopBook;
    private Button goBook;
    private MediaPlayer mySound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);
        tic = findViewById(R.id.libraryHunt);
        Button playAgain = (Button) findViewById(R.id.button4);
        Button home = (Button) findViewById(R.id.button3);
        TextView playerDisplay = (TextView) findViewById(R.id.textView5);
        stopBook = (Button) findViewById(R.id.button6);
        goBook = (Button) findViewById(R.id.button5);

        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        if(playerNames !=null){
            playerDisplay.setText((playerNames[0] + "'s turn!"));
        }

        tic.setUpGame(playAgain,home,playerDisplay, playerNames);

        playAgain.setEnabled(false);
        home.setEnabled(false);
    }

    public void playAgainButtonClick(View view){
        //start the hunt game again
        tic.resetGame();

    }
    public void homeButtonClick(View view){
        //load the home page of home page intent
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void playGameMusic(View view){
        mySound = MediaPlayer.create(this, R.raw.titanium);
        mySound.setLooping(true);
        mySound.start();
    }

    public void stopGameMusic(View view){
        mySound.stop();
    }
}