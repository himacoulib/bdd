/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.SecteurHierarDao;
import ml.satgrie.pl.ue.model.SecteurHierar;

/**
 * @author drTiefari
 *
 */
public class SecteurHierarViewService {
	private static SecteurHierarDao secteurHierarDao;

	/**
	 * 
	 */
	public SecteurHierarViewService() {
		secteurHierarDao = new SecteurHierarDao();
	}

	public void persist(SecteurHierar entity) {
		secteurHierarDao.persist(entity);
	}

	public void update(SecteurHierar entity) {
		secteurHierarDao.update(entity);
	}

	public SecteurHierar findById(int id) {
		SecteurHierar Utilisateur = secteurHierarDao.findById(id);
		return Utilisateur;
	}

	public void delete(int id) {
		SecteurHierar Utilisateur = secteurHierarDao.findById(id);
		secteurHierarDao.delete(Utilisateur);
	}

	public List<SecteurHierar> findAll() {
		List<SecteurHierar> Utilisateurs = secteurHierarDao.findAll();
		return Utilisateurs;
	}

	public void deleteAll() {
		secteurHierarDao.deleteAll();
	}

	public SecteurHierarDao SecteurHierarViewDao() {
		return secteurHierarDao;
	}
	
	public SecteurHierar findByAuth(String login) {
		SecteurHierar Utilisateur = secteurHierarDao.findByAuth(login);
		return Utilisateur;
	}

}
