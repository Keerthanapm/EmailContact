import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entity.Store;

@ManagedBean(name = "unsubscribe")
@SessionScoped
public class ManageUnsubscribe {
	
	private String category;
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void unsubscribe()
	{
		System.out.println(emailId);
		System.out.println(category);
		Store store = new Store();
		store.unsubscribe(emailId,category);
	}
	
}
