package com.trololesha.mathanvariantsgenerator;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private  VariantsGenerator generator;
    private ListView variantsListView;
    static final private int DATA_ENTER_ACTIVITY_ID = 1;
    private static final String HEADER_TEXT_TEMPLATE = "%d group, student № %d";
    private static final String VARIANT_TEXT_TEMPLATE = "%d ) variant: %d";
    TextView headerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton getDataButton = findViewById(R.id.getDataButton);
        variantsListView = findViewById(R.id.listViewId);
        headerTextView = findViewById(R.id.dataTextView);
    }

    public void ongetDBClick(View view) {
        Intent dataEnterIntent = new Intent(MainActivity.this, DataEnteringActivity.class);
        startActivityForResult(dataEnterIntent, DATA_ENTER_ACTIVITY_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == DATA_ENTER_ACTIVITY_ID) {
             if (resultCode == RESULT_OK) {
                 int groupNumber = data.getIntExtra("groupNumber", 0);
                 int studentNumber = data.getIntExtra("studentNumber", 0);
                 int firstTask = data.getIntExtra("firstTask", 0);
                 int lastTask = data.getIntExtra("lastTask", 0);
                 int[] variantsArray = data.getIntArrayExtra("Result");
                 headerTextView.setText(String.format(HEADER_TEXT_TEMPLATE, groupNumber, studentNumber));
                 String[] textedInfoArray = new String[variantsArray.length];
                 for (int i = 0; i < textedInfoArray.length; i++) {
                     textedInfoArray[i] = String.format(VARIANT_TEXT_TEMPLATE, firstTask + i, variantsArray[i]);
                 }
                 ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, textedInfoArray);
                 variantsListView.setAdapter(adapter);
             }
         }
    }
}
