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

	private final String SERVER_ADDRESS = "http://128.134.52.62:10001"; // ����
	// �ּ�(php������
	// ����Ǿ��ִ°�α���,
	// �����
	// 127.0.0.1�̳�
	// localhost������
	// �ȵȴ�!!)dd

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

		if (android.os.Build.VERSION.SDK_INT > 9) { // ��Ʈ��ũ�������Ӽ���

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

		btninsert.setOnClickListener(new View.OnClickListener() { // �Է¹�ư��������
				// ��

					public void onClick(View v) {
						// TODO Auto-generated method stub

						if (edtname.getText().toString().equals("")|| edtprice.getText().toString().equals("")) { // �̸��̳�
						// �����߿�
						// �ϳ���
						// �Է���
						// �ȵ�������
							Toast.makeText(Android_BookActivity.this,
									"�̸��̳��������Է��ϼ���", Toast.LENGTH_SHORT).show();
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
											+ URLEncoder.encode(price, "UTF-8")); // ��������
									// UTF-8��
									// ���ڵ��ϱ�
									// ����
									// URLEncoder��
									// �̿��Ͽ�
									// ���ڵ���
									url.openStream(); // ������DB���Է��ϱ�������������
									// insert.php���Ͽ��Էµ��̸���
									// �������ѱ�

									String result = getXmlData(
											"insertresult.xml", "result"); // �Է�
									// ��������

									if (result.equals("1")) { // result �±װ���1�϶�
									// ����
										Toast.makeText(
												Android_BookActivity.this,
												"DB insert ����",
												Toast.LENGTH_SHORT).show();

										edtname.setText("");
										edtprice.setText("");
									} else
										// result �±װ���1�̾ƴҶ�����
										Toast.makeText(
												Android_BookActivity.this,
												"DB insert ����",
												Toast.LENGTH_SHORT).show();
								} catch (Exception e) {
									Log.e("Error", e.getMessage());
								}
							}
						});
					}
				});

		btnsearch.setOnClickListener(new OnClickListener() { // �α����ϱ�

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

			if (response.equalsIgnoreCase("User Found")) {// php�����������������ϸ�User
															// Found���ϴ�
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(Android_BookActivity.this,
								"Login Success", Toast.LENGTH_SHORT).show();// �����������
						try{
							dialog = ProgressDialog.show(Android_BookActivity.this,
									"", "�α��� ���Դϴ�...",true);
							Thread thread=new Thread();
							thread.sleep(3000);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						Intent BoardIntent=new Intent(Android_BookActivity.this,board_activity.class);		//�α��� ������ �Խ��� ��Ƽ��Ƽ�� ��ȯ
							startActivity(BoardIntent);
							
							
					}
				});

				// startActivity(new Intent(AndroidPHPConnectionDemo.this,
				// UserPage.class));
			} else {
				Toast.makeText(Android_BookActivity.this, "Login fail",
						Toast.LENGTH_SHORT).show();// �����������
			}

		} catch (Exception e) {
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}

	private String getXmlData(String filename, String str) { // �±װ��ϳ����޾ƿ�������
	// String���Լ�
		String rss = SERVER_ADDRESS + "/";
		String ret = "";

		try { // XML �Ľ������Ѱ���
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().equals(str)) { // �±��̸���str ���ڰ����������
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

	private ArrayList<String> getXmlDataList(String filename, String str) { // �±װ�
	// ��������
	// �޾ƿ�������
	// ArrayList<String>��
	// ����
		String rss = SERVER_ADDRESS + "/";
		ArrayList<String> ret = new ArrayList<String>();

		try { // XML �Ľ������Ѱ���
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().equals(str)) { // �±��̸���str ���ڰ����������
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
