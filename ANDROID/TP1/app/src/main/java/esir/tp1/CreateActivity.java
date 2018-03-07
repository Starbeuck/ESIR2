package esir.tp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static esir.tp1.R.id.CreateButton;
import static esir.tp1.R.id.authorEdit;

public class CreateActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        final Button button = findViewById(CreateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateActivity.this, "coucou", Toast.LENGTH_SHORT).show();
                Intent data = new Intent();
                data.putExtra("VALUE", authorEdit);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });



    }
}
