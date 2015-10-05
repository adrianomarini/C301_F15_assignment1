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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BuzzerResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_result);
        Intent intent = getIntent();
        int winner = intent.getIntExtra("winner",0);
        if(winner == 0){
            finishActivity(0);
        }
        if(winner == 1){
            player1();
        }
        if(winner == 2){
            player2();
        }
        if(winner == 3){
            player3();
        }
        if(winner == 4){
            player4();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer_result, menu);
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

    //Strings to display Text View to show winner

    public void player1(){
        TextView winner = new TextView(this);
        winner.setTextSize(80);
        winner.setText(R.string.P1);
        winner.setGravity(Gravity.CENTER);
        setContentView(winner);
    }

    public void player2(){
        TextView winner = new TextView(this);
        winner.setTextSize(80);
        winner.setText(R.string.P2);
        winner.setGravity(Gravity.CENTER);
        setContentView(winner);
    }

    public void player3(){
        TextView winner = new TextView(this);
        winner.setTextSize(80);
        winner.setText(R.string.P3);
        winner.setGravity(Gravity.CENTER);
        setContentView(winner);
    }

    public void player4(){
        TextView winner = new TextView(this);
        winner.setTextSize(80);
        winner.setText(R.string.P4);
        winner.setGravity(Gravity.CENTER);
        setContentView(winner);
    }
}
