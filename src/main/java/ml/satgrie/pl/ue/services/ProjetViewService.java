/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.ProjetDao;
import ml.satgrie.pl.ue.model.Projet;

/**
 * @author drTiefari
 *
 */
public class ProjetViewService {
	private static ProjetDao projetViewDao;

	/**
	 * 
	 */
	public ProjetViewService() {
		projetViewDao = new ProjetDao();
	}

	public void persist(Projet entity) {
		projetViewDao.persist(entity);
	}

	public void update(Projet entity) {
		projetViewDao.update(entity);
	}

	public Projet findById(int id) {
		Projet Projet = projetViewDao.findById(id);
		return Projet;
	}

	public void delete(int id) {
		Projet Projet = projetViewDao.findById(id);
		projetViewDao.delete(Projet);
	}

	public List<Projet> findAll() {
		
		List<Projet> Projets = projetViewDao.findAll();
		
		return Projets;
	}

	public void deleteAll() {
		projetViewDao.deleteAll();
	}

	public ProjetDao projetViewDao() {
		return projetViewDao;
	}

}
