package com.example.exokaroline.countingcents;
import android.content.Context;
import android.graphics.*;
import android.nfc.Tag;
import android.view.*;
import android.hardware.Camera;
import android.util.*;
import java.io.IOException;

/**
 * Created by johnk on 3/24/2018.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "Activity";
    private SurfaceHolder mHolder;
    private android.hardware.Camera mCamera;

    public CameraPreview(Context context, Camera camera){

        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder){
        try{
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }
        catch(IOException e){
            Log.d(TAG,"ERROR: Setting camera " + e.getMessage());
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){

        if(mHolder.getSurface() == null){
            return;
        }

        try{
            mCamera.stopPreview();
        } catch(Exception e){
        }

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        }catch(Exception e){
            Log.d(TAG,"ERROR: Starting camera " + e.getMessage());
        }

    }

    public void surfaceDestroyed(SurfaceHolder holder){
        if(mCamera!=null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

}