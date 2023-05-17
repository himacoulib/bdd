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
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class ChapitreDao implements BddInterface<Chapitre, Integer> {

	/**
	 * 
	 */
	public ChapitreDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Chapitre entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Chapitre entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Chapitre findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Chapitre chapitre = session.find(Chapitre.class, id);
		session.close();
		return chapitre;
	}

	@Override
	public void delete(Chapitre entity) {
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
	public List<Chapitre> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Chapitre> getChapitres = session.createQuery(" FROM " + Chapitre.class.getSimpleName())
				.list();
		session.close();
		return getChapitres;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
