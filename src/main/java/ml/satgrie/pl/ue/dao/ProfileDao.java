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
import ml.satgrie.pl.ue.model.Profile;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class ProfileDao implements BddInterface<Profile, Integer> {

	/**
	 * 
	 */
	public ProfileDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Profile entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Profile entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Profile findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Profile pointProfile = session.find(Profile.class, id);
		session.close();
		return pointProfile;
	}

	@Override
	public void delete(Profile entity) {
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
	public List<Profile> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Profile> getProfiles = session.createQuery(" FROM " + Profile.class.getSimpleName())
				.list();
		session.close();
		return getProfiles;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
