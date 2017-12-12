package com.example.subin.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultText;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView pointsText;
    TextView sumText;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    TextView timerText;
    Button playAgain;
    RelativeLayout newLayout;

    public void playAgain(View view){

        score = 0;
        numberOfQuestions = 0;

        timerText.setText("30s");
        pointsText.setText("0/0");
        resultText.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {

                timerText.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                resultText.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();
    }

    public void generateQuestions(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumText.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer =rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;
        for (int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer){

                answers.add(a + b);
            }
            else
            {
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a+b){

                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

           score++;
           resultText.setText("Correct!");
        }
        else{
            resultText.setText("Incorrect!");
        }
        numberOfQuestions++;
        pointsText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        newLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumText = (TextView) findViewById(R.id.sumText);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        resultText = (TextView) findViewById(R.id.resultText);
        pointsText = (TextView) findViewById(R.id.pointsText);
        timerText = (TextView) findViewById(R.id.timerText);
        playAgain = (Button) findViewById(R.id.playAgain);
        newLayout = (RelativeLayout) findViewById(R.id.newLayout);





    }

}
