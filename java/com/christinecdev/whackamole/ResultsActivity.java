package com.christinecdev.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    //component declaration
    private TextView highestScore, finalScore;
    private Button exit, restart;

    //score transferred via intent
    int myScore;

    //store highest score
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //component id assignment
        highestScore = findViewById(R.id.textViewHighest);
        finalScore = findViewById(R.id.textViewFinalScore);
        exit = findViewById(R.id.buttonQuit);
        restart = findViewById(R.id.buttonRestart);

        //score transfer
        myScore = getIntent().getIntExtra("score", 0);
        finalScore.setText("Final Score: " + myScore);

        //score save
        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("highestScore", 0);

        if (myScore >= highScore)
        {
            sharedPreferences.edit().putInt("highestScore", myScore).apply();
            highestScore.setText("Highest Score: "+ myScore);
        }
        else
        {
            highestScore.setText("Highest Score: "+ highScore);
        }

        //button onclick events
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }
}