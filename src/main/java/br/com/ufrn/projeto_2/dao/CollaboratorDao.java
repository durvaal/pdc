package br.com.ufrn.projeto_2.dao;

import org.hibernate.Session;

import br.com.ufrn.projeto_2.domain.Collaborator;
import br.com.ufrn.projeto_2.util.HibernateUtil;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class CollaboratorDao extends GenericDao<Collaborator, Integer>{
	
	public CollaboratorDao() {
       super(Collaborator.class);
    }   

	public Collaborator login(Collaborator collaborator) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Collaborator collaboratorFinded = null;

		try {
			session.getTransaction().begin();
			collaboratorFinded = (Collaborator) session.createQuery("FROM Collaborator WHERE email = :email AND password = :password")
					.setParameter("email", collaborator.getEmail())
					.setParameter("password", collaborator.getPassword())
					.setMaxResults(1).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			
			exception.printStackTrace();

			throw new Exception("Houve um erro ao logar. Contate o canal de suporte do PDC.");
		} finally {
			// TODO: handle finally clause
			if (session.isOpen()) {
				session.close();
			}
		}

		return collaboratorFinded;
	}
}
