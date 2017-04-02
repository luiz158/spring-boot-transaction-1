package br.com.mangani.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mangani.ContactBean;
import br.com.mangani.dao.ContactDAO;
import br.com.mangani.dao.rowmapper.ContatoBeanRowMapper;

@Repository
public class ContactDAOImpl implements ContactDAO {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ContactBean select(int id) {
		
		String query = String.format("SELECT * FROM CONTACT WHERE CONTACT.ID = %d", id);

		ContactBean contato = (ContactBean) this.jdbc.queryForObject(query, new ContatoBeanRowMapper());

		return contato;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public void insert(final List<ContactBean> contacts) {
		String query = "INSERT INTO CONTACT(ID_PERSON, DDD, PHONE) VALUES (?, ?, ?)";

		this.jdbc.batchUpdate(query, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ContactBean contact = contacts.get(i);
				ps.setInt(1, contact.getIdPerson());
				ps.setString(2, contact.getDdd());
				ps.setString(3, contact.getPhone());
			}

			public int getBatchSize() {
				return contacts.size();
			}
		});
	}
}