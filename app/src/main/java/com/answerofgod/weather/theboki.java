package com.answerofgod.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class theboki extends Activity {
	
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

        // ����Ʈ�� ��ü ����
        listView1 = (ListView) findViewById(R.id.listView1);

        // ����� ��ü ����
        adapter = new iconTextListAdapter(this);

		// ������ ������ �����
		Resources res = getResources();
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon05), "�α���"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "�˸�����"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "��������"));
		adapter.addItem(new iconTextItem(res.getDrawable(R.drawable.icon06), "������"));
		
				listView1.setAdapter(adapter);

		// ���� ������ �����ʷ� ��ü�� ����� ����
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				iconTextItem curItem = (iconTextItem) adapter.getItem(position);
				String curData = curItem.getData();

			//	Toast.makeText(getApplicationContext(), "Selected : " + curData, 1000).show();
				
				if(curData=="�α���")
				{
					
					Intent intent1 = new Intent(getBaseContext(), Android_BookActivity.class);
					intent1.addFlags(intent1.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent1);
				}
				
				else if(curData=="�˸�����")
				{
					String url2 = "http://m.kma.go.kr/m/nation/today.jsp";
					Intent intent2 = new Intent(getBaseContext(), Web.class);
					intent2.putExtra("url", url2);
					startActivityForResult(intent2,WEB_VIEW);		
				}
				
				else if(curData=="��������")
				{
					String url3 = "http://m.kma.go.kr/m/image/image_04.jsp";
					Intent intent3 = new Intent(getBaseContext(), Web.class);
					intent3.putExtra("url", url3);
					startActivityForResult(intent3,WEB_VIEW);	
				}
				
				else if(curData=="������")
				{
					String url4 = "http://m.kma.go.kr/m/marine/marine_01.jsp";
					Intent intent4 = new Intent(getBaseContext(), Web.class);
					intent4.putExtra("url", url4);
					startActivityForResult(intent4,WEB_VIEW);
				}
				
				
				else if(curData=="��Ȱ���")
				{
					String url5 = "http://m.kma.go.kr/m/mountain/mountain_01.jsp";
					Intent intent5 = new Intent(getBaseContext(), Web.class);
					intent5.putExtra("url", url5);
					startActivityForResult(intent5,WEB_VIEW);
				}
				
				else if(curData=="��ǳ����")
				{
					String url6 = "http://m.kma.go.kr/m/forecast/forecast_03.jsp";
					Intent intent6 = new Intent(getBaseContext(), Web.class);
					intent6.putExtra("url", url6);
					startActivityForResult(intent6,WEB_VIEW);
				}
				
				else if(curData=="Ȳ������")
				{
					String url7 = "http://m.kma.go.kr/m/risk/risk_01.jsp";
					Intent intent7 = new Intent(getBaseContext(), Web.class);
					intent7.putExtra("url", url7);
					startActivityForResult(intent7,WEB_VIEW);
				}
				
				else if(curData=="��������")
				{
					String url8 = "http://m.kma.go.kr/m/risk/risk_02.jsp";
					Intent intent8 = new Intent(getBaseContext(), Web.class);
					intent8.putExtra("url", url8);
					startActivityForResult(intent8,WEB_VIEW);
				}
				
				else if(curData=="��������")
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
}
