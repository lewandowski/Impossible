package br.com.casadocodigo.impossible;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by familia on 27/07/13.
 */
public class Impossible extends SurfaceView implements Runnable {

    boolean running = false;
    Thread renderThread = null;
    SurfaceHolder holder;
    Paint paint;
    private int playerY = 300;

    public Impossible(Context context) {
        super(context);
        paint = new Paint();
        holder = getHolder();
    }

    @Override
    public void run() {

        while (running) {
            //System.out.println("Impossible Running...!");

            if (!holder.getSurface().isValid())
                continue;

            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            // Draw
            drawPlayer(canvas);

            holder.unlockCanvasAndPost(canvas);

        }

    }

    private void drawPlayer(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawCircle(300,playerY,50,paint);
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void moveDown(int pixels) {
        playerY += pixels;
    }

}
