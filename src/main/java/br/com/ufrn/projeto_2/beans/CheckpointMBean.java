package br.com.ufrn.projeto_2.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrn.projeto_2.dao.ActuationAreaDao;
import br.com.ufrn.projeto_2.dao.ActuationLevelDao;
import br.com.ufrn.projeto_2.dao.CareerPathDao;
import br.com.ufrn.projeto_2.dao.CheckpointDao;
import br.com.ufrn.projeto_2.dao.CheckpointStatusDao;
import br.com.ufrn.projeto_2.domain.CareerPath;
import br.com.ufrn.projeto_2.domain.Checkpoint;
import br.com.ufrn.projeto_2.domain.CheckpointStatus;
import br.com.ufrn.projeto_2.util.FacesContextCustom;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class CheckpointMBean {
	Checkpoint checkpoint = new Checkpoint();
	Integer actuationAreaId;
	Integer actuationLevelId;
	
	CheckpointDao checkpointDao = new CheckpointDao();
	ActuationAreaDao actuationAreaDao = new ActuationAreaDao();
	ActuationLevelDao actuationLevelDao = new ActuationLevelDao();
	
	/**
	 * @return the checkpoint
	 */
	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	/**
	 * @param checkpoint the checkpoint to set
	 */
	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
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
			this.checkpoint.setActuationArea(actuationAreaDao.findById(this.actuationAreaId));
			this.checkpoint.setActuationLevel(actuationLevelDao.findById(this.actuationLevelId));
			
			Integer idCreated = checkpointDao.save(this.checkpoint);
			createCheckpointStatus(idCreated);
			FacesContextCustom.addMessage("validateSaveFormMessage", "Criação realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateSaveFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.checkpoint = new Checkpoint();
		}
	}
	
	public void update() {
		try {
			this.checkpoint.setActuationArea(actuationAreaDao.findById(this.actuationAreaId));
			this.checkpoint.setActuationLevel(actuationLevelDao.findById(this.actuationLevelId));

			checkpointDao.update(this.checkpoint);
			FacesContextCustom.addMessage("validateEditFormMessage", "Atualização realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateEditFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.checkpoint = new Checkpoint();
		}
	}
	
	public void delete() {
		try {
			checkpointDao.delete(this.checkpoint);
			FacesContextCustom.addMessage("validateDeleteFormMessage", "Exclusão realizada com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			FacesContextCustom.addMessage("validateDeleteFormMessage", exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} finally {
			this.checkpoint = new Checkpoint();
		}
	}
	
	public List<Checkpoint> getAll() throws Exception {
		return checkpointDao.findAll();
	}
	
	/**
	 * Para não precisar criar os dados checkpointStatus manualmente, após a inserção de cada checkpoint será verificado se
	 * existe um plano de carreira com o mesmo nível e área de atuação do checkpoint, pois, isso garante que os colaboradores
	 * que possuirem o mesmo nível e área de atuação terão os mesmos objetivos
	 * @param idCreatedCheckpint
	 * @throws Exception 
	 */
	private void createCheckpointStatus(Integer idCreatedCheckpoint) throws Exception {
		CareerPathDao careerPathDao = new CareerPathDao();
		CheckpointStatusDao checkpointStatusDao = new CheckpointStatusDao();
		List<CareerPath> careerPaths = careerPathDao.findAll();

		careerPaths.stream().forEach((careerPath) -> {
			Boolean hasSameActuationLevel = careerPath.getActuationLevel().getId() == this.actuationLevelId;
			Boolean hasSameActuationArea = careerPath.getCollaborator().getActuationArea().getId() == this.actuationAreaId;

			if (hasSameActuationLevel && hasSameActuationArea) {
				try {
					CheckpointStatus checkpointStatus = new CheckpointStatus();
					
					checkpointStatus.setProgress("NOT_DONE");
					checkpointStatus.setCareerPath(careerPath);
					checkpointStatus.setCheckpoint(checkpointDao.findById(idCreatedCheckpoint));
					
					checkpointStatusDao.save(checkpointStatus);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
