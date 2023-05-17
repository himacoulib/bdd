package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;

import com.sun.jersey.api.model.Parameter.Source;

import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Profile;
import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.InstrumentfinancementViewService;
import ml.satgrie.pl.ue.services.ProfileViewService;
import ml.satgrie.pl.ue.services.SourceFinancementViewService;
import ml.satgrie.pl.ue.services.UtilisateurViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.SendMail;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "utilisateurControllerView")
@ViewScoped
public class UtilisateurControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Utilisateur> users;

	private Utilisateur selectedUser;

	private List<Utilisateur> selectedUsers;

	private boolean editSelected = false;

	private String selectedProfil;
	private String selectedPwd;
	private String selectedSourceFinancement;

	private List<String> listProfil;

	private UtilisateurViewService userService;

	private ProfileViewService profilService;

	private SourceFinancementViewService sourceFinancementViewService;

	private List<Profile> profiles;
	private List<String> listeSource;
	private List<SourceFinancement> sourceFinancements;

	@PostConstruct
	public void init() {
		this.selectedUser = new Utilisateur();
		this.userService = new UtilisateurViewService();
		this.sourceFinancementViewService = new SourceFinancementViewService();
		this.sourceFinancements = this.sourceFinancementViewService.findAll();
		this.users = this.userService.findAll();
		this.profilService = new ProfileViewService();
		this.profiles = this.profilService.findAll();
		this.listProfil = new ArrayList<String>();
		this.listeSource = new ArrayList<String>();

		for (SourceFinancement sourceFinancement : sourceFinancements) {
			this.listeSource.add(sourceFinancement.getSourceFinancementNom());
		}
		for (Profile profilC : this.profiles) {
			this.listProfil.add(profilC.getProfileNom());
		}

	}

	public void openNew() {
		this.selectedUser = new Utilisateur();
		Profile profile = new Profile();
		this.selectedUser.setProfile(profile);
		this.editSelected = false;

	}

	public void saveUser() {
		if (this.selectedUser.getUtilisateurId() == null) {
			Profile profil = null;
			SourceFinancement sourceFinancementTmp = null;
			for (Profile profilC : this.profiles) {
				if (profilC.getProfileNom().equals(this.selectedProfil)) {
					profil = profilC;
					break;
				}
			}
			
			for (SourceFinancement sourceFinancement : sourceFinancements) {
				if (sourceFinancement.getSourceFinancementNom().equals(this.selectedSourceFinancement)) {
					sourceFinancementTmp = sourceFinancement;
				}
			}
			if (profil != null && sourceFinancementTmp != null) {
				if (this.userService.findByAuth(this.selectedUser.getUtilisateurEmail()) == null) {
					try {
						this.selectedUser.setProfile(profil);
						this.selectedUser.setSourcefinancement(sourceFinancementTmp);
						int min = 1;
						int max = 999_999;
						int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						this.selectedUser.setUtilisateurId(randomNum);

						String hashed = BCrypt.hashpw(this.selectedUser.getUtilisateurEmail(), BCrypt.gensalt());
						this.selectedUser.setUtilisateurPwd(hashed);

						this.userService.persist(this.selectedUser);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur Ajouter"));
						String URL_ACCES = "http://bdd-cooperation-ue-mali.ml/PlateformeBdd";
						String msgsSend = "BONJOUR, Madame/Monsieur " + this.selectedUser.getUtilisateurNom() + ", \n \n"
								+ "Votre comte est créé. Vous pouvez accéder à la " + "plateforme via le lien: "
								+ URL_ACCES + ". Votre login est votre mail et votre mot "
								+ "de passe est aussi votre mail que vous pouvez changer à la première connexion."
								+ "\n" + "Bonne journée. " + "\n" + "Cordialement, " + "\n" + "\n"

								+ "L'equipe de SATGRIE S.A.S";

						SendMail.send(this.selectedUser.getUtilisateurEmail(), "CREATION DE COMPTE", msgsSend);

						this.users.add(this.selectedUser);
					} catch (Exception e) {
						System.out.println(e);
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Une erreure est survenue"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email deja enregistrer"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profile ne peut être vide / Pays ne peut être vide "));
			}

		} else {
			Profile profil = null;
			for (Profile profilC : this.profiles) {
				if (profilC.getProfileNom().equals(this.selectedProfil)) {
					profil = profilC;
					break;
				}
			}
			if (profil != null) {

				try {
					this.selectedUser.setProfile(profil);
					SourceFinancement sourceFinancementTmp = new SourceFinancement();
					for (SourceFinancement sourceFinancement : sourceFinancements) {
						if (sourceFinancement.getSourceFinancementNom().equals(this.selectedSourceFinancement)) {
							sourceFinancementTmp = sourceFinancement;
						}
					}
					
					if (this.selectedUser.getUtilisateurPwd().length() != 60) {
						String hashed = BCrypt.hashpw(this.selectedUser.getUtilisateurPwd(), BCrypt.gensalt());
						this.selectedUser.setUtilisateurPwd(hashed);	
					}
					
					this.selectedUser.setSourcefinancement(sourceFinancementTmp);
					this.userService.update(this.selectedUser);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur mise à jour"));
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().redirect("user.xhtml");

				} catch (Exception e) {
					System.out.println(e);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Profile ne peut être vide"));
			}

		}

		PrimeFaces.current().executeScript("PF('manageUtilisateurDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
	}

	public void deleteSelectedUser() {
		// this.users.remove(this.selectedUser);
		if (this.selectedUser.getUtilisateurId() != null) {
			try {
				this.userService.delete(this.selectedUser.getUtilisateurId());
				//this.users.remove(this.selectedUser);
				this.selectedUser = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur Supprimer"));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}

			PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
		}

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedUsers()) {
			int size = this.selectedUsers.size();
			return size > 1 ? size + " utilisateurs selected" : "1 region selected";
		}

		return "Delete";
	}

	public boolean hasSelectedUsers() {
		return this.selectedUsers != null && !this.selectedUsers.isEmpty();
	}

	public void deleteSelectedUsers() {
		this.users.removeAll(this.selectedUsers);
		this.selectedUsers = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateurs Removed"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
	}

	public List<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}

	public Utilisateur getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utilisateur selectedUser) {
		this.selectedUser = selectedUser;
		this.editSelected = true;
	}

	public List<Utilisateur> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<Utilisateur> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public boolean isEditSelected() {
		return editSelected;
	}

	public void setEditSelected(boolean editSelected) {
		this.editSelected = editSelected;
	}

	public String getSelectedProfil() {
		return selectedProfil;
	}

	public void setSelectedProfil(String selectedProfil) {
		this.selectedProfil = selectedProfil;
	}

	public List<String> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<String> listProfil) {
		this.listProfil = listProfil;
	}

	public UtilisateurViewService getUserService() {
		return userService;
	}

	public void setUserService(UtilisateurViewService userService) {
		this.userService = userService;
	}

	public ProfileViewService getProfilService() {
		return profilService;
	}

	public void setProfilService(ProfileViewService profilService) {
		this.profilService = profilService;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the selectedSourceFinancement
	 */
	public String getSelectedSourceFinancement() {
		return selectedSourceFinancement;
	}

	/**
	 * @param selectedSourceFinancement the selectedSourceFinancement to set
	 */
	public void setSelectedSourceFinancement(String selectedSourceFinancement) {
		this.selectedSourceFinancement = selectedSourceFinancement;
	}

	/**
	 * @return the sourceFinancementViewService
	 */
	public SourceFinancementViewService getSourceFinancementViewService() {
		return sourceFinancementViewService;
	}

	/**
	 * @param sourceFinancementViewService the sourceFinancementViewService to set
	 */
	public void setSourceFinancementViewService(SourceFinancementViewService sourceFinancementViewService) {
		this.sourceFinancementViewService = sourceFinancementViewService;
	}

	/**
	 * @return the listeSource
	 */
	public List<String> getListeSource() {
		return listeSource;
	}

	/**
	 * @param listeSource the listeSource to set
	 */
	public void setListeSource(List<String> listeSource) {
		this.listeSource = listeSource;
	}

	/**
	 * @return the sourceFinancements
	 */
	public List<SourceFinancement> getSourceFinancements() {
		return sourceFinancements;
	}

	/**
	 * @param sourceFinancements the sourceFinancements to set
	 */
	public void setSourceFinancements(List<SourceFinancement> sourceFinancements) {
		this.sourceFinancements = sourceFinancements;
	}

	/**
	 * @return the selectedPwd
	 */
	public String getSelectedPwd() {
		return selectedPwd;
	}

	/**
	 * @param selectedPwd the selectedPwd to set
	 */
	public void setSelectedPwd(String selectedPwd) {
		this.selectedPwd = selectedPwd;
	}
	
	

}