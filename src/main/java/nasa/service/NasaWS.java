package nasa.service;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nasa.beans.MarsCuriosityBean;
import nasa.connectivity.RequestToNasa;

@Path("/v1/nasa")
public class NasaWS {
	
	@EJB
	private MarsCuriosityBean marsBean;
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/curiosity")
	public Response marsCuriosity () {
		
		try {
			RequestToNasa requestToNasa = new RequestToNasa();
			//System.out.println("image link: " + requestToNase.marsSpiritConnection());
			
			/*MarsCuriosityBean marsBean = new MarsCuriosityBean();*/
			
			marsBean.persistImage(requestToNasa.marsRoverConnection());
			
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(404).build();
		}
		
		
	}
	
}
