package com.tawary.donationsmsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {
  EditText Donator,HeadNo,Amount,Sender_Key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Donator = (EditText)findViewById(R.id.DonatorNo);
        HeadNo = (EditText)findViewById(R.id.HeadNo);
        Amount = (EditText)findViewById(R.id.Amount);




    }

    public void send_sms(View view) {
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissioncheck == PackageManager.PERMISSION_GRANTED){
            MyMessage();

        }else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);


        }

    }

    private void MyMessage() {

          String dpn = Donator.getText().toString().trim();
          String Message = " Daim Al Hazoori Islamic Foundation ";
          String  hpn = HeadNo.getText().toString().trim();
          String  amt = Amount.getText().toString().trim();


          if(!Donator.getText().toString().equals("")||!HeadNo.getText().toString().equals("")||!Amount.getText().toString().equals("")) {
              SmsManager smsManger = SmsManager.getDefault();
              String greetings = "Thanks For Your Donation Your Amount : ";
              String amount = amt+" Rs Received By ";
              String DonatorMessage = greetings+amount+Message;
              String HeadMessage = "Dear Administration I Have Received Donation Amount  :"+amount +"Donator Mobile No : "+dpn+" For "+ Message;
              smsManger.sendTextMessage(dpn, null, DonatorMessage, null, null);
              smsManger.sendTextMessage(hpn, null, HeadMessage, null, null);
              Toast.makeText(this, "Message Send SuccessFully ", Toast.LENGTH_SHORT).show();
              Donator.setText("");
              Amount.setText("");

          }else {

              Toast.makeText(this, "Please Enter Number or Message  ", Toast.LENGTH_SHORT).show();
          }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
              if(grantResults.length>=0 && grantResults[0]==getPackageManager().PERMISSION_GRANTED){

               MyMessage();

              }else {



                  Toast.makeText(this, "SMS Permission Granted UnSuccessFull ", Toast.LENGTH_SHORT).show();
              }

               break;

        }

    }
}