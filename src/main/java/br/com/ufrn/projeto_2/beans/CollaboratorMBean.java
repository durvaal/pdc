package br.com.ufrn.projeto_2.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrn.projeto_2.dao.ActuationAreaDao;
import br.com.ufrn.projeto_2.dao.CollaboratorDao;
import br.com.ufrn.projeto_2.dao.PermissionDao;
import br.com.ufrn.projeto_2.domain.Collaborator;
import br.com.ufrn.projeto_2.util.FacesContextCustom;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class CollaboratorMBean {
	
	Collaborator collaborator = new Collaborator();
	Integer permissionId;
	Integer actuationAreaId;
	
	CollaboratorDao collaboratorDao = new CollaboratorDao();
	PermissionDao permissionDao = new PermissionDao();
	ActuationAreaDao actuationAreaDao = new ActuationAreaDao();

	/**
	 * @return the collaborator
	 */
	public Collaborator getCollaborator() {
		return collaborator;
	}

	/**
	 * @param collaborator the collaborator to set
	 */
	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}
	
	/**
	 * @return the permissionId
	 */
	public Integer getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @return the actuationAreaId
	 */
	public Integer getActuationAreaId() {
		return actuationAreaId;
	}

	/**
	 * @param actuationAreaId the actuationAreaId to set
	 */
	public void setActuationAreaId(Integer actuationAreaId) {
		this.actuationAreaId = actuationAreaId;
	}

	public String login() throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Collaborator collaboratorFinded = collaboratorDao.login(this.collaborator);

		if (collaboratorFinded != null) {
			HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
			
			httpSession.setAttribute("userIdLogged", collaboratorFinded.getId());
			httpSession.setAttribute("userNameLogged", collaboratorFinded.getName());
			httpSession.setAttribute("userEmailLogged", collaboratorFinded.getEmail());
			httpSession.setAttribute("userPermissionLogged", collaboratorFinded.getPermission().getDescription());
			httpSession.setMaxInactiveInterval(300);
			
			return "/dashboard?faces-redirect=true";
		} else {
			FacesContextCustom.addMessage("loginForm", "O colaborador não foi encontrado. Tente novamente!", FacesMessage.SEVERITY_ERROR);
			return "";
		}
	}
	
	public String logout()  {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

		httpSession.invalidate();

		return "/login?faces-redirect=true";
	}
	
	public void create() {
		try {
			this.collaborator.setPermission(permissionDao.findById(this.permissionId));
			this.collaborator.setActuationArea(actuationAreaDao.findById(this.actuationAreaId));

			collaboratorDao.save(this.collaborator);
			FacesContextCustom.addMessage("validateSaveFormMessage", "Criação realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateSaveFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.collaborator = new Collaborator();
		}
	}
	
	public void update() {
		try {
			this.collaborator.setPermission(permissionDao.findById(this.permissionId));
			this.collaborator.setActuationArea(actuationAreaDao.findById(this.actuationAreaId));

			updateCurrentUserSessionInformation();
			collaboratorDao.update(this.collaborator);
			FacesContextCustom.addMessage("validateEditFormMessage", "Atualização realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateEditFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.collaborator = new Collaborator();
		}
	}
	
	public void delete() {
		try {
			collaboratorDao.delete(this.collaborator);
			FacesContextCustom.addMessage("validateDeleteFormMessage", "Exclusão realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateDeleteFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.collaborator = new Collaborator();
		}
	}

	public List<Collaborator> getAll() throws Exception {
		return collaboratorDao.findAll();
	}

	private void updateCurrentUserSessionInformation() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

		httpSession.setAttribute("userIdLogged", this.collaborator.getId());
		httpSession.setAttribute("userNameLogged", this.collaborator.getName());
		httpSession.setAttribute("userEmailLogged", this.collaborator.getEmail());
		httpSession.setAttribute("userPermissionLogged", this.collaborator.getPermission().getDescription());
	}
}
