package com.example.android.snookerscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //These lines of code sets the logo in App Toolbar
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        //"Finding" the Views
        playerAscore = (TextView) findViewById(R.id.player_a_score);
        playerAframes = (TextView) findViewById(R.id.player_a_frame_score);
        playerBscore = (TextView) findViewById(R.id.player_b_score);
        playerBframes = (TextView) findViewById(R.id.player_b_frame_score);
        editA = (EditText) findViewById(R.id.editTextA);
        editB = (EditText) findViewById(R.id.editTextB);
        welcomeLayout = (LinearLayout) findViewById(R.id.welcomeScreen);
        playerAname = (TextView) findViewById(R.id.player_a_name);
        playerBname = (TextView) findViewById(R.id.player_b_name);

    }

    //Variables for player A
    int scorePlayerA = 0;
    int framesPlayerA = 0;

    //Variables for player B
    int scorePlayerB = 0;
    int framesPlayerB = 0;

    //Initialization of the Views
    private TextView playerAscore;
    private TextView playerBscore;
    private TextView playerAframes;
    private TextView playerBframes;
    private EditText editA;
    private EditText editB;
    private LinearLayout welcomeLayout;
    private TextView playerAname;
    private TextView playerBname;

    //"level" shows how many events happened until now
    int level = 0;

    /**
     * Based on the value we find on the level "K" of this array, we will know what happened that turn in the game
     * <p>
     * Legend:
     * <p>
     * -12 - +1 frame for player A
     * -11 - -7 points for player A
     * -10 - -6 points for player A
     * -9 - -5 points for player A
     * -8 - -4 points for player A
     * -7 - +7 points for player A
     * -6 - +6 points for player A
     * -5 - +5 points for player A
     * -4 - +4 points for player A
     * -3 - +3 points for player A
     * -2 - +2 points for player A
     * -1 - +1 point for player A
     * 0 - nothing
     * 1 - +1 point for player B
     * 2 - +2 points for player B
     * 3 - +3 points for player B
     * 4 - +4 points for player B
     * 5 - +5 points for player B
     * 6 - +6 points for player B
     * 7 - +7 points for player B
     * 8 - -4 points for player B
     * 9 - -5 points for player B
     * 10 - -6 points for player B
     * 11 - -7 points for player B
     * 12 - +1 frame for player B
     */

    int[] timeLine = new int[1000];

    // Code to save values when the device is rotated
    private static final String STATE_scorePlayerA = "scorePlayerA";
    private static final String STATE_framesPlayerA = "framesPlayerA";
    private static final String STATE_scorePlayerB = "scorePlayerB";
    private static final String STATE_framesPlayerB = "framesPlayerB";
    private static final String STATE_level = "level";
    private static final String STATE_timeLine = "timeLine";

    /**
     * Saving the instance state because the main activity will destroy when screen
     * orientation is changed.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_scorePlayerA, scorePlayerA);
        savedInstanceState.putInt(STATE_framesPlayerA, framesPlayerA);
        savedInstanceState.putInt(STATE_scorePlayerB, scorePlayerB);
        savedInstanceState.putInt(STATE_framesPlayerB, framesPlayerB);
        savedInstanceState.putInt(STATE_level, level);
        savedInstanceState.putIntArray(STATE_timeLine, timeLine);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Restoring values back to normal.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scorePlayerA = savedInstanceState.getInt(STATE_scorePlayerA);
        framesPlayerA = savedInstanceState.getInt(STATE_framesPlayerA);
        scorePlayerB = savedInstanceState.getInt(STATE_scorePlayerB);
        framesPlayerB = savedInstanceState.getInt(STATE_framesPlayerB);
        level = savedInstanceState.getInt(STATE_level);
        timeLine = savedInstanceState.getIntArray(STATE_timeLine);

        //Updating the TextViews.
        displayAScore();
        displayBScore();
        displayAFrames();
        displayBFrames();

    }

    //Increasing first / second player's score and displaying it.

    public void ballButton(View view) {

        //Increasing level by one because of event happened
        level++;

        int i = Integer.parseInt(view.getTag().toString());

        if (i == -1) {
            scorePlayerA++;
        } else if (i == -2) {
            scorePlayerA += 2;
        } else if (i == -3) {
            scorePlayerA += 3;
        } else if (i == -4) {
            scorePlayerA += 4;
        } else if (i == -5) {
            scorePlayerA += 5;
        } else if (i == -6) {
            scorePlayerA += 6;
        } else if (i == -7) {
            scorePlayerA += 7;
        } else if (i == -8) {
            if (scorePlayerA >= 4) {
                scorePlayerA -= 4;
            } else {
                scorePlayerA = 0;
            }
        } else if (i == -9) {
            if (scorePlayerA >= 5) {
                scorePlayerA -= 5;
            } else {
                scorePlayerA = 0;
            }
        } else if (i == -10) {
            if (scorePlayerA >= 6) {
                scorePlayerA -= 6;
            } else {
                scorePlayerA = 0;
            }
        } else if (i == -11) {
            if (scorePlayerA >= 7) {
                scorePlayerA -= 7;
            } else {
                scorePlayerA = 0;
            }
        } else if (i == 1) {
            scorePlayerB++;
        } else if (i == 2) {
            scorePlayerB += 2;
        } else if (i == 3) {
            scorePlayerB += 3;
        } else if (i == 4) {
            scorePlayerB += 4;
        } else if (i == 5) {
            scorePlayerB += 5;
        } else if (i == 6) {
            scorePlayerB += 6;
        } else if (i == 7) {
            scorePlayerB += 7;
        } else if (i == 8) {
            if (scorePlayerB >= 4) {
                scorePlayerB -= 4;
            } else {
                scorePlayerB = 0;
            }
        } else if (i == 9) {
            if (scorePlayerB >= 5) {
                scorePlayerB -= 5;
            } else {
                scorePlayerB = 0;
            }
        } else if (i == 10) {
            if (scorePlayerB >= 6) {
                scorePlayerB -= 6;
            } else {
                scorePlayerB = 0;
            }
        } else if (i == 11) {
            if (scorePlayerB >= 7) {
                scorePlayerB -= 7;
            } else {
                scorePlayerB = 0;
            }
        }

        //Keeps track of our events
        timeLine[level] = i;

        //Display both teams score
        displayAScore();
        displayBScore();

    }

    // Undo last action

    public void undo(View view) {

        if (level > 0) {
            switch (timeLine[level]) {
                case -12:
                    framesPlayerA--;
                    displayAFrames();
                    break;
                case -11:
                    scorePlayerA += 7;
                    displayAScore();
                    break;
                case -10:
                    scorePlayerA += 6;
                    displayAScore();
                    break;
                case -9:
                    scorePlayerA += 5;
                    displayAScore();
                    break;
                case -8:
                    scorePlayerA += 4;
                    displayAScore();
                    break;
                case -7:
                    scorePlayerA -= 7;
                    displayAScore();
                    break;
                case -6:
                    scorePlayerA -= 6;
                    displayAScore();
                    break;
                case -5:
                    scorePlayerA -= 5;
                    displayAScore();
                    break;
                case -4:
                    scorePlayerA -= 4;
                    displayAScore();
                    break;
                case -3:
                    scorePlayerA -= 3;
                    displayAScore();
                    break;
                case -2:
                    scorePlayerA -= 2;
                    displayAScore();
                    break;
                case -1:
                    scorePlayerA -= 1;
                    displayAScore();
                    break;
                case 1:
                    scorePlayerB -= 1;
                    displayBScore();
                    break;
                case 2:
                    scorePlayerB -= 2;
                    displayBScore();
                    break;
                case 3:
                    scorePlayerB -= 3;
                    displayBScore();
                    break;
                case 4:
                    scorePlayerB -= 4;
                    displayBScore();
                    break;
                case 5:
                    scorePlayerB -= 5;
                    displayBScore();
                    break;
                case 6:
                    scorePlayerB -= 6;
                    displayBScore();
                    break;
                case 7:
                    scorePlayerB -= 7;
                    displayBScore();
                    break;
                case 8:
                    scorePlayerB += 4;
                    displayBScore();
                    break;
                case 9:
                    scorePlayerB += 5;
                    displayBScore();
                    break;
                case 10:
                    scorePlayerB += 6;
                    displayBScore();
                    break;
                case 11:
                    scorePlayerB += 7;
                    displayBScore();
                    break;
                case 12:
                    framesPlayerB--;
                    displayBFrames();
                    break;
            }

            level--;

        }

    }

    //Starts new frame
    public void newFrame(View view) {

        if (scorePlayerA > scorePlayerB) {

            framesPlayerA++;
            displayAFrames();
            scorePlayerA = 0;
            scorePlayerB = 0;
            displayAScore();
            displayBScore();
            level = 1;
            timeLine = new int[1000];
            timeLine[level] = -12;

        } else if (scorePlayerA == scorePlayerB) {

            Toast.makeText(this, "It is a draw!", Toast.LENGTH_SHORT).show();

        } else {

            framesPlayerB++;
            displayBFrames();
            scorePlayerA = 0;
            scorePlayerB = 0;
            displayAScore();
            displayBScore();
            level = 1;
            timeLine = new int[1000];
            timeLine[level] = 12;

        }

    }

    //Starts new game and resets the values

    public void newGame(View view) {

        level = 0;
        timeLine = new int[1000];

        scorePlayerA = 0;
        framesPlayerA = 0;
        displayAScore();
        displayAFrames();

        scorePlayerB = 0;
        framesPlayerB = 0;
        displayBScore();
        displayBFrames();

        welcomeLayout.setVisibility(View.VISIBLE);
    }

    //Updating the score for player A
    public void displayAScore() {
        playerAscore.setText("" + scorePlayerA);
    }

    //Updating the score for player B
    public void displayBScore() {
        playerBscore.setText("" + scorePlayerB);
    }

    //Updating the frames for player A
    public void displayAFrames() {
        playerAframes.setText("(" + framesPlayerA + ")");
    }

    //Updating the frames for player B
    public void displayBFrames() {
        playerBframes.setText("(" + framesPlayerB + ")");
    }

    // Start game
    public void startGame(View view) {

        welcomeLayout.setVisibility(View.INVISIBLE);
        playerAname.setText(editA.getText().toString());
        playerBname.setText(editB.getText().toString());

    }

}

