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
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class ContractantDao implements BddInterface<Contractant, Integer> {

	/**
	 * 
	 */
	public ContractantDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Contractant entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Contractant entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Contractant findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Contractant contractant = session.find(Contractant.class, id);
		session.close();
		return contractant;
	}

	@Override
	public void delete(Contractant entity) {
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
	public List<Contractant> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<Contractant> getContractants = session.createQuery(" FROM " + Contractant.class.getSimpleName())
				.list();
		session.close();
		return getContractants;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
