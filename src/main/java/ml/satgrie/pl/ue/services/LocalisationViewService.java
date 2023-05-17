/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.LocalisationDao;
import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.LocalisationId;

/**
 * @author drTiefari
 *
 */
public class LocalisationViewService {
	private static LocalisationDao LocalisationViewDao;

	/**
	 * 
	 */
	public LocalisationViewService() {
		LocalisationViewDao = new LocalisationDao();
	}

	public void persist(Localisation entity) {
		LocalisationViewDao.persist(entity);
	}

	public void update(Localisation entity) {
		LocalisationViewDao.update(entity);
	}

	public Localisation findById(int id) {
		Localisation Localisation = LocalisationViewDao.findById(id);
		return Localisation;
	}

	public void delete(Localisation id) {
		LocalisationViewDao.delete(id);
	
	}
	
	public List<Localisation> findByRegionId(int id) {
		List<Localisation> localisations = LocalisationViewDao.findByRegionId(id);
		return localisations;
	}

	public List<Localisation> findAll() {
		List<Localisation> Localisations = LocalisationViewDao.findAll();
		return Localisations;
	}
	
	public List<Localisation> findByProject(int id) {
		List<Localisation> localisations  = LocalisationViewDao.findByProject(id);
		return localisations;
	}

	public void deleteAll() {
		LocalisationViewDao.deleteAll();
	}

	public LocalisationDao LocalisationViewDao() {
		return LocalisationViewDao;
	}

	

}
