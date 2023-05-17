/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.InstrumentfinancementDao;
import ml.satgrie.pl.ue.model.Instrumentfinancement;

/**
 * @author drTiefari
 *
 */
public class InstrumentfinancementViewService {
	private static InstrumentfinancementDao InstrumentfinancementViewDao;

	/**
	 * 
	 */
	public InstrumentfinancementViewService() {
		InstrumentfinancementViewDao = new InstrumentfinancementDao();
	}

	public void persist(Instrumentfinancement entity) {
		InstrumentfinancementViewDao.persist(entity);
	}

	public void update(Instrumentfinancement entity) {
		InstrumentfinancementViewDao.update(entity);
	}

	public Instrumentfinancement findById(int id) {
		Instrumentfinancement Instrumentfinancement = InstrumentfinancementViewDao.findById(id);
		return Instrumentfinancement;
	}
	
	public Instrumentfinancement findByName(String name) {
		Instrumentfinancement Instrumentfinancement = InstrumentfinancementViewDao.findByName(name);
		return Instrumentfinancement;
	}

	public void delete(int id) {
		Instrumentfinancement Instrumentfinancement = InstrumentfinancementViewDao.findById(id);
		InstrumentfinancementViewDao.delete(Instrumentfinancement);
	}

	public List<Instrumentfinancement> findAll() {
		List<Instrumentfinancement> Instrumentfinancements = InstrumentfinancementViewDao.findAll();
		return Instrumentfinancements;
	}

	public void deleteAll() {
		InstrumentfinancementViewDao.deleteAll();
	}

	public InstrumentfinancementDao InstrumentfinancementViewDao() {
		return InstrumentfinancementViewDao;
	}

}
