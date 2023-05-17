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
import ml.satgrie.pl.ue.model.Pays;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class PaysDao implements BddInterface<Pays, Integer> {

	/**
	 * 
	 */
	public PaysDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Pays entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Pays entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Pays findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Pays pays = session.find(Pays.class, id);
		session.close();
		return pays;
	}

	@Override
	public void delete(Pays entity) {
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
	public List<Pays> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Pays> getPays = session.createQuery(" FROM " + Pays.class.getSimpleName())
				.list();
		session.close();
		return getPays;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
