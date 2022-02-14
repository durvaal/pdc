package br.com.ufrn.projeto_2.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrn.projeto_2.dao.ActuationLevelDao;
import br.com.ufrn.projeto_2.domain.ActuationLevel;
import br.com.ufrn.projeto_2.util.FacesContextCustom;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class ActuationLevelMBean {
	ActuationLevel actuationLevel = new ActuationLevel();
	
	ActuationLevelDao actuationLevelDao = new ActuationLevelDao();
	
	/**
	 * @return the actuationLevel
	 */
	public ActuationLevel getActuationLevel() {
		return actuationLevel;
	}

	/**
	 * @param actuationLevel the actuationLevel to set
	 */
	public void setActuationLevel(ActuationLevel actuationLevel) {
		this.actuationLevel = actuationLevel;
	}

	public void create() {
		try {
			actuationLevelDao.save(this.actuationLevel);
			FacesContextCustom.addMessage("validateSaveFormMessage", "Criação realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateSaveFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.actuationLevel = new ActuationLevel();
		}
	}
	
	public void update() {
		try {
			actuationLevelDao.update(this.actuationLevel);
			FacesContextCustom.addMessage("validateEditFormMessage", "Atualização realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateEditFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.actuationLevel = new ActuationLevel();
		}
	}
	
	public void delete() {
		try {
			actuationLevelDao.delete(this.actuationLevel);
			FacesContextCustom.addMessage("validateDeleteFormMessage", "Exclusão realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateDeleteFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.actuationLevel = new ActuationLevel();
		}
	}

	public List<ActuationLevel> getAll() throws Exception {
		return actuationLevelDao.findAll();
	}

}
