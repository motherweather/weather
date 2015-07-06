package com.answerofgod.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class argumentedreality extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.argumentreality);

			Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_SHORT).show();
			
			Button aa=(Button)findViewById(R.id.button1);
			
			
			aa.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v)
				{
					
					Intent intent=new Intent(argumentedreality.this,WeatherPage.class);
					intent.addFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent);
				}
			});
	}
}
			
	
