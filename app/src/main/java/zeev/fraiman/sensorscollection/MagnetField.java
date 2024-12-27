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

public class MagnetField extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor magneticFieldSensor;
    SensorEventListener magneticFieldListener;
    TextView tvMX, tvMY, tvMZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnet_field);

        initComponents();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticFieldSensor == null) {
            Toast.makeText(this, "Not found magnet field sensor",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        magneticFieldListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float magneticFieldX = event.values[0];
                float magneticFieldY = event.values[1];
                float magneticFieldZ = event.values[2];
                tvMX.setText("X: " + magneticFieldX);
                tvMY.setText("Y: " + magneticFieldY);
                tvMZ.setText("Z: " + magneticFieldZ);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(magneticFieldListener,
                magneticFieldSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void initComponents() {
        tvMX = findViewById(R.id.tvMX);
        tvMY = findViewById(R.id.tvMY);
        tvMZ = findViewById(R.id.tvMZ);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(magneticFieldListener);
    }
}