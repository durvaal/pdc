package br.com.ufrn.projeto_2.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.ufrn.projeto_2.domain.CareerPath;
import br.com.ufrn.projeto_2.util.HibernateUtil;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class CareerPathDao extends GenericDao<CareerPath, Integer>{
	
	public CareerPathDao() {
       super(CareerPath.class);
    }
	
	@SuppressWarnings("unchecked")
	public List<CareerPath> getAllByCollaboratorId(Integer collaboratorId) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<CareerPath> careerPaths = null;

		try {
			session.getTransaction().begin();
			careerPaths = (List<CareerPath>) session.createQuery("FROM CareerPath WHERE collaborator.id = :collaboratorId")
					.setParameter("collaboratorId", collaboratorId).list();
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			
			exception.printStackTrace();

			throw new Exception("Houve um erro ao buscar. Contate o canal de suporte do PDC.");
		} finally {
			// TODO: handle finally clause
			if (session.isOpen()) {
				session.close();
			}
		}

		return careerPaths;
	}

}
