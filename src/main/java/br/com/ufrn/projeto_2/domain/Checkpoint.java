package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the checkpoint database table.
 * 
 */
@Entity
@Table(name="checkpoint")
public class Checkpoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to ActuationArea
	@ManyToOne
	@JoinColumn(name="actuation_area_id")
	private ActuationArea actuationArea;

	//bi-directional many-to-one association to ActuationLevel
	@ManyToOne
	@JoinColumn(name="actuation_level_id")
	private ActuationLevel actuationLevel;

	//bi-directional many-to-one association to CheckpointStatus
	@OneToMany(mappedBy="checkpoint")
	private List<CheckpointStatus> checkpointStatuses;

	public Checkpoint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActuationArea getActuationArea() {
		return this.actuationArea;
	}

	public void setActuationArea(ActuationArea actuationArea) {
		this.actuationArea = actuationArea;
	}

	public ActuationLevel getActuationLevel() {
		return this.actuationLevel;
	}

	public void setActuationLevel(ActuationLevel actuationLevel) {
		this.actuationLevel = actuationLevel;
	}

	public List<CheckpointStatus> getCheckpointStatuses() {
		return this.checkpointStatuses;
	}

	public void setCheckpointStatuses(List<CheckpointStatus> checkpointStatuses) {
		this.checkpointStatuses = checkpointStatuses;
	}

	public CheckpointStatus addCheckpointStatus(CheckpointStatus checkpointStatus) {
		getCheckpointStatuses().add(checkpointStatus);
		checkpointStatus.setCheckpoint(this);

		return checkpointStatus;
	}

	public CheckpointStatus removeCheckpointStatus(CheckpointStatus checkpointStatus) {
		getCheckpointStatuses().remove(checkpointStatus);
		checkpointStatus.setCheckpoint(null);

		return checkpointStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actuationArea, actuationLevel, checkpointStatuses, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checkpoint other = (Checkpoint) obj;
		return Objects.equals(actuationArea, other.actuationArea)
				&& Objects.equals(actuationLevel, other.actuationLevel)
				&& Objects.equals(checkpointStatuses, other.checkpointStatuses)
				&& Objects.equals(description, other.description) && id == other.id;
	}

}