/**
 * 
 */
package ml.satgrie.pl.ue.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.hibernate.query.Query;

import ml.satgrie.pl.ue.interfaces.BddInterface;
import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class LocalisationDao implements BddInterface<Localisation, Integer> {

	/**
	 * 
	 */
	public LocalisationDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Localisation entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Localisation entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Localisation findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Localisation localisation = null;

		try {
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(
					" FROM " + Localisation.class.getSimpleName() + " WHERE LocalisationID=:LocalisationID");
			query.setParameter("LocalisationID", id);
			localisation = (Localisation) query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getSingleResult();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return localisation;
	}

	public List<Localisation> findByRegionId(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Localisation> localisations = new ArrayList<>();

		try {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Query<Localisation> query = session
					.createQuery(" FROM " + Localisation.class.getSimpleName() + " WHERE RegionID=:RegionID");
			query.setParameter("RegionID", id);
			localisations = query
					.getResultList();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return localisations;
	}

	@Override
	public void delete(Localisation entity) {

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
	public List<Localisation> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Localisation> getLocalisations = session.createQuery(" FROM " + Localisation.class.getSimpleName())
				.list();
		session.close();
		return getLocalisations;
	}

	@SuppressWarnings("unchecked")
	public List<Localisation> findByProject(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Localisation> localisations = null;

		try {
			@SuppressWarnings("rawtypes")
			Query query = session
					.createQuery(" FROM " + Localisation.class.getSimpleName() + " WHERE ProjetId=:projetId");
			query.setParameter("projetId", id);
			localisations = query
					.getResultList();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return localisations;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
