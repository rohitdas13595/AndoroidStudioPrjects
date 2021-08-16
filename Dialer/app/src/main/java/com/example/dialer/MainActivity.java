package com.example.dialer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView lblinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edtPhoneNumber);
        lblinfo = findViewById(R.id.lblinfo);
    }
    @SuppressLint("NonConstantResourceId")
    public void buttonClickEvent(View view) {
        String phoneNo = editText.getText().toString();
        try {
            switch (view.getId()) {
                case R.id.btnOne:
                    lblinfo.setText("");
                    phoneNo += "1";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnTwo:
                    lblinfo.setText("");
                    phoneNo += "2";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnThree:
                    lblinfo.setText("");
                    phoneNo += "3";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnFour:
                    lblinfo.setText("");
                    phoneNo += "4";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnFive:
                    lblinfo.setText("");
                    phoneNo += "5";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnSix:
                    lblinfo.setText("");
                    phoneNo += "6";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnSeven:
                    lblinfo.setText("");
                    phoneNo += "7";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnEight:
                    lblinfo.setText("");
                    phoneNo += "8";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnNine:
                    lblinfo.setText("");
                    phoneNo += "9";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnZero:
                    lblinfo.setText("");
                    phoneNo += "0";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnAterisk:
                    lblinfo.setText("");
                    phoneNo += "*";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnHash:
                    lblinfo.setText("");
                    phoneNo += "#";
                    editText.setText(phoneNo);
                    break;
                case R.id.btndel:
                    lblinfo.setText("");
                    if(phoneNo !=null && phoneNo.length()>0){
                        phoneNo = phoneNo.substring(0,phoneNo.length()-1);
                    }
                    editText.setText(phoneNo);
                    break;
                case R.id.btnClearall:
                    lblinfo.setText("");
                    phoneNo ="";
                    editText.setText(phoneNo);
                    break;
                case R.id.btnCall:
                    if (phoneNo.trim().equals("")) {
                        lblinfo.setText("Please enter a number to call on!");
                    }else{
                        Boolean isHash = false;
                        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
                            Toast.makeText(getApplicationContext(),phoneNo,Toast.LENGTH_SHORT).show();
                            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                            String callInfo = "tel:" + phoneNo + Uri.encode("#");
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        }else{
                            Toast.makeText(getApplicationContext(),phoneNo,Toast.LENGTH_LONG).show();
                            String callInfo = "tel:" + phoneNo;
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        }
                    }
                    break;
                case R.id.btnSave:
                    // Creates a new Intent to insert a contact
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    // Sets the MIME type to match the Contacts Provider
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNo);
                    startActivity(intent);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        } catch (Exception ex) {
        }
    }
}