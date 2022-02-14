package br.com.ufrn.projeto_2.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrn.projeto_2.dao.ActuationLevelDao;
import br.com.ufrn.projeto_2.dao.CareerPathDao;
import br.com.ufrn.projeto_2.dao.CollaboratorDao;
import br.com.ufrn.projeto_2.domain.CareerPath;
import br.com.ufrn.projeto_2.util.FacesContextCustom;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class CareerPathMBean {
	CareerPath careerPath = new CareerPath();
	Integer collaboratorId;
	Integer actuationLevelId;
	
	CareerPathDao careerPathDao = new CareerPathDao();
	CollaboratorDao collaboratorDao = new CollaboratorDao();
	ActuationLevelDao actuationLevelDao = new ActuationLevelDao();
	
	/**
	 * @return the careerPath
	 */
	public CareerPath getCareerPath() {
		return careerPath;
	}
	
	/**
	 * @param careerPath the careerPath to set
	 */
	public void setCareerPath(CareerPath careerPath) {
		this.careerPath = careerPath;
	}
	
	/**
	 * @return the collaboratorId
	 */
	public Integer getCollaboratorId() {
		return collaboratorId;
	}

	/**
	 * @param collaboratorId the collaboratorId to set
	 */
	public void setCollaboratorId(Integer collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	/**
	 * @return the actuationLevelId
	 */
	public Integer getActuationLevelId() {
		return actuationLevelId;
	}
	
	/**
	 * @param actuationLevelId the actuationLevelId to set
	 */
	public void setActuationLevelId(Integer actuationLevelId) {
		this.actuationLevelId = actuationLevelId;
	}
	
	public void create() {
		try {
			this.careerPath.setCollaborator(collaboratorDao.findById(this.collaboratorId));
			this.careerPath.setActuationLevel(actuationLevelDao.findById(this.actuationLevelId));
			
			careerPathDao.save(this.careerPath);
			FacesContextCustom.addMessage("validateSaveFormMessage", "Criação realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateSaveFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.careerPath = new CareerPath();
		}
	}
	
	public void update() {
		try {
			this.careerPath.setCollaborator(collaboratorDao.findById(this.collaboratorId));
			this.careerPath.setActuationLevel(actuationLevelDao.findById(this.actuationLevelId));

			careerPathDao.update(this.careerPath);
			FacesContextCustom.addMessage("validateEditFormMessage", "Atualização realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateEditFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.careerPath = new CareerPath();
		}
	}
	
	public void delete() {
		try {
			careerPathDao.delete(this.careerPath);
			FacesContextCustom.addMessage("validateDeleteFormMessage", "Exclusão realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateDeleteFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.careerPath = new CareerPath();
		}
	}
	
	public List<CareerPath> getAll() throws Exception {
		return careerPathDao.findAll();
	}
	
	public List<CareerPath> getAllByCollaboratorId(Integer collaboratorId) throws Exception {
		if (collaboratorId != null) {
			return careerPathDao.getAllByCollaboratorId(collaboratorId);
		}
		
		return null;
	}

}
