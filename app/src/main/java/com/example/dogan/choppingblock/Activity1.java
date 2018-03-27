package com.example.dogan.choppingblock;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Activity1 extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("myApp","ACTIVITY 1 CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_iconfinder_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //linegraph
        double x,y;
        x = 0.0;
        y=0.0;

        GraphView graph = (GraphView)findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>(getDataPoint());

        graph.addSeries(series);
        series.setColor(Color.rgb(88,123,127));
        series.setThickness(15);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.rgb(226,192,68));
    }

    private DataPoint[] getDataPoint(){
        DataPoint[] dp = new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(2, 5),
                new DataPoint(3, 1),
                new DataPoint(5, 6),
                new DataPoint(8, 3),
        };
        return (dp);

        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.bottom_prices:
                //prices
                break;
            case R.id.bottom_alerts:
                Intent intent = new Intent(Activity1.this, Activity2.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottom_report:
                intent = new Intent(Activity1.this, Activity3.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.settings:
                //settings
                break;
            default:
                //unknown error
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the top toolbar
        getMenuInflater().inflate(R.menu.menu, menu);

        //inflate and initialize the bottom menu
        ActionMenuView bottom_toolbar = (ActionMenuView)findViewById(R.id.bottom_toolbar);
        Menu bottomMenu = bottom_toolbar.getMenu();
        getMenuInflater().inflate(R.menu.bottom_bar_menu, bottomMenu);
        for (int i = 0; i < bottomMenu.size(); i++) {
            bottomMenu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return onOptionsItemSelected(item);
                }
            });
        }




        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}