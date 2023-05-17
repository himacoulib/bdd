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
import ml.satgrie.pl.ue.model.CercleId;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class CercleIdDao implements BddInterface<CercleId, Integer> {

	/**
	 * 
	 */
	public CercleIdDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(CercleId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(CercleId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public CercleId findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		CercleId cercleId = session.find(CercleId.class, id);
		session.close();
		return cercleId;
	}

	@Override
	public void delete(CercleId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.delete(entity);
		session.close();
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CercleId> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<CercleId> getCercleIds = session
				.createQuery(" FROM " + CercleId.class.getSimpleName()).list();
		session.close();
		return getCercleIds;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

}
