package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnstart, btnstop;
    TextView txtcounter;
    int i = 1;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtcounter = findViewById(R.id.count);
        btnstart = findViewById(R.id.btn_start);
        btnstop = findViewById(R.id.btn_stop);
        btnstop.setVisibility(View.INVISIBLE);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnstart.setVisibility(View.INVISIBLE);
                btnstop.setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 100);

            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnstart.setVisibility(View.VISIBLE);
                btnstop.setVisibility(View.INVISIBLE);
                handler.removeCallbacks(runnable);
            }
        });


    }
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            txtcounter.setText(""+i);
            handler.postDelayed(this,100);
            i++;

        }
    };

}
