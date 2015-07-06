package com.answerofgod.weather;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class CameraView extends GLSurfaceView{
	CameraRenderer camera_renderer;
	
	CameraView(Context context)
	{
		super(context);
		
		camera_renderer=new CameraRenderer(this);
		setRenderer(camera_renderer);
	}

}
