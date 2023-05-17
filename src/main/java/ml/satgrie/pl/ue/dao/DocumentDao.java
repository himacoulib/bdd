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
import ml.satgrie.pl.ue.model.Document;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author HamayeLAH
 *
 */
public class DocumentDao implements BddInterface<Document, Integer> {

	/**
	 * 
	 */
	public DocumentDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Document entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(Document entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	@Override
	public Document findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Document document = session.find(Document.class, id);
		session.close();
		return document;
	}

	@Override
	public void delete(Document entity) {
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
	public List<Document> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();

		List<Document> getDocuments = session.createQuery(" FROM " + Document.class.getSimpleName())
				.list();
		session.close();
		return getDocuments;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
