package com.example.dogan.choppingblock;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
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

public class Activity4  extends AppCompatActivity {

    String[] mobileArray = {"Primary Color","Secondary Color","Tertiary Color"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.w("myApp","ACTIVITY 4 CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);



        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.settings);
        listView.setAdapter(adapter);

        final ColorPicker cp = new ColorPicker(Activity4.this, 0, 0, 0);

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
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                Log.d("list",""+pos+"");
                cp.show();
            }
        });

    }
}
