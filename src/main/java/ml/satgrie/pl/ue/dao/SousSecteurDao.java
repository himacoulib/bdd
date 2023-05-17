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
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.SousSecteur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class SousSecteurDao implements BddInterface<SousSecteur, Integer> {

	/**
	 * 
	 */
	public SousSecteurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(SousSecteur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(SousSecteur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public SousSecteur findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		SousSecteur sousSecteur = null;
				
		try {
			@SuppressWarnings("rawtypes")
			Query query = session
					.createQuery(" FROM " + SousSecteur.class.getSimpleName() + " WHERE SousSecteurID=:SousSecteurID");
			query.setParameter("SousSecteurID", id);
			sousSecteur = (SousSecteur) query.getSingleResult();
			 session.close();
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return sousSecteur;
	}

	@Override
	public void delete(SousSecteur entity) {
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
	public List<SousSecteur> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<SousSecteur> getSousSecteurs = session
				.createQuery(" FROM " + SousSecteur.class.getSimpleName()).list();
		session.close();
		return getSousSecteurs;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
