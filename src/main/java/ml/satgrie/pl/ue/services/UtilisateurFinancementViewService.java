/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.UtilisateurDao;
import ml.satgrie.pl.ue.model.Utilisateur;

/**
 * @author drTiefari
 *
 */
public class UtilisateurFinancementViewService {
	private static UtilisateurDao UtilisateurViewDao;

	/**
	 * 
	 */
	public UtilisateurFinancementViewService() {
		UtilisateurViewDao = new UtilisateurDao();
	}

	public void persist(Utilisateur entity) {
		UtilisateurViewDao.persist(entity);
	}

	public void update(Utilisateur entity) {
		UtilisateurViewDao.update(entity);
	}

	public Utilisateur findById(int id) {
		Utilisateur Utilisateur = UtilisateurViewDao.findById(id);
		return Utilisateur;
	}

	public void delete(int id) {
		Utilisateur Utilisateur = UtilisateurViewDao.findById(id);
		UtilisateurViewDao.delete(Utilisateur);
	}

	public List<Utilisateur> findAll() {
		List<Utilisateur> Utilisateurs = UtilisateurViewDao.findAll();
		return Utilisateurs;
	}

	public void deleteAll() {
		UtilisateurViewDao.deleteAll();
	}

	public UtilisateurDao UtilisateurViewDao() {
		return UtilisateurViewDao;
	}

}
