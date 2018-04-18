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
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

import java.util.HashMap;
import java.util.Map;

public class Activity1 extends AppCompatActivity {


//    //final TextView mTextView = (TextView) findViewById(R.id.text);
//// ...
//
//    // Instantiate the RequestQueue.
//    RequestQueue queue = Volley.newRequestQueue(this);
//    String url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=1MIN";
//
//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//                   // mTextView.setText("Response: " + response.toString());
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // TODO: Handle error
//
//                }
//            })
//
//    {
//
//        /**
//         * Passing some request headers*
//         */
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            HashMap<String, String> headers = new HashMap();
//            headers.put("Content-Type", "application/json");
//            headers.put("X-CoinAPI-Key", "75B42803-66B5-4590-BCA9-067F460A383F");
//            return headers;
//        }
//    };

    private RequestQueue mQueue;

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("myApp", "ACTIVITY 1 CREATED");
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

        mQueue = Volley.newRequestQueue(this);
        //jsonParseBTC();
    }

    private void jsonParseBTC(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/latest?period_id=1MIN";

        // TODO: in the below arrayrequest parameters, there is a null value present. it must be replaced with what i assume is the API key.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count<response.length()){
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

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