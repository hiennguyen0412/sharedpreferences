package com.example.hiennv.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView tvSelect;
    Context mContext;
    int position;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        tvSelect = this.findViewById(R.id.tv_select);

        final String goods[] = {"Tiếng Việt", "English"};
        Spinner spin = (Spinner) findViewById(R.id.spinnerLang);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, goods);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spin.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("my data" , MODE_PRIVATE);
        position = sharedPreferences.getInt("position", 0);
        spin.setSelection(position);
        Toast.makeText(mContext, "Vi tri " + position, Toast.LENGTH_SHORT).show();

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if(arg2 == 0) {
                    tvSelect.setText("Ứng dụng");
                } else if(arg2 == 1) {
                    tvSelect.setText("Application");
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                position = arg2;
                editor.clear();
                editor.putInt("position",position);
                editor.commit();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
            }
        });
    }
}
