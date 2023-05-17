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
import ml.satgrie.pl.ue.model.CommuneId;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class CommuneIdDao implements BddInterface<CommuneId, Integer> {

	/**
	 * 
	 */
	public CommuneIdDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(CommuneId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(CommuneId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public CommuneId findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		CommuneId communeId = session.find(CommuneId.class, id);
		session.close();
		return communeId;
	}

	@Override
	public void delete(CommuneId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.delete(entity);
		session.close();
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CommuneId> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<CommuneId> getCommuneIds = session.createQuery(" FROM " + CommuneId.class.getSimpleName())
				.list();
		session.close();
		return getCommuneIds;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
