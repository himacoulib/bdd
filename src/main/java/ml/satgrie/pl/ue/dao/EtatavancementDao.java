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
import ml.satgrie.pl.ue.model.Etatavancement;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class EtatavancementDao implements BddInterface<Etatavancement, Integer> {

	/**
	 * 
	 */
	public EtatavancementDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Etatavancement entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Etatavancement entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Etatavancement findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Etatavancement etatavancement = session.find(Etatavancement.class, id);
		session.close();
		return etatavancement;
	}

	@Override
	public void delete(Etatavancement entity) {
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
	public List<Etatavancement> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Etatavancement> getEtatavancements = session.createQuery(" FROM " + Etatavancement.class.getSimpleName())
				.list();
		session.close();
		return getEtatavancements;
	}

	@SuppressWarnings("unchecked")
	public List<Etatavancement> findByProject(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Etatavancement> etatavancements = new ArrayList<Etatavancement>();

		try {
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(" FROM " + Budget.class.getSimpleName() + " WHERE ProjetId=:projetId");
			query.setParameter("projetId", id);
			etatavancements = query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getResultList();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return etatavancements;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
