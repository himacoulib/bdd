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
import ml.satgrie.pl.ue.model.Photo;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class PhotoDao implements BddInterface<Photo, Integer> {

	/**
	 * 
	 */
	public PhotoDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Photo entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Photo entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Photo findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Photo photo = session.find(Photo.class, id);
		session.close();
		return photo;
	}

	@Override
	public void delete(Photo entity) {
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
	public List<Photo> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<Photo> getPhotos = session.createQuery(" FROM " + Photo.class.getSimpleName())
				.list();
		session.close();
		return getPhotos;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
