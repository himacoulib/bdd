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
import ml.satgrie.pl.ue.model.RegionId;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class RegionIdDao implements BddInterface<RegionId, Integer> {

	/**
	 * 
	 */
	public RegionIdDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(RegionId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(RegionId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public RegionId findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		RegionId regionId = session.find(RegionId.class, id);
		session.close();
		return regionId;
	}

	@Override
	public void delete(RegionId entity) {
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
	public List<RegionId> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<RegionId> getRegionIds = session.createQuery(" FROM " + RegionId.class.getSimpleName())
				.list();
		session.close();
		return getRegionIds;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
