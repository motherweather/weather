
package com.answerofgod.weather;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class board_activity extends Activity {
	
	private final String SERVER_ADDRESS ="http://128.134.52.62:10001"; //���� �ּ�(php������ ����Ǿ��ִ� ��α���, ����� 127.0.0.1�̳� localhost�� ���� �ȵȴ�!!)
	Handler YHandler;
	static EditText edtid;
	static EditText edtdate;
	static Button btninsert;

	static String content;
	static String date;
	static ListView list;
	static ArrayList<String> data;
	static ArrayAdapter<String> adapter;
	static Person P1;
	static ArrayList<Person> m_orders = new ArrayList<Person>();
	static	PersonAdapter m_adapter;
	static Timer mTimer;
	StringBuilder time;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

     
         time=new StringBuilder();			//�Խ��ǿ� ��� ��¥�� �Է��ϱ� ���� time ��ü ����
        Calendar cal=new GregorianCalendar();
        
        time.append(String.format("%d.%d.%d, %d:%d",
        		cal.get(Calendar.YEAR),
        		cal.get(Calendar.MONTH)+1,
        		cal.get(Calendar.DAY_OF_MONTH),
        		cal.get(Calendar.HOUR_OF_DAY),
        		cal.get(Calendar.MINUTE)));
        
        //String aa;
        //aa=time.toString();
    //    Toast.makeText(getApplicationContext(),aa, Toast.LENGTH_SHORT).show();
      
        edtid = (EditText )findViewById(R.id.editText1);
        edtdate = (EditText )findViewById(R.id.editText2);
        btninsert = (Button )findViewById(R.id.button1);
     
        
        list = (ListView )findViewById(R.id.listView1);
        //data = new ArrayList<String>();
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
        m_adapter = new PersonAdapter(this, R.layout.row, m_orders);
        list.setAdapter(m_adapter);
        
        
       
       
        btninsert.setOnClickListener(new View.OnClickListener() { //�Է� ��ư�� ������ ��
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(edtid.getText().toString().equals("")) { //�̸��̳� �����߿� �ϳ��� �Է��� �ȵ�������
					Toast.makeText(board_activity.this,
							"������ �Է��ϼ���!", Toast.LENGTH_SHORT).show();
					return;
				}
					
					runOnUiThread(new Runnable() {
						
						public void run() {
							// TODO Auto-generated method stub
							content = edtid.getText().toString();
							date = time.toString();
							
							try {
								URL url = new URL(SERVER_ADDRESS + "/Boardinsert.php?"
										+ "content=" + URLEncoder.encode(content, "UTF-8")
										+ "&date=" + URLEncoder.encode(date, "UTF-8")); //�������� UTF-8�� ���ڵ��ϱ� ���� URLEncoder�� �̿��Ͽ� ���ڵ���
								url.openStream(); //������ DB�� �Է��ϱ� ���� �������� insert.php���Ͽ� �Էµ� �̸��� ������ �ѱ�
								
								String result = getXmlData("Boardinsertresult.xml", "result"); //�Է� ��������
								
								if(result.equals("1")) { //result �±װ��� 1�϶� ����
									Toast.makeText(board_activity.this,
											"DB insert ����", Toast.LENGTH_SHORT).show();
									
									edtid.setText("");
								//	edtdate.setText("");
								}
								else //result �±װ��� 1�� �ƴҶ� ����
									Toast.makeText(board_activity.this,
											"DB insert ����", Toast.LENGTH_SHORT).show();
							} catch(Exception e) {
								Log.e("Error", e.getMessage());
							}
						}
					});
				}
			}); 
        
        
        
        updateListview();
        

		
		Runnable mTimerTask;
		
		YHandler=new Handler();
		mTimerTask=new Runnable(){
			public void run(){
				m_adapter.notifyDataSetChanged();
				updateListview();
				YHandler.postDelayed(this, 10000);
			}
		};
		
		YHandler.removeCallbacks(mTimerTask);
		YHandler.postDelayed(mTimerTask, 1000);
        
        
        
   	}
			
	
	public void updateListview(){	
		// TODO Auto-generated method stub
		final Handler handler = new Handler();
		runOnUiThread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				//final ProgressDialog dialog = ProgressDialog.show(Android_BookActivity.this,"�ҷ�������.....", "��ø� ��ٷ��ּ���.");
				
				handler.post(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						try {
							
							//m_adapter.clear();
							//data.clear(); //�ݺ������� ������� �Ȱ��� ���� ������ ���� �����ϱ� ���� data�� Ŭ������
							URL url = new URL(SERVER_ADDRESS + "/Boardsearch.php");
							url.openStream(); //������ serarch.php������ ������
				
							ArrayList<String> contentlist = getXmlDataList("Boardsearchresult.xml", "content");//name �±װ��� �о� namelist ����Ʈ�� ����
							ArrayList<String> datelist = getXmlDataList("Boardsearchresult.xml", "date"); //price �±װ��� �о� prica ����Ʈ�� ����
							
							if(contentlist.isEmpty()){
								//data.add("�ƹ��͵� �˻����� �ʾҽ��ϴ�.");
								//P1.add("�ƹ��͵� �˻� ���� �ʾҽ��ϴ�");
								
								}
							else {
								m_orders.clear();
								for(int i = contentlist.size()-1; i>=0; i--) {
									//String str = namelist.get(i) + " - " + pricelist.get(i);
									Person p1=new Person(contentlist.get(i),datelist.get(i));
									m_orders.add(p1);
									//data.add(str);
									//onResume();
								}
							}
						} catch(Exception e) {
							Log.e("Error", e.getMessage());
						} finally{
						//	dialog.dismiss();
						//	adapter.notifyDataSetChanged();
							m_adapter.dataChange();
							
						}
					}
				});
			}
			
		});
		
	
	

	}
        
 

   
    
    	
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	// updateListview();
    	m_adapter.notifyDataSetChanged();
    	//	MainTimerTask timerTask=new MainTimerTask();
    	//mTimer=new Timer();
    //	mTimer.schedule(timerTask,1000,100);
    }
   
    

    
    
    private class PersonAdapter extends ArrayAdapter<Person> {

        private ArrayList<Person> items;

        public PersonAdapter(Context context, int textViewResourceId, ArrayList<Person> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.row, null);
                }
                Person p = items.get(position);
                if (p != null) {
                        TextView tt = (TextView) v.findViewById(R.id.toptext);
                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                        if (tt != null){
                        	tt.setText("�ۼ���:" + p.getName());                            
                        }
                        if(bt != null){
                        		bt.setText(p.getNumber());
                        }
                }
                return v;
        }
        
        public void remove(int position){
        	m_adapter.remove(position);
        	dataChange();
        }
        
        public void dataChange(){
        	m_adapter.notifyDataSetChanged();
        }
    }
    
    
    class MainTimerTask extends TimerTask{
    	public void run(){
    	//	mHandler.post(mUpdateTimeTask); 
    	}
    }
    
    
    class Person {
        
        private String Name;
        private String Number;
        
        public Person(String _Name, String _Number){
        	this.Name = _Name;
        	this.Number = _Number;
        }
        
        public String getName() {
            return Name;
        }

        public String getNumber() {
            return Number;
        }

    }
    
    
	private String getXmlData(String filename, String str) { //�±װ� �ϳ��� �޾ƿ������� String�� �Լ�
		String rss = SERVER_ADDRESS + "/";
		String ret = "";
		
		try { //XML �Ľ��� ���� ����
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");
			
			int eventType = xpp.getEventType();
			
			while(eventType != XmlPullParser.END_DOCUMENT) {
				if(eventType == XmlPullParser.START_TAG) {
					if(xpp.getName().equals(str)) { //�±� �̸��� str ���ڰ��� ���� ���
						ret = xpp.nextText();
					}
				}
				eventType = xpp.next();
			}
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		return ret;
	}
    
	
	private ArrayList<String> getXmlDataList(String filename, String str) { //�±װ� �������� �޾ƿ������� ArrayList<String>�� ����
		String rss = SERVER_ADDRESS + "/";
		ArrayList<String> ret = new ArrayList<String>();
		
		try { //XML �Ľ��� ���� ����
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");
			
			int eventType = xpp.getEventType();
			
			while(eventType != XmlPullParser.END_DOCUMENT) {
				if(eventType == XmlPullParser.START_TAG) {
					if(xpp.getName().equals(str)) { //�±� �̸��� str ���ڰ��� ���� ���
						ret.add(xpp.nextText());
					}
				}
				eventType = xpp.next();
			}
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		return ret;
	}
	
}
