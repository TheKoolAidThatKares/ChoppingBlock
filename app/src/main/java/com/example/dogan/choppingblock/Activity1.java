package com.example.dogan.choppingblock;

import android.content.Context;
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
    //
    private static boolean btcBoolean;
    int graphSetupCount;
    int keyManager = 0;
    private static boolean ltcBoolean;
    private static boolean ethBoolean;
    private static boolean hourBoolean;
    private static boolean weekBoolean;
    private static boolean monthBoolean;
    private static boolean dayBoolean;
    ArrayList<Item> btcList = new ArrayList<Item>();
    ArrayList<Item> ethList = new ArrayList<Item>();
    ArrayList<Item> litList = new ArrayList<Item>();
    RequestQueue queue;
    JsonArrayRequest jsonArrayRequest;
    Cache cache;
    Network network;
    LineGraphSeries<DataPoint> series;
    Switch btcSwitch;
    Switch ethSwitch;
    Switch ltcSwitch;
    Switch hourSwitch;
    Switch daySwitch;
    Switch weekSwitch;
    Switch monthSwitch;
    char currentCurrency;
    char currentTime;
    String url;
    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        graphSetupCount = 0;
        series = new LineGraphSeries<>();
        currentCurrency = 'b';
        currentTime = 'h';
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_iconfinder_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        double x, y;
        x = 0.0;
        y = 0.0;

        graph = (GraphView) findViewById(R.id.graph);
        queue = Volley.newRequestQueue(this);


        //delete later
        clearArrayLists();
        jsonParseBTC();
        queue.add(jsonArrayRequest);
        keyManager++;
        btcSwitch = (Switch) findViewById(R.id.btc_switch);
        btcSwitch.setChecked(true);
        btcSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btcBoolean = true;
                    ethBoolean = false;
                    ltcBoolean = false;
                    ltcSwitch.setChecked(false);
                    ethSwitch.setChecked(false);
                    currentCurrency = 'b';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                    if (graphSetupCount == 0)
                        setUpGraph();
                } else {

                }
            }
        });
        ltcSwitch = (Switch) findViewById(R.id.ltc_switch);
        ltcSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btcBoolean = false;
                    ethBoolean = false;
                    ltcBoolean = true;
                    btcSwitch.setChecked(false);
                    ethSwitch.setChecked(false);
                    currentCurrency = 'l';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                } else {

                }
            }
        });
        ethSwitch = (Switch) findViewById(R.id.eth_switch);
        ethSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btcBoolean = false;
                    ethBoolean = true;
                    ltcBoolean = false;
                    ltcSwitch.setChecked(false);
                    btcSwitch.setChecked(false);
                    currentCurrency = 'e';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                } else {

                }
            }
        });
        hourSwitch = (Switch) findViewById(R.id.hour_switch);
        hourSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hourBoolean = true;
                    dayBoolean = false;
                    weekBoolean = false;
                    monthBoolean = false;
                    daySwitch.setChecked(false);
                    weekSwitch.setChecked(false);
                    monthSwitch.setChecked(false);
                    currentTime = 'h';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                } else {

                }
            }
        });
        daySwitch = (Switch) findViewById(R.id.day_switch);
        daySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hourBoolean = false;
                    dayBoolean = true;
                    weekBoolean = false;
                    monthBoolean = false;
                    hourSwitch.setChecked(false);
                    weekSwitch.setChecked(false);
                    monthSwitch.setChecked(false);
                    currentTime = 'd';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                } else {

                }
            }
        });
        weekSwitch = (Switch) findViewById(R.id.week_switch);
        weekSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hourBoolean = false;
                    dayBoolean = false;
                    weekBoolean = true;
                    monthBoolean = false;
                    daySwitch.setChecked(false);
                    hourSwitch.setChecked(false);
                    monthSwitch.setChecked(false);
                    currentTime = 'w';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                } else {
                }
            }
        });
        monthSwitch = (Switch) findViewById(R.id.month_switch);
        monthSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hourBoolean = true;
                    dayBoolean = false;
                    weekBoolean = false;
                    monthBoolean = true;
                    daySwitch.setChecked(false);
                    weekSwitch.setChecked(false);
                    hourSwitch.setChecked(false);
                    currentTime = 'm';
                    clearArrayLists();
                    jsonParseBTC();
                    queue.add(jsonArrayRequest);
                    keyManager++;
                } else {

                }
            }
        });

    }

    protected void onResume()
    {
        super.onResume();
        refresh();
    }

    private void setUpGraph() {
        if (graphSetupCount>0)
            graph.removeAllSeries();
        getDataPoint();
        graph.addSeries(series);
        series.setColor(Color.rgb(88, 123, 127));
        series.setThickness(15);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.rgb(226, 192, 68));
        refresh();
        graphSetupCount++;
    }

    public void refresh()
    {
        final View view1 = (View) findViewById(R.id.activity_1);
        final View view2 = (View) findViewById(R.id.action_bar);
        final View view3 = (View) findViewById(R.id.graph);
        final View view5 = (View) findViewById(R.id.bottom_toolbar);

        Context context = this;

        ColorChanger.changeColor(view1, "primary.txt", context);
        ColorChanger.changeColor(view2, "secondary.txt", context);
        ColorChanger.changeColor(view3, "primary.txt", context);
        ColorChanger.changeColor(view5, "tertiary.txt", context);
    }

    private void clearArrayLists() {
        btcList.clear();
        litList.clear();
        ethList.clear();

    }

    private void determineUrl() {
        if (currentCurrency == 'b') {
            if (currentTime == 'h') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=2MIN&limit=30";
            } else if (currentTime == 'd') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=1HRS&limit=24";
            } else if (currentTime == 'w') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=12HRS&limit=14";
            } else if (currentTime == 'm') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=1DAY&limit=30";
            }
        } else if (currentCurrency == 'l') {
            if (currentTime == 'h') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_LTC_USD/latest?period_id=2MIN&limit=30";
            } else if (currentTime == 'd') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_LTC_USD/latest?period_id=1HRS&limit=24";
            } else if (currentTime == 'w') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_LTC_USD/latest?period_id=12HRS&limit=14";
            } else if (currentTime == 'm') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_LTC_USD/latest?period_id=1DAY&limit=30";
            }
        } else if (currentCurrency == 'e') {
            if (currentTime == 'h') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_ETH_USD/latest?period_id=2MIN&limit=30";
            } else if (currentTime == 'd') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_ETH_USD/latest?period_id=1HRS&limit=24";
            } else if (currentTime == 'w') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_ETH_USD/latest?period_id=12HRS&limit=14";
            } else if (currentTime == 'm') {
                url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_ETH_USD/latest?period_id=1DAY&limit=30";
            }
        }
    }

    private void jsonParseBTC() {
        determineUrl();

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("ENTERED ON RESPONSE", " ");
                int count = 0;
                while (count < response.length()) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        Log.i("JSON OBJECT PRICE CLOSE", jsonObject.getString("price_close"));
                        Item item = new Item(jsonObject.getString("time_period_start"), jsonObject.getString("time_period_end"), jsonObject.getString("price_open"), jsonObject.getString("price_close"), jsonObject.getString("trades_count"));
                        if (currentCurrency == 'b') {
                            btcList.add(item);
                        } else if (currentCurrency == 'l') {
                            litList.add(item);
                        } else if (currentCurrency == 'e') {
                            ethList.add(item);
                        }
                        count++;

                    } catch (JSONException e) {
                        Log.i("error!", "JSON EXCEPTION");
                        e.printStackTrace();
                    }
                }
                Log.i("Length of BTC list", Integer.toString(btcList.size()));
                Log.i("value of cc and ct", Character.toString(currentCurrency) + Character.toString(currentTime));
                setUpGraph();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse: ", "Entered onErrorResponse");
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                switch(keyManager%3)
                {
                    case 0:
                        headers.put("X-CoinAPI-Key", "75B42803-66B5-4590-BCA9-067F460A383F");
                        break;
                    case 1:
                        headers.put("X-CoinAPI-Key", "F3EBFC18-C646-4EEE-B001-C044103649A4");
                        break;
                    case 2:
                        headers.put("X-CoinAPI-Key", "8142DB64-2037-4522-8C51-2DF62D426C4C");
                        break;
                }
                return headers;
            }
        };

    }


    private void getDataPoint() {
        series = new LineGraphSeries<DataPoint>();
        int currentArrayListLength = 1;
        double currentX = 0, currentY = 0;
        if (currentTime == 'h') {
            currentArrayListLength = 30;
        } else if (currentTime == 'd') {
            currentArrayListLength = 24;
        } else if (currentTime == 'w') {
            currentArrayListLength = 14;
        } else if (currentTime == 'm') {
            currentArrayListLength = 30;
        }

        for (int i = 0; i < currentArrayListLength; i++) {
            if (currentCurrency == 'b') {
                currentY = Double.parseDouble(btcList.get(i).getPriceClose());
            } else if (currentCurrency == 'l') {
                currentY = Double.parseDouble(litList.get(i).getPriceClose());
            } else if (currentCurrency == 'e') {
                currentY = Double.parseDouble(ethList.get(i).getPriceClose());
            }
            series.appendData(new DataPoint(i, currentY), true, currentArrayListLength);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
                intent = new Intent(Activity1.this, Activity4.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
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
        ActionMenuView bottom_toolbar = (ActionMenuView) findViewById(R.id.bottom_toolbar);
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