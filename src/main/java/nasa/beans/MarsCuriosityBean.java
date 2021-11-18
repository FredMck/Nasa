package nasa.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import nasa.dao.NasaDao;
import nasa.entity.MarsCuriosityEntity;

@Stateless
public class MarsCuriosityBean {
	
	@EJB 
	private NasaDao nasaDao;

	//@PersistenceContext(unitName = "mars_curiosity")
    //private EntityManager em;
	
	
	public void persistImage (MarsCuriosityEntity marsCuriosityEntity) {
		
		/*NasaDao nasaDao = new NasaDao();
		nasaDao.addImage(image);*/
		
		//em.persist(marsCuriosityEntity);
		checkAllImages(marsCuriosityEntity.getImage_location());
		nasaDao.addImage(marsCuriosityEntity);
	}
	
	
	private void checkAllImages (String image) {
		nasaDao.checkIfImageExists(image);
	}
	
}
