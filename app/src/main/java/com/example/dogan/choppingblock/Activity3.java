package com.example.dogan.choppingblock;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.HttpResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("myApp","ACTIVITY 3 CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_iconfinder_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final TextView tv1 = (TextView)findViewById(R.id.textView4);
        /*
        try
        {
            URL url = new URL("https://www.dev2qa.com/how-to-auto-import-all-class-in-android-studio/");
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String str;
            while ((str = in.readLine()) != null)
            {
                // str is one line of text; readLine() strips the newline character(s)
                // You can use the contain method here.
                if(str.contains("when you"))
                {
                    tv1.setText("yes");
                }
                else
                {
                    tv1.setText("no");
                }
            }
            in.close();
        } catch (MalformedURLException e) {
            tv1.setText("Malformed Error");
        }
        catch (IOException e) {
            tv1.setText("IOE Error");
        }
        catch (Exception e){
          //  tv1.setText(e.getMessage()+"Error");
        }
        */

        getWebsite(tv1);

    }

    private void getWebsite(final TextView tv1) {

        tv1.setText("Loading...");
                                                                 //This is the JSoup stuff.
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://cryptopotato.com/8-must-read-tips-trading-bitcoin-altcoins/").get();
                    int title = doc.html().indexOf("Have a reason");
                   // Elements links = doc.select("p[style]").after("p style").before("</p>");
                    Elements links = doc.select("p[style]");                     //scraping the p elements, html elements that construct paragraphs


                    //builder.append(title).append("\n");

                    for (Element link : links) {
                        builder.append("\n")
                                .append("\n").append(link.text());                          //I really need to change how this one part works just a bit
                        break;                                                              //https://medium.com/@ssaurel/learn-to-parse-html-pages-on-android-with-jsoup-2a9b0da0096f
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.bottom_prices:
                intent = new Intent(Activity3.this, Activity1.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottom_alerts:
                intent = new Intent(Activity3.this, Activity2.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottom_report:
                //report
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
