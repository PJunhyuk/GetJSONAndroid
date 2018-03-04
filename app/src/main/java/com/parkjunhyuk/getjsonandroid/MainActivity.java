package com.parkjunhyuk.getjsonandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.parse_text);
        String resultText = "NONE";

        try {
            resultText = new Task().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[][] resultTextList = null;

        resultTextList = new Task().listJSONParser(resultText, 0);
        String textViewText = "";

        Integer i = 0;

        while(resultTextList[i][0] != null) {
            textViewText = textViewText + resultTextList[i][5] + resultTextList[i][7] + "/";
            i = i + 1;
        }
        textView.setText(textViewText);
    }

    public void search_btn_click(View v) {
        TextView textView = (TextView)findViewById(R.id.parse_text);
        String resultText = "NONE";

        Integer sex_value = 0;

        RadioButton sex_radio_all = (RadioButton)findViewById(R.id.sex_radio_all);
        RadioButton sex_radio_male = (RadioButton)findViewById(R.id.sex_radio_male);
        RadioButton sex_radio_female = (RadioButton)findViewById(R.id.sex_radio_female);

        if (sex_radio_all.isChecked()) {
            sex_value = 0;
        } else if (sex_radio_male.isChecked()) {
            sex_value = 1;
        } else if (sex_radio_female.isChecked()) {
            sex_value = 2;
        }

        try {
            resultText = new Task().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[][] resultTextList = null;
        resultTextList = new Task().listJSONParser(resultText, sex_value);
        String textViewText = "";

        Integer i = 0;

        while(resultTextList[i][0] != null) {
            textViewText = textViewText + resultTextList[i][5] + resultTextList[i][7] + "/";
            i = i + 1;
        }
        textView.setText(textViewText);


//        new Task().putSex(sex_value);
//
//        try {
//            resultText = new Task().execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        String[][] resultTextList = null;
//        resultTextList = new Task().listJSONParser(resultText);
//        String textViewText = "";
//
//        Integer i = 0;
//
//        while(resultTextList[i][0] != null) {
//            textViewText = textViewText + resultTextList[i][5] + resultTextList[i][7] + "/";
//            i = i + 1;
//        }
//        textView.setText(textViewText);
    }
}
