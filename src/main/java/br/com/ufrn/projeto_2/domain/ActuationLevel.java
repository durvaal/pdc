package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the actuation_level database table.
 * 
 */
@Entity
@Table(name="actuation_level")
public class ActuationLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to CareerPath
	@OneToMany(mappedBy="actuationLevel")
	private List<CareerPath> careerPaths;

	//bi-directional many-to-one association to Checkpoint
	@OneToMany(mappedBy="actuationLevel")
	private List<Checkpoint> checkpoints;

	public ActuationLevel() {
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

	public List<CareerPath> getCareerPaths() {
		return this.careerPaths;
	}

	public void setCareerPaths(List<CareerPath> careerPaths) {
		this.careerPaths = careerPaths;
	}

	public CareerPath addCareerPath(CareerPath careerPath) {
		getCareerPaths().add(careerPath);
		careerPath.setActuationLevel(this);

		return careerPath;
	}

	public CareerPath removeCareerPath(CareerPath careerPath) {
		getCareerPaths().remove(careerPath);
		careerPath.setActuationLevel(null);

		return careerPath;
	}

	public List<Checkpoint> getCheckpoints() {
		return this.checkpoints;
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public Checkpoint addCheckpoint(Checkpoint checkpoint) {
		getCheckpoints().add(checkpoint);
		checkpoint.setActuationLevel(this);

		return checkpoint;
	}

	public Checkpoint removeCheckpoint(Checkpoint checkpoint) {
		getCheckpoints().remove(checkpoint);
		checkpoint.setActuationLevel(null);

		return checkpoint;
	}

	@Override
	public int hashCode() {
		return Objects.hash(careerPaths, checkpoints, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActuationLevel other = (ActuationLevel) obj;
		return Objects.equals(careerPaths, other.careerPaths) && Objects.equals(checkpoints, other.checkpoints)
				&& Objects.equals(description, other.description) && id == other.id;
	}

}