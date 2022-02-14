package br.com.ufrn.projeto_2.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrn.projeto_2.dao.ActuationAreaDao;
import br.com.ufrn.projeto_2.domain.ActuationArea;
import br.com.ufrn.projeto_2.util.FacesContextCustom;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class ActuationAreaMBean {
	ActuationArea actuationArea = new ActuationArea();
	
	ActuationAreaDao actuationAreaDao = new ActuationAreaDao();

	/**
	 * @return the actuationArea
	 */
	public ActuationArea getActuationArea() {
		return actuationArea;
	}

	/**
	 * @param actuationArea the actuationArea to set
	 */
	public void setActuationArea(ActuationArea actuationArea) {
		this.actuationArea = actuationArea;
	}
	
	public void create() {
		try {
			actuationAreaDao.save(this.actuationArea);
			FacesContextCustom.addMessage("validateSaveFormMessage", "Criação realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateSaveFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.actuationArea = new ActuationArea();
		}
	}
	
	public void update() {
		try {
			actuationAreaDao.update(this.actuationArea);
			FacesContextCustom.addMessage("validateEditFormMessage", "Atualização realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateEditFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.actuationArea = new ActuationArea();
		}
	}
	
	public void delete() {
		try {
			actuationAreaDao.delete(this.actuationArea);
			FacesContextCustom.addMessage("validateDeleteFormMessage", "Exclusão realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateDeleteFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.actuationArea = new ActuationArea();
		}
	}

	public List<ActuationArea> getAll() throws Exception {
		return actuationAreaDao.findAll();
	}

}
