package com.trololesha.mathanvariantsgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class DataEnteringActivity extends AppCompatActivity {
    private EditText groupNumberEditText;
    private EditText studentNumberEditText;
    private EditText firstTaskEditText;
    private EditText lastTaskEditText;
    private VariantsGenerator generator;
    private static byte[] piByteArray;
    private int kek = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entering);

        groupNumberEditText = findViewById(R.id.groupNumberEditText);
        studentNumberEditText = findViewById(R.id.studentNumberEditText);
        firstTaskEditText = findViewById(R.id.firstTaskEditText);
        lastTaskEditText = findViewById(R.id.lastTaskEditText);

        InitializePI();
    }

    void InitializePI() {
        if (piByteArray == null) {
            try {
                piByteArray = new byte[99999];
                InputStream inputStream = getResources().openRawResource(R.raw.pi_char_array);
                inputStream.read(piByteArray);
                inputStream.close();
                for (int i = 0; i < piByteArray.length; ++i)
                    piByteArray[i] -= '0';
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onOKButtonClick(View view) {
        try {
            int groupNumber = Integer.parseInt(groupNumberEditText.getText().toString());
            int studentNumber = Integer.parseInt(studentNumberEditText.getText().toString());
            int firstTask = Integer.parseInt(firstTaskEditText.getText().toString());
            int lastTask = Integer.parseInt(lastTaskEditText.getText().toString());

            generator = new VariantsGenerator(groupNumber, studentNumber);
            generator.SetGeneratorArray(piByteArray);
            int[] variantsArray = generator.GetVariants(firstTask, lastTask);
            Intent answerIntent = new Intent();
            answerIntent.putExtra("Result", variantsArray);
            answerIntent.putExtra("groupNumber", groupNumber);
            answerIntent.putExtra("studentNumber", studentNumber);
            answerIntent.putExtra("firstTask", firstTask);
            answerIntent.putExtra("lastTask", lastTask);
            setResult(RESULT_OK, answerIntent);
            finish();
        } catch (NumberFormatException ex) {
            Toast.makeText(getApplicationContext(), "Some information is not correct", Toast.LENGTH_SHORT).show();
        } catch (VGException ex) {
            Toast.makeText(getApplicationContext(), ex.getErrorCode().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
