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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//Activity to practice for GameBuzzer superiority
// Tests reflex time to press a button

public class ReflexTesting extends AppCompatActivity {

    //for the spec of the game, timer for delay before prompted to tap button.
    public CountDownTimer countDown;

    //close activity when done.
    @Override
    public void onBackPressed(){
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex_testing);

        //initialize TextViews for Usability
        TextView prompt = (TextView) findViewById(R.id.prompt_textView);
        TextView error = (TextView) findViewById(R.id.error_textView);

        Intent masterIntent = getIntent();

        //Make prompt and complaining error invisible at beginning.
        prompt.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);

        //Display Alert Dialog (c) by David Hedlund
        //  Display Alert Dialog is licensed under a
        //  Creative Commons Attribution-ShareAlike 3.0 Unported License.
        //  <http://creativecommons.org/licenses/by-sa/3.0/>.
        //http://stackoverflow.com/questions/2115758/how-to-display-
        // alert-dialog-in-android
        // Accessed: 04.10.2015 | Modified by Adriano Marini

        //Display a dialog for instructions and to start the game.

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(R.string.dialog_string);
        builder1.setCancelable(true);
        //if the user clicks the button, the game begins.
        builder1.setPositiveButton(R.string.ok_dialog,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        reflexGame();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_reflex_testing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //The main reflex testing game.
    public void reflexGame() {
        //prepare textviews for usage.
        final TextView prompt = (TextView) findViewById(R.id.prompt_textView);
        final TextView error = (TextView) findViewById(R.id.error_textView);
        final Button mainButton = (Button) findViewById(R.id.button);

        //http://stackoverflow.com/questions/5887709/getting
        // -random-numbers-in-java
        // Get the random number. Quick reference to the above
        // regarding how to use Random()
        Random rand = new Random();
        int delay = rand.nextInt(2000) + 10;

        //http://developer.android.com/reference/android/os
        // /CountDownTimer.html
        // Quick reference to the above about how to use
        // CountDownTimers properly
        //after the user begins, the game counts down from a random
        // value and then prompts the user to click the button.
        countDown = new CountDownTimer(delay, 1) {
            public void onTick(long millis) {
                mainButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //watching for click before the timer is up
                        //if click, complain and restart.
                        error.setVisibility(View.VISIBLE);
                        countDown.cancel();
                        reflexGame();
                    }
                });
            }

            public void onFinish() {
                //when the timer is finished, prompt user to tap.
                prompt.setVisibility(View.VISIBLE);
                //begin timing
                final long startTime;
                //http://docs.oracle.com/javase/7/docs/api/java
                // /lang/System.html - nanoTime()
                startTime = System.nanoTime();
                mainButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //after tap, end timing and calculate the delay
                        long endTime = System.nanoTime();
                        long totalTime = endTime - startTime;

                        //convert time (c) by Shawn Vader
                        //  convert time is licensed under a
                        //  Creative Commons Attribution-ShareAlike 3.0 Unported License.
                        //  <http://creativecommons.org/licenses/by-sa/3.0/>.
                        // Accessed: 04.10.2015 | Modified by Adriano Marini
                        //http://stackoverflow.com/questions/4300653/conversion-
                        // of-nanoseconds-to-milliseconds-and-nanoseconds-999999-in-java
                        long totalTimeNS = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
                        //end attribution

                        int finalTime = (int) totalTimeNS;

                        //reset
                        prompt.setVisibility(View.INVISIBLE);
                        error.setVisibility(View.INVISIBLE);

                        //add stats to list and repeat.
                        DataHandler.addReflexTime(finalTime);
                        reflexGame();
                    }

                });
            }
        }.start();
    }
}