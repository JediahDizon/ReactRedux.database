package domain;

public class Goal {	
	private Long id;
	private String title;
	private String description;
	private Long timestamp;
	
	public Goal() {}
	public Goal(Long id, String title, String description, Long timestamp) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.timestamp = timestamp != null ? timestamp : System.currentTimeMillis();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
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
