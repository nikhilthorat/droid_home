package org.eclipse.paho.android.service.sample;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import java.io.Serializable;

/**
 * Created by Nikhil on 25/09/2015.
 */
public class Connection implements Serializable {

    /** {@link Context} for performing various operations **/
    public static Context context;
    public static MqttAndroidClient mqtt_client;
    public static String server;
    public static String clientId;
    public static String port;

    /**
     * Connections status for  a connection
     */
    enum ConnectionStatus {

        /** Client is Connecting **/
        CONNECTING,
        /** Client is Connected **/
        CONNECTED,
        /** Client is Disconnecting **/
        DISCONNECTING,
        /** Client is Disconnected **/
        DISCONNECTED,
        /** Client has encountered an Error **/
        ERROR,
        /** Status is unknown **/
        NONE
    }

    public Connection(Context context) {
        this.context = context;
    }

    public void connect()
    {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        String uri = null;
        int portno = Integer.parseInt(port);
        // last will message
        String message = (String) (ActivityConstants.message);
        String topic = (String) (ActivityConstants.topic);
        Integer qos = (Integer) 1;
        Boolean retained = false;
        boolean cleanSession = false;

        // connection options
        String username = (String) (ActivityConstants.username);
        String password = (String) (ActivityConstants.password);
        int timeout = (Integer) 10000;
        int keepalive = (Integer) 50000;

        String notificationText = "Connecting " + clientId + " to : "+ server + "@" + port;
        Notify.toast(context, notificationText, Toast.LENGTH_LONG);

        final ActionListener callback = new ActionListener(context,
                ActionListener.Action.CONNECT, this, (String) null);

        uri = "tcp://" + server + ":" + portno;

        MqttAndroidClient client = new MqttAndroidClient(context, uri, clientId);
        mqtt_client = client;

        client.setCallback(new MqttCallbackHandler(context, clientId));

        conOpt.setCleanSession(cleanSession);
        conOpt.setConnectionTimeout(timeout);
        conOpt.setKeepAliveInterval(keepalive);
        if (!username.equals(ActivityConstants.empty)) {
            conOpt.setUserName(username);
        }
        if (!password.equals(ActivityConstants.empty)) {
            conOpt.setPassword(password.toCharArray());
        }

        try {
            client.connect(conOpt, null, callback);
        }
        catch (MqttException e) {
            Log.e(context.getClass().getCanonicalName(),
                    "MqttException Occured", e);
        }
    }

    public void subscribe()
    {
        String topic = "HOME/SWITCH1";
        String[] topics = new String[1];
        topics[0] = topic;

        try {
            mqtt_client.subscribe(topic, 0, null, new ActionListener(context, ActionListener.Action.SUBSCRIBE, this, (String) null));
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + topic + " the client with the handle " + clientId, e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + topic + " the client with the handle " + clientId, e);
        }
    }

    public void publish() {
        String topic = "HOME/SWITCH1";
        String message = "ONN";
        try {
            mqtt_client.publish(topic, message.getBytes(), 0, true, null, new ActionListener(context, ActionListener.Action.PUBLISH, this, (String) null));
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientId, e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientId, e);
        }
    }
}
