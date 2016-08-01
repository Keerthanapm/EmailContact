package entity;

import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

public class Store {

	/* Add new user contact */
	public void addContact(Contact contact) {
		Transaction transaction = null;
		boolean success = false;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(contact);
			session.getTransaction().commit();
			success = true;
			transaction.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		session.close();
		if (success) {
			FacesContext.getCurrentInstance().addMessage(
					"test",
					new javax.faces.application.FacesMessage(
							"Successfully added"));
			System.out.println("successfully saved");
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"test",
					new javax.faces.application.FacesMessage(
							"Email id already present"));
			System.out.println("Email id already present");
		}
	}

	/* Add new category */
	public void addCategory(Category category) {
		Transaction transaction = null;
		boolean success = false;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(category);
			session.getTransaction().commit();
			success = true;
			transaction.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		session.close();
		if (success) {
			FacesContext.getCurrentInstance().addMessage(
					"test",
					new javax.faces.application.FacesMessage(
							"Successfully added"));
			System.out.println("successfully saved");
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"test",
					new javax.faces.application.FacesMessage(
							"Record already present"));
			System.out.println("record already present");
		}
	}

	/* Delete an category from the record */
	@SuppressWarnings({ "rawtypes" })
	public void deleteCategory(String name) {
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			org.hibernate.query.Query delete = session
					.createQuery("delete from Category where name = :name");
			delete.setParameter("name", name);
			int result = delete.executeUpdate();

			if (result > 0) {
				FacesContext.getCurrentInstance().addMessage(
						"test",
						new javax.faces.application.FacesMessage(
								"Record removed"));
				System.out.println("Record removed");
			} else {
				FacesContext.getCurrentInstance().addMessage(
						"test",
						new javax.faces.application.FacesMessage(
								"Record not removed"));
				System.out.println("Record not removed");
			}

		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}

	/* Retrieving categories present */
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			categories = session.createQuery("select name from Category")
					.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return categories;
	}

	/* Retrieve all existing contacts */
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			contacts = session.createQuery("select emailId from Contact")
					.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return contacts;
	}

	/* Assign Contacts based on type */
	@SuppressWarnings({ "rawtypes" })
	public void basedOnType(List<String> selectedTypes,
			List<String> selectedCategory) {
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			if (transaction == null) {
				System.out.println("not valid");
			} else {
				System.out.println(selectedTypes.size());
				System.out.println(selectedCategory.size());
				int index1, index2;
				for (index1 = 0; index1 < selectedTypes.size(); index1++) {
					for (index2 = 0; index2 < selectedCategory.size(); index2++) {
						org.hibernate.query.Query assignContacts = session
								.createQuery("insert into AssignContacts(contactId,categoryId)"
										+ "select con.id, cat.id "
										+ "from  Category as cat, Contact as con "
										+ "where con.type=:type "
										+ "and cat.name=:name "
										+ "and con.id not in "
										+ "(select asg.contactId from AssignContacts asg, Category cat1 "
										+ " where asg.categoryId = cat1.id "
										+ "and cat1.id in (select c.id from Category c where c.name=:name))"
										+ "and con.id not in "
										+ "(select uns.contactId from Unsubscribe uns, Category cat2 "
										+ " where uns.categoryId = cat2.id "
										+ "and cat2.id in (select c1.id from Category c1 where c1.name=:name))");

						assignContacts.setParameter("type", selectedTypes.get(index1));
						assignContacts.setParameter("name", selectedCategory.get(index2));
						int result = assignContacts.executeUpdate();
						if (result > 0) {
							FacesContext.getCurrentInstance().addMessage(
									"test",
									new javax.faces.application.FacesMessage(
											"contacts assigned"));
							System.out.println("contacts assigned");
						} else {
							FacesContext.getCurrentInstance().addMessage(
									"test",
									new javax.faces.application.FacesMessage(
											"Contacts not assigned"));
							System.out.println("contacts not assigned");
						}
					}
				}
			}
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}

	/* Assign Contacts from list */
	@SuppressWarnings({ "rawtypes" })
	public void selectFromList(List<String> selectedContacts,
			List<String> selectedCategory) {
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			System.out.println(selectedContacts.size());
			System.out.println(selectedCategory.size());
			int index1, index2;
			for (index1 = 0; index1 < selectedContacts.size(); index1++) {
				for (index2 = 0; index2 < selectedCategory.size(); index2++) {
					org.hibernate.query.Query assignContacts = session
							.createQuery("insert into AssignContacts(contactId,categoryId) "
									+ "select con.id, cat.id "
									+ "from Contact as con , Category as cat "
									+ "where con.emailId = :emailId "
									+ "and cat.name = :name "
									+ "and con.id not in "
									+ "(select asg.contactId from AssignContacts asg, Category cat1 "
									+ " where asg.categoryId = cat1.id "
									+ "and cat1.id in (select c.id from Category c where c.name=:name))"
									+ "and con.id not in "
									+ "(select uns.contactId from Unsubscribe uns, Category cat2 "
									+ " where uns.categoryId = cat2.id "
									+ "and cat2.id in (select c1.id from Category c1 where c1.name=:name))");
				
					assignContacts.setParameter("emailId", selectedContacts.get(index1));
					assignContacts.setParameter("name", selectedCategory.get(index2));
					int result = assignContacts.executeUpdate();
					if (result > 0) {
						FacesContext.getCurrentInstance().addMessage(
								"test",
								new javax.faces.application.FacesMessage(
										"contacts assigned"));
						System.out.println("contacts assigned");
					} else {
						FacesContext.getCurrentInstance().addMessage(
								"test",
								new javax.faces.application.FacesMessage(
										"Contacts not assigned"));
						System.out.println("contacts not assigned");
					}
				}
			}
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}

	/* Assign all contacts */
	@SuppressWarnings({ "rawtypes" })
	public void all(List<String> selectedCategory) {
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			System.out.println(selectedCategory.size());
			int index;
			for (index = 0; index < selectedCategory.size(); index++) {
				org.hibernate.query.Query assignContacts = session
						.createQuery("insert into AssignContacts(contactId,categoryId) "
								+ "select con.id, cat.id "
								+ "from Contact as con ,Category as cat "
								+ "where cat.name=:name "
								+ "and con.id not in "
								+ "(select asg.contactId from AssignContacts asg, Category cat1 "
								+ " where asg.categoryId = cat1.id "
								+ "and cat1.id in (select c.id from Category c where c.name=:name))"
								+ "and con.id not in "
								+ "(select uns.contactId from Unsubscribe uns, Category cat2 "
								+ " where uns.categoryId = cat2.id "
								+ "and cat2.id in (select c1.id from Category c1 where c1.name=:name))");
				assignContacts.setParameter("name", selectedCategory.get(index));

				int result = assignContacts.executeUpdate();
				if (result > 0) {
					FacesContext.getCurrentInstance().addMessage(
							"test",
							new javax.faces.application.FacesMessage(
									"contacts assigned"));
					System.out.println("contacts assigned");
				} else {
					FacesContext.getCurrentInstance().addMessage(
							"test",
							new javax.faces.application.FacesMessage(
									"Contacts not assigned"));
					System.out.println("contacts not assigned");
				}
			}
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}

	/* Edit category name */
	@SuppressWarnings({ "rawtypes" })
	public void editCategory(String name, String newName) {
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			org.hibernate.query.Query edit = session
					.createQuery("update Category set name = :newName where name = :name");
			edit.setParameter("name", name);
			edit.setParameter("newName", newName);
			int result = edit.executeUpdate();

			if (result > 0) {
				FacesContext.getCurrentInstance().addMessage(
						"test",
						new javax.faces.application.FacesMessage(
								"Record edited"));
				System.out.println("Record edited");
			} else {
				FacesContext.getCurrentInstance().addMessage(
						"test",
						new javax.faces.application.FacesMessage(
								"Record not edited"));
				System.out.println("Record not edited");
			}

		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}

	/* Retrieve all existing contacts */
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<String> getContacts(String selectedMsgCategory) {
		List<String> contacts = new ArrayList<String>();
		Transaction transaction = null;
		int index;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			String hql = "select emailId from Contact " + "where id in "
					+ "(select contactId from AssignContacts "
					+ "where categoryId in " + "(select id from Category "
					+ " where name = :name))";
			contacts = session.createQuery(hql)
					.setParameter("name", selectedMsgCategory).list();
			for (index = 0; index < contacts.size(); index++) {
				System.out.println(contacts.get(index));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return contacts;
	}

	/* Unsubscribe contacts */
	@SuppressWarnings({ "rawtypes" })
	public void unsubscribe(String emailId, String category) {
		Transaction transaction = null;
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		try {
			transaction = session.beginTransaction();
			org.hibernate.query.Query unsubscribe = session
					.createQuery("insert into Unsubscribe(contactId,categoryId) "
							+ "select con.id, cat.id "
							+ "from Contact as con ,Category as cat "
							+ "where cat.name=:name "
							+ "and con.emailId=:emailId "
							+ "and con.id not in "
							+ "(select uns.contactId from Unsubscribe uns, Category cat1 "
							+ " where uns.categoryId = cat1.id "
							+ "and cat1.id in (select c.id from Category c where c.name=:name)) ");
			unsubscribe.setParameter("name", category);
			unsubscribe.setParameter("emailId", emailId);
			int result = unsubscribe.executeUpdate();
			unsubscribe = session.createQuery("delete from AssignContacts "
					+ "where contactId in "
					+ "(select id from Contact where emailId = :emailId) "
					+ "and categoryId in "
					+ "(select id from Category where name = :name)");

			unsubscribe.setParameter("name", category);
			unsubscribe.setParameter("emailId", emailId);
			result += unsubscribe.executeUpdate();
			if (result > 0) {
				FacesContext.getCurrentInstance().addMessage(
						"test",
						new javax.faces.application.FacesMessage(
								"Successfully unsubscribed"));
				System.out.println("successfully unsubscribed");
			} else {
				FacesContext.getCurrentInstance().addMessage(
						"test",
						new javax.faces.application.FacesMessage(
								"Not unsubscribed"));
				System.out.println("Not unsubscribed");

			}
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}

}
