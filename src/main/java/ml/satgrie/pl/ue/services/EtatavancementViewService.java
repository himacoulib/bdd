/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.EtatavancementDao;
import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Etatavancement;

/**
 * @author drTiefari
 *
 */
public class EtatavancementViewService {
	private static EtatavancementDao EtatavancementViewDao;

	/**
	 * 
	 */
	public EtatavancementViewService() {
		EtatavancementViewDao = new EtatavancementDao();
	}

	public void persist(Etatavancement entity) {
		EtatavancementViewDao.persist(entity);
	}

	public void update(Etatavancement entity) {
		EtatavancementViewDao.update(entity);
	}

	public Etatavancement findById(int id) {
		Etatavancement Etatavancement = EtatavancementViewDao.findById(id);
		return Etatavancement;
	}

	public void delete(int id) {
		Etatavancement Etatavancement = EtatavancementViewDao.findById(id);
		EtatavancementViewDao.delete(Etatavancement);
	}

	public List<Etatavancement> findAll() {
		List<Etatavancement> Etatavancements = EtatavancementViewDao.findAll();
		return Etatavancements;
	}

	public List<Etatavancement> findByProject(int id) {
		List<Etatavancement> etatavancements = EtatavancementViewDao.findByProject(id);
		return etatavancements;
	}
	
	public void deleteAll() {
		EtatavancementViewDao.deleteAll();
	}

	public EtatavancementDao EtatavancementViewDao() {
		return EtatavancementViewDao;
	}

}
