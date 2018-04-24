package com.example.dogan.choppingblock;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("myApp","ACTIVITY 2 CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_iconfinder_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.bottom_prices:
                intent = new Intent(Activity2.this, Activity1.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottom_alerts:
                //alerts
                break;
            case R.id.bottom_report:
                intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.settings:
                intent = new Intent(Activity2.this, Activity4.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            default:
                //unknown error
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume()
    {
        super.onResume();
        refresh();
    }


    public void refresh()
    {
        final View view1 = (View) findViewById(R.id.activity_2);
        final View view2 = (View) findViewById(R.id.action_bar);
        //final View view3 = (View) findViewById(R.id.graph);
        final View view5 = (View) findViewById(R.id.bottom_toolbar);

        Context context = this;

        ColorChanger.changeColor(view1, "primary.txt", context);
        ColorChanger.changeColor(view2, "secondary.txt", context);
        //ColorChanger.changeColor(view3, "primary.txt", context);
        ColorChanger.changeColor(view5, "tertiary.txt", context);
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