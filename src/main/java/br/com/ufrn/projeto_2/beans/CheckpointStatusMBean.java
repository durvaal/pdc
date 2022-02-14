package br.com.ufrn.projeto_2.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrn.projeto_2.dao.CheckpointStatusDao;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class CheckpointStatusMBean {
	CheckpointStatusDao checkpointStatusDao = new CheckpointStatusDao();

	public void updateProgress(Integer id, String progress) throws Exception {
		checkpointStatusDao.updateProgress(id, progress);;
	}

}
