package org.eclipse.paho.android.service.sample;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * This Class handles receiving information from the
 * MqttAndroidClient and updating the connection associated with
 * the action
 */
class ActionListener implements IMqttActionListener {
    private final String TAG = "DROID_HOME";
    /**
     * Actions that can be performed Asynchronously <strong>and</strong> associated with a
     * {@link ActionListener} object
     *
     */
    enum Action {
        /** Connect Action **/
        CONNECT,
        /** Disconnect Action **/
        DISCONNECT,
        /** Subscribe Action **/
        SUBSCRIBE,
        /** Publish Action **/
        PUBLISH
    }

    /**
     * The {@link Action} that is associated with this instance of
     * <code>ActionListener</code>
     **/
    private Action action;
    /** {@link Context} for performing various operations **/
    private Context context;
    private Connection ConnectionHandle;
    boolean switchboard_started = false;

    /**
     * Creates a generic action listener for actions performed form any activity
     *
     * @param context
     *            The application context
     * @param action
     *            The action that is being performed
     */
    public ActionListener(Context context, Action action, Connection ConnectionHandle) {
        this.context = context;
        this.action = action;
        this.ConnectionHandle = ConnectionHandle;
    }

    /**
     * The action associated with this listener has been successful.
     *
     * @param asyncActionToken
     *            This argument is not used
     */
    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        switch (action) {
            case CONNECT :
                connect();
                break;
            case DISCONNECT :
                disconnect();
                break;
            case SUBSCRIBE :
                subscribe();
                break;
            case PUBLISH :
                publish();
                break;
        }

    }

    /**
     * A publish action has been successfully completed, update connection
     * object associated with the client this action belongs to, then notify the
     * user of success
     */
    private void publish() {
        Log.d(TAG, "Publish Successful");
    }

    /**
     * A subscribe action has been successfully completed, update the connection
     * object associated with the client this action belongs to and then notify
     * the user of success
     */
    private void subscribe() {
        Log.d(TAG, "Subscribed to Home");
        if(!switchboard_started)
        {
            /*Intent swboardIntent = new Intent(context, SwitchBoard.class);
            swboardIntent.putExtra("ConnectionHandle", ConnectionHandle);
            context.startActivity(swboardIntent);
            switchboard_started = true;*/

            Intent swboardIntent = new Intent(context, HomeView.class);
            swboardIntent.putExtra("ConnectionHandle", ConnectionHandle);
            context.startActivity(swboardIntent);
            switchboard_started = true;
        }
    }

    /**
     * A disconnection action has been successfully completed, update the
     * connection object associated with the client this action belongs to and
     * then notify the user of success.
     */
    private void disconnect() {
        Log.d(TAG, "Disconnect successful");
    }

    /**
     * A connection action has been successfully completed, update the
     * connection object associated with the client this action belongs to and
     * then notify the user of success.
     */
    private void connect() {
        Notify.toast(context, "Connection Successful", Toast.LENGTH_LONG);
        ConnectionHandle.subscribe();
    }

    /**
     * The action associated with the object was a failure
     *
     * @param token
     *            This argument is not used
     * @param exception
     *            The exception which indicates why the action failed
     */
    @Override
    public void onFailure(IMqttToken token, Throwable exception) {
        switch (action) {
            case CONNECT :
                connect(exception);
                break;
            case DISCONNECT :
                disconnect(exception);
                break;
            case SUBSCRIBE :
                subscribe(exception);
                break;
            case PUBLISH :
                publish(exception);
                break;
        }

    }

    /**
     * A publish action was unsuccessful, notify user and update client history
     *
     * @param exception
     *            This argument is not used
     */
    private void publish(Throwable exception) {
        Log.e(TAG, "Publish failed");
    }

    /**
     * A subscribe action was unsuccessful, notify user and update client history
     * @param exception This argument is not used
     */
    private void subscribe(Throwable exception) {
        Log.e(TAG, "Subscribe failed");
    }

    /**
     * A disconnect action was unsuccessful, notify user and update client history
     * @param exception This argument is not used
     */
    private void disconnect(Throwable exception) {
        Log.e(TAG, "Disconnect failed");
    }

    /**
     * A connect action was unsuccessful, notify the user and update client history
     * @param exception This argument is not used
     */
    private void connect(Throwable exception) {
        Notify.toast(context, "Connection Failed", Toast.LENGTH_LONG);
    }

}