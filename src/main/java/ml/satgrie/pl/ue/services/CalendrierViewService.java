/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.CalendrierDao;
import ml.satgrie.pl.ue.model.Calendrier;

/**
 * @author drTiefari
 *
 */
public class CalendrierViewService {
	private static CalendrierDao CalendrierViewDao;

	/**
	 * 
	 */
	public CalendrierViewService() {
		CalendrierViewDao = new CalendrierDao();
	}

	public void persist(Calendrier entity) {
		CalendrierViewDao.persist(entity);
	}

	public void update(Calendrier entity) {
		CalendrierViewDao.update(entity);
	}

	public Calendrier findById(int id) {
		Calendrier Calendrier = CalendrierViewDao.findById(id);
		return Calendrier;
	}

	public void delete(int id) {
		Calendrier Calendrier = CalendrierViewDao.findById(id);
		CalendrierViewDao.delete(Calendrier);
	}

	public List<Calendrier> findAll() {
		List<Calendrier> Calendriers = CalendrierViewDao.findAll();
		return Calendriers;
	}

	public void deleteAll() {
		CalendrierViewDao.deleteAll();
	}

	public CalendrierDao CalendrierViewDao() {
		return CalendrierViewDao;
	}

}
