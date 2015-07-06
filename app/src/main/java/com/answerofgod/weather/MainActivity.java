package com.  answerofgod.weather;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

//import com.answerofgot.weather.etc.BkUtils;

public class MainActivity extends Activity {
	public network_thread aa=new network_thread();
	public  boolean isRun=true;
	//public static int flag=-1;
	static int SpinnerFlag=-1;
	private final int MAX = 10;
	private int mPrevPosition; // 이전에 선택되었던 포지션 값
	public int type=0;
	private Button mAddView; // 뷰 페이저에 아이템 추가하는 버튼
	// private Spinner mViewTypeSpinner; //뷰 페이저에 추가할 아이템의 타입을 고르는 스피너
	private ViewPager mPager; // 뷰 페이저
	private LinearLayout mPageMark; // 현재 몇 페이지 인지 나타내는 뷰
	private BkPagerAdapter mAdapter; // 아답터 객체. 아이템을 추가 하기 위해 변수 선언
	Intent intent2;
	//SpinnerActivity SA = new SpinnerActivity[5];
	
	static SpinnerActivity[] SA=new SpinnerActivity[10];
	
	
	MyLocationListener listener;
	LocationManager manager;
	 static listWeatherView listadapter; // 날씨정보를 뿌려주는 리스트뷰용 어댑터
	 static listWeatherView listadapter2;
	static listWeatherView listadapter3;
	static listWeatherView listadapter4;
	static listWeatherView listadapter5;
	static listWeatherView listadapter6;
	static listWeatherView listadapter7;
	static listWeatherView listadapter8;
	static listWeatherView listadapter9;
	// ArrayAdapter<String> sidoAdapter; //시도 정보를 뿌려주는 스피너용 어댑터
	// ArrayAdapter<String> gugunAdapter; //구군 정보를 뿌려주는 스피너용 어댑터
	// ArrayAdapter<String> dongAdapter; //동면 정보를 뿌려주는 스피너용 어댑터
	// Spinner sidoSpinner; //시도스피너
	// Spinner gugunSpinner; //구군스피너
	// Spinner dongSpinner; //동면스피너
	Button getBtn; // 날씨 가져오는 버튼
	Button gpsBtn;
	TextView text; // 날씨지역및 발표시각정보
	// ListView listView1; //날씨정보를 뿌려줄 리스트뷰
	static GridView gridView1;
	static GridView gridView2;
	// String tempDong="4215025000"; //기본dongcode
	// String sCategory; //동네
	String sTm; // 발표시각
	String[] sHour; // 예보시간(총 15개정도 받아옴 3일*5번)
	String[] sDay; // 날짜(몇번째날??)
	String[] sTemp; // 현재온도
	String[] sWdKor; // 풍향
	String[] sReh; // 습도
	String[] sWfKor; // 날씨
	// DB용 변수
	/*
	 * String [] sidonum; //시도 코드 String [] Nsidonum; //이건 구군table에서 가져오는 코드
	 * String [] sidoname; //시도 이름 String [] gugunnum; //구군 코드 String []
	 * Ngugunnum;//동네 table에서 가져온 구군 코드 String [] gugunname;//구군 이름 String []
	 * dongnum; //동 코드 String [] dongname; //동 이름 String [] gridx; //x좌표 String
	 * [] gridy; //y좌표 String [] id; //id String [] sLong_name; //gps로 지오코딩후 주소를
	 * 파서해서 저장할 변수 double latitude,longitutde; //위도와 경도를 저장할 변수 static
	 * SQLiteDatabase db; //디비
	 */
	
	int data = 0; // 이건 파싱해서 array로 넣을때 번지
	int geodata = 0; // 지오코딩용 파서 array 번지
	boolean updated; // 이건 날씨정보 뿌리기위한 플래그
	boolean bCategory; // 여긴 저장을 위한 플래그들
	boolean bTm;
	boolean bHour;
	boolean bDay;
	boolean bTemp;
	boolean bWdKor;
	boolean bReh;
	boolean bItem;
	boolean bWfKor;
	boolean bLong_name;
	boolean tCategory; // 이건 text로 뿌리기위한 플래그
	boolean tTm;
	boolean tItem;
	String dbFile = "weatherdb.db3";
	String dbFolder = "/data/data/com.answerofgod.weather/datebases/";
	static SQLiteDatabase db;

