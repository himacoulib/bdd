/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.DocumentDao;
import ml.satgrie.pl.ue.model.Document;

/**
 * @author drTiefari
 *
 */
public class DocumentViewService {
	private static DocumentDao DocumentViewDao;

	/**
	 * 
	 */
	public DocumentViewService() {
		DocumentViewDao = new DocumentDao();
	}

	public void persist(Document entity) {
		DocumentViewDao.persist(entity);
	}

	public void update(Document entity) {
		DocumentViewDao.update(entity);
	}

	public Document findById(int id) {
		Document document = DocumentViewDao.findById(id);
		return document;
	}

	public void delete(int id) {
		Document document = DocumentViewDao.findById(id);
		DocumentViewDao.delete(document);
	}

	public List<Document> findAll() {
		List<Document> documents = DocumentViewDao.findAll();
		return documents;
	}

	public void deleteAll() {
		DocumentViewDao.deleteAll();
	}

	public DocumentDao PhotoViewDao() {
		return DocumentViewDao;
	}

}
