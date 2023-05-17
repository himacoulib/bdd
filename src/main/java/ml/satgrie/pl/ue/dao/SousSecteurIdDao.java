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
import ml.satgrie.pl.ue.model.SousSecteurId;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class SousSecteurIdDao implements BddInterface<SousSecteurId, Integer> {

	/**
	 * 
	 */
	public SousSecteurIdDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(SousSecteurId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(SousSecteurId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public SousSecteurId findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		SousSecteurId sousSecteurId = session.find(SousSecteurId.class, id);
		session.close();
		return sousSecteurId;
	}

	@Override
	public void delete(SousSecteurId entity) {
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
	public List<SousSecteurId> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<SousSecteurId> getSousSecteurIds = session.createQuery(" FROM " + SousSecteurId.class.getSimpleName())
				.list();
		session.close();
		return getSousSecteurIds;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
