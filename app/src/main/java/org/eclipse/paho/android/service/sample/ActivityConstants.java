package org.eclipse.paho.android.service.sample;

/**
 * Created by Nikhil on 23/09/2015.
 */
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * This Class provides constants used for returning results from an activity
 *
 */
public class ActivityConstants {

    /** Application TAG for logs where class name is not used*/
    static final String TAG = "DROID HOME";

   /*Default values **/

    /** Default QOS value*/
    static final int defaultQos = 0;
    /** Default timeout*/
    static final int defaultTimeOut = 1000;
    /** Default keep alive value*/
    static final int defaultKeepAlive = 10;
    /** Default SSL enabled flag*/
    static final boolean defaultSsl = false;
    /** Default message retained flag */
    static final boolean defaultRetained = false;
    /** Default last will message*/
    static final MqttMessage defaultLastWill = null;
    /** Default port*/
    static final int defaultPort = 1883;

  /* Bundle Keys */

    /** Server Bundle Key **/
    static final String server = "server";
    /** Port Bundle Key **/
    static final String port = "port";
    /** ClientID Bundle Key **/
    static final String clientId = "clientId";
    /** Topic Bundle Key **/
    static final String topic = "topic";
    /** Message Bundle Key **/
    static final String message = "message";
    /** Retained Flag Bundle Key **/
    static final String retained = "retained";
    /** QOS Value Bundle Key **/
    static final String qos = "qos";
    /** User name Bundle Key **/
    static final String username = "username";
    /** Password Bundle Key **/
    static final String password = "password";
    /** Keep Alive value Bundle Key **/
    static final String keepalive = "keepalive";
    /** Timeout Bundle Key **/
    static final String timeout = "timeout";
    /** SSL Enabled Flag Bundle Key **/
    static final String ssl = "ssl";
    /** SSL Key File Bundle Key **/
    static final String ssl_key = "ssl_key";
    /** Connections Bundle Key **/
    static final String connections = "connections";
    /** Clean Session Flag Bundle Key **/
    static final String cleanSession = "cleanSession";
    /** Action Bundle Key **/
    static final String action = "action";

  /* Useful constants*/

    /** Space String Literal **/
    static final String space = " ";
    /** Empty String for comparisons **/
    static final String empty = "";
}
