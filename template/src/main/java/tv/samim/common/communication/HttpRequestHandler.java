package tv.samim.common.communication;

import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Auhtor: maJid~ASGARI
 */
@SuppressWarnings({ "unchecked", "restriction" })
public class HttpRequestHandler {

	public enum ResponseType {
		json, xml, plain
	}

	static <T> UrlAnswer<T> getResponse(Class<T> clazz,
			ResponseType responseType, String lastSessionId, final String url,
			String... httpRequestProperties) {
		try {
			UrlAnswer<T> answer = new UrlAnswer<>();
			HttpURLConnection httpURLConnection = new HttpURLConnection(
					new URL(url), Proxy.NO_PROXY);

			// JSESSIONID=CF8AA566760C8D85728D5485BAC3AA2F; Path=/welcome/;
			// HttpOnly
			if (lastSessionId != null) {
				httpURLConnection.addRequestProperty("Cookie", "JSESSIONID="
						+ lastSessionId);
			}
			if (httpRequestProperties != null
					&& httpRequestProperties.length > 0) {
				for (int i = 0; i < httpRequestProperties.length; i = i + 2) {
					String key = httpRequestProperties[i];
					String val = httpRequestProperties[i + 1];
					// httpURLConnection.addRequestProperty(key, val);
					httpURLConnection.setRequestProperty(key, val);
				}
			}
			httpURLConnection.connect();
			Map<String, List<String>> heMap = httpURLConnection
					.getHeaderFields();

			List<String> cookies = heMap.get("Set-Cookie");

			if (cookies != null) {
				String sessionIdString = cookies.get(0);
				if (sessionIdString.contains(";")
						&& sessionIdString.startsWith("JSESSIONID"))
					answer.sessionId = sessionIdString.substring(11,
							sessionIdString.indexOf(';'));
				else
					answer.sessionId = sessionIdString;
			}

			switch (responseType) {
			case plain:
				BufferedReader inputTxtReader = new BufferedReader(
						new BufferedReader(new InputStreamReader(
								httpURLConnection.getInputStream(),
								Charset.forName("UTF-8"))));

				String str;
				@SuppressWarnings("unused")
				String fileInStr = "";

				str = inputTxtReader.readLine();
				if (str != null) {
					str = URLDecoder.decode(str, "UTF-8");
				}
				while (!(str == null)) {
					fileInStr += (str + "\r\n");
					str = inputTxtReader.readLine();
					if (str != null) {
						str = URLDecoder.decode(str, "UTF-8");
					}
				}
				answer.result = (T) str;
				break;
			case json:
				/*answer.result = JsonTools.jsonStreamToObject(clazz,
						httpURLConnection.getInputStream(), "result");
				*/break;
			case xml:
				/*answer.result = XmlTools.xmlStreamToObject(clazz,
						httpURLConnection.getInputStream());
				*/break;
			}
			return answer;
		} catch (Exception exp) {
			//Logger.logException(exp,false);
			return null;
		}
	}
}
