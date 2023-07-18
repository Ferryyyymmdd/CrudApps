package com.ferrysaptawan.crudapps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnalisisActivity extends AppCompatActivity {

    private TextView animatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);

        animatedText = findViewById(R.id.text_animated);
        animateText();
    }

    private void animateText() {
        animatedText.setVisibility(TextView.VISIBLE);

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                animatedText.setTranslationX(progress * 200); // Adjust the translation distance
                animatedText.setAlpha(1 - progress);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatedText.setVisibility(TextView.INVISIBLE);
            }
        });
        animator.start();
    }
}
