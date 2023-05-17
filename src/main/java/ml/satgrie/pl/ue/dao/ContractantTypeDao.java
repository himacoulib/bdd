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
import ml.satgrie.pl.ue.model.Contractanttype;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class ContractantTypeDao implements BddInterface<Contractanttype, Integer> {

	/**
	 * 
	 */
	public ContractantTypeDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Contractanttype entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Contractanttype entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Contractanttype findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Contractanttype contractanttype = session.find(Contractanttype.class, id);
		session.close();
		return contractanttype;
	}

	@Override
	public void delete(Contractanttype entity) {
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
	public List<Contractanttype> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List<Contractanttype> getContractanttypes = session
				.createQuery(" FROM " + Contractanttype.class.getSimpleName())
				.list();
		session.close();
		return getContractanttypes;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
