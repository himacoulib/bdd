/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.ArrayList;
import java.util.List;

import ml.satgrie.pl.ue.dao.ProjetchapitreDao;
import ml.satgrie.pl.ue.model.Projetchapitre;
import ml.satgrie.pl.ue.model.Secteur;

/**
 * @author drTiefari
 *
 */
public class ProjetchapitreViewService {
	private static ProjetchapitreDao ProjetchapitreViewDao;

	/**
	 * 
	 */
	public ProjetchapitreViewService() {
		ProjetchapitreViewDao = new ProjetchapitreDao();
	}

	public void persist(Projetchapitre entity) {
		ProjetchapitreViewDao.persist(entity);
	}
	
	public void persistMultiple(List<Secteur> secteurs, int projetId, String type) {
		ProjetchapitreViewDao.persist2(secteurs,projetId, type);
	}

	public void update(Projetchapitre entity) {
		ProjetchapitreViewDao.update(entity);
	}

	public Projetchapitre findById(int id) {
		Projetchapitre Projetchapitre = ProjetchapitreViewDao.findById(id);
		return Projetchapitre;
	}

	public void delete(int id) {
		Projetchapitre Projetchapitre = ProjetchapitreViewDao.findById(id);
		ProjetchapitreViewDao.delete(Projetchapitre);
	}
	
	public void deleteMultiple(List<Projetchapitre> projchaps) {
		List<Integer> ids = new ArrayList<Integer>();
		for(Projetchapitre proj: projchaps) {
			ids.add(proj.getId());
		}
		ProjetchapitreViewDao.deleteMultiple(ids);
	}

	public List<Projetchapitre> findAll() {
		List<Projetchapitre> Projetchapitres = ProjetchapitreViewDao.findAll();
		return Projetchapitres;
	}
	
	public List<Projetchapitre> findByProjet(int id) {
		List<Projetchapitre> Projetchapitres = ProjetchapitreViewDao.findByProject(id);
		return Projetchapitres;
	}

	public void deleteAll() {
		ProjetchapitreViewDao.deleteAll();
	}

	public ProjetchapitreDao ProjetchapitreViewDao() {
		return ProjetchapitreViewDao;
	}

}
