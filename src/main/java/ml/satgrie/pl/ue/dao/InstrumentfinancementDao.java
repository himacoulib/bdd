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
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class InstrumentfinancementDao implements BddInterface<Instrumentfinancement, Integer> {

	/**
	 * 
	 */
	public InstrumentfinancementDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Instrumentfinancement entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Instrumentfinancement entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Instrumentfinancement findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Instrumentfinancement instrumentfinancement = session.find(Instrumentfinancement.class, id);
		session.close();
		return instrumentfinancement;
	}

	@Override
	public void delete(Instrumentfinancement entity) {
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
	public List<Instrumentfinancement> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Instrumentfinancement> getInstrumentfinancements = session
				.createQuery(" FROM " + Instrumentfinancement.class.getSimpleName())
				.list();
		session.close();
		session.close();
		return getInstrumentfinancements;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public Instrumentfinancement findByName(String name) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session.createQuery(
				" FROM " + Instrumentfinancement.class.getSimpleName() + " WHERE InstrumentFinancementNom=:name");
		query.setParameter("name", name);
		try {
			Instrumentfinancement instrument = (Instrumentfinancement) query
					.getSingleResult();
			session.close();
			return instrument;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

}
