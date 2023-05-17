/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.SecteurDao;
import ml.satgrie.pl.ue.model.Secteur;

/**
 * @author drTiefari
 *
 */
public class SecteurViewService {
	private static SecteurDao SecteurViewDao;

	/**
	 * 
	 */
	public SecteurViewService() {
		SecteurViewDao = new SecteurDao();
	}

	public void persist(Secteur entity) {
		SecteurViewDao.persist(entity);
	}

	public void update(Secteur entity) {
		SecteurViewDao.update(entity);
	}

	public Secteur findById(int id) {
		Secteur Secteur = SecteurViewDao.findById(id);
		return Secteur;
	}

	public void delete(int id) {
		Secteur Secteur = SecteurViewDao.findById(id);
		SecteurViewDao.delete(Secteur);
	}

	public List<Secteur> findAll() {
		List<Secteur> Secteurs = SecteurViewDao.findAll();
		return Secteurs;
	}

	public void deleteAll() {
		SecteurViewDao.deleteAll();
	}

	public SecteurDao SecteurViewDao() {
		return SecteurViewDao;
	}

}
