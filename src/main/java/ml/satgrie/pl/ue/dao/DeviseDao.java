/**
 * 
 */
package ml.satgrie.pl.ue.dao;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;

import ml.satgrie.pl.ue.interfaces.BddInterface;
import ml.satgrie.pl.ue.model.Devise;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class DeviseDao implements BddInterface<Devise, Integer> {

	/**
	 * 
	 */
	public DeviseDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Devise entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Devise entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Devise findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Devise devise = session.find(Devise.class, id);
		session.close();
		return devise;
	}

	@Override
	public void delete(Devise entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
		session.close();
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Devise> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Devise> getDevises = session.createQuery(" FROM " + Devise.class.getSimpleName())
				.list();
		session.close();
		return getDevises;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
