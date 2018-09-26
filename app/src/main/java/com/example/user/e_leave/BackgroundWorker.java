package com.example.user.e_leave;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by USER on 07-09-2018.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;

    }
    String type;
    String empid=null;
    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        if(type.equals("login")) {
            String login_url = "http://16mca004.000webhostapp.com/eleave/eleave/login.php";
            try {
                String user_name = params[1];
                String password = params[2];
               // String user_name = "admin";
                //String password = "admin";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        + URLEncoder.encode("pwd", "UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                JSONArray jarr;
                String s=null;

                try {
                    jarr=new JSONArray(result);
                    s=jarr.getString(0);
                    JSONObject jobj=new JSONObject(jarr.getString(1));
                    empid=jobj.getString("login_id");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SharedPreferences pref =context.getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("status", s); // Storing string
                editor.putString("empid", empid); // Storing string
                editor.commit();

                return s;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




            if(type.equals("signup")) {
            String login_url = "http://16mca004.000webhostapp.com/eleave/eleave/signup.php";

            try {
                String user_name = params[1];
                String password = params[2];
                String fname = params[3];
                String lname = params[4];
                String email = params[5];
                String mobileno = params[6];
                String address = params[7];
                String designationname = params[8];
                String departmentname = params[9];


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")
                        +"&"
                        + URLEncoder.encode("pwd", "UTF-8")+"="+URLEncoder.encode(password,"UTF-8")
                        +"&"
                        + URLEncoder.encode("fname", "UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")
                        +"&"
                        + URLEncoder.encode("lname", "UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")
                        +"&"
                        + URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        +"&"
                        + URLEncoder.encode("mobile", "UTF-8")+"="+URLEncoder.encode(mobileno,"UTF-8")
                        +"&"
                        + URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address,"UTF-8")
                        +"&"
                        + URLEncoder.encode("designationname", "UTF-8")+"="+URLEncoder.encode(designationname,"UTF-8")
                        +"&"
                        + URLEncoder.encode("departmentname", "UTF-8")+"="+URLEncoder.encode(departmentname,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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





        if(type.equals("insertleave")) {
            String login_url = "http://16mca004.000webhostapp.com/eleave/eleave/insertleave.php";

            try {
                String desc1 = params[1];
                String todate = params[2];
                String  fromdate = params[3];
                String leavetype1 = params[4];
                String empid = params[5];


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("empid","UTF-8")+"="+URLEncoder.encode(empid,"UTF-8")
                        +"&"
                        + URLEncoder.encode("leave_name", "UTF-8")+"="+URLEncoder.encode(leavetype1,"UTF-8")
                        +"&"
                        + URLEncoder.encode("from", "UTF-8")+"="+URLEncoder.encode(fromdate,"UTF-8")
                        +"&"
                        + URLEncoder.encode("to", "UTF-8")+"="+URLEncoder.encode(todate,"UTF-8")
                        +"&"
                        + URLEncoder.encode("desc", "UTF-8")+"="+URLEncoder.encode(desc1,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
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
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override

    protected void onPostExecute(String s) {
       if(type.equals("login")) {
           SharedPreferences pref = context.getSharedPreferences("MyPref", 0);
           String status = pref.getString("status", null);
           if (status.equals("true")) {

               Intent intent = new Intent(context,Home.class);
               context.startActivity(intent);

           } else {
               Toast.makeText(context, "Username Or Password Wrong", Toast.LENGTH_LONG).show();

           }
       }
       if(type.equals("signup"))
       {
           if (s.equals("true"))
           {
               Toast.makeText(context, "SuccessFully! User Created ", Toast.LENGTH_LONG).show();
               Intent intent = new Intent(context, MainActivity.class);
               context.startActivity(intent);
           } else {
               Toast.makeText(context, "Something Eroor", Toast.LENGTH_LONG).show();

           }

       }

        if(type.equals("insertleave"))
        {
            alertDialog.setMessage(s);
            alertDialog.show();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
