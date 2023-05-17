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
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class CommuneDao implements BddInterface<Commune, Integer> {

	/**
	 * 
	 */
	public CommuneDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Commune entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Commune entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Commune findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(" FROM " + Commune.class.getSimpleName() + " WHERE CommuneID=:id");
		query.setParameter("id", id);
		try {
			Commune commune = (Commune) query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getSingleResult();
			session.close();
			return commune;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void delete(Commune entity) {
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
	public List<Commune> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<Commune> getCommunes = session.createQuery(" FROM " + Commune.class.getSimpleName())
				.list();
		session.close();
		return getCommunes;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
