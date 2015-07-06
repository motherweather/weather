package com.answerofgod.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MinSun extends Activity {
	
	public static final int WEB_VIEW=1;
	/*Button jungki;
	Button jeonkuk;
	Button weather_video;
	Button sea_weather;
	Button mountain_weather;
	Button living_weather;
	Button typhoon;
	Button yellow_sand;
	Button earthquake;  */
	//ListView listView;
	
	ListView listView1;
	iconTextListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

        // 리스트뷰 객체 참조
        listView1 = (ListView) findViewById(R.id.listView1);

        // 어댑터 객체 생성
        adapter = new iconTextListAdapter(this);

		// 아이템 데이터 만들기
		Resources res = getResources();
		RelativeLayout rl=(RelativeLayout)findViewById(R.id.RelativeLayout1);
		//rl.setBackgroundColor(res.getDrawable(R.drawable.main2));
		rl.setBackgroundDrawable(res.getDrawable(R.drawable.main4));
		
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon05), "중기예보"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "전국날씨"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "날씨영상"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "바다날씨"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "산악날씨"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "생활기상"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "태풍정보"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "황사정보"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "지진정보"));
		
		//adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon05), "친구찾기 (Friends Seeker)", "300,000 다운로드", "900 원"));
		/*
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "강좌 검색", "120,000 다운로드", "900 원"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "지하철 노선도 - 서울", "4,000 다운로드", "1500 원"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "지하철 노선도 - 도쿄", "6,000 다운로드", "1500 원"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "지하철 노선도 - LA", "8,000 다운로드", "1500 원"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "지하철 노선도 - 워싱턴", "7,000 다운로드", "1500 원"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon05), "지하철 노선도 - 파리", "9,000 다운로드", "1500 원"));
		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.icon06), "지하철 노선도 - 베를린", "38,000 다운로드", "1500 원"));
*/
		// 리스트뷰에 어댑터 설정
		listView1.setAdapter(adapter);

		// 새로 정의한 리스너로 객체를 만들어 설정
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				iconTextItem curItem = (iconTextItem) adapter.getItem(position);
				String curData = curItem.getData();

			//	Toast.makeText(getApplicationContext(), "Selected : " + curData, 1000).show();
				
				if(curData=="중기예보")
				{
					String url1 = "http://m.kma.go.kr/m/forecast/forecast_02.jsp";
					Intent intent1 = new Intent(getBaseContext(), Web.class);
					intent1.putExtra("url", url1);
					startActivityForResult(intent1,WEB_VIEW);
				}
				
				else if(curData=="전국날씨")
				{
					String url2 = "http://m.kma.go.kr/m/nation/today.jsp";
					Intent intent2 = new Intent(getBaseContext(), Web.class);
					intent2.putExtra("url", url2);
					startActivityForResult(intent2,WEB_VIEW);		
				}
				
				else if(curData=="바다날씨")
				{
					String url3 = "http://m.kma.go.kr/m/image/image_04.jsp";
					Intent intent3 = new Intent(getBaseContext(), Web.class);
					intent3.putExtra("url", url3);
					startActivityForResult(intent3,WEB_VIEW);	
				}
				
				else if(curData=="산악날씨")
				{
					String url4 = "http://m.kma.go.kr/m/marine/marine_01.jsp";
					Intent intent4 = new Intent(getBaseContext(), Web.class);
					intent4.putExtra("url", url4);
					startActivityForResult(intent4,WEB_VIEW);
				}
				
				
				else if(curData=="생활기상")
				{
					String url5 = "http://m.kma.go.kr/m/mountain/mountain_01.jsp";
					Intent intent5 = new Intent(getBaseContext(), Web.class);
					intent5.putExtra("url", url5);
					startActivityForResult(intent5,WEB_VIEW);
				}
				
				else if(curData=="태풍정보")
				{
					String url6 = "http://m.kma.go.kr/m/forecast/forecast_03.jsp";
					Intent intent6 = new Intent(getBaseContext(), Web.class);
					intent6.putExtra("url", url6);
					startActivityForResult(intent6,WEB_VIEW);
				}
				
				else if(curData=="황사정보")
				{
					String url7 = "http://m.kma.go.kr/m/risk/risk_01.jsp";
					Intent intent7 = new Intent(getBaseContext(), Web.class);
					intent7.putExtra("url", url7);
					startActivityForResult(intent7,WEB_VIEW);
				}
				
				else if(curData=="지진정보")
				{
					String url8 = "http://m.kma.go.kr/m/risk/risk_02.jsp";
					Intent intent8 = new Intent(getBaseContext(), Web.class);
					intent8.putExtra("url", url8);
					startActivityForResult(intent8,WEB_VIEW);
				}
				
				else if(curData=="날씨영상")
				{
					String url9 = "http://m.kma.go.kr/m/risk/risk_03.jsp";
					Intent intent9 = new Intent(getBaseContext(), Web.class);
					intent9.putExtra("url", url9);
					startActivityForResult(intent9,WEB_VIEW);
				}
				
				////////////////////////////////////
				
				
			}
			
		});
		
	}

