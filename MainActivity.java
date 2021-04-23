package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView openingText;
    private Animation myAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openingText = (TextView) findViewById(R.id.textView);
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        openingText.setAnimation(myAnimation);
    }

    public void playButtonClick(View view){
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent);
        finish();
    }
}