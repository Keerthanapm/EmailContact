import entity.Category;
import entity.Store;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "allCategories")
@SessionScoped
public class AllCategories {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		Store store = new Store();
		categories = store.getAllCategories();
		return categories;
	}

}
