/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.ChapitreDao;
import ml.satgrie.pl.ue.model.Chapitre;

/**
 * @author drTiefaria
 *
 */
public class ChapitreViewService {
	private static ChapitreDao ChapitreViewDao;

	/**
	 * 
	 */
	public ChapitreViewService() {
		ChapitreViewDao = new ChapitreDao();
	}

	public void persist(Chapitre entity) {
		ChapitreViewDao.persist(entity);
	}

	public void update(Chapitre entity) {
		ChapitreViewDao.update(entity);
	}

	public Chapitre findById(int id) {
		Chapitre Chapitre = ChapitreViewDao.findById(id);
		return Chapitre;
	}

	public void delete(int id) {
		Chapitre Chapitre = ChapitreViewDao.findById(id);
		ChapitreViewDao.delete(Chapitre);
	}

	public List<Chapitre> findAll() {
		List<Chapitre> Chapitres = ChapitreViewDao.findAll();
		return Chapitres;
	}

	public void deleteAll() {
		ChapitreViewDao.deleteAll();
	}

	public ChapitreDao ChapitreViewDao() {
		return ChapitreViewDao;
	}

}
