package com.answerofgod.weather;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class CameraRenderer extends Activity implements GLSurfaceView.Renderer{
	private CameraView camera_view;
	private Camera camera;
	SurfaceView cameraPreview;
	SurfaceHolder previewHolder;
	
	CameraRenderer(CameraView view)
	{
		camera_view=view;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		//실질적으로 그려지는 부분
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		//사용할 값들 초기화
	
		
	}
	


}
