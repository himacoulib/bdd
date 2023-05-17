/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.PaysDao;
import ml.satgrie.pl.ue.model.Pays;

/**
 * @author drTiefari
 *
 */
public class PaysViewService {
	private static PaysDao PaysViewDao;

	/**
	 * 
	 */
	public PaysViewService() {
		PaysViewDao = new PaysDao();
	}

	public void persist(Pays entity) {
		entity.setPaysId(null);
		PaysViewDao.persist(entity);
	}

	public void update(Pays entity) {
		PaysViewDao.update(entity);
	}

	public Pays findById(int id) {
		Pays Pays = PaysViewDao.findById(id);
		return Pays;
	}

	public void delete(int id) {
		Pays Pays = PaysViewDao.findById(id);
		PaysViewDao.delete(Pays);
	}

	public List<Pays> findAll() {
		List<Pays> Payss = PaysViewDao.findAll();
		return Payss;
	}

	public void deleteAll() {
		PaysViewDao.deleteAll();
	}

	public PaysDao PaysViewDao() {
		return PaysViewDao;
	}

}
