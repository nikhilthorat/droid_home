package org.eclipse.paho.android.service.sample;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import android.content.Context;
import android.widget.Toast;

/**
 * Handles call backs from the MQTT Client
 *
 */
public class MqttCallbackHandler implements MqttCallback {

    /** {@link Context} for the application used to format and import external strings**/
    private Context context;

    /**
     * Creates an <code>MqttCallbackHandler</code> object
     * @param context The application's context
     */
    public MqttCallbackHandler(Context context, String clientHandle)
    {
        this.context = context;
    }

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.Throwable)
     */
    @Override
    public void connectionLost(Throwable cause) {

        if (cause != null) {
            Notify.toast(context, "Connection Lost", Toast.LENGTH_LONG);
        }
    }

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String, org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String msg_payload = new String(message.getPayload());
        String toast_msg = "Message Arrived -> " + topic + " : " + msg_payload;
        Notify.toast(context, toast_msg, Toast.LENGTH_LONG);
    }

    /**
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken)
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Do nothing
    }

}

