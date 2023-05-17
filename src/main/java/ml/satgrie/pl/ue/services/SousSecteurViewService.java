/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.SousSecteurDao;
import ml.satgrie.pl.ue.model.SousSecteur;

/**
 * @author drTiefari
 *
 */
public class SousSecteurViewService {
	private static SousSecteurDao SousSecteurViewDao;

	/**
	 * 
	 */
	public SousSecteurViewService() {
		SousSecteurViewDao = new SousSecteurDao();
	}

	public void persist(SousSecteur entity) {
		SousSecteurViewDao.persist(entity);
	}

	public void update(SousSecteur entity) {
		SousSecteurViewDao.update(entity);
	}

	public SousSecteur findById(int id) {
		SousSecteur SousSecteur = SousSecteurViewDao.findById(id);
		return SousSecteur;
	}

	public void delete(int id) {
		SousSecteur SousSecteur = SousSecteurViewDao.findById(id);
		SousSecteurViewDao.delete(SousSecteur);
	}

	public List<SousSecteur> findAll() {
		List<SousSecteur> SousSecteurs = SousSecteurViewDao.findAll();
		return SousSecteurs;
	}

	public void deleteAll() {
		SousSecteurViewDao.deleteAll();
	}

	public SousSecteurDao SousSecteurViewDao() {
		return SousSecteurViewDao;
	}

}