	Handler MainHandler;
	Handler handler; // 핸들러
	Handler handler2; // 지오코딩파서용 핸들러

	
	
	
	/*
	 * Handler handler; //핸들러 Handler handler2; //지오코딩파서용 핸들러 String
	 * dbFile="weatherdb.db3"; String
	 * dbFolder="/data/data/com.answerofgod.weather/datebases/"; String numDong;
	 * //최종적으로 가져다 붙일 동네코드가 저장되는 변수 String numSido; //시도 코드가 저장되어 구군table에서 비교하기
	 * 위한 변수 String numGugun;//구군 코드가 저장되어 동table에서 비교하기 위한 변수
	 * 
	 * final int tableSido=1; //이건 switch case문에서 쓸려고 만든 변수 final int
	 * tableGugun=2; final int tableDong=3;
	 */

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other2);
		 
		// ///////////////
		handler = new Handler(); // 스레드&핸들러처리
		handler2 = new Handler(); // 스레드&핸들러처리
		MainHandler=new Handler();
		
		mAddView = (Button) findViewById(R.id.btn_add);
		 		//테스트용

		
		//for(int i=0;i<3;i++)				//객체 초기화
		//SA[i].numDong=SA[i].tempDong;
		
		//SA.tableSido ;
		//SA.numSido="aa";
		
		
		
		
		 // onCreate 에서
        try {
        	boolean bResult = isCheckDB(getBaseContext());	// DB가 있는지?
        	
        	if(!bResult){	// DB가 없으면
        		copyDB(getBaseContext());	//bd복사
        	//	Toast.makeText(getApplicationContext(), "DB를 만들어요",Toast.LENGTH_SHORT).show();
        	}else{			//DB가 있으면
        		//Toast.makeText(getApplicationContext(), "이미 DB가있어요",Toast.LENGTH_SHORT).show();
        		
        	}
        	
		} catch (Exception e) {	//예외발생용

			Toast.makeText(getApplicationContext(), "예외가 발생했어요",Toast.LENGTH_SHORT).show();
		}
		
