import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entity.Store;



@ManagedBean(name = "editCategory")
@SessionScoped
public class EditCategory {

	private String name, newName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public void editCategory() {
		Store store = new Store();
		store.editCategory(name,newName);
	}
	
}
