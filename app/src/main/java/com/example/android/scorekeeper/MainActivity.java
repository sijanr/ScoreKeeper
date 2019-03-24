package com.example.android.scorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner1 = findViewById(R.id.spinner_1);
        final Spinner spinner2 = findViewById(R.id.spinner_2);

        String[] colorOption = {"Black", "Green", "Blue", "Red"};

        ArrayAdapter<String> newAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorOption);

        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(newAdapter);
        spinner2.setAdapter(newAdapter);

        Button beginButton = findViewById(R.id.begin_button);

        final Intent startGame = new Intent(MainActivity.this, game_layout.class);

//      get the player 1 color
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startGame.putExtra("COLOR_PLAYER1", android.R.color.black);
                        break;

                    case 1:
                        startGame.putExtra("COLOR_PLAYER1", android.R.color.holo_green_dark);
                        break;

                    case 2:
                        startGame.putExtra("COLOR_PLAYER1", android.R.color.holo_blue_dark);
                        break;

                    case 3:
                        startGame.putExtra("COLOR_PLAYER1", android.R.color.holo_red_dark);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//      get the player 2 color
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        startGame.putExtra("COLOR_PLAYER2", android.R.color.black);
                        break;

                    case 1:
                        startGame.putExtra("COLOR_PLAYER2", android.R.color.holo_green_dark);
                        break;

                    case 2:
                        startGame.putExtra("COLOR_PLAYER2", android.R.color.holo_blue_dark);
                        break;

                    case 3:
                        startGame.putExtra("COLOR_PLAYER2", android.R.color.holo_red_dark);
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//      pass the data from the first activity to the next activity
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                get the first player name
                EditText firstPlayerName = (EditText) findViewById(R.id.firstPlayer);
                if (firstPlayerName.getText().toString().equals("")) {
                    startGame.putExtra("FIRST_PLAYER", "PLAYER 1");
                } else {
                    startGame.putExtra("FIRST_PLAYER", firstPlayerName.getText().toString());
                }


//                get the second player name
                EditText secondPlayerName = (EditText) findViewById(R.id.secondPlayer);
                if (secondPlayerName.getText().toString().equals("")) {
                    startGame.putExtra("SECOND_PLAYER", "PLAYER 2");
                } else {
                    startGame.putExtra("SECOND_PLAYER", secondPlayerName.getText().toString());
                }


                startActivity(startGame);

            }
        });
    }
}
