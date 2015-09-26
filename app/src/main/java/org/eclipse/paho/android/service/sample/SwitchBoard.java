package org.eclipse.paho.android.service.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by Nikhil on 26/09/2015.
 */
public class SwitchBoard  extends Activity {
    private Switch Bulb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switchboard);

        Bulb1 = (Switch) findViewById(R.id.bulb1);

        //attach a listener to check for changes in state
        Bulb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    Intent intent = getIntent();
                    Connection c = (Connection) intent.getSerializableExtra("ConnectionHandle");

                    c.publish();

                }else{

                }

            }
        });

    }
}
