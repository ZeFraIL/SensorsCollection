package zeev.fraiman.sensorscollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class OrientationSensor extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor orientationSensor;
    SensorEventListener orientationListener;
    TextView tvX, tvY, tvZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_sensor);

        initComponents();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (orientationSensor == null) {
            Toast.makeText(this, "Orientation sensor not found", Toast.LENGTH_SHORT).show();
            return;
        }
        orientationListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float orientationX = event.values[0];
                float orientationY = event.values[1];
                float orientationZ = event.values[2];
                tvX.setText("X: " + orientationX);
                tvY.setText("Y: " + orientationY);
                tvZ.setText("Z: " + orientationZ);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(orientationListener,
                orientationSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void initComponents() {
        tvX = findViewById(R.id.tvX);
        tvY = findViewById(R.id.tvY);
        tvZ = findViewById(R.id.tvZ);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(orientationListener);
    }
}