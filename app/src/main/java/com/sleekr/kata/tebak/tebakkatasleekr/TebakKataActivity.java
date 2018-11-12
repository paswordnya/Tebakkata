package com.sleekr.kata.tebak.tebakkatasleekr;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TebakKataActivity extends AppCompatActivity {
    private TextView question;
    private TextView scores;
    private EditText inputKata;
    private Button submit;
    private int number;
    private int score;
    private String[] value = new String[] { "Buku", "roti", "remot" };
    private   String[] questions = new String[] { "Bkuu", "irot", "romet" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak_kata);
        scores = findViewById(R.id.scores);
        question = findViewById(R.id.question);
        inputKata = findViewById(R.id.inputkata);
        submit = findViewById(R.id.submit);
        scores.setVisibility(View.GONE);
        number = 0;
        score = 1;
        question.setText(questions[number]);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number != 3) {
                    if (!inputKata.getText().toString().trim().equalsIgnoreCase("")){
                        cekTebakKata(inputKata.getText().toString(), number);
                    }
                }else {
                    messageDialog("Tebak kata selesai Point Anda "+score);
                }

            }
        });
        //ok  fsfsff
    }

    private void cekTebakKata(String answer, int cekAnswer){
        scores.setVisibility(View.VISIBLE);
        inputKata.setText("");
        if (!answer.equalsIgnoreCase(value[cekAnswer])){
            messageDialog("Jawaban Anda Salah, Silahkan coba lagi");
        }else {
            number++;
            scores.setText("Point Anda " +String.valueOf(score));
            messageDialog("Jawaaban Anda benar, Point "+score);
            score++;
        }
    }

    private  void messageDialog(String message) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(true)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (number != 3) {
                            question.setText(questions[number]);
                        }else {
                            inputKata.setVisibility(View.GONE);
                            submit.setText("Finish");
                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(TebakKataActivity.this, MainActivity.class);
                                    TebakKataActivity.this.startActivity(intent);
                                }
                            });
                            question.setText("Game Selesai point anda "+ String.valueOf(score-1));
                        }
                    }
                });

        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
