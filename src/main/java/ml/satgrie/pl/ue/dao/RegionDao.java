/**
 * 
 */
package ml.satgrie.pl.ue.dao;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.hibernate.query.Query;

import ml.satgrie.pl.ue.interfaces.BddInterface;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class RegionDao implements BddInterface<Region, Integer> {

	/**
	 * 
	 */
	public RegionDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Region entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		HibernateUtils.getSessionFactory().close();
	}

	@Override
	public void update(Region entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		HibernateUtils.getSessionFactory().close();
	}

	@Override
	public Region findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(" FROM " + Region.class.getSimpleName() + " WHERE RegionID=:id");
		query.setParameter("id", id);
		try {
			Region region = (Region) query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getSingleResult();
			session.close();
			return region;
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return null;
	}

	@Override
	public void delete(Region entity) {
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
	public List<Region> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Region> getRegions = session.createQuery(" FROM " + Region.class.getSimpleName())
				.list();
		session.close();
		return getRegions;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
