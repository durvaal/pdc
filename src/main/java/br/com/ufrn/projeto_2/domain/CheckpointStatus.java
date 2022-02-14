package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the checkpoint_status database table.
 * 
 */
@Entity
@Table(name="checkpoint_status")
public class CheckpointStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String progress;

	//bi-directional many-to-one association to CareerPath
	@ManyToOne
	@JoinColumn(name="career_path_id")
	private CareerPath careerPath;

	//bi-directional many-to-one association to Checkpoint
	@ManyToOne
	private Checkpoint checkpoint;

	public CheckpointStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProgress() {
		return this.progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public CareerPath getCareerPath() {
		return this.careerPath;
	}

	public void setCareerPath(CareerPath careerPath) {
		this.careerPath = careerPath;
	}

	public Checkpoint getCheckpoint() {
		return this.checkpoint;
	}

	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
	}

	@Override
	public int hashCode() {
		return Objects.hash(careerPath, checkpoint, id, progress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckpointStatus other = (CheckpointStatus) obj;
		return Objects.equals(careerPath, other.careerPath) && Objects.equals(checkpoint, other.checkpoint)
				&& id == other.id && Objects.equals(progress, other.progress);
	}

}