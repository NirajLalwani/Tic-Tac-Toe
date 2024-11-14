package com.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

public class MainActivity extends AppCompatActivity {


    final private Button[] buttons = new Button[9];
    String turn = "X";
    String player1 = "";
    String player2 = "";

    String currentPlayer = "";
    boolean isGameOver = false;
    TextView message;
    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =  getIntent();
        player1 = intent.getStringExtra("Player1_Name");
        player2 = intent.getStringExtra("Player2_Name");
        Log.d("handleGameStart", player1 + player2 + "Main Activity");

        currentPlayer = player1;





        GridLayout gridLayout = findViewById(R.id.gridLayout);
        message = findViewById(R.id.message);
        restart = findViewById(R.id.restart);
        message.setText(currentPlayer + " Turn");
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    onGridButtonClick(clickedButton);
                }
            });
        }
    }

    private boolean checkWinOrDraw() {
        int[][] winPossibility = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6},
        };
        for (int i = 0; i < 8; i++) {
            String btn1 = buttons[winPossibility[i][0]].getText().toString();
            String btn2 = buttons[winPossibility[i][1]].getText().toString();
            String btn3 = buttons[winPossibility[i][2]].getText().toString();
            if (btn1.equals(btn2) && btn2.equals(btn3) && (!btn1.isEmpty())) {
                Toast.makeText(this, "Game Win", Toast.LENGTH_SHORT).show();

                message.setText(currentPlayer + " Win's");
                return true;
            }
        }
        boolean isDraw = true;
        Log.d("Draw", "Draw" + (!buttons[0].getText().toString().isEmpty()));
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().toString().isEmpty()) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            message.setText("Game Draw");
        }
        return isDraw;


    }

    private void onGridButtonClick(Button button) {


        if (button.getText().toString().isEmpty() && !isGameOver) {
            button.setText(turn);
            if (checkWinOrDraw()) {
                isGameOver = true;
            } else {
                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }

                if (currentPlayer.equals(player1)) {
                    currentPlayer = player2;
                    message.setText(currentPlayer + " Turn");
                } else {
                    currentPlayer = player1;
                    message.setText(currentPlayer + " Turn");
                }
            }
        }
    }

    public void handleGameRestart(View v) {
        isGameOver = false;
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        currentPlayer = player1;
        message.setText(currentPlayer + " Turn");
    }

    public void handleBack(View v){
        Intent i = new Intent(getApplicationContext(), PlayerNameScreen.class);
        startActivity(i);

    }

}
