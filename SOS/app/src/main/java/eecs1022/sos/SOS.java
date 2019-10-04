package eecs1022.sos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import eecs1022.sos.models.GridEntry;
import eecs1022.sos.models.Player;

public class SOS extends AppCompatActivity
{

    //TODO::highlight player turn.

    private String currentMove = "S";
    private ArrayList<ArrayList<GridEntry>> grid = new ArrayList<ArrayList<GridEntry>>();
    private Player player1 = new Player();
    private Player player2 = new Player();
    private boolean isPlayer1Turn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        //Tracks the index of the button in the activity
        int buttonIndexCounter = 1;

        //Create the initial logic for the grid
        //i reperesents the index for the row
        //j represents the index for the column
        for(int i = 0; i <= 5 ; i++){
            ArrayList rowEntries = new ArrayList<GridEntry>();
            for(int j = 0; j <= 5; j++){
                int buttonId = getResources().getIdentifier("button"+buttonIndexCounter, "id", getPackageName());
                GridEntry entry = new GridEntry(buttonId);
                rowEntries.add(entry);
                buttonIndexCounter++;
            }
            grid.add(rowEntries);
        }


    }


    //Helper Methods
    private String getTextField(int id){
        View view = findViewById(id);
        EditText tv = (EditText) view;
        return tv.getText().toString();
    }

    private void setScore(int id, int score){
        View view = findViewById(id);
        TextView scoreTextView = (TextView) view;
        scoreTextView.setText((Integer.toString(score).toString()));
    }

    private void setTextField(int id, String text){
        View view = findViewById(id);
        EditText tv = (EditText) view;
        tv.setText(text);
    }

    private void setGridButtonText(int id){
        View view = findViewById(id);
        Button gridButton = (Button) view;
        gridButton.setText(currentMove);
    }

    private Button getGridButton(int id){
        View view = findViewById(id);
        Button gridButton = (Button) view;
        return gridButton;
    }


    //Event Metohds
    public void selectionButtonClicked(View view){

       Button selectionButton = (Button) view;

        if(selectionButton.getId() == R.id.sButton){
            currentMove = "S";
            Button oButton = (Button) findViewById(R.id.oButton);
            //Find where the button above is in the grid array
            selectionButton.setTextColor(Color.YELLOW);
            oButton.setTextColor(Color.WHITE);

        }
        else if(selectionButton.getId() == R.id.oButton){
            currentMove = "O";
            Button sButton = (Button) findViewById(R.id.sButton);
            selectionButton.setTextColor(Color.YELLOW);
            sButton.setTextColor(Color.WHITE);
        }
    }

    public void gridButtonClicked(View view){
        Button gridButton = (Button) view;
        if(gridButton.getText().equals("")){
            setGridButtonText(gridButton.getId());
            setButtonMove(gridButton.getId());
            checkHorizontal();
            checkVertical();
            checkDiagonalRight();
            checkDiagonalLeft();
            isPlayer1Turn = !isPlayer1Turn;

            TextView p1TV = (TextView) findViewById(R.id.p1TV);
            TextView p2TV = (TextView) findViewById(R.id.p2TV);


            //Change player turn color
            if(isPlayer1Turn){
                p1TV.setTextColor(Color.YELLOW);
                p2TV.setTextColor(Color.WHITE);
            }
            else{
                p1TV.setTextColor(Color.WHITE);
                p2TV.setTextColor(Color.YELLOW);
            }

        }
    }

    //Gets the location of the physical button on the screen in the grid array, and sets the move to it
    private void setButtonMove(int gridButtonId){
        for(int i = 0; i <= 5 ; i++){
            for(int j = 0; j <= 5; j++){
                if(gridButtonId == grid.get(i).get(j).getGridButtonId()){//PROBLEM
                    grid.get(i).get(j).setSelection(currentMove);
                    grid.get(i).get(j).setHasBeenChecked(true);
                }
            }
        }
    }

    //Score methods
    public void checkHorizontal(){

        for(int i = 0; i <= 5 ; i++){

            for(int j = 0; j <= 5; j++){

                //This accounts for points on the grid that do not exist.
                if(j < 4)
                {
                    GridEntry entrySelection1 = grid.get(i).get(j);
                    GridEntry entrySelection2 = grid.get(i).get(j + 1);
                    GridEntry entrySelection3 = grid.get(i).get(j + 2);

                    if ((entrySelection1.getSelection().equals("S") && entrySelection2.getSelection().equals("O") && entrySelection3.getSelection().equals("S")) &&  !(entrySelection1.gethasBeenApartOfSequence() && entrySelection2.gethasBeenApartOfSequence() && entrySelection3.gethasBeenApartOfSequence()))
                    {
                        if(isPlayer1Turn){
                            player1.setScore(player1.getScore() + 1);
                            setScore(R.id.p1Score, player1.getScore());
                        }
                        else{
                            player2.setScore(player2.getScore() + 1);
                            setScore(R.id.p2Score, player2.getScore());
                        }

                        //After a winning sequence, flag the grid entries as being a part of a winning sequence and color the grid buttons
                        entrySelection1.sethasBeenApartOfSequence(true);
                        entrySelection2.sethasBeenApartOfSequence(true);
                        entrySelection3.sethasBeenApartOfSequence(true);
                        getGridButton(entrySelection1.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection2.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection3.getGridButtonId()).setTextColor(Color.YELLOW);

                    }

                }
            }
        }
    }

    public void checkVertical(){

        for(int i = 0; i <= 5 ; i++){

            for(int j = 0; j <= 5; j++){

                //This accounts for points on the grid that do not exist.
                if(i < 4)
                {
                    GridEntry entrySelection1 = grid.get(i).get(j);
                    GridEntry entrySelection2 = grid.get(i + 1).get(j);
                    GridEntry entrySelection3 = grid.get(i + 2).get(j);

                    if ((entrySelection1.getSelection().equals("S") && entrySelection2.getSelection().equals("O") && entrySelection3.getSelection().equals("S")) &&  !(entrySelection1.gethasBeenApartOfSequence() && entrySelection2.gethasBeenApartOfSequence() && entrySelection3.gethasBeenApartOfSequence()))
                    {
                        if(isPlayer1Turn){
                            player1.setScore(player1.getScore() + 1);
                            setScore(R.id.p1Score, player1.getScore());
                        }
                        else{
                            player2.setScore(player2.getScore() + 1);
                            setScore(R.id.p2Score, player2.getScore());
                        }

                        //After a winning sequence, flag the grid entries as being a part of a winning sequence and color the grid buttons
                        entrySelection1.sethasBeenApartOfSequence(true);
                        entrySelection2.sethasBeenApartOfSequence(true);
                        entrySelection3.sethasBeenApartOfSequence(true);
                        getGridButton(entrySelection1.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection2.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection3.getGridButtonId()).setTextColor(Color.YELLOW);

                    }

                }
            }
        }
    }

    public void checkDiagonalRight(){

        for(int i = 0; i <= 5 ; i++){

            for(int j = 0; j <= 5; j++){

                //This accounts for points on the grid that do not exist.
                if((i < 4 && j < 4))
                {
                    GridEntry entrySelection1 = grid.get(i).get(j);
                    GridEntry entrySelection2 = grid.get(i + 1).get(j + 1);
                    GridEntry entrySelection3 = grid.get(i + 2).get(j + 2);

                    if (((entrySelection1.getSelection().equals("S") && entrySelection2.getSelection().equals("O") && entrySelection3.getSelection().equals("S"))) &&  !((entrySelection1.gethasBeenApartOfSequence() && entrySelection2.gethasBeenApartOfSequence() && entrySelection3.gethasBeenApartOfSequence())))
                    {
                        if(isPlayer1Turn){
                            player1.setScore(player1.getScore() + 1);
                            setScore(R.id.p1Score, player1.getScore());
                        }
                        else{
                            player2.setScore(player2.getScore() + 1);
                            setScore(R.id.p2Score, player2.getScore());
                        }

                        //After a winning sequence, flag the grid entries as being a part of a winning sequence and color the grid buttons
                        entrySelection1.sethasBeenApartOfSequence(true);
                        entrySelection2.sethasBeenApartOfSequence(true);
                        entrySelection3.sethasBeenApartOfSequence(true);
                        getGridButton(entrySelection1.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection2.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection3.getGridButtonId()).setTextColor(Color.YELLOW);

                    }

                }
            }
        }
    }

    public void checkDiagonalLeft(){

        for(int i = 0; i <= 5 ; i++){

            for(int j = 0; j <= 5; j++){

                //This accounts for points on the grid that do not exist.
                if((i < 4  && j > 1))
                {
                    GridEntry entrySelection1 = grid.get(i).get(j);
                    GridEntry entrySelection2 = grid.get(i + 1).get(j - 1);
                    GridEntry entrySelection3 = grid.get(i + 2).get(j - 2);

                    if (((entrySelection1.getSelection().equals("S") && entrySelection2.getSelection().equals("O") && entrySelection3.getSelection().equals("S"))) &&  !((entrySelection1.gethasBeenApartOfSequence() && entrySelection2.gethasBeenApartOfSequence() && entrySelection3.gethasBeenApartOfSequence())))
                    {
                        if(isPlayer1Turn){
                            player1.setScore(player1.getScore() + 1);
                            setScore(R.id.p1Score, player1.getScore());
                        }
                        else{
                            player2.setScore(player2.getScore() + 1);
                            setScore(R.id.p2Score, player2.getScore());
                        }

                        //After a winning sequence, flag the grid entries as being a part of a winning sequence and color the grid buttons
                        entrySelection1.sethasBeenApartOfSequence(true);
                        entrySelection2.sethasBeenApartOfSequence(true);
                        entrySelection3.sethasBeenApartOfSequence(true);
                        getGridButton(entrySelection1.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection2.getGridButtonId()).setTextColor(Color.YELLOW);
                        getGridButton(entrySelection3.getGridButtonId()).setTextColor(Color.YELLOW);

                    }

                }
            }
        }
    }


}
