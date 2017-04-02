package br.com.mangani;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mangani.dao.PersonDAO;

@SpringBootApplication
public class Main implements CommandLineRunner {
	
	@Autowired
	private PersonDAO personDAO;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public void run(String... args) throws Exception {
		PersonBean person = new PersonBean();
		person.setName("Gregório Dourado");

		List<ContactBean> contacts = new ArrayList<ContactBean>();
		ContactBean contact = new ContactBean(person);
		contact.setDdd("011");
		contact.setPhone("98767-7564");
		contacts.add(contact);

		person.setContacts(contacts);

		this.personDAO.insert(person);

		System.out.println("idPerson = " + person.getIdPerson());
	}
}
