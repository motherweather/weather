package com.answerofgod.weather;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * pull parsing 첨에 개념을 잘못잡아서 좀 해맸다...;; 그래도 재밌네 ㅎ
 * 
 * @author Ans
 *
 */
public class SpinnerActivity extends Activity {

	// MyLocationListener listener;
	// LocationManager manager;
	// 날씨정보를 뿌려주는 리스트뷰용 어댑터
	MainActivity e=new MainActivity();
	//network_thread aa=new network_thread();
	
	Button BtnOk;
	ArrayAdapter<String> sidoAdapter; // 시도 정보를 뿌려주는 스피너용 어댑터
	ArrayAdapter<String> gugunAdapter; // 구군 정보를 뿌려주는 스피너용 어댑터
	ArrayAdapter<String> dongAdapter; // 동면 정보를 뿌려주는 스피너용 어댑터
	Spinner sidoSpinner; // 시도스피너
	Spinner gugunSpinner; // 구군스피너
	Spinner dongSpinner; // 동면스피너
	//Button getBtn; // 날씨 가져오는 버튼
	//Button gpsBtn;
//	TextView text; // 날씨지역및 발표시각정보
	//ListView listView1; // 날씨정보를 뿌려줄 리스트뷰

	
	static String tempDong = "0"; // 기본dongcode
	static String sCategory; // 동네
	/*
	String sTm; // 발표시각
	String[] sHour; // 예보시간(총 15개정도 받아옴 3일*5번)
	String[] sDay; // 날짜(몇번째날??)
	String[] sTemp; // 현재온도
	String[] sWdKor; // 풍향
	String[] sReh; // 습도
	String[] sWfKor; // 날씨
	// DB용 변수
	  */
	 
	static String[] sidonum; // 시도 코드
	static String[] Nsidonum; // 이건 구군table에서 가져오는 코드
	static String[] sidoname; // 시도 이름
	static String[] gugunnum; // 구군 코드
	static 	String[] Ngugunnum;// 동네 table에서 가져온 구군 코드
	static String[] gugunname;// 구군 이름
	static	String[] dongnum; // 동 코드
	static 	String[] dongname; // 동 이름
	static String[] gridx; // x좌표
	static	String[] gridy; // y좌표
	static	String[] id; // id
	static String[] sLong_name; // gps로 지오코딩후 주소를 파서해서 저장할 변수
	static double latitude, longitutde; // 위도와 경도를 저장할 변수
	static SQLiteDatabase db; // 디비
	
	/*
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
*/
	//thread th=new thread();
	//Thread Yoonthread=new Thread();
	Handler handler; // 핸들러
	Handler handler2; // 지오코딩파서용 핸들러
	String dbFile = "weatherdb.db3";
	String dbFolder = "/data/data/com.answerofgod.weather/datebases/";
	static String numDong=tempDong; // 최종적으로 가져다 붙일 동네코드가 저장되는 변수
	static String numSido; // 시도 코드가 저장되어 구군table에서 비교하기 위한 변수
	static String numGugun;// 구군 코드가 저장되어 동table에서 비교하기 위한 변수

