package com.example.dogan.choppingblock;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Factoid {

    static ArrayList<String> coins = new ArrayList<String>();
    static ArrayList<Coin> coinInfo = new ArrayList<Coin>();

    public static String getFactoid()
    {
        String factoid = "";
        buildCoins();
        return factoid;
    }
    private static void buildCoins()
    {
        //This is the JSoup stuff.
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                boolean coin = true;
                try {
                    Document doc = Jsoup.connect("https://coinmarketcap.com/").get();
                    String content = doc.getElementById("currencies").outerHtml();

                    while(coin)
                    {
                        if(content.contains("tr id=\"id-"))
                        {
                            Log.d("factoid", Integer.toString(content.indexOf("tr id=\"id-")));
                            Log.d("factoid", Integer.toString(content.substring(content.indexOf("tr id=\"id-"), content.length()-1).indexOf("\" class=")+content.indexOf("tr id=\"id-")));
                            coins.add(content.substring(content.indexOf("tr id=\"id-") + 7, content.substring(content.indexOf("tr id=\"id-"), content.length()-1).indexOf("\" class=")+content.indexOf("tr id=\"id-")));
                            content = content.replaceFirst("tr id=\"id-", "");
                        }
                        else
                            coin=false;
                    }
                    Log.d("factoid", coins.toString());

                    for(String id : coins)
                    {
                        coinInfo.add(new Coin(
                                doc.getElementById(id).outerHtml(),id,"","",""));
                    }


                    //builder.append("\n").append("\n").append(content.substring(content.indexOf(web.starts.get(place)), content.indexOf(web.ends.get(place))+web.ends.get(place).length()));

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                } catch (Error e){
                    builder.append("Error in Website Retrieval");
                    Log.d("web", "Error in Website Retrieval try block");
                }

            }
        }).start();
    }


}
