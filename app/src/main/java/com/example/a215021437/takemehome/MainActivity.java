package com.example.a215021437.takemehome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //private ImageView imageView;
    private TextView textView;
    private Button nDriver, nCustomer;

    //private Button nMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call components
        //imageView = (ImageView) findViewById(R.id.logo);
        textView = (TextView) findViewById(R.id.choose);
        nDriver = (Button) findViewById(R.id.driver);
        nCustomer = (Button) findViewById(R.id.customer);

        //nMap = (Button) findViewById(R.id.map);

        //TESTING MAP ACTIVITY
  /*      nMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DriverMapActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });*/

        //imageView.setImageResource(R.drawable.home);


        nDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DriverLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        nCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomerloginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


    }
}

