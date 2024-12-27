package zeev.fraiman.sensorscollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GyroscopeSensor extends AppCompatActivity {

    Context context;
    TextView tvGX, tvGY, tvGZ;
    Button bRegL, bUnRegL;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_sensor);

        initComponents();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "Gyroscope sensor not found", Toast.LENGTH_SHORT).show();
            return;
        }

        gyroscopeListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];
                    tvGX.setText("on X speed=" + x);
                    tvGY.setText("on Y speed=" + y);
                    tvGZ.setText("on Z speed=" + z);
                }
                else {
                    Toast.makeText(context, "Gyroscope sensor not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        bRegL.setOnClickListener(v ->
                sensorManager.registerListener(gyroscopeListener,
                        gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL));

        bUnRegL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager.unregisterListener(gyroscopeListener);

                tvGX.setText("on X speed=?");
                tvGY.setText("on Y speed=?");
                tvGZ.setText("on Z speed=?");
            }
        });
    }

    private void initComponents() {
        context = this;
        tvGX = findViewById(R.id.tvGX);
        tvGY = findViewById(R.id.tvGY);
        tvGZ = findViewById(R.id.tvGZ);
        bRegL = findViewById(R.id.bRegL);
        bUnRegL = findViewById(R.id.bUnRegL);
    }
}