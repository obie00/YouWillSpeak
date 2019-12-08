package com.example.youwillspeak;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SpanishActivity extends AppCompatActivity {

    private Button spanishCardButton;
    private Animation spinInAnimation;
    private Animation spinOutAnimation;
    private GameManager  gameManager;
    private String currentCardText;
    private Handler buttonCardAnimationHandler;
    private Runnable buttonCardAnimationRunnable;

    private Button testButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spanish);
        gameManager = new GameManager(this);
        setUpButtonCard();

        setUpTestButton();
    }

    private void setUpButtonCard(){
        setUpButton();
        setUpButtoncardAnimation();
    }

    private void setUpButtoncardAnimation(){
        spinInAnimation = AnimationUtils.loadAnimation(SpanishActivity.this, R.anim.spin_in);
        spinOutAnimation = AnimationUtils.loadAnimation(SpanishActivity.this, R.anim.spin_out);
        spinInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                spanishCardButton.setText(currentCardText);
                spanishCardButton.startAnimation(spinOutAnimation);
            }
        });
    }

    private void setUpButton(){
        buttonCardAnimationHandler = new Handler();
        spanishCardButton = (Button)findViewById(R.id.spanishCardButton);
        spanishCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCardAnimationHandler.removeCallbacks(buttonCardAnimationRunnable);
                final Word nextWord = gameManager.getNextWord();
                flashCard(nextWord.english);

                buttonCardAnimationRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        flashCard(nextWord.spanish);
                    }
                };
                buttonCardAnimationHandler.postDelayed(buttonCardAnimationRunnable, 2000);
            }
        });
    }
    private void flashCard(String cardText){
        currentCardText = cardText;
        spanishCardButton.startAnimation(spinInAnimation);
    }

    private void setUpTestButton(){
        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager.saveVocabularyWords();
                gameManager.loadVocabularyWords();
                Log.d("maaayybbbeeee",gameManager.loadText);
                //gameManager.testSave();
            }
        });
    }
}
