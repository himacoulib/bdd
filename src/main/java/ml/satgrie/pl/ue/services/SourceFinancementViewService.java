/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.SourceFinancementDao;
import ml.satgrie.pl.ue.model.SourceFinancement;

/**
 * @author drTiefari
 *
 */
public class SourceFinancementViewService {
	private static SourceFinancementDao SourceFinancementViewDao;

	/**
	 * 
	 */
	public SourceFinancementViewService() {
		SourceFinancementViewDao = new SourceFinancementDao();
	}

	public void persist(SourceFinancement entity) {
		SourceFinancementViewDao.persist(entity);
	}

	public void update(SourceFinancement entity) {
		SourceFinancementViewDao.update(entity);
	}

	public SourceFinancement findById(int id) {
		SourceFinancement SourceFinancement = SourceFinancementViewDao.findById(id);
		return SourceFinancement;
	}

	public void delete(int id) {
		SourceFinancement SourceFinancement = SourceFinancementViewDao.findById(id);
		SourceFinancementViewDao.delete(SourceFinancement);
	}

	public List<SourceFinancement> findAll() {
		List<SourceFinancement> SourceFinancements = SourceFinancementViewDao.findAll();
		return SourceFinancements;
	}

	public void deleteAll() {
		SourceFinancementViewDao.deleteAll();
	}

	public SourceFinancementDao SourceFinancementViewDao() {
		return SourceFinancementViewDao;
	}

}
