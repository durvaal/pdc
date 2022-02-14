package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the actuation_area database table.
 * 
 */
@Entity
@Table(name="actuation_area")
public class ActuationArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to Checkpoint
	@OneToMany(mappedBy="actuationArea")
	private List<Checkpoint> checkpoints;

	//bi-directional many-to-one association to Collaborator
	@OneToMany(mappedBy="actuationArea")
	private List<Collaborator> collaborators;

	public ActuationArea() {
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

	public List<Checkpoint> getCheckpoints() {
		return this.checkpoints;
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public Checkpoint addCheckpoint(Checkpoint checkpoint) {
		getCheckpoints().add(checkpoint);
		checkpoint.setActuationArea(this);

		return checkpoint;
	}

	public Checkpoint removeCheckpoint(Checkpoint checkpoint) {
		getCheckpoints().remove(checkpoint);
		checkpoint.setActuationArea(null);

		return checkpoint;
	}

	public List<Collaborator> getCollaborators() {
		return this.collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public Collaborator addCollaborator(Collaborator collaborator) {
		getCollaborators().add(collaborator);
		collaborator.setActuationArea(this);

		return collaborator;
	}

	public Collaborator removeCollaborator(Collaborator collaborator) {
		getCollaborators().remove(collaborator);
		collaborator.setActuationArea(null);

		return collaborator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(checkpoints, collaborators, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActuationArea other = (ActuationArea) obj;
		return Objects.equals(checkpoints, other.checkpoints) && Objects.equals(collaborators, other.collaborators)
				&& Objects.equals(description, other.description) && id == other.id;
	}

}