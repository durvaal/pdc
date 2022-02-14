package br.com.ufrn.projeto_2.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import br.com.ufrn.projeto_2.util.HibernateUtil;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class GenericDao<T, I extends Serializable> {
	private Class<T> persistedClass;

	protected GenericDao() {
	}

	protected GenericDao(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}

	@SuppressWarnings("unchecked")
	public I save(T entity) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		I idCreated = null;

		try {
			session.getTransaction().begin();
			idCreated = (I) session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			
			exception.printStackTrace();

			throw new Exception("Houve um erro ao salvar. Contate o canal de suporte do PDC.");
		} finally {
			// TODO: handle finally clause
			if (session.isOpen()) {
				session.close();
			}
		}
		
		return idCreated;
	}

	public void update(T entity) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.getTransaction().begin();
			session.update(entity);
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

	public void delete(T entity) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.getTransaction().begin();
			session.delete(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			
			exception.printStackTrace();

			throw new Exception("Houve um erro ao excluir. Contate o canal de suporte do PDC.");
		} finally {
			// TODO: handle finally clause
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public List<T> findAll() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<T> list = null;

		try {
			session.getTransaction().begin();

			CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(this.persistedClass);
			query.select(query.from(this.persistedClass));
			list = session.createQuery(query).getResultList();
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

		return list;
	}

	public T findById(I id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T entity = null;

		try {
			session.getTransaction().begin();
			entity = (T) session.find(this.persistedClass, id);
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

		return entity;
	}

}
