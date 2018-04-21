package com.example.dogan.choppingblock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Activity3 extends AppCompatActivity {

    final Random Ran = new Random();
    final ArrayList<Website> Websites = new ArrayList<Website>();
   // final String[] websites =
    //        {"https://cryptopotato.com/8-must-read-tips-trading-bitcoin-altcoins/",
    //         "https://hackernoon.com/5-cryptocurrency-investment-tips-6e9e23e223be"};
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
        final TextView tv2 = (TextView)findViewById(R.id.motd_date_textview);
        final TextView tv3 = (TextView)findViewById(R.id.motd_author_textview);

        Log.d("web", "About To Create Website ArrayList");
        createWebsites();
        Log.d("web", "Created Website ArrayList");
        getWebsite(tv1, tv2, tv3);

    }

    private void createWebsites()
    {
        Website web1 = new Website("hackernoon.com", "Ermos Kyriakides", "https://hackernoon.com/5-cryptocurrency-investment-tips-6e9e23e223be");
        web1.addLocation("a145","What is the circulating"," An");
        //web1.addLocation("0c32","What percentage do the coin","</li>");

        Websites.add(web1);

        Website web2 = new Website("CryptoPotato.com", "Yuval Gov", "https://cryptopotato.com/8-must-read-tips-trading-bitcoin-altcoins/");
        //web2.addLocation("post-2251","Have a reason before","<br>");
        web2.addLocation("post-2251","Manage risk","buying level.");

        Websites.add(web2);
    }

    private void getWebsite(final TextView tv1, final TextView tv2, final TextView tv3) {

        tv1.setText("Loading...");
        tv2.setText("Loading...");
        tv3.setText("Loading...");



                                                                 //This is the JSoup stuff.
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final Website web = Websites.get(Ran.nextInt(Websites.size()));
                final int place = Ran.nextInt(web.locations.size());

                try {
                    Document doc = Jsoup.connect(web.url).get();
                    Elements links = doc.select("p[style]");                     //scraping the p elements, html elements that construct paragraphs

                    String content = doc.getElementById(web.locations.get(place)).outerHtml();


                   // for (Element link : links) {
                        builder.append("\n")
                                .append("\n").append(content.substring(content.indexOf(web.starts.get(place)), content.indexOf(web.ends.get(place)+web.ends.get(place).length())));                          //I really need to change how this one part works just a bit
                     //   break;                                                              //https://medium.com/@ssaurel/learn-to-parse-html-pages-on-android-with-jsoup-2a9b0da0096f
                   // }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                } catch (Error e){
                    builder.append("Error in Website Retrieval");
                    Log.d("web", "Error in Website Retrieval try block");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(builder.toString());
                        tv2.setText(web.name);
                        tv3.setText("Author: " + web.author);
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





        return super.onCreateOptionsMenu(menu);
    }
}
