package br.com.ufrn.projeto_2.util;

import org.hibernate.Session;
import org.junit.Test;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class HibernateUtilTest {
	
	@Test
	public void connectDatabase() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
		HibernateUtil.getSessionFactory().close();
	}

}
