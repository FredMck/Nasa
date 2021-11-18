package nasa.connectivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import nasa.builder.ConnectionBuilder;
import nasa.entity.MarsCuriosityEntity;
import nasa.openimage.OpenImageOnBrowser;

public class RequestToNasa {

	
	
	/*public static void main(String[] args) {
		marsRoverConnection();
		//marsOpportunityConnection();
		//marsSpiritConnection();
	}*/

	
	public MarsCuriosityEntity marsSpiritConnection() {
		ConnectionBuilder connBuilder = new ConnectionBuilder.Builder("https://api.nasa.gov/mars-photos/api/v1/rovers/spirit/photos?sol=1000", "GET")
				.build();
		ClientConnection clientConnection = new ClientConnection();
		HttpsURLConnection connection = clientConnection.makeUrlConnection(connBuilder);
		JSONObject json = checkResponse(connection);
		
		JSONArray array = json.getJSONArray("photos");
		List<String> photoList = new ArrayList<>();
		
		for (int i = 0; i < array.length(); i++) {
			photoList.add(i, array.getJSONObject(i).get("img_src").toString());
			
		}
		
		int low = 0;
		int high = array.length();
		Random r = new Random();
		int result = r.nextInt(high-low) + low;
		
		
		
		OpenImageOnBrowser image = new OpenImageOnBrowser();
		image.openImage(photoList.get(result));
		
		MarsCuriosityEntity marsEntity = new MarsCuriosityEntity();
		marsEntity.setImage_location(photoList.get(result));
		return marsEntity;
	}
	
	public void marsOpportunityConnection() {
		
		ConnectionBuilder connBuilder = new ConnectionBuilder.Builder("https://api.nasa.gov/mars-photos/api/v1/rovers/opportunity/photos?sol=1000", "GET")
				.build();
		ClientConnection clientConnection = new ClientConnection();
		HttpsURLConnection connection = clientConnection.makeUrlConnection(connBuilder);
		JSONObject json = checkResponse(connection);
		
		JSONArray array = json.getJSONArray("photos");
		List<String> photoList = new ArrayList<>();
		
		for (int i = 0; i < array.length(); i++) {
			photoList.add(i, array.getJSONObject(i).get("img_src").toString());
			
		}
		
		int low = 0;
		int high = array.length();
		Random r = new Random();
		int result = r.nextInt(high-low) + low;
		
		
		
		OpenImageOnBrowser image = new OpenImageOnBrowser();
		image.openImage(photoList.get(result));
	}
	
	
	public MarsCuriosityEntity marsRoverConnection () {
		
		
		ConnectionBuilder connBuilder = new ConnectionBuilder.Builder("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000", "GET")
				.build();
		
		ClientConnection clientConnection = new ClientConnection();
		HttpsURLConnection connection = clientConnection.makeUrlConnection(connBuilder);
		JSONObject json = checkResponse(connection);
		
		JSONArray array = json.getJSONArray("photos");
		List<String> photoList = new ArrayList<>();
		
		for (int i = 0; i < array.length(); i++) {
			photoList.add(i, array.getJSONObject(i).get("img_src").toString());
			
		}
		
		int low = 0;
		int high = array.length();
		Random r = new Random();
		int result = r.nextInt(high-low) + low;
		
		
		//Open image in browser
		OpenImageOnBrowser image = new OpenImageOnBrowser();
		image.openImage(photoList.get(result));
		
		
		//Persist new image to database
		MarsCuriosityEntity marsEntity = new MarsCuriosityEntity();
		marsEntity.setImage_location(photoList.get(result));
	
		
		return marsEntity;
		
	}
	
	
	
	
	
	private static JSONObject checkResponse (HttpsURLConnection connection) {
		
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			if (200 <= connection.getResponseCode() && connection.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				System.out.println("ResponseCode: " + connection.getResponseCode());
				if (connection.getInputStream() != null) {
					br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				} else {
					br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				}
				
			}
			
			
			String line;
	        while((line = br.readLine()) != null) {
	        	sb.append(line);
	        }
	        br.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(sb.toString());
		return json;
		
		
	}
	
}
