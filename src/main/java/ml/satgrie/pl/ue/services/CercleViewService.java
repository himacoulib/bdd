/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.CercleDao;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.CercleId;

/**
 * @author drTiefari
 *
 */
public class CercleViewService {
	private static CercleDao CercleViewDao;

	/**
	 * 
	 */
	public CercleViewService() {
		CercleViewDao = new CercleDao();
	}

	public void persist(Cercle entity) {
		CercleViewDao.persist(entity);
	}

	public void update(Cercle entity) {
		CercleViewDao.update(entity);
	}

	public Cercle findById(int id) {
		Cercle Cercle = CercleViewDao.findById(id);
		return Cercle;
	}

	public void delete(int id) {
		Cercle Cercle = CercleViewDao.findById(id);
		CercleViewDao.delete(Cercle);
	}

	public List<Cercle> findAll() {
		List<Cercle> Cercles = CercleViewDao.findAll();
		return Cercles;
	}

	public void deleteAll() {
		CercleViewDao.deleteAll();
	}

	public CercleDao CercleViewDao() {
		return CercleViewDao;
	}

}
