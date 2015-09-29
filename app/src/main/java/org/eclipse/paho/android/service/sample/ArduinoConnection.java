package org.eclipse.paho.android.service.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ArduinoConnection extends ActionBarActivity {

    private ArduinoConnection mainThreadContext = this;
    public Connection HomeConnection = new Connection(mainThreadContext);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Required call through to Activity.onCreate()
        // Restore any saved instance state
        super.onCreate(savedInstanceState);

        // Set content view
        setContentView(R.layout.main);
        final Button button = (Button) findViewById(R.id.connectButton);


        // Link UI elements to actions in code
        button.setOnClickListener(new View.OnClickListener() {
            //used for starting activities

            public void onClick(View v) {
                //extract client information
                HomeConnection.server = ((AutoCompleteTextView) findViewById(R.id.serverURI))
                        .getText().toString();
                HomeConnection.port = ((EditText) findViewById(R.id.port))
                        .getText().toString();
                HomeConnection.clientId = "MotoG2_MQTT";

                if (HomeConnection.server.equals(ActivityConstants.empty) || HomeConnection.port.equals(ActivityConstants.empty)) {
                    String notificationText = mainThreadContext.getString(R.string.missingOptions);
                    Notify.toast(mainThreadContext, notificationText, Toast.LENGTH_LONG);
                } else {
                    HomeConnection.connect();
                }
            }
        });
    }
}
