package zeev.fraiman.sensorscollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class ShakeAndStart extends AppCompatActivity {

    Context context;
    SensorManager sensorManager;
    Sensor sensor_shake;
    SensorEventListener sensorEventListener;
    Intent gotostart;
    int SHAKE_LEVEL=14;
    TextToSpeech tts;
    Locale ldef;
    String[] stadb={"Three seconds to start","Two seconds to start","One second to start","Back to start"};
    int i=0, speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_and_start);

        initComponents();

        sensorManager.registerListener(sensorEventListener,
                sensor_shake,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void initComponents() {
        context = ShakeAndStart.this;
        gotostart = new Intent(context, MainActivity.class);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_shake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x_accl = sensorEvent.values[0];
                float y_accl = sensorEvent.values[1];
                float z_accl = sensorEvent.values[2];
                float floatSum = Math.abs(x_accl) + Math.abs(y_accl) + Math.abs(z_accl);
                if (floatSum>SHAKE_LEVEL) {
                    tts=new TextToSpeech(getApplicationContext(),
                            new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    if (status==TextToSpeech.SUCCESS)  {
                                        ldef=Locale.getDefault();
                                    }
                                }
                            });
                    new CountDownTimer(8000, 2000) {
                        @Override
                        public void onTick(long l) {
                            if (i<=3)
                                speech=tts.speak(stadb[i], TextToSpeech.QUEUE_FLUSH, null,null);
                            i++;
                        }

                        @Override
                        public void onFinish() {
                            sensorManager.unregisterListener(sensorEventListener);
                            startActivity(gotostart);
                        }
                    }.start();

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
}