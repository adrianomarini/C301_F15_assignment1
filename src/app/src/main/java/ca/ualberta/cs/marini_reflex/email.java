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
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//emails the standings to a person.

public class Email extends AppCompatActivity {

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Intent masterIntent = getIntent();

        //watch for button clicks
        Button send = (Button) findViewById(R.id.email_Button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Portions of this page are modifications based on work created and
                // shared by the Android Open Source Project and used according to
                // terms described in the Creative Commons 2.5 Attribution License.
                //Accessed 04.10.2015 | Modified by Adriano Marini
                //http://developer.android.com/guide/components/intents-common.html#Email

                //create the email message
                String email = new StringBuilder().append("Reflex Statistics: \n")
                        .append(DataHandler.getReflexStats()).append("\n\nBuzzer Statistics: \n")
                        .append(DataHandler.getStandings()).toString();

                //create and send the intent to the system
                Intent sender = new Intent(Intent.ACTION_SENDTO);
                sender.setType("*/*");
                sender.setData(Uri.parse("mailto:"));
                sender.putExtra(sender.EXTRA_SUBJECT, "Reflex Standings");
                sender.putExtra(sender.EXTRA_TEXT, email);
                startActivity(Intent.createChooser(sender, "email"));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_email, menu);
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
}
