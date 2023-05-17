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
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class SecteurDao implements BddInterface<Secteur, Integer> {

	/**
	 * 
	 */
	public SecteurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Secteur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Secteur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Secteur findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session.createQuery(" FROM " + Secteur.class.getSimpleName() + " WHERE SecteurID=:id");
		query.setParameter("id", id);
		try {
			Secteur secteur = (Secteur) query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getSingleResult();
			session.close();
			return secteur;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void delete(Secteur entity) {
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
	public List<Secteur> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Secteur> getSecteurs = session.createQuery(" FROM " + Secteur.class.getSimpleName())
				.list();
		session.close();
		return getSecteurs;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
