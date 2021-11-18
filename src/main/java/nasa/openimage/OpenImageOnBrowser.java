package nasa.openimage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenImageOnBrowser {

	
	
	public void openImage (String imageUrl) { 
		
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		    try {
		    	
				Desktop.getDesktop().browse(new URI(imageUrl));
				
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
