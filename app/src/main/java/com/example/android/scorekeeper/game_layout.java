package com.example.android.scorekeeper;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class game_layout extends AppCompatActivity {

    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
    private int countTime = 0;
    private TextView countDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

        final TextView gameOver = (TextView) findViewById(R.id.game_over);
        gameOver.setVisibility(View.INVISIBLE);

        final TextView winningPlayer = (TextView) findViewById(R.id.player_win);

//      get the player names from the previous activity
        Intent startGame = getIntent();
        final TextView firstPlayerName = (TextView) findViewById(R.id.firstPlayerName);
        firstPlayerName.setText(startGame.getStringExtra("FIRST_PLAYER"));
        final TextView secondPlayerName = (TextView) findViewById(R.id.secondPlayerName);
        secondPlayerName.setText(startGame.getStringExtra("SECOND_PLAYER"));

//       get the players selected color and apply it to their names
        firstPlayerName.setTextColor(ContextCompat.getColor(this, startGame.getIntExtra("COLOR_PLAYER1", 0)));
        secondPlayerName.setTextColor(ContextCompat.getColor(this, startGame.getIntExtra("COLOR_PLAYER2", 0)));

        final TextView firstPlayerResult = (TextView) findViewById(R.id.firstPlayerScore);
        final TextView secondPlayerResult = (TextView) findViewById(R.id.secondPlayerScore);

//        apply the selected colors to their respective score
        firstPlayerResult.setTextColor(ContextCompat.getColor(this, startGame.getIntExtra("COLOR_PLAYER1", 0)));
        secondPlayerResult.setTextColor(ContextCompat.getColor(this, startGame.getIntExtra("COLOR_PLAYER2", 0)));


//        increase score for Player 1 when clicked the 'TAP' button
        final Button firstPlayerButton = (Button) findViewById(R.id.firstPlayer_hit);
        firstPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstPlayerScore++;
                firstPlayerResult.setText(Integer.toString(firstPlayerScore));
            }
        });


//        increase score for Player 2 when clicked the 'TAP' button
        final Button secondPlayerButton = (Button) findViewById(R.id.secondPlayer_hit);
        secondPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondPlayerScore++;
                secondPlayerResult.setText(Integer.toString(secondPlayerScore));
            }
        });


        countDown = (TextView) findViewById(R.id.countDown_text_view);

//      get the saved instances if any and set the data in the respective views
        if(savedInstanceState!=null){
            countTime = savedInstanceState.getInt("COUNT_TIME");
            firstPlayerScore = savedInstanceState.getInt("PLAYER1_SCORE");
            secondPlayerScore = savedInstanceState.getInt("PLAYER2_SCORE");
            firstPlayerResult.setText(Integer.toString(firstPlayerScore));
            secondPlayerResult.setText(Integer.toString(secondPlayerScore));
            setCountDownText();
        }


//        start the count down and display the game result once it finishes
        new CountDownTimer(10000-(countTime*1000), 1000) {
            @Override
            public void onTick(long l) {
                countTime++;
                setCountDownText();
            }


            //          display the game score once its over
            @Override
            public void onFinish() {
                firstPlayerButton.setEnabled(false);
                secondPlayerButton.setEnabled(false);
                gameOver.setVisibility(View.VISIBLE);
                if (firstPlayerScore > secondPlayerScore) {
                    winningPlayer.setText(firstPlayerName.getText().toString() + " won the game");
                } else if (firstPlayerScore == secondPlayerScore) {
                    winningPlayer.setText("tie");
                } else {
                    winningPlayer.setText(secondPlayerName.getText().toString() + " won the game");
                }
            }
        }.start();




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("COUNT_TIME",countTime);
        outState.putInt("PLAYER1_SCORE",firstPlayerScore);
        outState.putInt("PLAYER2_SCORE",secondPlayerScore);
    }

    private void setCountDownText(){
        if (countTime == 10) {
            countDown.setText("00:" + Integer.toString(countTime));
        } else {
            countDown.setText("00:0" + Integer.toString(countTime));
        }
    }




}
