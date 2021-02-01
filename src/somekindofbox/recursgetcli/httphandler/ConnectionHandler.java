package somekindofbox.recursgetcli.httphandler;

import java.net.HttpURLConnection;
import java.net.URLConnection;

public class ConnectionHandler {
	public static HttpURLConnection openHTTPConnection(URLConnection conn) {
		HttpURLConnection http = null;
		try {
			System.out.println("Attempting to open connection");
			http = (HttpURLConnection) conn;
		} catch (Exception e) {
			System.out.println("Failed to open connection, aborting");
			e.printStackTrace();
			System.exit(0);
		}
		if (http != null) {
			System.out.println("Connection opened successfully");
			return http;
		} else {
			System.out.println("i am null, please fix");
			return null;
		}
	}
	
}
