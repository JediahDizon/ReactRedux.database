
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Goals")
public class Goal {

	@Id
	@GenericGenerator(name="Goals_ID", strategy="increment")
	@GeneratedValue(generator="Goals_ID")
	@Column(name="ID")
	private Long id;
	
	@Column(name="TITLE")	
	private String title;
	
	@Column(name="DESCRIPTION")	
	private String description;
	
	@Column(name="TIMESTAMP")
	private Long timestamp;
	public Goal() {}
	public Goal(String title, String description) {
		this.id = null;
		this.title = title;
		this.description = description;
		this.timestamp = System.currentTimeMillis();
	}
	public Goal(Long id, String title, String description, Long timestamp) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.timestamp = timestamp;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getTimestamp() {
		return this.timestamp;
	}
	public void setTitle(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return	"ID: " + this.getId() +
				"Title: " + this.getTitle() + 
				"Description: " + this.getDescription() +
				"Timestamp: " + this.getTimestamp();
	}
}