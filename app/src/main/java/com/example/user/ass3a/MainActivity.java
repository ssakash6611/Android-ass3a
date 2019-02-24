package com.example.user.ass3a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText ed;
BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.et);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(br!=null){
            unregisterReceiver(br);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(br==null){
            br=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Toast.makeText(context,"Broadcasting: "+intent.getStringExtra("aa").toString(),Toast.LENGTH_LONG).show();
                }
            };
        }
        registerReceiver(br,new IntentFilter("My_Custom_Action"));
    }

    public void broadcast(View view) {
     Intent i=new Intent();
     i.putExtra("aa",ed.getText().toString());
     i.setAction("My_Custom_Action");
     sendBroadcast(i);
    }
}
