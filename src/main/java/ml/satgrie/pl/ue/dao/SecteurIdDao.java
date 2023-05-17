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
import ml.satgrie.pl.ue.model.SecteurId;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class SecteurIdDao implements BddInterface<SecteurId, Integer> {

	/**
	 * 
	 */
	public SecteurIdDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(SecteurId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(SecteurId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public SecteurId findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		SecteurId secteurId = session.find(SecteurId.class, id);
		session.close();
		return secteurId;
	}

	@Override
	public void delete(SecteurId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.delete(entity);
		session.close();
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SecteurId> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<SecteurId> getSecteurIds = session.createQuery(" FROM " + SecteurId.class.getSimpleName())
				.list();
		session.close();
		return getSecteurIds;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
