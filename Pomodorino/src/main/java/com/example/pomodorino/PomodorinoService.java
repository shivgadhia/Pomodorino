package com.example.pomodorino;


import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class PomodorinoService extends Service {

    private WindowManager windowManager;
    private ImageView pomodorino;

    @Override
    public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showPomodorino();
    }

    private void showPomodorino() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        pomodorino = new ImageView(this);
        pomodorino.setImageResource(R.drawable.floating_pomodorino);
        pomodorino.setOnClickListener(dismissPomodorinoListener);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(pomodorino, params);
    }


    private View.OnClickListener dismissPomodorinoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stopSelf();
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePomodorino();
    }

    private void hidePomodorino() {
        if (pomodorino != null) windowManager.removeView(pomodorino);
    }


}
