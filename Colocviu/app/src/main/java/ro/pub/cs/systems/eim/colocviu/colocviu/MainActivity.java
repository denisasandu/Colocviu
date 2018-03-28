package ro.pub.cs.systems.eim.colocviu.colocviu;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text1 = (TextView) findViewById(R.id.view1);
        final TextView text2 = (TextView) findViewById(R.id.view2);
        if (savedInstanceState != null) {
            text1.setText(savedInstanceState.getInt("first"));
            text2.setText(savedInstanceState.getInt("snd"));
        }
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = "" + (Integer.parseInt((text1.getText().toString())) + 1);
                text1.setText(newText);
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = "" + (Integer.parseInt((text2.getText().toString())) + 1);
                text2.setText(newText);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        final TextView text1 = (TextView) findViewById(R.id.view1);
        outState.putInt("first", Integer.parseInt((text1.getText().toString())));
        final TextView text2 = (TextView) findViewById(R.id.view2);
        outState.putInt("snd", Integer.parseInt((text2.getText().toString())));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final TextView text1 = (TextView) findViewById(R.id.view1);
        final TextView text2 = (TextView) findViewById(R.id.view2);
        if (savedInstanceState != null) {
            text1.setText(savedInstanceState.getInt("first"));
            text2.setText(savedInstanceState.getInt("snd"));
        }
    }
}
