package com.example.quizapp_pro;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MusicService extends Service implements MediaPlayer.OnErrorListener {

    private final IBinder mBinder = new ServiceBinder();
    MediaPlayer mPlayerTrivia;
    MediaPlayer mPlayerTriviaDemo;

    MediaPlayer mPlayerRock;
    MediaPlayer mPlayerRockDemo;

    MediaPlayer mPlayerGeneral;
    MediaPlayer mPlayerGeneralDemo;

    MediaPlayer mPlayerClasica;
    MediaPlayer mPlayerClasicaDemo;
    private int length = 0;

    public MusicService() {
    }

    public class ServiceBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mPlayerTriviaDemo = MediaPlayer.create(this, R.raw.trividademo);
        mPlayerRockDemo = MediaPlayer.create(this, R.raw.rockdemo);
        mPlayerGeneralDemo = MediaPlayer.create(this, R.raw.generaldemo);
        mPlayerClasicaDemo = MediaPlayer.create(this, R.raw.clasicademo);

        mPlayerTrivia = MediaPlayer.create(this, R.raw.trivia);
        mPlayerRock = MediaPlayer.create(this, R.raw.rock);
        mPlayerGeneral = MediaPlayer.create(this, R.raw.general);
        mPlayerClasica = MediaPlayer.create(this, R.raw.clasica);

        mPlayerTriviaDemo.setOnErrorListener(this);

        mPlayerTriviaDemo.setLooping(false);
        mPlayerTriviaDemo.setVolume(100, 100);
        mPlayerRockDemo.setLooping(false);
        mPlayerRockDemo.setVolume(100, 100);
        mPlayerGeneralDemo.setLooping(false);
        mPlayerGeneralDemo.setVolume(100, 100);
        mPlayerClasicaDemo.setLooping(false);
        mPlayerClasicaDemo.setVolume(100, 100);


        mPlayerTrivia.setLooping(true);
        mPlayerTrivia.setVolume(100, 100);
        mPlayerRock.setLooping(true);
        mPlayerRock.setVolume(100, 100);
        mPlayerGeneral.setLooping(true);
        mPlayerGeneral.setVolume(100, 100);
        mPlayerClasica.setLooping(true);
        mPlayerClasica.setVolume(100, 100);

        mPlayerTriviaDemo.setOnErrorListener(new OnErrorListener() {

            public boolean onError(MediaPlayer mp, int what, int
                    extra) {

                onError(mPlayerTriviaDemo, what, extra);
                return true;
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() == null) {
            mPlayerTrivia.start();
        }
        int DEMO_KEY = intent.getIntExtra("DEMO_KEY", 0);
        int FULL_KEY = intent.getIntExtra("FULL_KEY", 0);
        if (FULL_KEY == 0) {
            switch (DEMO_KEY) {
                case 1:
                    mPlayerTriviaDemo.start();
                    break;
                case 2:
                    mPlayerRockDemo.start();
                    break;
                case 3:
                    mPlayerGeneralDemo.start();
                    break;
                case 4:
                    mPlayerClasicaDemo.start();
                    break;
                default:
                    //mPlayerTriviaDemo.start();
                    break;
            }
        } else if (DEMO_KEY == 0) {
            switch (FULL_KEY) {
                case 1:
                    mPlayerTrivia.start();
                    break;
                case 2:
                    mPlayerRock.start();
                    break;
                case 3:
                    mPlayerGeneral.start();
                    break;
                case 4:
                    mPlayerClasica.start();
                    break;
                default:
                    //mPlayerTrivia.start();
                    break;
            }
        }


        return START_STICKY;
    }

    public void pauseMusic() {
        if (mPlayerTriviaDemo.isPlaying()) {
            mPlayerTriviaDemo.pause();
            length = mPlayerTriviaDemo.getCurrentPosition();

        }
    }

    public void resumeMusic() {
        if (mPlayerTriviaDemo.isPlaying() == false) {
            mPlayerTriviaDemo.seekTo(length);
            mPlayerTriviaDemo.start();
        }
    }

    public void stopMusic() {
        mPlayerTriviaDemo.stop();
//        mPlayer.release();
//        mPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayerTrivia != null) {
            try {
                mPlayerTrivia.stop();
                mPlayerTrivia.release();
            } finally {
                mPlayerTrivia = null;
            }
        }
        if (mPlayerTriviaDemo != null) {
            try {
                mPlayerTriviaDemo.stop();
                mPlayerTriviaDemo.release();
            } finally {
                mPlayerTriviaDemo = null;
            }
        }

        if (mPlayerRock != null) {
            try {
                mPlayerRock.stop();
                mPlayerRock.release();
            } finally {
                mPlayerRock = null;
            }
        }
        if (mPlayerRockDemo != null) {
            try {
                mPlayerRockDemo.stop();
                mPlayerRockDemo.release();
            } finally {
                mPlayerRockDemo = null;
            }
        }
        if (mPlayerGeneral != null) {
            try {
                mPlayerGeneral.stop();
                mPlayerGeneral.release();
            } finally {
                mPlayerGeneral = null;
            }
        }
        if (mPlayerGeneralDemo != null) {
            try {
                mPlayerGeneralDemo.stop();
                mPlayerGeneralDemo.release();
            } finally {
                mPlayerGeneralDemo = null;
            }
        }
        if (mPlayerClasica != null) {
            try {
                mPlayerClasica.stop();
                mPlayerClasica.release();
            } finally {
                mPlayerClasica = null;
            }
        }
        if (mPlayerClasicaDemo != null) {
            try {
                mPlayerClasicaDemo.stop();
                mPlayerClasicaDemo.release();
            } finally {
                mPlayerClasicaDemo = null;
            }
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {

        Toast.makeText(this, "music player failed", Toast.LENGTH_SHORT).show();
        if (mPlayerTriviaDemo != null) {
            try {
                mPlayerTriviaDemo.stop();
                mPlayerTriviaDemo.release();
            } finally {
                mPlayerTriviaDemo = null;
            }
        }
        return false;
    }
}