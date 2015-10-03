/*Copyright (c) 2015 Adriano Marini

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package ca.ualberta.cs.marini_reflex;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataHandler {

    private static final String ARRAYFILE = "array.sav";
    private static final String TWOPLAYERFILE = "twoplayer.sav";
    private static final String THREEPLAYERFILE = "threeplayer.sav";
    private static final String FOURPLAYERFILE = "fourplayer.sav";

    private static ArrayList<ReflexTime> reflexTimes;
    private static FourPlayerStanding fourPlayer;
    private static ThreePlayerStanding threePlayer;
    private static TwoPlayerStanding twoPlayer;

    //https://github.com/joshua2ua/lonelyTwitter | Fork: https://github.com/adrianomarini/lonelyTwitter/
    //Original Author: Joshua Campbell | Accessed: 03.10.2015
    //  Used as template for designing use of Gsons

    //http://stackoverflow.com/questions/3625837/android-what-is-wrong-with-openfileoutput
    //Original Author: Naikus | Accessed: 03.10.2015
    //  Adapted use of Context from the top answer: No code copied directly

    //Opens file and populates data from the file.sav
    //Catches file not found error and initiates variables with blank
    public static void init(Context ctx){
        try{

            //create the input readers
            FileInputStream fis1 = ctx.openFileInput(ARRAYFILE);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(fis1));
            FileInputStream fis2 = ctx.openFileInput(TWOPLAYERFILE);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));
            FileInputStream fis3 = ctx.openFileInput(THREEPLAYERFILE);
            BufferedReader in3 = new BufferedReader(new InputStreamReader(fis3));
            FileInputStream fis4 = ctx.openFileInput(FOURPLAYERFILE);
            BufferedReader in4 = new BufferedReader(new InputStreamReader(fis4));

            //create the gsons
            Gson arrayGson = new Gson();
            Gson twoPlayerGson = new Gson();
            Gson threePlayerGson = new Gson();
            Gson fourPlayerGson = new Gson();

            ////http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 03.10.2015
            Type arrayListType = new TypeToken<ArrayList<ReflexTime>>() {}.getType();
            Type twoPlayerStandingType = new TypeToken<TwoPlayerStanding>() {}.getType();
            Type threePlayerStandingType = new TypeToken<ThreePlayerStanding>() {}.getType();
            Type fourPlayerStandingType = new TypeToken<FourPlayerStanding>() {}.getType();

            //write the gsons to the files
            twoPlayer = twoPlayerGson.fromJson(in1, twoPlayerStandingType);
            threePlayer = threePlayerGson.fromJson(in2, threePlayerStandingType);
            fourPlayer = fourPlayerGson.fromJson(in3, fourPlayerStandingType);
            reflexTimes = arrayGson.fromJson(in4, arrayListType);
        }
        catch (FileNotFoundException e){
            reflexTimes = new ArrayList<>();
            twoPlayer = new TwoPlayerStanding(0,0);
            threePlayer = new ThreePlayerStanding(0,0,0);
            fourPlayer = new FourPlayerStanding(0,0,0,0);
        }


    }

    //Saves updated data to the file
    public static void save(Context ctx){
        try{

            FileOutputStream fos1 = ctx.openFileOutput(ARRAYFILE, 0);
            BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(fos1));
            FileOutputStream fos2 = ctx.openFileOutput(ARRAYFILE, 0);
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            FileOutputStream fos3 = ctx.openFileOutput(ARRAYFILE, 0);
            BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fos3));
            FileOutputStream fos4 = ctx.openFileOutput(ARRAYFILE, 0);
            BufferedWriter out4 = new BufferedWriter(new OutputStreamWriter(fos4));


            Gson arrayGson = new Gson();
            Gson twoPlayerGson = new Gson();
            Gson threePlayerGson = new Gson();
            Gson fourPlayerGson = new Gson();

            twoPlayerGson.toJson(twoPlayer, out1);
            threePlayerGson.toJson(threePlayer,out2);
            fourPlayerGson.toJson(fourPlayer,out3);
            arrayGson.toJson(reflexTimes, out4);

            out1.flush();
            fos1.close();
            out2.flush();
            fos2.close();
            out3.flush();
            fos3.close();
            out4.flush();
            fos4.close();
        }

        catch (FileNotFoundException e){
            reflexTimes = new ArrayList<>();
            twoPlayer = new TwoPlayerStanding(0,0);
            threePlayer = new ThreePlayerStanding(0,0,0);
            fourPlayer = new FourPlayerStanding(0,0,0,0);
        }

        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    //end attribution (Joshua Campbell)

    //stable add data to standings
    public static void addReflexTime(int time){
        ReflexTime temp = new ReflexTime(time);
        reflexTimes.add(temp);
        save(MainMenu.baseContext);
    }

    public static void twoPlayerP1(){
        int temp = twoPlayer.getData();
        temp++;
        twoPlayer.setData(temp);
        save(MainMenu.baseContext);
    }

    public static void twoPlayerP2(){
        int temp = twoPlayer.getPlayer2();
        temp++;
        twoPlayer.setPlayer2(temp);
        save(MainMenu.baseContext);
    }

    public static void threePlayerP1(){
        int temp = threePlayer.getData();
        temp++;
        threePlayer.setData(temp);
        save(MainMenu.baseContext);
    }

    public static void threePlayerP2(){
        int temp = threePlayer.getPlayer2();
        temp++;
        threePlayer.setPlayer2(temp);
        save(MainMenu.baseContext);
    }

    public static void threePlayerP3(){
        int temp = threePlayer.getPlayer3();
        temp++;
        threePlayer.setPlayer3(temp);
        save(MainMenu.baseContext);
    }

    public static void fourPlayerP1(){
        int temp = fourPlayer.getData();
        temp++;
        fourPlayer.setData(temp);
        save(MainMenu.baseContext);
    }

    public static void fourPlayerP2(){
        int temp = fourPlayer.getPlayer2();
        temp++;
        fourPlayer.setPlayer2(temp);
        save(MainMenu.baseContext);
    }

    public static void fourPlayerP3(){
        int temp = fourPlayer.getPlayer3();
        temp++;
        fourPlayer.setPlayer3(temp);
        save(MainMenu.baseContext);
    }

    public static void fourPlayerP4(){
        int temp = fourPlayer.getPlayer4();
        temp++;
        fourPlayer.setPlayer4(temp);
        save(MainMenu.baseContext);
    }

    //clears all data
    // for use with clear button
    public static void clear(){
        reflexTimes.clear();
        twoPlayer.setData(0);
        twoPlayer.setPlayer2(0);
        threePlayer.setData(0);
        threePlayer.setPlayer2(0);
        threePlayer.setPlayer3(0);
        fourPlayer.setData(0);
        fourPlayer.setPlayer2(0);
        fourPlayer.setPlayer3(0);
        fourPlayer.setPlayer4(0);
        save(MainMenu.baseContext);
    }

    //Return standings in string format
    public static String getStandings(){
        String standings;
        int temp;
        String tempString;
        //create string with standings, divided logically with labels
        standings =  "Two Player: P1: ";
        temp = twoPlayer.getData();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += " P2: ";
        temp = twoPlayer.getPlayer2();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += "\n";
        standings += "Three Player: P1: ";
        temp = threePlayer.getData();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += " P2: ";
        temp = threePlayer.getPlayer2();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += " P3: ";
        temp = threePlayer.getPlayer3();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += "\nFour Player: P1: ";
        temp = fourPlayer.getData();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += " P2: ";
        temp = fourPlayer.getPlayer2();
        tempString = Integer.toString(temp);
        standings += tempString;
        standings += " P3: ";
        temp = fourPlayer.getPlayer3();
        tempString= Integer.toString(temp);
        standings += tempString;
        standings += " P4: ";
        temp = fourPlayer.getPlayer4();
        tempString = Integer.toString(temp);
        standings += tempString;
        //return the created string that has all of the standings
        return standings;
    }

    //todo return max of reflex times: While loop - continuous checking
    public int max(){
        int maximum = 0;

        return maximum;
    }

    //todo return min of reflex times: While Loop - continuous checking
    public int min(){
        int minimum = 0;

        return minimum;
    }

    //todo return average of reflex times: while loop - continuous adding
    public int average(){
        int average = 0;

        return average;
    }

    //todo return median of reflex times: while loop - continuous checking
    public int median(){
        int median = 0;

        return median;
    }


    //todo all above for 10: for loops w. Error Handling
    public int tenMax(){
        int maximum = 0;

        return maximum;
    }

    public int tenMin(){
        int minimum = 0;

        return minimum;
    }

    public int tenAverage(){
        int average = 0;

        return average;
    }

    public int tenMedian(){
        int median = 0;

        return median;
    }

    //todo all above for 100: for loops w. Error Handling

    public int hundredMax(){
        int maximum = 0;

        return maximum;
    }

    public int hundredMinimum(){
        int minimum = 0;

        return minimum;
    }

    public int hundredAverage(){
        int average = 0;

        return average;
    }

    public int hundredMedian(){
        int median = 0;

        return median;
    }

}
