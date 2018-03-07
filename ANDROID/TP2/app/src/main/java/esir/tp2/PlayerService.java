package esir.tp2;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import java.io.IOException;

public class PlayerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static final String PLAY = "PLAY";
    static final String STOP = "STOP";


    final Uri myUri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/sample.mp3");
    final MediaPlayer mediaPlayer = new MediaPlayer();
    public PlayerService(){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(PLAY)) {

            try {

                mediaPlayer.setDataSource(getApplicationContext(), myUri);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


                @Override
                public void onPrepared(MediaPlayer mp) {
                        mp.start();
                }

            });
        }

        if (intent.getAction().equals(STOP)){
            mediaPlayer.stop();
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
