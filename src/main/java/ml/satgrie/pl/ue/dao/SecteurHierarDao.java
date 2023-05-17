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
import ml.satgrie.pl.ue.model.SecteurHierar;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class SecteurHierarDao implements BddInterface<SecteurHierar, Integer> {

	/**
	 * 
	 */
	public SecteurHierarDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(SecteurHierar entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(SecteurHierar entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public SecteurHierar findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		SecteurHierar utilisateur = session.find(SecteurHierar.class, id);
		session.close();
		return utilisateur;
	}

	@Override
	public void delete(SecteurHierar entity) {
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
	public List<SecteurHierar> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<SecteurHierar> getUtilisateurs = session.createQuery(" FROM " + SecteurHierar.class.getSimpleName())
				.list();
		session.close();
		return getUtilisateurs;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public SecteurHierar findByAuth(String login) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session
				.createQuery(" FROM " + SecteurHierar.class.getSimpleName() + " WHERE UtilisateurEmail=:email");
		query.setParameter("email", login);
		try {
			SecteurHierar utilisateur = (SecteurHierar) query.getSingleResult();
			session.close();
			return utilisateur;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

}
