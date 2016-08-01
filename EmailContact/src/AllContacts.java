import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import entity.Store;
import entity.Contact;

@ManagedBean(name = "allContacts")
@RequestScoped
public class AllContacts {
	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		Store store = new Store();
		contacts = store.getAllContacts();
		return contacts;
	}
}
