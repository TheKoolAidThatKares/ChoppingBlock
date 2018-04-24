package com.example.dogan.choppingblock;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.dogan.choppingblock.Factoid.getFactoid;


public class Activity2 extends AppCompatActivity {

    String[] mobileArray = {"Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading",
            "Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading","Loading"};
    ArrayList<Coin> coinInfo = new ArrayList<Coin>();

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

        coinInfo = getFactoid();

        Log.d("CoinStuff", "Got factoid");


        Log.d("CoinStuff", "waited");

        coinInfo.add(new Coin("dslkfjasl;","","","","","",""));

        for(int name = 0; name < 100; name++) {
            mobileArray[name] = coinInfo.get(name).name;
            mobileArray[name] = mobileArray[name].concat("\n\n\t\tPrice: ").concat(coinInfo.get(name).price);
            mobileArray[name] = mobileArray[name].concat("\n\t\tMarket Cap: ").concat(coinInfo.get(name).marketcap);
            mobileArray[name] = mobileArray[name].concat("\n\t\tVolume (24hr): ").concat(coinInfo.get(name).volume);
            mobileArray[name] = mobileArray[name].concat("\n\t\tTotal Supply: ").concat(coinInfo.get(name).supply);
            mobileArray[name] = mobileArray[name].concat("\n\t\tChange (24hr): ").concat(coinInfo.get(name).change);
        }



        ArrayAdapter adapter = new ArrayAdapter<String>(this,
               R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.allCoins);
        listView.setAdapter(adapter);

/*
        TextView currencyName1 = findViewById(R.id.currency_name_1);
        TextView currencyPrice1 = findViewById(R.id.currency_price_1);
        TextView currencyName2 = findViewById(R.id.currency_name_2);
        TextView currencyPrice2 = findViewById(R.id.currency_price_2);
        TextView currencyName3 = findViewById(R.id.currency_name_3);
        TextView currencyPrice3 = findViewById(R.id.currency_price_3);
        TextView currencyName4 = findViewById(R.id.currency_name_4);
        TextView currencyPrice4 = findViewById(R.id.currency_price_4);
        TextView currencyName5 = findViewById(R.id.currency_name_5);
        TextView currencyPrice5 = findViewById(R.id.currency_price_5);
        TextView currencyName6 = findViewById(R.id.currency_name_6);
        TextView currencyPrice6 = findViewById(R.id.currency_price_6);
        TextView currencyName7 = findViewById(R.id.currency_name_7);
        TextView currencyPrice7 = findViewById(R.id.currency_price_7);
        TextView currencyName8 = findViewById(R.id.currency_name_8);
        TextView currencyPrice8 = findViewById(R.id.currency_price_8);
        TextView currencyName9 = findViewById(R.id.currency_name_9);
        TextView currencyPrice9 = findViewById(R.id.currency_price_9);
        TextView currencyName10 = findViewById(R.id.currency_name_10);
        TextView currencyPrice10 = findViewById(R.id.currency_price_10);
*/
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