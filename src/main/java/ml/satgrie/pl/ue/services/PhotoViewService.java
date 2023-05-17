/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.PhotoDao;
import ml.satgrie.pl.ue.model.Photo;

/**
 * @author drTiefari
 *
 */
public class PhotoViewService {
	private static PhotoDao PhotoViewDao;

	/**
	 * 
	 */
	public PhotoViewService() {
		PhotoViewDao = new PhotoDao();
	}

	public void persist(Photo entity) {
		PhotoViewDao.persist(entity);
	}

	public void update(Photo entity) {
		PhotoViewDao.update(entity);
	}

	public Photo findById(int id) {
		Photo Photo = PhotoViewDao.findById(id);
		return Photo;
	}

	public void delete(int id) {
		Photo Photo = PhotoViewDao.findById(id);
		PhotoViewDao.delete(Photo);
	}

	public List<Photo> findAll() {
		List<Photo> Photos = PhotoViewDao.findAll();
		return Photos;
	}

	public void deleteAll() {
		PhotoViewDao.deleteAll();
	}

	public PhotoDao PhotoViewDao() {
		return PhotoViewDao;
	}

}