/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public void onCreate(Bundle savedInsatanceState) {
		super.onCreate(savedInsatanceState);
		//startActivity(new Intent(this, SplashActivity.class));
		setContentView(R.layout.oms);

		
	//	listView=(ListView) findViewById(R.id.);
		jungki = (Button) findViewById(R.id.jungki);
		jeonkuk = (Button) findViewById(R.id.jeonkuk);
		weather_video = (Button) findViewById(R.id.weather_video);
		sea_weather = (Button) findViewById(R.id.sea_weather);
		mountain_weather = (Button) findViewById(R.id.mountain_weather);
		living_weather = (Button) findViewById(R.id.living_weather);
		typhoon = (Button) findViewById(R.id.typhoon);
		yellow_sand = (Button) findViewById(R.id.yellow_sand);
		earthquake = (Button) findViewById(R.id.earthquake);

		setClick();

	}

	private void setClick() {
		jungki.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url1 = "http://m.kma.go.kr/m/forecast/forecast_02.jsp";
				Intent intent1 = new Intent(getBaseContext(), Web.class);
				intent1.putExtra("url", url1);
				startActivityForResult(intent1,WEB_VIEW);
			}

		});
		jeonkuk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url2 = "http://m.kma.go.kr/m/nation/today.jsp";
				Intent intent2 = new Intent(getBaseContext(), Web.class);
				intent2.putExtra("url", url2);
				startActivityForResult(intent2,WEB_VIEW);
			}

		});
		weather_video.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url3 = "http://m.kma.go.kr/m/image/image_04.jsp";
				Intent intent3 = new Intent(getBaseContext(), Web.class);
				intent3.putExtra("url", url3);
				startActivityForResult(intent3,WEB_VIEW);
			}

		});
		sea_weather.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url4 = "http://m.kma.go.kr/m/marine/marine_01.jsp";
				Intent intent4 = new Intent(getBaseContext(), Web.class);
				intent4.putExtra("url", url4);
				startActivityForResult(intent4,WEB_VIEW);
			}

		});
		mountain_weather.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url5 = "http://m.kma.go.kr/m/mountain/mountain_01.jsp";
				Intent intent5 = new Intent(getBaseContext(), Web.class);
				intent5.putExtra("url", url5);
				startActivityForResult(intent5,WEB_VIEW);
			}

		});
		living_weather.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url6 = "http://m.kma.go.kr/m/forecast/forecast_03.jsp";
				Intent intent6 = new Intent(getBaseContext(), Web.class);
				intent6.putExtra("url", url6);
				startActivityForResult(intent6,WEB_VIEW);
			}

		});
		typhoon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url7 = "http://m.kma.go.kr/m/risk/risk_01.jsp";
				Intent intent7 = new Intent(getBaseContext(), Web.class);
				intent7.putExtra("url", url7);
				startActivityForResult(intent7,WEB_VIEW);
			}

		});
		yellow_sand.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url8 = "http://m.kma.go.kr/m/risk/risk_02.jsp";
				Intent intent8 = new Intent(getBaseContext(), Web.class);
				intent8.putExtra("url", url8);
				startActivityForResult(intent8,WEB_VIEW);
			}

		});
		earthquake.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { // TODO Auto-generated method
				String url9 = "http://m.kma.go.kr/m/risk/risk_03.jsp";
				Intent intent9 = new Intent(getBaseContext(), Web.class);
				intent9.putExtra("url", url9);
				startActivityForResult(intent9,WEB_VIEW);
			}

		});

	}

}
	
	
	
	*/