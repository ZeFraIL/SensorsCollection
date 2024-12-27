package zeev.fraiman.sensorscollection;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListAllSensors extends AppCompatActivity {

    ListView lvAllSensors;
    Button bView;
    ArrayList<String> allSensors;
    ArrayAdapter<String> adapter;
    Context context;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_sensors);

        initElements();

        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allSensors=new ArrayList<>();
                SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
                for (Sensor sensor : sensorList) {
                    allSensors.add(sensor.getName() + "(" + sensor.getType()+")");
                }
                adapter=new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1,
                        allSensors);
                lvAllSensors.setAdapter(adapter);
            }
        });
    }



    private void initElements() {
        context=this;
        lvAllSensors=findViewById(R.id.lvAllSensors);
        bView=findViewById(R.id.bView);
    }
}