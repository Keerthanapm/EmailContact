package entity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "category")
@ManagedBean(name = "manageCategory")
@SessionScoped
public class Category {
	private int id;
	private String name;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
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

	@Column(name = "name", unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void addCategory() {
		Store store = new Store();
		store.addCategory(this);
		clearAll();
	}

	public void deleteCategory() {
		Store store = new Store();
		store.deleteCategory(name);
		clearAll();
	}


	private void clearAll() {
		this.name = "";

	}

}
