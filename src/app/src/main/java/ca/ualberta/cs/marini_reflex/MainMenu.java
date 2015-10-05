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
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    //http://stackoverflow.com/questions/3625837/android-what-is-
    // wrong-with-openfileoutput
    //Original Author: Naikus | Accessed: 03.10.2015
    //http://stackoverflow.com/questions/2785670/best-way-to-get-
    // an-application-context-into-a-static-method-in-android
    //Original Author: gjpc | Accessed: 04.10.2015
    //  Adapted use of Context from the top answer: No code copied directly
    public static Context baseContext;

    @Override
    public void onBackPressed(){
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //instantiate the data handler class
        DataHandler dataHandler = new DataHandler();
        dataHandler.init(getApplicationContext());
        //get context for use in the DataHandler later on.
        baseContext = getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    //Takes button presses on the menu and sends to appropriate activities
    public void reflexTesting(View view){
        Intent intent = new Intent(this, ReflexTesting.class);
        startActivity(intent);
    }

    public void gameBuzzer(View view){
        Intent intent = new Intent(this, GameBuzzer.class);
        startActivity(intent);
    }

    public void statistics(View view){
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }
}
