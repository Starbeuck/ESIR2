package esir.tp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Button button = findViewById(R.id.AddLivre);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(ListActivity.this,CreateActivity.class);
                startActivityForResult(i,1);
            }


        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(ListActivity.this, data.getExtras().getString("VALUE"), Toast.LENGTH_SHORT).show();
    }
}