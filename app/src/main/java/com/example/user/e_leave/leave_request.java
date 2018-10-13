package com.example.user.e_leave;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;

public class leave_request extends Activity {
     ListView llistview;
     customleaveadeptor ladeptor;
     List<Leave> llleave;
    public static TextView f;
     public void  popid(String s)
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String status = pref.getString("empid", null);
        MyTask backgroundWorker = new MyTask(this);
        backgroundWorker.execute("list", status);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leave_request, menu);
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


    public class MyTask extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;

        MyTask(Context ctx) {
            context = ctx;
        }
        String type;
        @Override
        protected String doInBackground(String... params) {
             type = params[0];
            if (type.equals("list")) {
                String login_url = "http://16mca004.000webhostapp.com/eleave/eleave/notification.php";

                try {
                    String user_name = params[1];
                    // String user_name = "admin";
                    //String password = "admin";
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("emp_id","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
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



            if(type.equals("leaveupdate")) {
                String url1 = "http://16mca004.000webhostapp.com/eleave/eleave/updatestatus.php";
                try {
                    String user_name = params[1];
                    // String user_name = "admin";
                    //String password = "admin";
                    URL url = new URL(url1);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("leave_id","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
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


                    return s;
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
        protected void onPostExecute(String result) {
            if (type.equals("list")) {
                 String s = "";
                llistview = (ListView) findViewById(R.id.tvlist);
                llleave = new ArrayList<>();
                String leaveid, name, desc;
                try {

                    JSONArray jobj = new JSONArray(result);
                    for (int i = 0; i < jobj.length(); i++) {
                        JSONArray jsonArray1 = new JSONArray(jobj.getString(i));
                        leaveid = jsonArray1.getString(0);
                        name = jsonArray1.getString(1);
                        desc = jsonArray1.getString(2);
                        llleave.add(new Leave(i, name, desc, leaveid));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ladeptor = new customleaveadeptor(getApplicationContext(), llleave);
                llistview.setAdapter(ladeptor);
            }

                if (type.equals("leaveupdate")) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                    String status = pref.getString("empid", null);
                    MyTask backgroundWorker = new MyTask(leave_request.this);
                    backgroundWorker.execute("list", status);
                    ladeptor.notifyDataSetChanged();
                    Toast.makeText(context,"Leave Accept Sucessfully!",Toast.LENGTH_LONG).show();


                }




            }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }





    public class customleaveadeptor extends BaseAdapter {
        private Context lcontext;
        private List<Leave> llist;
        public customleaveadeptor(Context lcontext, List<Leave> llist) {
            this.lcontext = lcontext;
            this.llist = llist;
        }

        @Override
        public int getCount() {
            return llist.size();
        }

        @Override
        public Object getItem(int position) {
            return llist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public void  popid(String s)
        {

        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            View v=View.inflate(lcontext,R.layout.cutomleavelayout,null);
            TextView tname=(TextView)v.findViewById(R.id.tvname);
            TextView tprice=(TextView)v.findViewById(R.id.tvprice);
            TextView tdec=(TextView)v.findViewById((R.id.tvdec));
            final Button tbtn=(Button)v.findViewById(R.id.tvbtn);

            tbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyTask backgroundWorker = new MyTask(leave_request.this);
                    backgroundWorker.execute("leaveupdate",llist.get(position).getDesc());



                }
            });
            tname.setText(llist.get(position).getName());
            tprice.setText(llist.get(position).getLeaveid());
            tdec.setText(llist.get(position).getDesc());

            v.setTag(llist.get(position).getId());
            return v;


        }
    }


















}
























