/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.DeviseDao;
import ml.satgrie.pl.ue.model.Devise;

/**
 * @author drTiefari
 *
 */
public class DeviseViewService {
	private static DeviseDao DeviseViewDao;

	/**
	 * 
	 */
	public DeviseViewService() {
		DeviseViewDao = new DeviseDao();
	}

	public void persist(Devise entity) {
		DeviseViewDao.persist(entity);
	}

	public void update(Devise entity) {
		DeviseViewDao.update(entity);
	}

	public Devise findById(int id) {
		Devise Devise = DeviseViewDao.findById(id);
		return Devise;
	}

	public void delete(int id) {
		Devise Devise = DeviseViewDao.findById(id);
		DeviseViewDao.delete(Devise);
	}

	public List<Devise> findAll() {
		List<Devise> Devises = DeviseViewDao.findAll();
		return Devises;
	}

	public void deleteAll() {
		DeviseViewDao.deleteAll();
	}

	public DeviseDao DeviseViewDao() {
		return DeviseViewDao;
	}

}
