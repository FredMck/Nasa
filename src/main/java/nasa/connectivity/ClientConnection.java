package nasa.connectivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


import nasa.builder.ConnectionBuilder;

public class ClientConnection {

	private final static Logger logger = Logger.getLogger(ClientConnection.class.getName());
	
	
	
	public HttpsURLConnection makeUrlConnection (ConnectionBuilder connectionBuilder) {
		
		
		
		HttpsURLConnection connection = null;
		try {
			
			
			String nasaApiKey = "PrJOhd1Dx06Et5pK2S9aRQAuRc4WPG7DALdW407u";
			String urlAppendKey = connectionBuilder.getUrl()+"&api_key="+nasaApiKey;
			
			URL url = new URL(urlAppendKey);
			connection = (HttpsURLConnection) url.openConnection();
			loadTheKeystore(connection);
			connection.setRequestMethod(connectionBuilder.getMethod());
			
			
			
		} catch (MalformedURLException e) {
			logger.info("URL invalid");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Cannot open a connection");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	
	
	private SSLContext loadTheKeystore(HttpsURLConnection connection) {
		
		SSLContext ctx = null;
		SSLServerSocketFactory ssf = null;
		try {
           
            TrustManagerFactory tmf;
            KeyStore ks;
            char[] passphrase = "changeit".toCharArray();

            ctx = SSLContext.getInstance("TLS");
            tmf = TrustManagerFactory.getInstance("X.509");
            ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("D:\\Development\\SSL\\truststore.jks"), passphrase);
            tmf.init(ks);
            ctx.init(null, tmf.getTrustManagers(), null);

            ssf = ctx.getServerSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		SSLSocketFactory factory = ctx.getSocketFactory();
        connection.setSSLSocketFactory(factory);
		
		return ctx;
	}
	
	
	
}
