package com.example.dogan.choppingblock;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class ColorChanger {


    public static void changeColor(View view, String file, Context context)
    {



        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                view.setBackgroundColor(Integer.parseInt(ret));
            }
        } catch (FileNotFoundException e) {
            Log.e("color", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("color", "Can not read file: " + e.toString());
        } catch (Error e){
            Log.e("color", "You're an idiot: " + e.toString());
        }

        finally {
            Log.e("Color", ret);
        }


    }

    public String returnColor(String file, Context context)
    {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("color", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("color", "Can not read file: " + e.toString());
        } catch (Error e){
            Log.e("color", "Dogans an idiot: " + e.toString());
        }

        finally {
            Log.e("Color", ret);
        }

        return ret;
    }
}
