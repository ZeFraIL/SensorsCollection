package zeev.fraiman.sensorscollection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LightSensor extends AppCompatActivity
        implements SensorEventListener {

    LinearLayout LL;
    Sensor brightness;
    SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        LL=findViewById(R.id.LL);
        setUpSensor();
    }

    private void setUpSensor() {
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        brightness=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT) {
            float light1 = sensorEvent.values[0];
            String st="Sensor: "+light1+"/"+brightness(light1);
            Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();
        }
    }

    private String brightness(float light1) {
        if (light1==0) {
            LL.setBackgroundColor(Color.BLACK);
            return "Pitch black";
        }
        if(light1>=1 && light1<=10) {
            LL.setBackgroundColor(Color.DKGRAY);
            return "Dark";
        }
        if(light1>=11 && light1<=50) {
            LL.setBackgroundColor(Color.RED);
            return "Grey";
        }
        if(light1>=51 && light1<=5000) {
            LL.setBackgroundColor(Color.YELLOW);
            return "Normal";
        }
        if(light1>=5001 && light1<=25000) {
            LL.setBackgroundColor(Color.MAGENTA);
            return "Incredibly bright";
        }
        LL.setBackgroundColor(Color.WHITE);
        return "This light will blind you";
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        return;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}