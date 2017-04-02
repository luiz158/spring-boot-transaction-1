package br.com.mangani.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.mangani.ContactBean;
import br.com.mangani.PersonBean;

public class ContatoBeanRowMapper implements RowMapper<ContactBean>{
	
	public ContactBean mapRow(ResultSet rs, int i) throws SQLException	{
		PersonBean person = new PersonBean();
		person.setIdPerson(rs.getInt("ID_PERSON"));
  
		ContactBean contato = new ContactBean(person);
		contato.setIdContact(rs.getInt("ID_CONTACT"));
		contato.setDdd(rs.getString("DDD"));
		contato.setPhone(rs.getString("PHONE"));
		return contato;
	}
}
