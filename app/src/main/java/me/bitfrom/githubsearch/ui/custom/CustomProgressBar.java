package me.bitfrom.githubsearch.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;

/**
 * <p>Custom {@link ProgressBar} without awful internal padding.</p>
 *
 * @author const
 * @version 1
 * @since 01.07.2017
 */
public class CustomProgressBar extends ProgressBar {

    private DisplayMetrics displayMetrics;

    public CustomProgressBar(Context context) {
        super(context);
        initDisplayMetrics();
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDisplayMetrics();
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDisplayMetrics();
    }

    @SuppressWarnings("unused")
    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDisplayMetrics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int marginTop = dpToPx(7);
        canvas.translate(0, -marginTop);
        super.onDraw(canvas);
    }

    private int dpToPx(int dp) {
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private void initDisplayMetrics() {
        displayMetrics = getContext().getResources().getDisplayMetrics();
    }
}