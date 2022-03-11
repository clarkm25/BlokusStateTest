package com.example.blokusstatetest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View view) {
        Path firstPiece = new Path();

        BlokusGameState firstInstance = new BlokusGameState();
        BlokusGameState firstCopy = new BlokusGameState(firstInstance);

        firstInstance.rotatePiece(1, firstPiece, 15, 15);
        firstInstance.placePiece(1, 500, 500, 1);
        firstInstance.helpMenu();
        firstInstance.quitGame(true);

        BlokusGameState secondInstance = new BlokusGameState();
        BlokusGameState secondCopy = new BlokusGameState(secondInstance);
    }
}