	final int tableSido = 1; // 이건 switch case문에서 쓸려고 만든 변수
	final int tableGugun = 2;
	final int tableDong = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);

		
	
		
		/*
		 * 
		 * // onCreate 에서 try { boolean bResult = isCheckDB(getBaseContext());
		 * // DB가 있는지?
		 * 
		 * if(!bResult){ // DB가 없으면 copyDB(getBaseContext()); //bd복사
		 * Toast.makeText(getApplicationContext(),
		 * "DB를 만들어요",Toast.LENGTH_SHORT).show(); }else{ //DB가 있으면
		 * Toast.makeText(getApplicationContext(),
		 * "이미 DB가있어요",Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * } catch (Exception e) { //예외발생용
		 * 
		 * Toast.makeText(getApplicationContext(),
		 * "예외가 발생했어요",Toast.LENGTH_SHORT).show(); }
		 */
		
		
	
		
		handler = new Handler(); // 스레드&핸들러처리
		handler2 = new Handler(); // 스레드&핸들러처리
		sidoSpinner = (Spinner) findViewById(R.id.sidospinner); // 시도용 스피너
		gugunSpinner = (Spinner) findViewById(R.id.gugunspinner); // 구군용 스피너
		dongSpinner = (Spinner) findViewById(R.id.dongspinner); // 동면용 스피너
		// listView1=(ListView) findViewById(R.id.listView1); //날씨정보 리스트뷰

		//bCategory = bTm = bHour = bTemp = bWdKor = bReh = bDay = bWfKor = tCategory = tTm = tItem = false; // 부울상수는
	
												// false로
																											// 초기화해주자

		BtnOk=(Button)findViewById(R.id.btn_ok);
		
		BtnOk.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v)
			{
				
				
			//	Intent intent1=new Intent(SpinnerActivity.this,MainActivity.class);
				//startActivity(intent1);
				//thread th=new thread();
				Intent intent=getIntent();
				//MainActivity.check=true;
				//MainActivity.flag=0;
				//th.start();
				Toast.makeText(getApplicationContext(), "도시를 추가하였습니다",Toast.LENGTH_SHORT).show();
				
			//	isRun=true;
				
				setResult(RESULT_OK,intent); 
				finish();
				//getaddress aa=new getaddress();		
			//	MainActivity aa=new MainActivity();
				
				//MainActivity.network_thread thread=new network_thread();		//스레드생성(UI 스레드사용시 system 뻗는다)
				//thread.start();	//스레드 시작
				
			}
		}
	);


		sidoSpinner.setOnItemSelectedListener(new OnItemSelectedListener() { // 이부분은
																				// 스피너에
																				// 나타나는
																				// 내용

					@Override
					public void onItemSelected(AdapterView<?> parent, View v,
							int position, long id) { // 선택시
						numSido = sidonum[position]; // 시도가 선택되면 해당 코드를 변수에 넣는다
						queryData(tableGugun); // 구군 DB가지러~
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) { // 미
																			// 선택시

					}
				});
		gugunSpinner.setOnItemSelectedListener(new OnItemSelectedListener() { // 이부분은
																				// 스피너에
																				// 나타나는
																				// 내용

					@Override
					public void onItemSelected(AdapterView<?> parent, View v,
							int position, long id) { // 선택시
						numGugun = gugunnum[position]; // 구군이 선택되면 해당 코드를 변수에
						queryData(tableDong); // 동면 DB가지러~
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) { // 미
																			// 선택시

					}
				});
		dongSpinner.setOnItemSelectedListener(new OnItemSelectedListener() { // 이부분은
																				// 스피너에
																				// 나타나는
																				// 내용

					@Override
					public void onItemSelected(AdapterView<?> parent, View v,
							int position, long id) { // 선택시
						tempDong = dongnum[position];
						numDong = tempDong; // 선택된 동면코드를 변수에 넣자

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) { // 미
																			// 선택시

					}
				});

		queryData(tableSido); // 시도 DB 가지고 오자
	}

	/**
	 * DB를 가져오는 부분 시도, 구군, 동면 모두 테이블명과 레코드가 다르기때문에 case문을 썼는데 코드가 너무 길어짐;;
	 * 
	 * @author Ans
	 * @param table
	 */
	private void queryData(final int table) {
		// TODO Auto-generated method stub

		openDatabase(dbFolder + dbFile); // db가 저장된 폴더에서 db를 가지고 온다
		String sql = null; // sql명령어를 저장할 변수
		Cursor cur = null; // db가져올 커서
		int Count; // db갯수 셀 변수

		switch (table) {

		case tableSido:
			sql = "select sido_num, sido_name from t_sido"; // 시도 테이블에선 시도코드와
															// 시도이름
			cur = db.rawQuery(sql, null); // 커서에 넣자
			break;
		case tableGugun: // 구군 테이블에선 시도에서 선택된 시도의 구군정보만
			sql = "select sido_num, gugun_num, gjgun_name from t_gugun where sido_num = "
					+ numSido;
			cur = db.rawQuery(sql, null);
			break;
		case tableDong: // 동면 테이블도 선택된 구군코드와 비교해서
			sql = "select gugun_num, dong_num, dong_name, gridx, gridy, _id from t_dong where gugun_num = "
					+ numGugun;
			cur = db.rawQuery(sql, null);
			break;
		default:
			break;
		}

		Count = cur.getCount(); // db의 갯수를 세고

		switch (table) {

		case tableSido:

			sidoname = new String[Count]; // 갯수만큼 배열을 만든다
			sidonum = new String[Count];

			if (cur != null) { // 이부분이 커서로 데이터를 읽어와서 변수에 저장하는 부분
				cur.moveToFirst();
				startManagingCursor(cur);
				for (int i = 0; i < Count; i++) {
					sidonum[i] = cur.getString(0);
					sidoname[i] = cur.getString(1);
					cur.moveToNext();
				}
				// 변수에 저장이 되었으니 스피너를 만들어 뿌려주자
				sidoAdapter = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, sidoname); // 어댑터를
																			// 통해
																			// 스피너에
																			// donglist
																			// 넣어줌
				sidoAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // dropdown형식
				sidoSpinner.setAdapter(sidoAdapter); // 스피너 생성

			}
			break;
		case tableGugun: // 구군도 같은작업

			Nsidonum = new String[Count];
			gugunnum = new String[Count];
			gugunname = new String[Count];
			if (cur != null) {
				cur.moveToFirst();
				startManagingCursor(cur);
				for (int i = 0; i < Count; i++) {
					Nsidonum[i] = cur.getString(0);
					gugunnum[i] = cur.getString(1);
					gugunname[i] = cur.getString(2);
					cur.moveToNext();
				}
				gugunAdapter = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, gugunname); // 어댑터를
																			// 통해
																			// 스피너에
																			// donglist
																			// 넣어줌
				gugunAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // dropdown형식
				gugunSpinner.setAdapter(gugunAdapter);

			}
			break;

		case tableDong: // 동면도 같은작업

			Ngugunnum = new String[Count];
			dongnum = new String[Count];
			dongname = new String[Count];
			gridx = new String[Count];
			gridy = new String[Count];
			id = new String[Count];
			if (cur != null) {
				cur.moveToFirst();
				startManagingCursor(cur);
				for (int i = 0; i < Count; i++) {
					Ngugunnum[i] = cur.getString(0);
					dongnum[i] = cur.getString(1);
					dongname[i] = cur.getString(2);
					gridx[i] = cur.getString(3);
					gridy[i] = cur.getString(4);
					id[i] = cur.getString(5);
					cur.moveToNext();
				}
				cur.close();
				dongAdapter = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, dongname); // 어댑터를
																			// 통해
																			// 스피너에
																			// donglist
																			// 넣어줌
				dongAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // dropdown형식
				dongSpinner.setAdapter(dongAdapter);
			}
			break;

		default:
			break;

		}

	}

	/**
	 * 이부분이 db를 열어주는 부분
	 * 
	 * @author Ans
	 * @param databaseFile
	 */
	public static void openDatabase(String databaseFile) {

		try {
			db = SQLiteDatabase.openDatabase( // 선택한 폴더의 db를 가져와서 읽고,쓰기 가능하게
												// 읽어온다
					databaseFile, null, SQLiteDatabase.OPEN_READWRITE);

		} catch (SQLiteException ex) {

		}
	}

	public static void closeDatabase() {
		try {
			// close database
			db.close();
		} catch (Exception ext) {
			ext.printStackTrace();

		}
	}

	// DB가 있나 체크하기
	public boolean isCheckDB(Context mContext) {

		String filePath = dbFolder + dbFile;
		File file = new File(filePath);

		if (file.exists()) { // db폴더에 파일이 있으면 true
			return true;
		}

		return false; // 아님 default로 false를 반환

	}
	
	

	


}


