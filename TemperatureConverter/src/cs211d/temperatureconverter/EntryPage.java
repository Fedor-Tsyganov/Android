package cs211d.temperatureconverter;

//*************************************************
//Homework 4
//Author: Fedor Tsyganov
//Date: 10/03/2013
//Application name: TemperatureConverter.java
//Task: Converting temperature entered by the user in one form of
//    measurement to another form of measurement.
//    For example user enters temperature in celsius and program
//    converts it to fahrenheit.
//*************************************************
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EntryPage extends Activity
{
    Button submitButton;
    private final String PASSCODE = "HomeWork4";
    EditText userName, password;
    String usrName, pass;
	@Override
//*******************************onCreate()*************************
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.entry_page);
        userName = (EditText) findViewById (R.id.etName);
        password = (EditText) findViewById (R.id.etPassword);
        submitButton = (Button) findViewById (R.id.buttonSubmit);
        userName.setOnKeyListener(new View.OnKeyListener() 
        {
            public boolean onKey(View v, int keyCode, KeyEvent event) 
            {
                if ((event.getAction()==KeyEvent.ACTION_DOWN 
                		&& keyCode == KeyEvent.KEYCODE_TAB)
                	    ||(event.getAction()==KeyEvent.ACTION_DOWN 
                	    && keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    usrName = userName.getText().toString();
                    return true;
                }
                return false;
                }
            });
        password.setOnKeyListener(new View.OnKeyListener() 
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) 
            {
                if  ((event.getAction()==KeyEvent.ACTION_DOWN 
                        && keyCode == KeyEvent.KEYCODE_TAB)
                        ||(event.getAction()==KeyEvent.ACTION_DOWN 
                        && keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    pass = password.getText().toString();
                    if (pass.equals(PASSCODE) && pass != null && !(pass.equals("")))
                    {
                        Intent i = new Intent (EntryPage.this, TemperatureConverter.class);
                        i.putExtra("USERNAME", usrName);
                        startActivity(i);
                    }
                    if (!pass.equals(PASSCODE))
                    {
                        password.setText("");
                        mkToast("Incorrect Password");
                    }
                    return true;
                }
                return false;
            }
        });	
        submitButton.setOnClickListener(new View.OnClickListener() 
        {	
            public void onClick(View v) 
            {
                usrName = userName.getText().toString();
                pass = password.getText().toString();
                if (pass.equals(PASSCODE) && pass != null && !(pass.equals("")))
                {
                    Intent i = new Intent (EntryPage.this, TemperatureConverter.class);
                    i.putExtra("USERNAME", usrName);
                    startActivity(i);
                }
                if (!pass.equals(PASSCODE))
                {
                    password.setText("");
                    mkToast("Incorrect Password");
                }
            }
        });	
    }
    private void mkToast(String text)
    {
        //toast message that informs users
        Context con = getApplicationContext();
        Toast t = Toast.makeText(con, text, Toast.LENGTH_SHORT);
        t.show();
    }
}