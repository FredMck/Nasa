package nasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import nasa.beans.MarsCuriosityBean;
import nasa.entity.MarsCuriosityEntity;

@Stateless
public class NasaDao {

	
	
	@PersistenceContext(unitName = "mars_curiosity")
    private EntityManager em;
	
	
	
	public void addImage (MarsCuriosityEntity marsCuriosityEntity) {
		em.persist(marsCuriosityEntity);
		
	}
	
	
	public boolean checkIfImageExists(String image) {
		
		Query getAllImages = em.createNamedQuery("MarsCuriosityEntity.findAll");
		List<String> imageList = getAllImages.getResultList();
		
		if (!imageList.contains(image)) {
			System.out.println("Enters false: " + image);
			imageList.add(image);
			return false;
		}
		System.out.println("Image Exitsts True");
		
		return true;
	}
	
}
