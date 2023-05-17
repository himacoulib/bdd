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
import ml.satgrie.pl.ue.model.LocalisationSecteur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class LocalisationSecteurDao implements BddInterface<LocalisationSecteur, Integer> {

	/**
	 * 
	 */
	public LocalisationSecteurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(LocalisationSecteur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(LocalisationSecteur entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public LocalisationSecteur findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		LocalisationSecteur utilisateur = session.find(LocalisationSecteur.class, id);
		session.close();
		return utilisateur;
	}

	@Override
	public void delete(LocalisationSecteur entity) {
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
	public List<LocalisationSecteur> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<LocalisationSecteur> getUtilisateurs = session
				.createQuery(" FROM " + LocalisationSecteur.class.getSimpleName())
				.list();
		session.close();
		return getUtilisateurs;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
