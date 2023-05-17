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
import ml.satgrie.pl.ue.model.LocalisationId;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class LocalisationIdDao implements BddInterface<LocalisationId, Integer> {

	/**
	 * 
	 */
	public LocalisationIdDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(LocalisationId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(LocalisationId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public LocalisationId findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		LocalisationId localisationId = session.find(LocalisationId.class, id);
		session.close();
		return localisationId;
	}

	@Override
	public void delete(LocalisationId entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		session.delete(entity);
		session.close();
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<LocalisationId> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<LocalisationId> getLocalisationIds = session.createQuery(" FROM " + LocalisationId.class.getSimpleName())
				.list();
		session.close();
		return getLocalisationIds;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
