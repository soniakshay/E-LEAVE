        package com.example.user.e_leave;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class signup extends Activity {
    EditText UsernameEt, PasswordEt,FirstnameEt,LastnameEt,EmailEt,MobileEt,AddressEt;
    Spinner DesignaationEt,DepartmentEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        FirstnameEt = (EditText) findViewById(R.id.etFirstname);
        LastnameEt = (EditText) findViewById(R.id.etLastName);
        EmailEt = (EditText) findViewById(R.id.etEmail);
        MobileEt = (EditText) findViewById(R.id.etMobileno);
        AddressEt = (EditText) findViewById(R.id.etAddress);
        DesignaationEt = (Spinner) findViewById(R.id.etDesignation);
        DepartmentEt =(Spinner)findViewById(R.id.etDepartment);
        new Catagorie().execute("Designation");
        new Catagorie().execute("Department");


    }

    public void OnSignUp(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String fname=FirstnameEt.getText().toString();
        String lname=LastnameEt.getText().toString();
        String email=EmailEt.getText().toString();
        String mobileno=MobileEt.getText().toString();
        String address=AddressEt.getText().toString();
        String desination=DesignaationEt.getSelectedItem().toString();
        String department=DepartmentEt.getSelectedItem().toString();
        String type = "signup";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password,fname,lname,email,mobileno,address,desination,department);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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

    private class Catagorie extends AsyncTask<String, Void, String> {
        String type;
        @Override
        protected String doInBackground(String... params) {
            type = params[0];
            if(type.equals("Designation")) {
                String designation_url = "http://16mca004.000webhostapp.com/eleave/eleave/designation.php";

                try {
                    URL url = new URL(designation_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if(type.equals("Department")) {
                String designation_url = "http://16mca004.000webhostapp.com/eleave/eleave/department.php";

                try {
                    URL url = new URL(designation_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            // Locate the spinner in activity_main.xml
            if(type.equals("Designation")) {

                Spinner s = (Spinner) findViewById(R.id.etDesignation);
                ArrayList<String> options = new ArrayList<String>();
                try {
                    JSONArray array =  new JSONArray(args);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        options.add(jsonObject.getString("Name"));
                    }
                } catch (JSONException e) {
                    // handle
                }
                 ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup.this, android.R.layout.select_dialog_item, options);
                s.setAdapter(adapter);
            }

            if(type.equals("Department")) {

                Spinner s = (Spinner) findViewById(R.id.etDepartment);
                ArrayList<String> options = new ArrayList<String>();
                try {
                    JSONArray array =  new JSONArray(args);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        options.add(jsonObject.getString("Name"));
                    }
                } catch (JSONException e) {
                    // handle
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup.this, android.R.layout.select_dialog_item, options);
                s.setAdapter(adapter);
            }
        }


    }




}





