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
import ml.satgrie.pl.ue.model.CercleId;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class CercleDao implements BddInterface<Cercle, Integer> {

	/**
	 * 
	 */
	public CercleDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Cercle entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Cercle entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Cercle findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(" FROM " + Cercle.class.getSimpleName() + " WHERE CerlceID=:id");
		query.setParameter("id", id);
		try {
			Cercle cercle = (Cercle) query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getSingleResult();
			session.close();
			return cercle;
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return null;
	}

	@Override
	public void delete(Cercle entity) {
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
	public List<Cercle> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Cercle> getCercles = session.createQuery(" FROM " + Cercle.class.getSimpleName())
				.list();
		session.close();
		return getCercles;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
