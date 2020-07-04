package ca.jisan.mcalcpro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ca.roumani.i2c.MPro;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener {

    private static final String TAG = "DEBUG: MCalcPro";
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"OnCreate");
        this.tts = new TextToSpeech(this,this);
    }

    public void onInit(int initStatus) {
        this.tts.setLanguage(Locale.US);
    }

    public void analyzedClick(View v){

        Log.d(TAG,"analyze button Clicked");
        try {
            MPro mp = new MPro();

            EditText cashPrice =  findViewById(R.id.pBox);      // cash
            String csh = cashPrice.getText().toString();
            mp.setPrinciple(csh);

            EditText amortization =  findViewById(R.id.aBox);      // amort
            String amz = cashPrice.getText().toString();
            mp.setPrinciple(amz);

            EditText interest =  findViewById(R.id.iBox);      // interest
            String inter = cashPrice.getText().toString();
            mp.setPrinciple(inter);

            String s = "Monthly Payment = " + mp.computePayment("%.2f");
            s += "\n\n";
            s += "By making the payments monthly for " + amz + " years, the mortgage will be paid in full. But if you " +
                    "terminate the mortgage on its nth anniversary, the balance still owing depends on n as shown below: \n\n\n";

            s += String.format(Locale.CANADA,"%8d", 0) + mp.outstandingAfter(0, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 1) + mp.outstandingAfter(1, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 2) + mp.outstandingAfter(2, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 3) + mp.outstandingAfter(3, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 4) + mp.outstandingAfter(4, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 5) + mp.outstandingAfter(5, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 6) + mp.outstandingAfter(6, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 7) + mp.outstandingAfter(7, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 8) + mp.outstandingAfter(8, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 9) + mp.outstandingAfter(9, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 10) + mp.outstandingAfter(10, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 11) + mp.outstandingAfter(11, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 12) + mp.outstandingAfter(12, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 13) + mp.outstandingAfter(13, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 14) + mp.outstandingAfter(14, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 15) + mp.outstandingAfter(15, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 16) + mp.outstandingAfter(16, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 17) + mp.outstandingAfter(17, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 18) + mp.outstandingAfter(18, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 19) + mp.outstandingAfter(19, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 20) + mp.outstandingAfter(20, "%16.0f\n");
            s += "\n\n";

            //output
        TextView txt = (TextView) findViewById(R.id.output);
//
//        String op = "I plan to specialize in Full stack Development, with expertise  in both software and web engineering and have specialization in ML, Deep learning, big data, Cyber security and project management.  \n" +
//                "Additionally, I aspire to be a part of and potentially play key roles in innovative projects that will change the future of the  world and society we live in.\n" +
//                "\n" +
//                "Tell us about yourself;\n" +
//                "\n" +
//                "I am a challenge driven individual with a strong focus towards work and a deep interest in learning new technologies. I am a quick learner and always driven to work on innovative and complex projects, with a passion for solving problems and delivering best possible results. \n";
//
        txt.setText(s);
        txt.setMovementMethod(new ScrollingMovementMethod());
        tts.speak(s, TextToSpeech.QUEUE_FLUSH,null);
        } catch (Exception e) {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged called. We can't check on the computer because we won't be able to shake the laptop");
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];

        double a = Math.sqrt(ax*ax + ay*ay + az*az);
        if (a>20) {
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
