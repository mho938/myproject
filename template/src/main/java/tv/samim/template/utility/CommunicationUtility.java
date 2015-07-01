package tv.samim.template.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @author mojtaba khallash
 */
public class CommunicationUtility {
    public static String escape(String text) {
        text = text.replace("\"", "&quot;");
        text = text.replace("&", "&amp;");
        text = text.replace("\'", "&apos;");
        text = text.replace("<", "&lt;");
        text = text.replace(">", "&gt;");

        return text;
    }

    public static String sendCommand(String parameter, String[] headerKey,
                                     String[] headerValue) {
        return sendCommand("commander", parameter, headerKey, headerValue);
    }

    public static String sendCommand(String servletName, String parameter,
                                     String[] headerKey, String[] headerValue) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        try {
            StringBuilder result = new StringBuilder();
            if (parameter.length() > 0 && !parameter.startsWith("?")) {
                parameter = "?" + parameter;
            }
            url = new URL(/*Config.i().getWelcomeRoot() + servletName + parameter*/"");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            for (int i = 0; i < headerKey.length; i++) {
                conn.setRequestProperty(headerKey[i], headerValue[i]);
            }
            rd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF8"));
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return URLDecoder.decode(result.toString(), "UTF-8");
        } catch (IOException e) {
           // Logger.e(e, "Error in Commander.SendCommand()");
            return null;
        }
    }
}
