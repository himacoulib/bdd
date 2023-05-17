/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.CommuneDao;
import ml.satgrie.pl.ue.model.Commune;

/**
 * @author drTiefari
 *
 */
public class CommuneViewService {
	private static CommuneDao CommuneViewDao;

	/**
	 * 
	 */
	public CommuneViewService() {
		CommuneViewDao = new CommuneDao();
	}

	public void persist(Commune entity) {
		CommuneViewDao.persist(entity);
	}

	public void update(Commune entity) {
		CommuneViewDao.update(entity);
	}

	public Commune findById(int id) {
		Commune Commune = CommuneViewDao.findById(id);
		return Commune;
	}

	public void delete(int id) {
		Commune Commune = CommuneViewDao.findById(id);
		CommuneViewDao.delete(Commune);
	}

	public List<Commune> findAll() {
		List<Commune> Communes = CommuneViewDao.findAll();
		return Communes;
	}

	public void deleteAll() {
		CommuneViewDao.deleteAll();
	}

	public CommuneDao CommuneViewDao() {
		return CommuneViewDao;
	}

}
