/**
 * 
 */
package ml.satgrie.pl.ue.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.QueryHints;
import org.hibernate.query.Query;

import ml.satgrie.pl.ue.interfaces.BddInterface;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class UtilisateurDao implements BddInterface<Utilisateur, Integer> {

	/**
	 * 
	 */
	public UtilisateurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Utilisateur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void update(Utilisateur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
		
	}

	@Override
	public Utilisateur findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Utilisateur utilisateur = session.find(Utilisateur.class, id);
		session.close();
		
		return utilisateur;
	}

	@Override
	public void delete(Utilisateur entity) {
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
	public List<Utilisateur> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Utilisateur> getUtilisateurs = session.createQuery(" FROM " + Utilisateur.class.getSimpleName()).list();
		session.close();
		
		return getUtilisateurs;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public Utilisateur findByAuth(String login) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session
				.createQuery(" FROM " + Utilisateur.class.getSimpleName() + " WHERE UtilisateurEmail=:email");
		query.setParameter("email", login);

		try {
			Utilisateur utilisateur = (Utilisateur) query.getSingleResult();
			session.close();
			
			return utilisateur;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

}
