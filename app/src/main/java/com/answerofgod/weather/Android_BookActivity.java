package com.answerofgod.weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Android_BookActivity extends Activity {

	private final String SERVER_ADDRESS = "http://128.134.52.62:10001"; // 서버
	// 주소(php파일이
	// 저장되어있는경로까지,
	// 절대로
	// 127.0.0.1이나
	// localhost를쓰면
	// 안된다!!)dd

	EditText edtname;
	EditText edtprice;
	Button btninsert;
	Button btnsearch;

	// ArrayList<String> data;
	// ArrayAdapter<String> adapter;

	HttpPost httppost;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;

	/** Called when the activity is first created. */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login2);

		if (android.os.Build.VERSION.SDK_INT > 9) { // 네트워크강제접속설정

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		// setContentView(R.layout.activity_main);
		edtname = (EditText) findViewById(R.id.editText1);
		edtprice = (EditText) findViewById(R.id.editText2);
		btninsert = (Button) findViewById(R.id.button2);
		btnsearch = (Button) findViewById(R.id.button1);

		// list = (ListView) findViewById(R.id.listView1);
		// data = new ArrayList<String>();
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, data);
		// list.setAdapter(adapter);

		btninsert.setOnClickListener(new View.OnClickListener() { // 입력버튼을눌렀을
				// 때

					public void onClick(View v) {
						// TODO Auto-generated method stub

						if (edtname.getText().toString().equals("")|| edtprice.getText().toString().equals("")) { // 이름이나
						// 가격중에
						// 하나라도
						// 입력이
						// 안돼있을때
							Toast.makeText(Android_BookActivity.this,
									"이름이나가격을입력하세요", Toast.LENGTH_SHORT).show();
							return;
						}

						runOnUiThread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								String name = edtname.getText().toString()
										.trim();
								String price = edtprice.getText().toString()
										.trim();

								try {
									URL url = new URL(SERVER_ADDRESS
											+ "/insert.php?" + "name="
											+ URLEncoder.encode(name, "UTF-8")
											+ "&price="
											+ URLEncoder.encode(price, "UTF-8")); // 변수값을
									// UTF-8로
									// 인코딩하기
									// 위해
									// URLEncoder를
									// 이용하여
									// 인코딩함
									url.openStream(); // 서버의DB에입력하기위해웹서버의
									// insert.php파일에입력된이름과
									// 가격을넘김

									String result = getXmlData(
											"insertresult.xml", "result"); // 입력
									// 성공여부

									if (result.equals("1")) { // result 태그값이1일때
									// 성공
										Toast.makeText(
												Android_BookActivity.this,
												"DB insert 성공",
												Toast.LENGTH_SHORT).show();

										edtname.setText("");
										edtprice.setText("");
									} else
										// result 태그값이1이아닐때실패
										Toast.makeText(
												Android_BookActivity.this,
												"DB insert 실패",
												Toast.LENGTH_SHORT).show();
								} catch (Exception e) {
									Log.e("Error", e.getMessage());
								}
							}
						});
					}
				});

		btnsearch.setOnClickListener(new OnClickListener() { // 로그인하기

					public void onClick(View v) {
						dialog = ProgressDialog.show(Android_BookActivity.this,
								"", "Validating user...", true);
						new Thread(new Runnable() {
							public void run() {
								Looper.prepare();
								login();
								Looper.loop();

							}
						}).start();
					}
				});
	}

	void login() {
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost("http://128.134.52.62:10001/sung2.php"); // make
			// sure
			// the
			// url
			// is
			// correct.
			nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("name", edtname.getText()
					.toString().trim())); // $Edittext_value =
			// $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("price", edtprice
					.getText().toString().trim()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost,responseHandler);
			System.out.println("Response : " + response);
			runOnUiThread(new Runnable() {
				public void run() {

					dialog.dismiss();
				}
			});

			if (response.equalsIgnoreCase("User Found")) {// php문에서쿼리가성공하면User
															// Found라뜹니다
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(Android_BookActivity.this,
								"Login Success", Toast.LENGTH_SHORT).show();// 성공했을경우
						try{
							dialog = ProgressDialog.show(Android_BookActivity.this,
									"", "로그인 중입니다...",true);
							Thread thread=new Thread();
							thread.sleep(3000);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						Intent BoardIntent=new Intent(Android_BookActivity.this,board_activity.class);		//로그인 성공시 게시판 엑티비티로 전환
							startActivity(BoardIntent);
							
							
					}
				});

				// startActivity(new Intent(AndroidPHPConnectionDemo.this,
				// UserPage.class));
			} else {
				Toast.makeText(Android_BookActivity.this, "Login fail",
						Toast.LENGTH_SHORT).show();// 실패했을경우
			}

		} catch (Exception e) {
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}

	private String getXmlData(String filename, String str) { // 태그값하나를받아오기위한
	// String형함수
		String rss = SERVER_ADDRESS + "/";
		String ret = "";

		try { // XML 파싱을위한과정
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().equals(str)) { // 태그이름이str 인자값과같은경우
						ret = xpp.nextText();
					}
				}
				eventType = xpp.next();
			}
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
		}

		return ret;
	}

	private ArrayList<String> getXmlDataList(String filename, String str) { // 태그값
	// 여러개를
	// 받아오기위한
	// ArrayList<String>형
	// 변수
		String rss = SERVER_ADDRESS + "/";
		ArrayList<String> ret = new ArrayList<String>();

		try { // XML 파싱을위한과정
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().equals(str)) { // 태그이름이str 인자값과같은경우
						ret.add(xpp.nextText());
					}
				}
				eventType = xpp.next();
			}
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
		}

		return ret;
	}
}
