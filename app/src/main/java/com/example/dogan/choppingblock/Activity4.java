package com.example.dogan.choppingblock;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.io.FileOutputStream;

public class Activity4  extends AppCompatActivity {

    String[] mobileArray = {"Primary Color","Secondary Color","Tertiary Color"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.w("myApp","ACTIVITY 4 CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        View view1 = (View) findViewById(R.id.settings_view);
        Context context = this;
        ColorChanger.changeColor(view1, "primary.txt", context);





        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.settings);
        listView.setAdapter(adapter);

       // Color opaqueRed = Color.valueOf(3);

        final ColorPicker cp = new ColorPicker(Activity4.this, 0, 0, 0);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, final int pos, long id) {
                Log.d("list",""+pos+"");
                /* Show color picker dialog */
                cp.show();

                /* On Click listener for the dialog, when the user select the color */
                Button okColor = (Button)cp.findViewById(R.id.okColorButton);

                okColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /* You can get single channel (value 0-255) */
                        int selectedColorR = cp.getRed();
                        int selectedColorG = cp.getGreen();
                        int selectedColorB = cp.getBlue();

                        /* Or the android RGB Color (see the android Color class reference) */
                        int selectedColorRGB = cp.getColor();

                        cp.dismiss();
                        switch (pos)
                        {
                            case 0:
                                changePrimary(selectedColorRGB);
                                break;
                            case 1:
                                changeSecondary(selectedColorRGB);
                                break;
                            case 2:
                                changeTertiary(selectedColorRGB);
                                break;
                        }


                    }
                });
            }
        });

    }
    private void changePrimary(int color)
    {
        final View view = (View) findViewById(R.id.settings_view);
        view.setBackgroundColor(color);

        String filename = "primary.txt";
        String fileContents = Integer.toString(color);
        FileOutputStream outputStream;


        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void changeSecondary(int color)
    {
        String filename = "secondary.txt";
        String fileContents = Integer.toString(color);
        FileOutputStream outputStream;


        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void changeTertiary(int color)
    {
        String filename = "tertiary.txt";
        String fileContents = Integer.toString(color);
        FileOutputStream outputStream;


        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
