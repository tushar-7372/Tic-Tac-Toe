package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:cross  1:zero   2:empty
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    int n=0;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        // Log.i("Tag", counter.getTag().toString());
        int tappedCounter = (Integer.parseInt(counter.getTag().toString()));
        if (gameState[tappedCounter] == 2 && gameActive)
        {
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1500);
                n++;
            //counter.setImageResource(R.drawable.zero);

            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.zero);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(400);

            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1)
                        winner += "CROSS";
                    else
                        winner += "ZERO";

                // Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();

                    Button playAgainButton=(Button)findViewById(R.id.playagainButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won");

                    playAgainButton.setVisibility(view.VISIBLE);

                    winnerTextView.setVisibility(view.VISIBLE);
                }
                if(n==9 && gameActive==true)
                {
                    Button playAgainButton=(Button)findViewById(R.id.playagainButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                    playAgainButton.setVisibility(view.VISIBLE);
                    winnerTextView.setVisibility(view.VISIBLE);
                    winnerTextView.setText("It's a draw");
                }
            }

        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton=(Button)findViewById(R.id.playagainButton);
        TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout= (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
         activePlayer=0;
         for(int i=0;i<gameState.length;i++)
             gameState[i]=2;

//        int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
         gameActive=true;
         n=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}