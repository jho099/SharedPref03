package com.hojennifer.sharedpref03;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView topLeft;
    TextView topRight;
    TextView botLeft;
    TextView botRight;
    SeekBar seekbar;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topLeft = findViewById(R.id.topLeft);
        topRight = findViewById(R.id.topRight);
        botLeft = findViewById(R.id.botLeft);
        botRight = findViewById(R.id.botRight);
        seekbar = findViewById(R.id.seekbar);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPrefs.edit();

        editor.putInt(topLeft.getText().toString(), 0);
        editor.putInt(topRight.getText().toString(), 0);
        editor.putInt(botLeft.getText().toString(), 0);
        editor.putInt(botRight.getText().toString(), 0);

        editor.apply();

        View.OnClickListener p = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                String str = tv.getText().toString();
                int count = sharedPrefs.getInt(str, -1);
                Log.i("origCount", str + " " + count);
                int newCount = count+1;
                Log.i("newCount", str + " " + newCount);
                editor.putInt(str, newCount);
                editor.apply();
                Toast toast = Toast.makeText(getApplicationContext(),
                        str + newCount ,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        topLeft.setOnClickListener(p);
        topRight.setOnClickListener(p);
        botLeft.setOnClickListener(p);
        botRight.setOnClickListener(p);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // When the progress value has changed
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                topLeft.setTextSize(progress + 1);
                topRight.setTextSize(progress + 1);
                botLeft.setTextSize(progress + 1);
                botRight.setTextSize(progress + 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

                // This method will automatically
                // called when the user touches the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

                // This method will automatically
                // called when the user
                // stops touching the SeekBar
            }
        });
    }

}
