package nasa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="mars_curiosity_rover")
@NamedQueries({
	 @NamedQuery(name="MarsCuriosityEntity.findAll", query="SELECT m.image_location FROM MarsCuriosityEntity m")
})
public class MarsCuriosityEntity {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@Column(name="image_location")
	private String image_location;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage_location() {
		return image_location;
	}
	public void setImage_location(String image_location) {
		this.image_location = image_location;
	}
	
	
	
}
