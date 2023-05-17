/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.LocalisationSecteurDao;
import ml.satgrie.pl.ue.model.LocalisationSecteur;

/**
 * @author drTiefari
 *
 */
public class LocalisationSecteurViewService {
	private static LocalisationSecteurDao socalisationSecteurDao;

	/**
	 * 
	 */
	public LocalisationSecteurViewService() {
		socalisationSecteurDao = new LocalisationSecteurDao();
	}

	public void persist(LocalisationSecteur entity) {
		socalisationSecteurDao.persist(entity);
	}

	public void update(LocalisationSecteur entity) {
		socalisationSecteurDao.update(entity);
	}

	public LocalisationSecteur findById(int id) {
		LocalisationSecteur Utilisateur = socalisationSecteurDao.findById(id);
		return Utilisateur;
	}

	public void delete(int id) {
		LocalisationSecteur Utilisateur = socalisationSecteurDao.findById(id);
		socalisationSecteurDao.delete(Utilisateur);
	}

	public List<LocalisationSecteur> findAll() {
		List<LocalisationSecteur> Utilisateurs = socalisationSecteurDao.findAll();
		return Utilisateurs;
	}

	public void deleteAll() {
		socalisationSecteurDao.deleteAll();
	}

	public LocalisationSecteurDao SecteurHierarViewDao() {
		return socalisationSecteurDao;
	}

}
