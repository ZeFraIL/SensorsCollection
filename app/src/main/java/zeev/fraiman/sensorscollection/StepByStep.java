package zeev.fraiman.sensorscollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StepByStep extends AppCompatActivity {

    Context context;
    ImageView iv_sbs;
    TextView tv_sbs;
    Button bStart, bStop;
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    SensorEventListener sensorEventListener;
    int all_steps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_by_step);

        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACTIVITY_RECOGNITION},1);

        initComponents();

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_sbs.setImageResource(R.drawable.walk_man);
                iv_sbs.setVisibility(View.VISIBLE);
                sensorManager.registerListener(sensorEventListener,
                        stepCounterSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_sbs.setImageResource(R.drawable.stand_man);
                iv_sbs.setVisibility(View.INVISIBLE);
                sensorManager.unregisterListener(sensorEventListener);
            }
        });

    }

    private void initComponents() {
        context = this;
        iv_sbs = findViewById(R.id.iv_sbs);
        tv_sbs = findViewById(R.id.tv_sbs);
        tv_sbs.setText("Steps: " + all_steps);
        bStart = findViewById(R.id.bStart);
        bStop = findViewById(R.id.bStop);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCounterSensor==null)  {
            Toast.makeText(context, "Pedometer sensor not found", Toast.LENGTH_LONG).show();
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Toast.makeText(context, "Step", Toast.LENGTH_SHORT).show();
                if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                    float steps = event.values[0];
                    all_steps += (int) steps;
                    tv_sbs.setText("Steps: " + all_steps);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
}