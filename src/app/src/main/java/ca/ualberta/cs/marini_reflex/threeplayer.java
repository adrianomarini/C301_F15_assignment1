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
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

//Three player mode of GameBuzzer

public class ThreePlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threeplayer);
        Intent masterIntent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_threeplayer, menu);
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

    //Buttons in view are set to OnClick direct to these methods
    //Depending on which button is pushed, one is called indicating the winner.
    public void player1Wins(View view){
        //prepare intent
        Intent intent = new Intent(this, BuzzerResult.class);

        //increment score
        DataHandler.threePlayerP1();

        // call result view
        intent.putExtra("winner", 1);
        startActivity(intent);
    }

    public void player2Wins(View view){
        //prepare intent
        Intent intent = new Intent(this, BuzzerResult.class);

        //increment score
        DataHandler.threePlayerP2();

        //call result view
        intent.putExtra("winner", 2);
        startActivity(intent);
    }

    public void player3Wins(View view){
        //prepare intent
        Intent intent = new Intent(this, BuzzerResult.class);

        //increment score
        DataHandler.threePlayerP3();

        //call result view
        intent.putExtra("winner", 3);
        startActivity(intent);
    }
}
