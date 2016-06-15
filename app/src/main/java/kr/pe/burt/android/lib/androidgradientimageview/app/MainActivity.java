package kr.pe.burt.android.lib.androidgradientimageview.app;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import kr.pe.burt.android.lib.androidgradientimageview.AndroidGradientImageView;

public class MainActivity extends AppCompatActivity {

    private AndroidGradientImageView gradientImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_agassi);

        gradientImageView = (AndroidGradientImageView)findViewById(R.id.gradientImageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rotateAnimation();
    }


    int animationIndex = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            animationIndex = animationIndex % 3;
            switch (animationIndex) {
                case 0:
                    widthAnimation();
                    break;
                case 1:
                    heightAnimation();
                    break;
                default:
                    rotateAnimation();
            }
            animationIndex++;
        }
        return super.onTouchEvent(event);
    }

    private void rotateAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(gradientImageView, "rotate", 0.0f, 359.0f);
        animator.setDuration(1000);
        animator.start();
    }

    private void widthAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(gradientImageView, "widthRatio", 1.0f, 0.0f, 1.0f);
        animator.setDuration(1000);
        animator.start();
    }

    private void heightAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(gradientImageView, "heightRatio", 1.0f, 0.0f, 1.0f);
        animator.setDuration(1000);
        animator.start();
    }
}