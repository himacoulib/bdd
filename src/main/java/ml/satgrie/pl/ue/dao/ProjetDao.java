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
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class ProjetDao implements BddInterface<Projet, Integer> {

	/**
	 * 
	 */
	public ProjetDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Projet entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
		HibernateUtils.getSessionFactory().close();
	}

	@Override
	public void update(Projet entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();

		session.close();
		HibernateUtils.getSessionFactory().close();
	}

	@Override
	public Projet findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Projet Projet = session.find(Projet.class, id);
		session.close();
		return Projet;
	}

	@Override
	public void delete(Projet entity) {
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
	public List<Projet> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<Projet> getprojets = session.createQuery(" FROM " + Projet.class.getSimpleName())
				.list();

		session.close();
		return getprojets;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
