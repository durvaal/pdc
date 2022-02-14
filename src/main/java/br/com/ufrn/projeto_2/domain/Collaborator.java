package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the collaborator database table.
 * 
 */
@Entity
@Table(name="collaborator")
public class Collaborator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String name;

	private String password;

	//bi-directional many-to-one association to CareerPath
	@OneToMany(mappedBy="collaborator")
	private List<CareerPath> careerPaths;

	//bi-directional many-to-one association to ActuationArea
	@ManyToOne
	@JoinColumn(name="actuation_area_id")
	private ActuationArea actuationArea;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	private Permission permission;

	public Collaborator() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CareerPath> getCareerPaths() {
		return this.careerPaths;
	}

	public void setCareerPaths(List<CareerPath> careerPaths) {
		this.careerPaths = careerPaths;
	}

	public CareerPath addCareerPath(CareerPath careerPath) {
		getCareerPaths().add(careerPath);
		careerPath.setCollaborator(this);

		return careerPath;
	}

	public CareerPath removeCareerPath(CareerPath careerPath) {
		getCareerPaths().remove(careerPath);
		careerPath.setCollaborator(null);

		return careerPath;
	}

	public ActuationArea getActuationArea() {
		return this.actuationArea;
	}

	public void setActuationArea(ActuationArea actuationArea) {
		this.actuationArea = actuationArea;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actuationArea, careerPaths, email, id, name, password, permission);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collaborator other = (Collaborator) obj;
		return Objects.equals(actuationArea, other.actuationArea) && Objects.equals(careerPaths, other.careerPaths)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(permission, other.permission);
	}

}