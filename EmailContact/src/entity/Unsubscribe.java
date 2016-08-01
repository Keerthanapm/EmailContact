package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unsubscribe")
public class Unsubscribe {
	private int id;
	private int contactId;
	private int categoryId;

	public Unsubscribe() {
	}

	public Unsubscribe(int contactId, int categoryId) {
		this.contactId = contactId;
		this.categoryId = categoryId;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
