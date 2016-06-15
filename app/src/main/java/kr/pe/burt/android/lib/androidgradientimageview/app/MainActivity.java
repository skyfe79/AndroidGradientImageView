package kr.pe.burt.android.lib.androidgradientimageview.app;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import kr.pe.burt.android.lib.androidgradientimageview.AndroidGradientImageView;

public class MainActivity extends AppCompatActivity {

    private AndroidGradientImageView gradientImageView;
    private TextView title;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_agassi);

        gradientImageView = (AndroidGradientImageView)findViewById(R.id.gradientImageView);
        title = (TextView)findViewById(R.id.title);
        desc = (TextView)findViewById(R.id.desc);
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
            animationIndex = animationIndex % 7;
            switch (animationIndex) {
                case 0:
                    showUpAniamtion();
                    break;
                case 1:
                    alphaAnimation();
                    break;
                case 2:
                    widthAnimation();
                    break;
                case 3:
                    startXAnimation();
                    break;
                case 4:
                    startYAnimation();
                    break;
                case 5:
                    rotateAnimation();
                    break;
                case 6:
                    heightAnimation();
                    break;
                default:
                    showUpAniamtion();
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

    private void startXAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(gradientImageView, "startX", 1.0f, 0.0f);
        animator.setDuration(1000);
        animator.start();
    }

    private void startYAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(gradientImageView, "startY", 1.0f, 0.0f);
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

    private void alphaAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(gradientImageView, "gradientAlpha", 1.0f, 0.0f, 1.0f);
        animator.setDuration(1000);
        animator.start();
    }

    private void showUpAniamtion() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(gradientImageView, "startY", 1.0f, 0.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(gradientImageView, "gradientAlpha", 0.0f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(title, "alpha", 0, 1.0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(desc, "alpha", 0, 1.0f);

        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(animator3, animator4);
        set2.setDuration(1000);
        set2.setInterpolator(new AccelerateInterpolator());

        AnimatorSet set3 = new AnimatorSet();
        set3.playSequentially(set, set2);
        set3.start();
    }
}