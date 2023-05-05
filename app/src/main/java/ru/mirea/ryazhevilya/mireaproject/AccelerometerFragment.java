package ru.mirea.ryazhevilya.mireaproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.ryazhevilya.mireaproject.databinding.FragmentAccelerometerBinding;

public class AccelerometerFragment extends Fragment implements SensorEventListener {

    private FragmentAccelerometerBinding binding;
    private SensorManager sensorManager;
    AccelerometerFragment.CustomDrawableView mCustomDrawableView = null;
    ShapeDrawable mDrawable = new ShapeDrawable();
    public static int x;
    public static int y;

    public AccelerometerFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccelerometerBinding.inflate(inflater, container, false);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mCustomDrawableView = new AccelerometerFragment.CustomDrawableView(getContext());
        binding.ballContainer.addView(mCustomDrawableView);
        return binding.getRoot();
    }

    public void onSensorChanged(SensorEvent event)
    {
        {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                x -= (int) event.values[0];
                y += (int) event.values[1];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {

    }

    @Override
    public void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStop()
    {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    class CustomDrawableView extends View
    {
        static final int width = 50;
        static final int height = 50;

        public CustomDrawableView(Context context)
        {
            super(context);

            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setColor(0xff74AC23);
            mDrawable.setBounds(x, y, x + width, y + height);
        }

        protected void onDraw(Canvas canvas)
        {
            RectF oval = new RectF(
                    AccelerometerFragment.x,
                    AccelerometerFragment.y,
                    AccelerometerFragment.x + width,
                    AccelerometerFragment.y + height);
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            canvas.drawOval(oval, p);
            invalidate();
        }
    }

}