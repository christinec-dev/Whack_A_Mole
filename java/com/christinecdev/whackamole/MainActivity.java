package com.christinecdev.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //component declaration
    private TextView textViewTimer, textViewScore, textViewCountDown;
    private ImageView m1, m2, m3, m4, m5, m6, m7, m8, m9;
    private GridLayout gridLayout, gridLayout2;

    //public variables
    int score = 0;
    Runnable runnable;
    Handler handler;
    ImageView[] molesArray;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //component id assignment
        textViewTimer = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        m1 = findViewById(R.id.mole1);
        m2 = findViewById(R.id.mole2);
        m3 = findViewById(R.id.mole3);
        m4 = findViewById(R.id.mole4);
        m5 = findViewById(R.id.mole5);
        m6 = findViewById(R.id.mole6);
        m7 = findViewById(R.id.mole7);
        m8 = findViewById(R.id.mole8);
        m9 = findViewById(R.id.mole9);
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout2 = findViewById(R.id.gridLayout2);

        molesArray = new ImageView[]{m1, m2, m3, m4, m5, m6, m7, m8, m9};

        mediaPlayer = MediaPlayer.create(this, R.raw.ow);

        //game countdown timer class
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                //set our timer to count down from 5 > 1
                textViewCountDown.setText(String.valueOf(l/1000));
            }
            @Override
            public void onFinish() {
                //start randomising mole visibility
                molesVisibility();

                //and start new timer for 30 seconds
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long l) {
                        //show time remaining
                        textViewTimer.setText("Time Remaining: " + l/1000);
                    }
                    @Override
                    public void onFinish() {
                        //show game over activity when time runs out
                        Intent i = new Intent(MainActivity.this, ResultsActivity.class);
                        //share score
                        i.putExtra("score", score);
                        startActivity(i);
                        finish();
                    }
                }.start();
            }
        }.start();
    }

    //adds click listener to each mole so our score can increase
    public void increaseScore(View view)
    {
        //defined in xml file by each image view
        //increase value of score when player clicks on mole and prints it to the screen
        score++;
        textViewScore.setText("Score: " + score);

        //mole hit sound effect
        if (mediaPlayer.isPlaying())
        {
         mediaPlayer.seekTo(0);
         mediaPlayer.start();
        }
        mediaPlayer.start();

        //change mole background image on hit
        if (view.getId() == m1.getId())
        {
            m1.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m2.getId())
        {
            m2.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m3.getId())
        {
            m3.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m4.getId())
        {
            m4.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m5.getId())
        {
            m5.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m6.getId())
        {
            m6.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m7.getId())
        {
            m7.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m8.getId())
        {
            m8.setImageResource(R.drawable.whack6);
        }

        if (view.getId() == m9.getId()) {
            m9.setImageResource(R.drawable.whack6);
        }
    }

    //will randomise balloon visibility via runnable and handler class
    public void molesVisibility()
    {
        //when timer is up, show game components and start mole randomising
        textViewCountDown.setVisibility(View.INVISIBLE);
        textViewTimer.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);
        gridLayout2.setVisibility(View.VISIBLE);
        handler = new Handler();
            runnable = new Runnable() {
            @Override
            public void run() {
                //make all moles invisible and visible at random
                for (ImageView moles : molesArray)
                {
                    moles.setVisibility(View.INVISIBLE);
                    moles.setImageResource(R.drawable.mole);
                }
                gridLayout.setVisibility(View.VISIBLE);

                Random random = new Random();
                int i = random.nextInt(molesArray.length);
                molesArray[i].setVisibility(View.VISIBLE);

                //we will speed up the game to make it harder as score increases
                if (score <= 5)
                {
                    handler.postDelayed(runnable, 2000);
                }
                else if(score > 5 && score <= 10)
                {
                    handler.postDelayed(runnable, 1500);
                }
                else if(score > 10 && score <= 15)
                {
                    handler.postDelayed(runnable, 1000);
                }
                else if(score > 15)
                {
                    handler.postDelayed(runnable, 500);
                }
            }
        };

        handler.post(runnable);
    }
}