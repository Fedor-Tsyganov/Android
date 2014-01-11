//*************************************************
// Homework 4
// Author: Fedor Tsyganov
// Date: 10/03/2013
// Application name: TemperatureConverter.java
// Task: Converting temperature entered by the user in one form of
//       measurement to another form of measurement.
//       For example user enters temperature in celsius and program
//       converts it to fahrenheit.
//*************************************************
package cs211d.temperatureconverter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

public class TemperatureConverter extends Activity 
{
	private Button btnGO;
	private RadioGroup radioGroup1;
	private RadioButton radioBtnCtoF, radioBtnFtoC;
	private EditText enterData, showData;
	private double inputNum = 0, endNum;
	String result;
//*******************************onCreate()*************************
	@Override
	protected void onCreate(Bundle bun) 
	{
		super.onCreate(bun);
		setContentView(R.layout.activity_temperature_converter);
		initialize();
        Intent i = this.getIntent();  //adding Intent, in order to receive data from EntryPage class 
        Bundle nameBundle = i.getExtras(); //adding Bundle that carries data from EntryPage class
        String usrName = nameBundle.getString("USERNAME"); 
        setTitle("Welcome to Temperature Converter " +usrName); //changing Title to display name of the user
		//setting up onCheckedChangeListener, so we will perform different actions
		//in different cases.
		radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup arg0, int id) 
			{
				switch(id)
				{
				case R.id.radioButton1:
					btnAction(1);
					//toast message will pop up in order to notify user
					mkToast("Converting temperature in celsius to fahrenheit");
					break;
				case R.id.radioButton2:
					btnAction(2);
					//toast message will pop up in order to notify user
					mkToast("Converting temperature in fahrenheit to celsius");
					break;
				}
			}	
		});
	}
//*********************************initialize()*********************
	private void initialize() 
	{
		//initializing  radio buttons, edit texts, and a button
		radioBtnCtoF = (RadioButton) findViewById (R.id.radioButton1);
		radioBtnFtoC = (RadioButton) findViewById (R.id.radioButton2);
		radioGroup1  = (RadioGroup)  findViewById(R.id.radioGrpTempConverter);
		enterData    = (EditText)    findViewById(R.id.editText1);
		showData     = (EditText)    findViewById(R.id.editText2);
		btnGO        = (Button)      findViewById(R.id.buttonGO);
		//setting second edit text field to show only a result of calculation
		//users will not be able to edit text in this field
		showData.setKeyListener(null);
	}
//*************************onCreateOptionsMenu()*****************
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temperature_converter, menu);
		return true;
	}
//***************************formulaA()****************************
//formula to Convert Celsius to Fahrenheit
	private double formulaA (double celsius)
	{
		double convert = celsius*9/5+32;
		return convert;
	}
//****************************formulaB()****************************
//formula to Convert Fahrenheit to Celsius
	private double formulaB (double farenheight)
	{
		double convert = (farenheight-32)*5/9;
		return convert;
	}
//****************************mkToast()******************************
	private void mkToast(String text)
	{
		//toast message that informs users
		Context con = getApplicationContext();
		Toast t = Toast.makeText(con, text, Toast.LENGTH_SHORT);
		t.show();
	}
//********************************btnAction()************************
	private void btnAction( int formulaNumber)
	{
		if (formulaNumber == 1)
		{
			btnGO.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					switch(v.getId())
					{
					case R.id.buttonGO:
						inputNum = Double.parseDouble(enterData.getText().toString());	
						endNum = formulaA(inputNum);
						//converting endNum to String and using floating point number, so showing only one symbol after a dot
						showData.setText(String.format("%.1f", endNum) + " F");
						break;
					}
				}
			});
		}
		if (formulaNumber == 2)
		{
			btnGO.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v) 
				{
					switch(v.getId())
					{
					case R.id.buttonGO:
						inputNum = Double.parseDouble(enterData.getText().toString());	
						endNum = formulaB(inputNum);
						showData.setText(String.format("%.1f", endNum) + " C");
						break;
					}
				}
			});
		}
	}
}