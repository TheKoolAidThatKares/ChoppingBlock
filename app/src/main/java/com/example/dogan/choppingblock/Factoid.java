package com.example.dogan.choppingblock;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Factoid {

    static ArrayList<String> coins = new ArrayList<String>();
    static ArrayList<Coin> coinInfo = new ArrayList<Coin>();

    public static ArrayList<Coin> getFactoid()
    {
        buildCoins();
        return coinInfo;
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
                        String nameInfo = doc.getElementById(id).getElementsByClass("currency-name-container").outerHtml().replaceFirst("<", "");
                        String priceInfo = doc.getElementById(id).getElementsByClass("price").outerHtml().replaceFirst("<", "");
                        String capInfo = doc.getElementById(id).getElementsByClass("no-wrap market-cap text-right").outerHtml().replaceFirst("<", "");
                        String volumeInfo = doc.getElementById(id).getElementsByClass("volume").outerHtml().replaceFirst("<", "");
                        String supplyInfo = doc.getElementById(id).getElementsByClass("no-wrap text-right circulating-supply").outerHtml().replaceFirst("<", "");
                        String changeInfo = doc.getElementById(id).outerHtml();

                        changeInfo = changeInfo.substring(changeInfo.indexOf("data-percentusd"), changeInfo.indexOf("%<")+3);
                        changeInfo = changeInfo.substring(changeInfo.indexOf(">")+1, changeInfo.indexOf("<"));

                        capInfo = capInfo.substring(capInfo.indexOf(">") + 1, capInfo.indexOf("<"));
                        capInfo = capInfo.trim();
                        if(capInfo.contains("e"))
                        {
                            capInfo = capInfo.substring(0, capInfo.indexOf("e"));
                            capInfo = capInfo.replace(".","");
                        }
                        NumberFormat myFormat = NumberFormat.getInstance();
                        myFormat.setGroupingUsed(true);
                        capInfo = "$" + myFormat.format(Double.parseDouble(capInfo));

                        Log.d("factoid",  changeInfo);
                        coinInfo.add(new Coin(
                                nameInfo.substring(nameInfo.indexOf(">") + 1, nameInfo.indexOf("<")),
                                id,
                                capInfo,
                                priceInfo.substring(priceInfo.indexOf(">") + 1, priceInfo.indexOf("<")),
                                volumeInfo.substring(volumeInfo.indexOf(">") + 1, volumeInfo.indexOf("<")),
                                supplyInfo.substring(supplyInfo.indexOf("er>") + 3, supplyInfo.indexOf("</sp")),
                                changeInfo));
                       // Log.d("factoid", coinInfo.get(0).getDetails());
                       // Log.d("factoid",  Integer.toString(nameInfo.substring(nameInfo.indexOf(">"), nameInfo.length()-1).indexOf("<")+nameInfo.indexOf(">")));
                       // Log.d("factoid",  nameInfo.substring(nameInfo.indexOf(">") + 1, nameInfo.indexOf("<")));
                    }
                    for(Coin c : coinInfo)
                    {
                        Log.d("factoid",  c.getDetails());
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
