package br.com.mangani.dao;

import br.com.mangani.ContactBean;
import java.util.List;

public interface ContactDAO {

	public ContactBean select(int paramInt);

	public void insert(List<ContactBean> paramList);
}
