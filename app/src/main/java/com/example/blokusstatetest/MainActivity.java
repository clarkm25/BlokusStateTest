package com.example.blokusstatetest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Main Activity used to display a multiline edit text and a "Run Test" button which, when pressed,
 * shows user a series of unit tests based on the methods in BlokusGameState in written form.
 *
 * @author Max Clark, Skyelar Cann, Gavin Raguindin
 * @version March 15th 2022
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calls onClick method below
        Button runTest = findViewById(R.id.runButton);
        runTest.setOnClickListener(this);
    }

    /**
     * onClick
     *
     * When button "Run Test" clicked, this method is called which runs a series of unit tests
     * that create instances and copies of the BlokusGameState as well as call all methods within
     * the BlokusGameState.
     *
     * @param view
     */
    @Override
    public void onClick (View view){
        //Creates an EditText to display the BlokusGameState data
        EditText textDisplay = findViewById(R.id.multiTextView);
        textDisplay.setText(""); //Clears the display

        //String that gets appended to display the GameState in the multi text.
        String gameStateData = "";

        //Path for a specific piece needed for rotation
        Path firstPiece = new Path();

        //Creates instances of default and copy constructor
        BlokusGameState firstInstance = new BlokusGameState();
        BlokusGameState firstCopy = new BlokusGameState(firstInstance);

        //Calls all methods within BlokusGameState
        firstInstance.rotatePiece(1, firstPiece, 15, 15);
        firstInstance.placePiece(1, 10, 10, 1);
        firstInstance.helpMenu();
        firstInstance.quitGame(true);

        BlokusGameState secondInstance = new BlokusGameState();
        BlokusGameState secondCopy = new BlokusGameState(secondInstance);

        gameStateData += firstCopy.toString();
        gameStateData +="\n ---------- \n"; //Separates first copy from second copy

        gameStateData += secondCopy.toString();

        //Checks for any errors or differences between copy instances
        if (firstCopy.toString().equals(secondCopy.toString())) {
            gameStateData += "\n The two copies are equal to each other!";
        }
        else {
            gameStateData += "\n The two copies are not equal to each other..";
        }

        textDisplay.setText(gameStateData);
    }
}