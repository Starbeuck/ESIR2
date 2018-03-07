package esir.tp2;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    Uri myUri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/sample.mp3");
    MediaPlayer mediaPlayer = new MediaPlayer();


    public void playIt(View view) {
        Intent i = new Intent(this, PlayerService.class);
        i.setAction(PlayerService.PLAY);
        this.startService(i);

    }

    public void stopIt(View view) {
        Intent i = new Intent(this, PlayerService.class);
        i.setAction(PlayerService.STOP);
        this.startService(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
