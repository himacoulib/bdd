/**
 * 
 */
package ml.satgrie.pl.ue.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.hibernate.query.Query;

import ml.satgrie.pl.ue.interfaces.BddInterface;
import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Projetchapitre;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class ProjetchapitreDao implements BddInterface<Projetchapitre, Integer> {

	/**
	 * 
	 */
	public ProjetchapitreDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Projetchapitre entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	public void persist2(List<Secteur> secteurs, int projetId, String type) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		for (Secteur secteurC : secteurs) {
			Projetchapitre prochap = new Projetchapitre();
			prochap.setProjetId(projetId);
			prochap.setFormerSectorId(secteurC.getId().getSecteurId());
			prochap.setType(type);
			int min = 1;
			int max = 999_999;
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			prochap.setId(randomNum);
			session.persist(prochap);
		}
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Projetchapitre entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Projetchapitre findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Projetchapitre projetchapitre = session.find(Projetchapitre.class, id);
		session.close();
		return projetchapitre;
	}

	@Override
	public void delete(Projetchapitre entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
		session.close();
	}

	public void deleteMultiple(List<Integer> ids) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		for (Integer id : ids) {
			Projetchapitre projchap = session.get(Projetchapitre.class, id);
			session.delete(projchap);
		}
		transaction.commit();
		session.close();
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Projetchapitre> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Projetchapitre> getProjetchapitres = session.createQuery(" FROM " + Projetchapitre.class.getSimpleName())
				.list();
		session.close();
		return getProjetchapitres;
	}

	@SuppressWarnings("unchecked")
	public List<Projetchapitre> findByProject(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Projetchapitre> projetschapitre = new ArrayList<Projetchapitre>();

		try {
			@SuppressWarnings("rawtypes")
			Query query = session
					.createQuery(" FROM " + Projetchapitre.class.getSimpleName() + " WHERE ProjetID=:projetId");
			query.setParameter("projetId", id);
			projetschapitre = query.setCacheMode(CacheMode.REFRESH)
					.setHint(QueryHints.CACHEABLE, true).getResultList();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			session.close();
		}
		return projetschapitre;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
