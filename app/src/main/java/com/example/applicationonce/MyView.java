package com.example.applicationonce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class MyView extends View {
    boolean vhold = true;
    int scorArray[][] = new int[5][2];
    int kordArray[][] = new int[5][2];


    public MyView(Context context) {
        super(context);
        newTraek();
    }

    public void newTraek(){
        Random random = new Random();
        for(int i = 0; i<5; i++){
            scorArray[i][0] = random.nextInt() % 10;
            scorArray[i][1] = random.nextInt() % 10;

            kordArray[i][0] = 0;
            kordArray[i][1] = 0;
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (vhold){
            vhold = false;
            for(int i = 0; i<5; i++) {
                kordArray[i][0] = canvas.getWidth()/2;
                kordArray[i][1] = canvas.getHeight()/2;
            }
        }

        @SuppressLint("DrawAllocation")
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

//        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 200, paint);
//
//        for(int i=0; i<canvas.getHeight(); i+=10){
//            canvas.drawLine(0, i, canvas.getWidth(), i, paint);
//        }

        for(int i = 0; i<5; i++) {
            kordArray[i][0] += scorArray[i][0];
            kordArray[i][1] += scorArray[i][1];
            canvas.drawCircle(kordArray[i][0], kordArray[i][1], 100, paint);

            if(kordArray[i][0] <= 0 || kordArray[i][1] <= 0 || kordArray[i][0] >= canvas.getWidth() || kordArray[i][1] >= canvas.getHeight()){

                Random random = new Random();
                scorArray[i][0] = random.nextInt() % 10;
                scorArray[i][1] = random.nextInt() % 10;

                kordArray[i][0] = canvas.getWidth()/2;
                kordArray[i][1] = canvas.getHeight()/2;
            }
        }

        invalidate(); //обновление экрана
    }
}
