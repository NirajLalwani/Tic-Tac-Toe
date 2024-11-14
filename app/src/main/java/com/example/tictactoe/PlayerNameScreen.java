package com.example.tictactoe;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.util.Log;
import android.widget.*;
import  android.content.Intent;

public class PlayerNameScreen extends AppCompatActivity {

    Button gameStartBtn;
    EditText player1,player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player_name_screen);

        gameStartBtn = findViewById(R.id.start);
        player1 = findViewById(R.id.player1Name);
        player2 = findViewById(R.id.player2Name);

    }

    public void handleGameStart(View view){

        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();
        if(player1Name.isEmpty() || player2Name.isEmpty()){
            Toast.makeText(this,"Fill all the field properly",Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("handleGameStart", "handleGameStart: "+player1Name+ "    "+player2Name);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("Player1_Name",player1Name);
        i.putExtra("Player2_Name",player2Name);
        startActivity(i);
    }
}