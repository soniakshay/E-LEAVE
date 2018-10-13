package com.example.user.e_leave;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Home extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String status = pref.getString("empid", null);




    }
    public void logout(View v)
    {
        SharedPreferences pref =getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("status",null); // Storing string
        editor.putString("empid", null); // Storing string
        editor.commit();

        Intent i=new Intent(Home.this,MainActivity.class);
        startActivity(i);


    }

    public void  redirecrLeaveReq(View v)
    {
        Intent i=new Intent(Home.this,leave_request.class);
        startActivity(i);

    }

    public void onBackPressed() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String status = pref.getString("empid", null);
        if(status!=null)
        {
            Toast.makeText(getApplicationContext(),"You Are Already Login!",Toast.LENGTH_LONG).show();
            Intent i=new Intent(Home.this,Home.class);
            startActivity(i);

        }

    }

    public void leaveform(View view)
    {

        Intent i=new Intent(getApplicationContext(),LeaveForm.class);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
