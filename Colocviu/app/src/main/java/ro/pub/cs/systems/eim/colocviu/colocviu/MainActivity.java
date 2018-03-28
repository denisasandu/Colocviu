package ro.pub.cs.systems.eim.colocviu.colocviu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    IntentFilter intentFilter = new IntentFilter();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter.addAction("time");
        intentFilter.addAction("aritmetica");
        intentFilter.addAction("geometrica");

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
                int count = Integer.parseInt((text2.getText().toString())) +
                        Integer.parseInt((text1.getText().toString())) + 1;
                String newText = "" + (Integer.parseInt((text1.getText().toString())) + 1);
                text1.setText(newText);
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt((text2.getText().toString())) +
                        Integer.parseInt((text1.getText().toString())) + 1;
                String newText = "" + (Integer.parseInt((text2.getText().toString())) + 1);
                text2.setText(newText);
                if (count > 10) {
                    Intent intent = new Intent(getApplicationContext(), MyService.class);
                    intent.putExtra("firstNumber", Integer.parseInt((text1.getText().toString())));
                    intent.putExtra("secondNumber", Integer.parseInt((text2.getText().toString())));
                    getApplicationContext().startService(intent);
                }
            }
        });
        Button activ = (Button)findViewById(R.id.activity_btn);
        activ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clicks = Integer.parseInt((text1.getText().toString())) +
                        Integer.parseInt((text2.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                intent.putExtra("clicks", clicks);
                startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }
}
