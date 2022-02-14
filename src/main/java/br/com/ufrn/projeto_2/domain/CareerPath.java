package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the career_path database table.
 * 
 */
@Entity
@Table(name="career_path")
public class CareerPath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to ActuationLevel
	@ManyToOne
	@JoinColumn(name="actuation_level_id")
	private ActuationLevel actuationLevel;

	//bi-directional many-to-one association to Collaborator
	@ManyToOne
	private Collaborator collaborator;

	//bi-directional many-to-one association to CheckpointStatus
	@OneToMany(mappedBy="careerPath", fetch = FetchType.EAGER)
	private List<CheckpointStatus> checkpointStatuses;

	public CareerPath() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActuationLevel getActuationLevel() {
		return this.actuationLevel;
	}

	public void setActuationLevel(ActuationLevel actuationLevel) {
		this.actuationLevel = actuationLevel;
	}

	public Collaborator getCollaborator() {
		return this.collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public List<CheckpointStatus> getCheckpointStatuses() {
		return this.checkpointStatuses;
	}

	public void setCheckpointStatuses(List<CheckpointStatus> checkpointStatuses) {
		this.checkpointStatuses = checkpointStatuses;
	}

	public CheckpointStatus addCheckpointStatus(CheckpointStatus checkpointStatus) {
		getCheckpointStatuses().add(checkpointStatus);
		checkpointStatus.setCareerPath(this);

		return checkpointStatus;
	}

	public CheckpointStatus removeCheckpointStatus(CheckpointStatus checkpointStatus) {
		getCheckpointStatuses().remove(checkpointStatus);
		checkpointStatus.setCareerPath(null);

		return checkpointStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actuationLevel, checkpointStatuses, collaborator, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CareerPath other = (CareerPath) obj;
		return Objects.equals(actuationLevel, other.actuationLevel)
				&& Objects.equals(checkpointStatuses, other.checkpointStatuses)
				&& Objects.equals(collaborator, other.collaborator) && id == other.id;
	}

}