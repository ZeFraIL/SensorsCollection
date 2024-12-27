package zeev.fraiman.sensorscollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Context context;
    String[] names={"List of all sensors","Gyroscope","Magnetometer","Orientation","StepByStep","LightSensor","ShakeAndStart"};
    int n;
    Button[] buttons;
    LinearLayout LLallButtons;
    Class[] classes={ListAllSensors.class,GyroscopeSensor.class,MagnetField.class,OrientationSensor.class,StepByStep.class, LightSensor.class, ShakeAndStart.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        context=MainActivity.this;
        n=names.length;
        buttons=new Button[n];
        LLallButtons=findViewById(R.id.LLallButtons);

        for (int i = 0; i < n; i++) {
            buttons[i]=new Button(context);
            buttons[i].setText(names[i]);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 150 );
            layoutParams.setMargins(10,10,10,40);
            buttons[i].setLayoutParams(new LinearLayout.LayoutParams(layoutParams));
            buttons[i].requestLayout();
            buttons[i].setPadding(10,10,10,10);
            buttons[i].setTextSize(20f);
            Intent go=new Intent(context,classes[i]);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(go);
                }
            });
            LLallButtons.addView(buttons[i]);
        }
    }
}