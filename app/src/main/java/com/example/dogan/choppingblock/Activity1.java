package com.example.dogan.choppingblock;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity1 extends AppCompatActivity {

    ArrayList<Item> btcList = new ArrayList<Item>();
    ArrayList<Item> ethList = new ArrayList<Item>();
    ArrayList<Item> litList = new ArrayList<Item>();
    RequestQueue queue;
    JsonArrayRequest jsonArrayRequest;
    Cache cache;
    Network network;
    LineGraphSeries<DataPoint> series;
    private static boolean btcBoolean;
    private static boolean ltcBoolean;
    private static boolean ethBoolean;
    private static boolean hourBoolean;
    private static boolean weekBoolean;
    private static boolean monthBoolean;
    private static boolean dayBoolean;
    Switch btcSwitch;
    Switch ethSwitch;
    Switch ltcSwitch;
    Switch hourSwitch;
    Switch daySwitch;
    Switch weekSwitch;
    Switch monthSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_iconfinder_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //linegraph
        double x, y;
        x = 0.0;
        y = 0.0;

        GraphView graph = (GraphView) findViewById(R.id.graph);

        series = new LineGraphSeries<DataPoint>(getDataPoint());

        graph.addSeries(series);
        series.setColor(Color.rgb(88, 123, 127));
        series.setThickness(15);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.rgb(226, 192, 68));

        //below this is volley stuff
        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        queue.start();
        //jsonParseBTC();
        //queue.add(jsonArrayRequest);

        //
        btcSwitch = (Switch) findViewById(R.id.btc_switch);
        btcSwitch.setChecked(true);
        btcSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    btcBoolean = true;
                    ethBoolean = false;
                    ltcBoolean = false;
                    ltcSwitch.setChecked(false);
                    ethSwitch.setChecked(false);
                }
                else{

                }
            }
        });
        ltcSwitch = (Switch) findViewById(R.id.ltc_switch);
        ltcSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    btcBoolean = false;
                    ethBoolean = false;
                    ltcBoolean = true;
                    btcSwitch.setChecked(false);
                    ethSwitch.setChecked(false);
                }
                else{

                }
            }
        });
        ethSwitch = (Switch) findViewById(R.id.eth_switch);
        ethSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    btcBoolean = false;
                    ethBoolean = true;
                    ltcBoolean = false;
                    ltcSwitch.setChecked(false);
                    btcSwitch.setChecked(false);
                }
                else{

                }
            }
        });
        hourSwitch = (Switch) findViewById(R.id.hour_switch);
        hourSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    hourBoolean = true;
                    dayBoolean = false;
                    weekBoolean = false;
                    monthBoolean = false;
                    daySwitch.setChecked(false);
                    weekSwitch.setChecked(false);
                    monthSwitch.setChecked(false);
                }
                else{

                }
            }
        });
        daySwitch = (Switch) findViewById(R.id.day_switch);
        daySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    hourBoolean = false;
                    dayBoolean = true;
                    weekBoolean = false;
                    monthBoolean = false;
                    hourSwitch.setChecked(false);
                    weekSwitch.setChecked(false);
                    monthSwitch.setChecked(false);
                }
                else{

                }
            }
        });
        weekSwitch = (Switch) findViewById(R.id.week_switch);
        weekSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    hourBoolean = false;
                    dayBoolean = false;
                    weekBoolean = true;
                    monthBoolean = false;
                    daySwitch.setChecked(false);
                    hourSwitch.setChecked(false);
                    monthSwitch.setChecked(false);
                }
                else{

                }
            }
        });
        monthSwitch = (Switch) findViewById(R.id.month_switch);
        monthSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    hourBoolean = true;
                    dayBoolean = false;
                    weekBoolean = false;
                    monthBoolean = true;
                    daySwitch.setChecked(false);
                    weekSwitch.setChecked(false);
                    hourSwitch.setChecked(false);
                }
                else{

                }
            }
        });

    }

    private void jsonParseBTC(){

        Log.i("jsonParseBTC: ", "Entered jsonParseBTC");

        String url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=1DAY&limit=100";

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("onResponse: ", "Entered onResponse");
                int count = 0;
                while (count<response.length()){
                    try {
                        btcList.clear();
                        JSONObject jsonObject = response.getJSONObject(count);
                        Item item = new Item(jsonObject.getString("time_period_start"), jsonObject.getString("time_period_end"), jsonObject.getString("price_open"), jsonObject.getString("price_close"), jsonObject.getString("trades_count") );
                        btcList.add(item);
                        Log.i("item:", jsonObject.getString("price_open"));
                        Log.i("item:", jsonObject.getString("time_period_start"));
                        count++;
                    } catch (JSONException e){
                        Log.i("error!", "JSON EXCEPTION");
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse: ", "Entered onErrorResponse");
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("X-CoinAPI-Key", "75B42803-66B5-4590-BCA9-067F460A383F");
                return headers;
            }
        };

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
