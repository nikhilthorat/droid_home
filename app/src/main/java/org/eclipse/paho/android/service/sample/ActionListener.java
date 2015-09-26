package org.eclipse.paho.android.service.sample;

/**
 * Created by Nikhil on 25/09/2015.
 */

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import org.eclipse.paho.android.service.sample.Connection.ConnectionStatus;

/**
 * This Class handles receiving information from the
 * MqttAndroidClient and updating the connection associated with
 * the action
 */
class ActionListener implements IMqttActionListener {
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
    /** The arguments passed to be used for formatting strings**/
    private String[] additionalArgs;
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
     * @param additionalArgs
     *            Used for as arguments for string formating
     */
    public ActionListener(Context context, Action action,
                          Connection ConnectionHandle, String... additionalArgs) {
        this.context = context;
        this.action = action;
        this.additionalArgs = additionalArgs;
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
        Notify.toast(context, "Publish Successful", Toast.LENGTH_LONG);
    }

    /**
     * A subscribe action has been successfully completed, update the connection
     * object associated with the client this action belongs to and then notify
     * the user of success
     */
    private void subscribe() {
        Notify.toast(context, "Subscribed to Home", Toast.LENGTH_LONG);
        if(!switchboard_started)
        {
            Intent swboardIntent = new Intent(context, SwitchBoard.class);
            swboardIntent.putExtra("ConnectionHandle", ConnectionHandle);
            context.startActivity(swboardIntent);
            switchboard_started = true;
        }
        /*ConnectionHandle.publish();*/
    }

    /**
     * A disconnection action has been successfully completed, update the
     * connection object associated with the client this action belongs to and
     * then notify the user of success.
     */
    private void disconnect() {
        Notify.toast(context, "Disconnected from Home", Toast.LENGTH_LONG);
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
        Notify.toast(context, "Publish unsuccessful", Toast.LENGTH_LONG);
    }

    /**
     * A subscribe action was unsuccessful, notify user and update client history
     * @param exception This argument is not used
     */
    private void subscribe(Throwable exception) {
        Notify.toast(context, "Could not subscribe to Home", Toast.LENGTH_LONG);
    }

    /**
     * A disconnect action was unsuccessful, notify user and update client history
     * @param exception This argument is not used
     */
    private void disconnect(Throwable exception) {
        Notify.toast(context, "Could not disconnect from Home", Toast.LENGTH_LONG);
    }

    /**
     * A connect action was unsuccessful, notify the user and update client history
     * @param exception This argument is not used
     */
    private void connect(Throwable exception) {
        Notify.toast(context, "Connection Failed", Toast.LENGTH_LONG);
    }

}