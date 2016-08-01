import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entity.Contact;
import entity.Store;

@ManagedBean(name = "selectedItems")
@SessionScoped
public class SelectedItems {

	private String selected;
	private String selectedMsgCategory;

	private List<String> selectedTypes;
	private List<String> selectedContacts;
	private List<String> selectedCategory;

	List<String> contacts = new ArrayList<String>();
	private String mail;

	private String subject;

	public String getSelectedMsgCategory() {
		return selectedMsgCategory;
	}

	public void setSelectedMsgCategory(String selectedMsgCategory) {
		this.selectedMsgCategory = selectedMsgCategory;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void send() {
		System.out.println(mail);
		Store store = new Store();
		contacts = store.getContacts(selectedMsgCategory);
		SendMail send = new SendMail();
		send.send(contacts, mail, subject, selectedMsgCategory);
	}

	public List<String> getSelectedTypes() {
		return selectedTypes;
	}

	public void setSelectedTypes(List<String> selectedTypes) {
		this.selectedTypes = selectedTypes;
	}

	public List<String> getSelectedContacts() {
		return selectedContacts;
	}

	public void setSelectedContacts(List<String> selectedContacts) {
		this.selectedContacts = selectedContacts;
	}

	public List<String> getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(List<String> selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		Store store = new Store();
		contacts = store.getAllContacts();
		return contacts;
	}

	public String goToPage() {
		String page;
		PageNavigate navigate = new PageNavigate();
		page = navigate.goToPage(selected);
		return page;
	}

	public void basedOnType() {
		Store store = new Store();
		store.basedOnType(selectedTypes, selectedCategory);
	}

	public void selectFromList() {
		Store store = new Store();
		store.selectFromList(selectedContacts, selectedCategory);
	}

	public void all() {
		Store store = new Store();
		store.all(selectedCategory);
	}
}
