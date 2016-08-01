package entity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "contact")
@ManagedBean(name = "manageContact")
@RequestScoped
public class Contact {
	private int id;
	private String emailId;
	private String firstName;
	private String lastName;
	private String type;

	public Contact() {
	}

	public Contact(String emailId, String firstName, String lastName,
			String type) {
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
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

	@Column(name = "emailid", unique=true)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastname")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addContact() {
		Store store = new Store();
		store.addContact(this);
		clearAll();
	}

	private void clearAll() {
		this.emailId = "";
		this.firstName = "";
		this.lastName = "";
		this.type = "";
	}

}
