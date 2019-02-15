package com.zexteam.pwnage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class PwnageLoader extends AppCompatActivity {

    public void setPwnage (Context context){
        Toast.makeText(context, "ALERT!", Toast.LENGTH_LONG).show();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("ALERT!");
        LinearLayout diagLayout = new LinearLayout(context);
        diagLayout.setOrientation(LinearLayout.VERTICAL);
        diagLayout.setBackgroundColor(Color.RED);
        TextView text = new TextView(context);
        text.setText("Your\nDevice\nHave\nBeen\nPWNED");
        text.setPadding(10, 10, 10, 10);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        diagLayout.addView(text);
        alertDialog.setView(diagLayout);
        alertDialog.show();

    }
}