/*
		handler=new Handler();	//스레드&핸들러처리
		handler2=new Handler();	//스레드&핸들러처리
		sidoSpinner=(Spinner)findViewById(R.id.sidospinner);	//시도용 스피너
		gugunSpinner=(Spinner)findViewById(R.id.gugunspinner); 	//구군용 스피너
		dongSpinner=(Spinner)findViewById(R.id.dongspinner);	//동면용 스피너
*/
		gridView1=(GridView) findViewById(R.id.gridView1);		//날씨정보 리스트뷰
		gridView2=(GridView) findViewById(R.id.gridView1);		//날씨정보 리스트뷰
		
		gridView1.setVisibility(View.INVISIBLE);
		gridView2.setVisibility(View.INVISIBLE);

		
		bCategory=bTm=bHour=bTemp=bWdKor=bReh=bDay=bWfKor=tCategory=tTm=tItem=false;	//부울상수는 false로 초기화해주자
		
		
		
		listadapter=new listWeatherView(getBaseContext());	//리스트뷰를 만들어주자		10개 까지 도시추가 가능하므로
		listadapter2=new listWeatherView(getBaseContext());
		listadapter3=new listWeatherView(getBaseContext());
		listadapter4=new listWeatherView(getBaseContext());
		listadapter5=new listWeatherView(getBaseContext());
		listadapter6=new listWeatherView(getBaseContext());
		listadapter7=new listWeatherView(getBaseContext());
		listadapter8=new listWeatherView(getBaseContext());
		listadapter9=new listWeatherView(getBaseContext());

		
		gridView1.setAdapter(listadapter);					//어댑터와 리스트뷰를 연결
		gridView2.setAdapter(listadapter2);					//어댑터와 리스트뷰를 연결

		text=(TextView) findViewById(R.id.textView1);	//텍스트 객체생성
	//	getBtn=(Button) findViewById(R.id.getBtn);		//버튼 객체생성
		//gpsBtn=(Button) findViewById(R.id.gpsBtn);		//버튼 객체생성
		
		
		
	
       /*
		getBtn.setOnClickListener(new OnClickListener() {	//날씨 버튼을 눌러보자
			
			@Override
			public void onClick(View arg0) {
				
				SA.numDong=SA.tempDong;
				text.setText("");	//일단 중복해서 누를경우 대비해서 내용 지워줌
				network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
				thread.start();	//스레드 시작
			}
		});
		gpsBtn.setOnClickListener(new OnClickListener() {	//GPS정보 가져오기 버튼을 눌러보자
			
			@Override
			public void onClick(View arg0) {
				
				text.setText("");	//일단 중복해서 누를경우 대비해서 내용 지워줌
				getMyLocation();	//GPS setting
			}
		});
		*/
		
		//queryData(tableSido);	//시도 DB 가지고 오자
		
		
		
		/////////////////
		
		 // 추가 버튼
			mAddView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//SpinnerFlag=0;
					SpinnerFlag++;
					if(SpinnerFlag==10){
						Toast.makeText(getApplicationContext(),
								"최대 10개의 아이템만 등록 가능합니다. 소스를 수정하세요.",
								Toast.LENGTH_SHORT).show();
						SpinnerFlag=9;
					}
				/*	if(SpinnerFlag==1)
					{
						isRun=false;
					}
					*/
								//	isRun=false;
					
					/*
					if(SpinnerFlag==1)
					{
						Toast.makeText(getApplicationContext(), "aaaaa",Toast.LENGTH_SHORT).show();
						LinearLayout topLL=(LinearLayout)findViewById(R.id.DynamicLayout);
							TextView tv=new TextView(MainActivity.this);
							tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
							tv.setText("aa");
							topLL.addView(tv);
							
						
					}
					*/
		
					Intent intent1 = new Intent(getBaseContext(), SpinnerActivity.class);
					//intent1.putExtra("url", url1);
					startActivityForResult(intent1,1);
					
				/*	
					if(SpinnerFlag==0){
						SA[0].numDong=SA[0].tempDong;
						//Toast.makeText(getApplicationContext(), SA[0].numDong, Toast.LENGTH_SHORT).show();
					}
					else if(SpinnerFlag==1){
						SA[1].numDong=SA[1].tempDong;
					//	Toast.makeText(getApplicationContext(), SA[0].numDong, Toast.LENGTH_SHORT).show();
					}
					else if(SpinnerFlag==2)
						SA[2].numDong=SA[2].tempDong;
					else if(SpinnerFlag==3)
						SA[3].numDong=SA[3].tempDong;
					else if(SpinnerFlag==4){
						SA[4].numDong=SA[4].tempDong;
					}
					*/
					//String str=String.valueOf(SpinnerFlag);
				//	a=SpinnerFlag;
					
				//	Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
					// 뷰 페이저에 아이템 추가
					// LinearLayout DynamicLayout
					// =(LinearLayout)findViewById(R.id.DynamicLayout);
					//Intent intent2 = new Intent(this, SpinnerActivity.class); // 동네
					 //Intent intent3=new Intent().setClass(this,MainActivity.class);														// 설정
					//Intent intent3=new Intent(this,MinSun.class);															// 스피너
				
					// 인텐트															// 시작
					//startActivity(intent2);
					// DynamicLayout.setBackgroundResource(R.drawable.paris);
				//SA.BtnOk.setOnclickListener(new)
					//String aa=String.valueOf(flag);
					//Toast.makeText(getApplicationContext(), aa,Toast.LENGTH_SHORT).show();
					if (v == mAddView) {}
					
					
					text.setText("");	//일단 중복해서 누를경우 대비해서 내용 지워줌
					//Toast.makeText(getApplicationContext(), check, duration)
					//if(check==true){
					
				
					
					//network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
					//thread.start();	//스레드 시작
					
					
					
				/*	TestButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							
									
						
							
							network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
							thread.start();	//스레드 시작
							
						}

						
					});*/
				//	Thread.interrupted();
						//스레드 시작
			//	thread2.destroy();
			
						
				}

				
			}); // 클릭 리스너 등록
	
	
			


			// mViewTypeSpinner = (Spinner)findViewById(R.id.spinner_view_type);
			// //아이템 타입 선택하는 스피너

			mPageMark = (LinearLayout) findViewById(R.id.page_mark); // 상단의 현재 페이지
																		// 나타내는 뷰
		
			mPager = (ViewPager) findViewById(R.id.pager); // 뷰 페이저
			mAdapter = new BkPagerAdapter(getApplicationContext());
			mPager.setAdapter(mAdapter); // PagerAdapter로 설정
			mPager.setOnPageChangeListener(new OnPageChangeListener() { // 아이템이
																		// 변경되면,
																		// gallery나
																		// listview의
																		// onItemSelectedListener와
																		// 비슷
				
				// 아이템이 선택이 되었으면
				@Override
				public void onPageSelected(int position) {
			
					mPageMark.getChildAt(mPrevPosition).setBackgroundResource(
							R.drawable.page_not); // 이전 페이지에 해당하는 페이지 표시 이미지 변경
					mPageMark.getChildAt(position).setBackgroundResource(
							R.drawable.page_select); // 현재 페이지에 해당하는 페이지 표시 이미지 변경
					mPrevPosition = position; // 이전 포지션 값을 현재로 변경
				//	String aa=String.valueOf(position);
					//Toast.makeText(getApplicationContext(),"rrrr" ,Toast.LENGTH_SHORT);
					
				}

				@Override
				public void onPageScrolled(int position, float positionOffest,
						int positionOffsetPixels) {
			
				}

				@Override
				public void onPageScrollStateChanged(int state) {
			
				}
			});

			mPrevPosition = 0; // 이전 포지션 값 초기화
			
			//addPageMark(); // 현재 페이지 표시하는 뷰 추가
		//	mPageMark.getChildAt(mPrevPosition).setBackgroundResource(
			//		R.drawable.page_select); // 이전 페이지에 해당하는 페이지 표시 이미지 변경
		}
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data){
	super.onActivityResult(requestCode, resultCode, data);
	
	if(resultCode==RESULT_OK) // 액티비티가 정상적으로 종료되었을 경우
	{
		

		if (mAdapter.getCount() <= MAX) { // 최대 갯수 이하이면

			 type ++;// 아이템 타입을 가져와
			mAdapter.addItem(type); // 해당 타입의 아이템을 추가한다. 뷰 페이져 새로고침
			addPageMark(); // 페이지 표시 뷰도 추가
			network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
			thread.setDaemon(true);
			thread.start();	
		
			
		} else
			Toast.makeText(getApplicationContext(),
					"최대 10개의 아이템만 등록 가능합니다. 소스를 수정하세요.",
					Toast.LENGTH_SHORT).show();
	
		//network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
			//thread.start();	//스레드 시작
						
	//Toast.makeText(getApplicationContext(), "aa",Toast.LENGTH_SHORT).show();
	//isRun=false;
	try{
	//	Thread aa=new Thread();
	//	aa.sleep(2000);
	//	thread.destroy();
		//thread.interrupt();
		//thread.stop();
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	isRun=true;
//	handler.removeCallbacks(null);

	
	
	
	
 // 클릭 리스너 등록

}
		///////////////
	
	




	/**
	 * 이부분이 db를 열어주는 부분
	 * 
	 * @author Ans
	 * @param databaseFile
	 */
	public static void openDatabase(String databaseFile) {
	    	
	    	
	
	    	try {
	    		db = SQLiteDatabase.openDatabase(	//선택한 폴더의 db를 가져와서 읽고,쓰기 가능하게 읽어온다
	    	    				databaseFile, null, SQLiteDatabase.OPEN_READWRITE);
		
	    	} catch (SQLiteException ex) {
	    		
	    	}
	    }
	
	    public static void closeDatabase() {
			try {
				// close database
				db.close();
			} catch(Exception ext) {
				ext.printStackTrace();
				
			}
	    }
	
	
	// DB가 있나 체크하기
		public boolean isCheckDB(Context mContext){
			
			String filePath = dbFolder+dbFile;
			File file = new File(filePath);
			  
			   if (file.exists()) {	//db폴더에 파일이 있으면 true
				   return true;
			   }
			   
			   return false;		//아님 default로 false를 반환
			
		}
		
		// DB를 복사하기
		// assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기
		public void copyDB(Context mContext){	//만약 db가 없는 경우 복사를 해야된다.
			 
			  AssetManager manager = mContext.getAssets();	//asserts 폴더에서 파일을 읽기위해 쓴단다.아직 잘
			  String folderPath = dbFolder;	//db폴더			//일단 DB를 이 폴더에 저장을 하였으니 써야겠지?
			  String filePath = dbFolder+dbFile; //db폴더와 파일경로
			  File folder = new File(folderPath);	
			  File file = new File(filePath);
			  
			  FileOutputStream fos = null;	
			  BufferedOutputStream bos = null;
			  try {
			   InputStream is = manager.open("db/" + "weather.db3");	//일던 asserts폴더밑 db폴더에서 db를 가져오자
			   BufferedInputStream bis = new BufferedInputStream(is);

			   	   if (folder.exists()) {			//우리가 복사하려는 db폴더가 이미 있으면 넘어가고
			   	   }else{						
					   folder.mkdirs();				//없을경우 폴더를 만들자
				   }
			   	   
			   
				   if (file.exists()) {				//파일이 있다면 
					   file.delete();				//일단 지우고
					   file.createNewFile();		//새 파일을 만들자
				   }
				   
				   fos = new FileOutputStream(file);	
				   bos = new BufferedOutputStream(fos);
				   int read = -1;
				   byte[] buffer = new byte[1024];	//buffer는 1024byte니깐 1k로 지정해주고
				   while ((read = bis.read(buffer, 0, 1024)) != -1) {	//db파일을 읽어서 buffer에 넣고
				    bos.write(buffer, 0, read);							//buffer에서 새로 만든 파일에 쓴다
				   }													//대충이해는 되는데 어렵네;;

				   bos.flush();
				 
				   bos.close();
				   fos.close();
				   bis.close();
				   is.close();

			   } catch (IOException e) {
			   
			  }	
		}
			
		
	
	
	/**
	 * 기상청을 연결하여 정보받고 뿌려주는 스레드
	 * 
	 * @author Ans
	 *
	 */
	class network_thread extends Thread{	//기상청 연결을 위한 스레드
		/**
		 * 기상청을 연결하는 스레드
		 * 이곳에서 풀파서를 이용하여 기상청에서 정보를 받아와 각각의 array변수에 넣어줌
		 * @author Ans
		 */
		
		public void run(){
			MainHandler.post(new Runnable(){	
			
				public void run(){
				//Toast.makeText(getApplicationContext(), "aaa", Toast.LENGTH_SHORT).show();
			}
		});
			
			try{
				updated=false;
				sHour=new String[100];	//예보시간(사실 15개밖에 안들어오지만 넉넉하게 20개로 잡아놓음)
				sDay=new String[100];	//날짜
				sTemp=new String[100];	//현재온도
				sWdKor=new String[100];	//풍향
				sReh=new String[100];	//습도
				sWfKor=new String[100];	//날씨
				data=0;
				XmlPullParserFactory factory=XmlPullParserFactory.newInstance();	//이곳이 풀파서를 사용하게 하는곳
				factory.setNamespaceAware(true);									//이름에 공백도 인식
				XmlPullParser xpp=factory.newPullParser();							//풀파서 xpp라는 객체 생성
				
				String weatherUrl="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone="+SA[SpinnerFlag].numDong;	//이곳이 기상청URL
				
				URL url=new URL(weatherUrl);		//URL객체생성
				InputStream is=url.openStream();	//연결할 url을 inputstream에 넣어 연결을 하게된다.
				xpp.setInput(is,"UTF-8");			//이렇게 하면 연결이 된다. 포맷형식은 utf-8로
				
				int eventType=xpp.getEventType();	//풀파서에서 태그정보를 가져온다.
					
				while(eventType!=XmlPullParser.END_DOCUMENT){	//문서의 끝이 아닐때
					
					switch(eventType){
					case XmlPullParser.START_TAG:	//'<'시작태그를 만났을때
						
						if(xpp.getName().equals("category")){	//태그안의 이름이 카테고리일?? (이건 동네이름이 나온다)
							bCategory=true;	
							
						} if(xpp.getName().equals("pubDate")){		//발표시각정보
							bTm=true;
						
						} if(xpp.getName().equals("hour")){		//예보시간
							bHour=true;
							
						} if(xpp.getName().equals("day")){		//예보날(오늘 내일 모레)
							bDay=true;
							
						} if(xpp.getName().equals("temp")){		//예보시간기준 현재온도
							bTemp=true;
							
						} if(xpp.getName().equals("wdKor")){	//풍향정보
							bWdKor=true;
							
						} if(xpp.getName().equals("reh")){		//습도정보
							bReh=true;
							
						} if(xpp.getName().equals("wfKor")){	//날씨정보(맑음, 구름조금, 구름많음, 흐림, 비, 눈/비, 눈)
							bWfKor=true;
							
						}
						
						break;
					
					case XmlPullParser.TEXT:	//텍스트를 만났을때
												//앞서 시작태그에서 얻을정보를 만나면 플래그를 true로 했는데 여기서 플래그를 보고
												//변수에 정보를 넣어준 후엔 플래그를 false로~
						if(bCategory){				//동네이름
							SA[SpinnerFlag].sCategory=xpp.getText();
							bCategory=false;
						} if(bTm){					//발표시각
							sTm=xpp.getText();	
							bTm=false;	
						}  if(bHour){				//예보시각			
							sHour[data]=xpp.getText();
							bHour=false;
						}  if(bDay){				//예보날짜
							sDay[data]=xpp.getText();
							bDay=false;
						}  if(bTemp){				//현재온도
							sTemp[data]=xpp.getText();
							bTemp=false;
						}  if(bWdKor){				//풍향
							sWdKor[data]=xpp.getText();
							bWdKor=false;
						}  if(bReh){				//습도
							sReh[data]=xpp.getText();
							bReh=false;
						} if(bWfKor){				//날씨
							sWfKor[data]=xpp.getText();
							bWfKor=false;
							}
						break;
						
					case XmlPullParser.END_TAG:		//'</' 엔드태그를 만나면 (이부분이 중요)
						
						if(xpp.getName().equals("item")){	//태그가 끝나느 시점의 태그이름이 item이면(이건 거의 문서의 끝
							tItem=true;						//따라서 이때 모든 정보를 화면에 뿌려주면 된다.
							view_text();					//뿌려주는 곳~
						} if(xpp.getName().equals("pubDate")){	//이건 발표시각정보니까 1번만나오므로 바로 뿌려주자
							tTm=true;
							view_text();
						} if(xpp.getName().equals("category")){	//이것도 동네정보라 바로 뿌려주면 됨
							tCategory=true;
							view_text();
						} if(xpp.getName().equals("data")){	//data태그는 예보시각기준 예보정보가 하나씩이다.
							data++;							//즉 data태그 == 예보 개수 그러므로 이때 array를 증가해주자
						}
						break;
					}
					eventType=xpp.next();	//이건 다음 이벤트로~
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			}
		//isRun=false;
		}
			
	//	}

	
		
	/*	public void threadStop(){
			try{
				isRun=false;
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
		
		/**
		 * 이 부분이 뿌려주는곳 
		 * 뿌리는건 핸들러가~
		 * @author Ans
		 */
		private void view_text(){
			
			handler.post(new Runnable() {	//기본 핸들러니깐 handler.post하면됨
				
				@Override
				public void run() {
					//if(isRun){
					
					if(tCategory){	//동네이름 들어왔다
						text.setText(text.getText()+"지역:"+SA[SpinnerFlag].sCategory+"\n");
						tCategory=false;
					}if((tTm)&&(sTm.length()>11)){		//발표시각 들어왔다
						text.setText(text.getText()+"발표시각:"+sTm+"\n");
						tTm=false;
					}if(tItem){		//문서를 다 읽었다
						
						for(int i=0;i<data;i++){	//array로 되어있으니 for문으로
							if(sDay[i]!=null){		//이건 null integer 에러 예방을 위해(String은 null이 가능하지만intger는 안되니깐)
								if(sDay[i].equals("0")){	//발표시각이 0이면 오늘 
									sDay[i]="날짜:"+"오늘";
									
								}else if(sDay[i].equals("1")){	//1이면 내일
									sDay[i]="날짜:"+"내일";
								
								}else if(sDay[i].equals("2")){	//2이면 모레
									sDay[i]="날짜:"+"모레";
									
								}
							}
							
						}
					if(SpinnerFlag==0){
						listadapter.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter.setTime(sHour);
					    listadapter.setTemp(sTemp);
						listadapter.setWind(sWdKor);
						listadapter.setHum(sReh);
					    listadapter.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
					    listadapter.notifyDataSetChanged();
					    tItem=false;
						data=0;		
					}
					
					
					else if(SpinnerFlag==1){
					    listadapter2.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter2.setTime(sHour);
					    listadapter2.setTemp(sTemp);
						listadapter2.setWind(sWdKor);
						listadapter2.setHum(sReh);
					    listadapter2.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter2.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					else if(SpinnerFlag==2){
					    listadapter3.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter3.setTime(sHour);
					    listadapter3.setTemp(sTemp);
						listadapter3.setWind(sWdKor);
						listadapter3.setHum(sReh);
					    listadapter3.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter3.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					
					else if(SpinnerFlag==3){
					    listadapter4.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter4.setTime(sHour);
					    listadapter4.setTemp(sTemp);
						listadapter4.setWind(sWdKor);
						listadapter4.setHum(sReh);
					    listadapter4.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter4.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					
					
					else if(SpinnerFlag==4){
					    listadapter5.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter5.setTime(sHour);
					    listadapter5.setTemp(sTemp);
						listadapter5.setWind(sWdKor);
						listadapter5.setHum(sReh);
					    listadapter5.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter5.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					
					else if(SpinnerFlag==5){
					    listadapter6.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter6.setTime(sHour);
					    listadapter6.setTemp(sTemp);
						listadapter6.setWind(sWdKor);
						listadapter6.setHum(sReh);
					    listadapter6.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter6.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					
					else if(SpinnerFlag==6){
					    listadapter7.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter7.setTime(sHour);
					    listadapter7.setTemp(sTemp);
						listadapter7.setWind(sWdKor);
						listadapter7.setHum(sReh);
					    listadapter7.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter7.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					
					else if(SpinnerFlag==7){
					    listadapter8.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter8.setTime(sHour);
					    listadapter8.setTemp(sTemp);
						listadapter8.setWind(sWdKor);
						listadapter8.setHum(sReh);
					    listadapter8.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter8.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					else if(SpinnerFlag==8){
					    listadapter9.setDay(sDay);	//날씨정보를 listview로 뿌려보자
						listadapter9.setTime(sHour);
					    listadapter9.setTemp(sTemp);
						listadapter9.setWind(sWdKor);
						listadapter9.setHum(sReh);
					    listadapter9.setWeather(sWfKor);
					    updated=true;					//정보가 담겼으니 flag를 true로
						listadapter9.notifyDataSetChanged();
						
						tItem=false;
						data=0;		//다음에 날씨를 더가져오게 되면 처음부터 저장해야겠지?
					}
					
					}
					
					
				}
				
		//	}		//추가부분
			});
			
		}
	
	/**
	 * 이곳에서 GPS방법과 리스너등을 세팅해 준다
	 * @author Ans
	 */
	public void getMyLocation() {
		
		manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);	//GPS 서비스
		
		long minTime=60000;		//every 60sec
		float minDistance=1;	//if moved over 1miter
		listener=new MyLocationListener();	//리스너 만들자
		//manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime ,minDistance, listener);//GPS
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime ,minDistance, listener);//기지국
		
	}
	/**
	 * 이부분은 지오코딩해서 파서하는 곳
	 * @author Administrator
	 *
	 */
	class getaddress extends Thread{
		
		
		public void run(){
		
		try{
			
		
			SA[SpinnerFlag].sLong_name=new String[100];
			XmlPullParserFactory fa = XmlPullParserFactory.newInstance();
			fa.setNamespaceAware(true);		
			XmlPullParser xp=fa.newPullParser();
			
			String geocode="http://maps.googleapis.com/maps/api/geocode/xml?latlng="+SA[SpinnerFlag].latitude+","+SA[SpinnerFlag].longitutde+"&sensor=true&language=ko";
			URL url = new URL(geocode);
				//URL객체생성
			InputStream is = url.openStream();
			
			xp.setInput(is,"UTF-8");
			int eventType=xp.getEventType();
			geodata=0;

		
				
			while(eventType!=XmlPullParser.END_DOCUMENT){	//문서의 끝이 아닐때
					
					switch(eventType){
					case XmlPullParser.START_TAG:	//'<'시작태그를 만났을때
						
						if(xp.getName().equals("long_name")){	//태그안의 이름이 카테고리일?? (이건 동네이름이 나온다)
							bLong_name=true;	
							
						} 
						
						break;
					
					case XmlPullParser.TEXT:	//텍스트를 만났을때
												//앞서 시작태그에서 얻을정보를 만나면 플래그를 true로 했는데 여기서 플래그를 보고
												//변수에 정보를 넣어준 후엔 플래그를 false로~
						if(bLong_name){				//동네이름
							SA[SpinnerFlag].sLong_name[geodata]=xp.getText();
							bLong_name=false;
						} 
						break;
						
					case XmlPullParser.END_TAG:		//'</' 엔드태그를 만나면 (이부분이 중요)
						
						if(xp.getName().equals("GeocodeResponse")){	//태그가 끝나느 시점의 태그이름이 item이면(이건 거의 문서의 끝
							
							showtext();
							break;						//따라서 이때 모든 정보를 화면에 뿌려주면 된다.
							
						} if(xp.getName().equals("address_component")){	//data태그는 예보시각기준 예보정보가 하나씩이다.
							geodata++;							//즉 data태그 == 예보 개수 그러므로 이때 array를 증가해주자
						}
						break;
					}
					
						eventType=xp.next();
					
						// TODO Auto-generated catch block
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		private void showtext(){
			handler2.post(new Runnable() {
				
				@Override
				public void run() {
					
					Toast.makeText(getApplicationContext(),"현재 위치는 "+SA[SpinnerFlag].sLong_name[3]+" "+SA[SpinnerFlag].sLong_name[2]+" "+SA[SpinnerFlag].sLong_name[1], Toast.LENGTH_SHORT).show();
					String sql="select gugun_num, dong_num, dong_name, gridx, gridy, _id from t_dong where dong_name = "+"'"+SA[SpinnerFlag].sLong_name[1]+"'";
					Cursor cur=db.rawQuery(sql, null);
					if(cur.getCount()!=0){
						cur.moveToFirst();
						SA[SpinnerFlag].numDong=cur.getString(1);
						cur.close();
					//	network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
					//	thread.start();	//스레드 시작
					}
					
					
					
				}
				
			});
		//	handler.removeCallbacks(null);
		}
		
		
	}

	/**
	 * 이곳에서 실제로 지피에스 정보를 받아온다
	 * @author Ans
	 *
	 */
	class MyLocationListener implements LocationListener{
	
		@Override
		public void onLocationChanged(Location location) {
			
			SA[SpinnerFlag].latitude=location.getLatitude();//get latitued
			SA[SpinnerFlag].longitutde=location.getLongitude();//get longitutde
			manager.removeUpdates(listener);	//지피에스 서비스 종료(이부분을 주석처리하면 설정한거대로 계속 받아옴)
			getaddress thread=new getaddress();		//지오코딩할 스레드
			
				thread.start();	//스레드 시작
			
			
		}
			
		@Override
		public void onProviderDisabled(String provider) {
			
		}
	
		@Override
		public void onProviderEnabled(String provider) {
			
			
		}
	
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
			
		}
	
}
	
	
	
	
	
	/**
	 * 
	 * 이부분은 날씨를 리스트뷰에 뿌려주는 어댑터
	 * @author Ans
	 *
	 */
	class listWeatherView extends BaseAdapter{

		String[] day,time,temp,wind,hum,weather;
		Context mContext;
		String temp_data[]=new String [15];	//임시로 만들었다 nullpointexception 방지
		
		public listWeatherView(Context context){
			mContext = context;
		}
		
		public void setDay(String[] data){
			day=data;
		}
		public void setTime(String[] data){
			time=data;
		}
		public void setTemp(String[] data){	
			temp=data;
		}
		public void setWind(String[] data){
			wind=data;
		}
		public void setHum(String[] data){
			hum=data;
		}
		public void setWeather(String[] data){
			weather=data;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			
			return temp_data.length;		//리스트뷰의 갯수
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return temp_data[position];		
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		 public View getView(int position, View convertView, ViewGroup parents) {
			// parents.LayoutParams params=convertView.getLayoutParams();
			// ViewGroup.LayoutParams params = convertView.getLayoutParams();			//동적 크기조절
			 //params.width=10;
			 //params.height = 10;

			// convertView.setLayoutParams(params);
			showWeather layout=null;	
			
			
			if(convertView!=null){	//스크롤로 넘어간 뷰를 버리지 않고 재사용
				layout=(showWeather)convertView;
			}
			else{
				layout=new showWeather(mContext.getApplicationContext());	//레이아웃 설정
				
			}
			
			if(updated){	//날씨정보를 받아왔으면
				Log.w("position",String.valueOf(position));
				layout.setDate(day[position]);	//레이아웃으로 뿌려줌
				layout.setTime(time[position]);
				layout.setTemp(temp[position]);
				layout.setWind(wind[position]);
				layout.setHum(hum[position]);
				layout.setWeather(weather[position]);
				
			}
			
			return layout;
		
		}
	// ////////////////
	}
	
	/////////////////////
	
	
	
	


	
	/////////////////
	// 상단의 현재 페이지 표시하는 뷰 추가.
	// 뷰 페이저에 아이템이 추가 될 때마다 한개 씩 추가한다
	private void addPageMark() {
		ImageView iv = new ImageView(getApplicationContext()); // 페이지 표시 이미지 뷰
																// 생성
		iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		iv.setBackgroundResource(R.drawable.page_not);
		mPageMark.addView(iv);// LinearLayout에 추가
	}

	// Pager 아답터 구현
	private class BkPagerAdapter extends PagerAdapter {
		private Context mContext;
		private ArrayList<Integer> mItems; // 아이템 뷰의 타입. 아이템 갯수 만큼
		
		public BkPagerAdapter(Context con) {
			super();
			mContext = con;
			mItems = new ArrayList<Integer>(); // 아답터 생성시 리스트 생성
			//mItems.add(BkUtils.TYPE_LISTVIEW); // 최초 1개의 아이템은 기본으로 생성한다
		}

		// 뷰 페이저의 아이템 갯수는 리스트의 갯수
		// 나중에 뷰 페이저에 아이템을 추가하면 리스트에 아이템의 타입을 추가 후 새로 고침하게 되면
		// 자동으로 뷰 페이저의 갯수도 늘어난다.
		@Override
		public int getCount() {
			return mItems == null ? 0 : mItems.size();
		}

		// 뷰페이저에서 사용할 뷰객체 생성/등록
		@Override
		public Object instantiateItem(View pager, int position) {
			View v = BkUtils.getView(mItems.get(position), mContext); // 해당 포지션의
																		// 아이템
																		// 뷰를
																		// 생성한다
			((ViewPager) pager).addView(v);
			return v;
		}

		// 뷰 객체 삭제.
		@Override
		public void destroyItem(View pager, int position, Object view) {
			((ViewPager) pager).removeView((View) view);
		}

		// instantiateItem메소드에서 생성한 객체를 이용할 것인지
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

		/**
		 * 동적으로 아이템을 추가하는 메소드
		 * 
		 * @param type
		 *            - 아이템 타입
		 */
		public void addItem(int type) {
			
			mItems.add(type); // 아이템 목록에 추가
			notifyDataSetChanged(); // 아답터에 데이터 변경되었다고 알림. 알아서 새로고침
		}
	}
}
		




