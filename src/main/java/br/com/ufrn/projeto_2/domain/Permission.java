package br.com.ufrn.projeto_2.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@Table(name="permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to Collaborator
	@OneToMany(mappedBy="permission")
	private List<Collaborator> collaborators;

	public Permission() {
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

	public List<Collaborator> getCollaborators() {
		return this.collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public Collaborator addCollaborator(Collaborator collaborator) {
		getCollaborators().add(collaborator);
		collaborator.setPermission(this);

		return collaborator;
	}

	public Collaborator removeCollaborator(Collaborator collaborator) {
		getCollaborators().remove(collaborator);
		collaborator.setPermission(null);

		return collaborator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(collaborators, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		return Objects.equals(collaborators, other.collaborators) && Objects.equals(description, other.description)
				&& id == other.id;
	}

}