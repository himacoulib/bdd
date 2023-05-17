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
import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class SourceFinancementDao implements BddInterface<SourceFinancement, Integer> {

	/**
	 * 
	 */
	public SourceFinancementDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(SourceFinancement entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(SourceFinancement entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public SourceFinancement findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		SourceFinancement secteurFinancement = session.find(SourceFinancement.class, id);
		session.close();
		return secteurFinancement;
	}

	@Override
	public void delete(SourceFinancement entity) {
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
	public List<SourceFinancement> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<SourceFinancement> getSourceFinancements = session
				.createQuery(" FROM " + SourceFinancement.class.getSimpleName())
				.list();
		session.close();
		return getSourceFinancements;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
