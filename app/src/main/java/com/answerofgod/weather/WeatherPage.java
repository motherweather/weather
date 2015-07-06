package com.answerofgod.weather;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

public class WeatherPage extends Activity {
	SurfaceView cameraPreview;
	SurfaceHolder previewHolder;
	Camera camera;
	boolean inPreview;
	final static String TAG = "PAAR";
	// SensorManager sensorManager;
	// int orientationSensor;
	// float headingAngle;
	// float pitchAngle;
	// float rollAngle;
	// LocationManager locationManager;
	double altitude;
	static String dongnum;
	static String dongname;
	static double lat;
	static double longitude;
	// 임의로 대현동으로 설정
	// TextView text;
	String dbFile = "weatherdb.db3";
	String dbFolder = "/data/data/com.answerofgod.weather/datebases/";
	static SQLiteDatabase db;
	private final String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		// 카메라 설정부분
		inPreview = false;

		cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
		previewHolder = cameraPreview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		findlatlong();
		Parsingtask parsing = new Parsingtask();
		
		//위도 경도 찾는거도 추가해줘야함
		findAddress(lat,longitude);
		parsing.execute(url + dongnum);
		// 방향 센서
		/*
		 * sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		 * orientationSensor=Sensor.TYPE_ORIENTATION;
		 * sensorManager.registerListener(sensorEventListener,
		 * sensorManager.getDefaultSensor
		 * (orientationSensor),sensorManager.SENSOR_DELAY_NORMAL); //gps
		 * locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
		 * locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		 * 2000, 2, locationListener);
		 */
	}

	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub

			try {
				camera.setDisplayOrientation(90);
				camera.setPreviewDisplay(previewHolder);

			} catch (Throwable t) {
				Log.e("ProAndroidAR2Activity",
						"Exception in setpreviewdisplay()", t);
			}

		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			Camera.Parameters parameters = camera.getParameters();
			Camera.Size size = getBestPreviewSize(width, height, parameters);

			if (size != null) {
				parameters.setPreviewSize(size.width, size.height);
				camera.setParameters(parameters);

				camera.startPreview();
				inPreview = true;
			}

		}
	};

	public void onResume() {
		super.onResume();

		// locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		// 2000, 2, locationListener);

		// sensorManager.registerListener(sensorEventListener,
		// sensorManager.getDefaultSensor(orientationSensor),SensorManager.SENSOR_DELAY_NORMAL);
		camera = Camera.open();
	}

	public void onPause() {
		if (inPreview) {
			camera.startPreview();
		}
		// locationManager.removeUpdates(locationListener);
		// sensorManager.unregisterListener(sensorEventListener);

		camera.release();
		camera = null;
		inPreview = false;

		super.onPause();
	}

	private Camera.Size getBestPreviewSize(int width, int height,
			Camera.Parameters parameters) {
		Camera.Size result = null;

		for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
			if (size.width <= width && size.height <= height) {
				if (result == null) {
					result = size;
				} else {
					int resultArea = result.width * result.height;
					int newArea = size.width * size.height;

					if (newArea > resultArea) {
						result = size;
					}
				}
			}

		}
		return result;
	}

	/*
	 * final SensorEventListener sensorEventListener=new SensorEventListener() {
	 * public void onSensorChanged(SensorEvent sensorEvent) {
	 * if(sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION) {
	 * headingAngle=sensorEvent.values[0]; pitchAngle=sensorEvent.values[1];
	 * rollAngle=sensorEvent.values[2];
	 * 
	 * Log.d(TAG, "Heading : "+String.valueOf(headingAngle)); Log.d(TAG,
	 * "Pitch : "+String.valueOf(pitchAngle)); Log.d(TAG,
	 * "Roll : "+String.valueOf(rollAngle)); } }
	 * 
	 * @Override public void onAccuracyChanged(Sensor sensor, int accuracy) { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * 
	 * };
	 * 
	 * LocationListener locationListener=new LocationListener() { public void
	 * onLocationChanged(Location location) { latitude=location.getLatitude();
	 * longitude=location.getLongitude(); altitude=location.getAltitude();
	 * 
	 * Log.d(TAG, "Latitude : "+String.valueOf(latitude)); Log.d(TAG,
	 * "Longitude : "+String.valueOf(longitude)); Log.d(TAG,
	 * "Altitude : "+String.valueOf(altitude)); }
	 * 
	 * @Override public void onProviderDisabled(String provider) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void onProviderEnabled(String provider) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void onStatusChanged(String provider, int status, Bundle
	 * extras) { // TODO Auto-generated method stub
	 * 
	 * } };
	 */
	class Parsingtask extends AsyncTask<String, Integer, Long> {
		Weather weather = new Weather();
		boolean isTmp, isHu, isWind, isCa;
		int count = 0;

		@Override
		protected Long doInBackground(String... params) {
			// TODO Auto-generated method stub
			String parameter = params[0];

			try {
				XmlPullParserFactory parser_factory = XmlPullParserFactory
						.newInstance();
				parser_factory.setNamespaceAware(true);
				XmlPullParser parser = parser_factory.newPullParser();

				URL rss_url = new URL(parameter);
				InputStream is = rss_url.openStream();
				parser.setInput(is, "UTF-8");

				int eventType = parser.getEventType();

				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_TAG) {
						if (parser.getName().equals("temp")) {
							isTmp = true;
						}
						if (parser.getName().equals("wdKor")) {
							isWind = true;
						}
						if (parser.getName().equals("reh")) {
							isHu = true;
						}
						if (parser.getName().equals("category")) {
							isCa = true;
						}

					}
					if (eventType == XmlPullParser.TEXT) {
						if (isTmp) {
							weather.setTmp(parser.getText());
							isTmp = false;
						}
						if (isWind) {
							weather.setWind(parser.getText());
							isWind = false;
						}
						if (isHu) {
							weather.setHu(parser.getText());
							isHu = false;
						}
						if (isCa) {
							weather.setCategory(parser.getText());
							isCa = false;
						}
					}
					if (eventType == XmlPullParser.END_TAG) {
						if (parser.getName().equals("data")) {
							count++;
							publishProgress(1);
						}
					}
					if (count == 1) {
						break;
					} else if (count == 0) {
						eventType = parser.next();
					}

				}
			} catch (Exception e) {
				Log.e("parsingError", e.getMessage());
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			TextView category = (TextView) findViewById(R.id.category);
			TextView tmp = (TextView) findViewById(R.id.tmp);
			TextView hu = (TextView) findViewById(R.id.hu);
			TextView wind = (TextView) findViewById(R.id.wind);

			category.setText(dongname); //이 부분 수정(xml에서 동이름 읽어올지 그냥 동이름으로 할지 고민중)
			tmp.setText(weather.getTmp()+ "℃");
			hu.setText(weather.getHu() + "%");
			wind.setText(weather.getWind());
			
			//화살표 뷰 추가
		}

	}
	class arrowView extends View
	{

		public arrowView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		protected void onDraw(Canvas canvas)
		{
			Paint arrow=new Paint();
			//arrow
		}
		
	}

	private void findlatlong() {
		// 위도 경도 찾기
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = lm
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		lat = location.getLatitude();
		longitude = location.getLongitude();

	}

	private void findAddress(double lat, double longitude) {
		Geocoder geocoder = new Geocoder(this, Locale.KOREAN);
		List<Address> address;
		try {
			if (geocoder != null) {
				address = geocoder.getFromLocation(lat, longitude, 3);
				if (address != null) {
					dongname = address.get(0).getThoroughfare().toString();
					findDongCode();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void findDongCode() {
		String sql = "select dong_num from t_dong where dong_name=" + "'"
				+ dongname + "'";
		if (isCheckDB(getBaseContext())) {
			queryData(sql);
		} else {
			copyDB(getBaseContext());
			queryData(sql);

		}
	}

	private void queryData(String sql) {
		openDatabase(dbFolder + dbFile);

		Cursor cur = null;
		cur = db.rawQuery(sql, null); // 동이름에 해당하는 동코드 읽어오기

		if (cur != null) {
			cur.moveToFirst();
			dongnum = cur.getString(0);
		}
		// text.setText(dongnum);
		cur.close();

	}

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

	// DB를 복사하기
	// assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기
	public void copyDB(Context mContext) { // 만약 db가 없는 경우 복사를 해야된다.

		AssetManager manager = mContext.getAssets(); // asserts 폴더에서 파일을 읽기위해
														// 쓴단다.아직 잘
		String folderPath = dbFolder; // db폴더 //일단 DB를 이 폴더에 저장을 하였으니 써야겠지?
		String filePath = dbFolder + dbFile; // db폴더와 파일경로
		File folder = new File(folderPath);
		File file = new File(filePath);

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			InputStream is = manager.open("db/" + "weather.db3"); // 일던
																	// asserts폴더밑
																	// db폴더에서
																	// db를 가져오자
			BufferedInputStream bis = new BufferedInputStream(is);

			if (folder.exists()) { // 우리가 복사하려는 db폴더가 이미 있으면 넘어가고
			} else {
				folder.mkdirs(); // 없을경우 폴더를 만들자
			}

			if (file.exists()) { // 파일이 있다면
				file.delete(); // 일단 지우고
				file.createNewFile(); // 새 파일을 만들자
			}

			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			int read = -1;
			byte[] buffer = new byte[1024]; // buffer는 1024byte니깐 1k로 지정해주고
			while ((read = bis.read(buffer, 0, 1024)) != -1) { // db파일을 읽어서
																// buffer에 넣고
				bos.write(buffer, 0, read); // buffer에서 새로 만든 파일에 쓴다
			} // 대충이해는 되는데 어렵네;;

			bos.flush();

			bos.close();
			fos.close();
			bis.close();
			is.close();

		} catch (IOException e) {

		}
	}

}
