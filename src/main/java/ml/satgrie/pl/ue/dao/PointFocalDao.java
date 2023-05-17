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
import ml.satgrie.pl.ue.model.Pointfocal;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class PointFocalDao implements BddInterface<Pointfocal, Integer> {

	/**
	 * 
	 */
	public PointFocalDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Pointfocal entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Pointfocal entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Pointfocal findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Pointfocal pointfocal = session.find(Pointfocal.class, id);
		session.close();
		return pointfocal;
	}

	@Override
	public void delete(Pointfocal entity) {
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
	public List<Pointfocal> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Pointfocal> getPointfocals = session.createQuery(" FROM " + Pointfocal.class.getSimpleName())
				.list();
		session.close();
		return getPointfocals;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
