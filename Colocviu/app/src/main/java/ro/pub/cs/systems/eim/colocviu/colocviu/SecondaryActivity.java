package ro.pub.cs.systems.eim.colocviu.colocviu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        TextView counter = (TextView)findViewById(R.id.total);
        Button ok = (Button)findViewById(R.id.ok);
        Button cancel = (Button)findViewById(R.id.cancel);
        Intent intent = getIntent();
        int clicks_1 = 0;
        if (intent != null) {
            clicks_1 = intent.getIntExtra("clicks", 0);
            counter.setText("" + clicks_1);
        }
        final int clicks = clicks_1;
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("count", clicks);
                setResult(2, result);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("count", clicks * 2);
                setResult(3, result);
                finish();
            }
        });

    }
}
