package br.com.ufrn.projeto_2.dao;

import org.hibernate.Session;

import br.com.ufrn.projeto_2.domain.CheckpointStatus;
import br.com.ufrn.projeto_2.util.HibernateUtil;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class CheckpointStatusDao extends GenericDao<CheckpointStatus, Integer>{
	
	public CheckpointStatusDao() {
       super(CheckpointStatus.class);
    }
	
	public void updateProgress(Integer id, String progress) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.getTransaction().begin();
			session.createQuery("UPDATE CheckpointStatus SET progress = :progress WHERE id = :id")
					.setParameter("progress", progress)
					.setParameter("id", id)
					.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			
			exception.printStackTrace();

			throw new Exception("Houve um erro ao atualizar. Contate o canal de suporte do PDC.");
		} finally {
			// TODO: handle finally clause
			if (session.isOpen()) {
				session.close();
			}
		}
	}

}
