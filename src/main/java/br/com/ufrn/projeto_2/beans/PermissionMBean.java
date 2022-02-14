package br.com.ufrn.projeto_2.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrn.projeto_2.dao.PermissionDao;
import br.com.ufrn.projeto_2.domain.Permission;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class PermissionMBean {
	PermissionDao permissionDao = new PermissionDao();

	public List<Permission> getAll() throws Exception {
		return permissionDao.findAll();
	}
}
