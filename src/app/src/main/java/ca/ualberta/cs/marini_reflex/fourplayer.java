package ca.ualberta.cs.marini_reflex;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FourPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourplayer);
        Intent masterIntent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourplayer, menu);
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

    public void player1Wins(View view){
        Intent intent = new Intent(this, BuzzerResult.class);
        DataHandler.fourPlayerP1();
        intent.putExtra("winner", 1);
        startActivity(intent);
    }

    public void player2Wins(View view){
        Intent intent = new Intent(this, BuzzerResult.class);
        DataHandler.fourPlayerP2();
        intent.putExtra("winner", 2);
        startActivity(intent);
    }

    public void player3Wins(View view){
        Intent intent = new Intent(this, BuzzerResult.class);
        DataHandler.fourPlayerP3();
        intent.putExtra("winner", 3);
        startActivity(intent);
    }

    public void player4Wins(View view){
        Intent intent = new Intent(this, BuzzerResult.class);
        DataHandler.fourPlayerP4();
        intent.putExtra("winner", 4);
        startActivity(intent);
    }
}
