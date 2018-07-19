package me.walten.fastgo.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customStyle(View v){
        startActivity(new Intent(this,CustomStyleActivity.class));
    }

    public void defaultStyle(View v){
        startActivity(new Intent(this,DefaultStyleActivity.class));
    }
}
