package com.answerofgod.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ButtonTab extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabimage);

		setTabAction();
	}

	private void setTabAction() {

		final LinearLayout tab1 = (LinearLayout) findViewById(R.id.tab1);
		final LinearLayout tab2 = (LinearLayout) findViewById(R.id.tab2);

		ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				tab1.setVisibility(LinearLayout.VISIBLE);

				tab2.setVisibility(LinearLayout.GONE);

			}

		});

		ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				tab1.setVisibility(LinearLayout.GONE);

				tab2.setVisibility(LinearLayout.VISIBLE);

			}

		});

	}

}
