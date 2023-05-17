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
import ml.satgrie.pl.ue.model.Calendrier;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class CalendrierDao implements BddInterface<Calendrier, Integer> {

	/**
	 * 
	 */
	public CalendrierDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Calendrier entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Calendrier entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Calendrier findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Calendrier calendrier = session.find(Calendrier.class, id);
		session.close();
		return calendrier;
	}

	@Override
	public void delete(Calendrier entity) {

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
	public List<Calendrier> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Calendrier> getCalendriers = session.createQuery(" FROM " + Calendrier.class.getSimpleName())
				.list();
		session.close();
		return getCalendriers;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

}
