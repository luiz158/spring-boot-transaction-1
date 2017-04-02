package br.com.mangani.dao.impl;

import br.com.mangani.PersonBean;
import br.com.mangani.dao.ContactDAO;
import br.com.mangani.dao.PersonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private ContactDAO contactDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(final PersonBean person) {
		final String query = "INSERT INTO PERSON(NAME) VALUES(?)";

		KeyHolder key = new GeneratedKeyHolder();

		this.jdbc.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query, 1);
				ps.setString(1, person.getName());
				return ps;
			}
		}, key);

		int idPerson = key.getKey().intValue();
		person.setIdPerson(idPerson);

		this.contactDAO.insert(person.getContacts());
	}
}