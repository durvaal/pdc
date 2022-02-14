package br.com.ufrn.projeto_2.dao;

import br.com.ufrn.projeto_2.domain.Permission;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class PermissionDao extends GenericDao<Permission, Integer>{
	
	public PermissionDao() {
       super(Permission.class);
    }   

}
