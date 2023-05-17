package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;

import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.UtilisateurViewService;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "passwordControllerView")
@ViewScoped
public class PasswordControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private UtilisateurViewService userService;

	private String password;

	private String oldPassword;

	Utilisateur userCurrent;

	@PostConstruct
	public void init() {

		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur = (Utilisateur) SessionUtils.getRequest().getSession().getAttribute("user");
		this.userCurrent = utilisateur;

	}

	public void updatePassword() {
		if (BCrypt.checkpw(this.oldPassword, userCurrent.getUtilisateurPwd())) {
			try {

				String hashed = BCrypt.hashpw(this.password, BCrypt.gensalt());
				this.userCurrent.setUtilisateurPwd(hashed);
				
				this.userService = new UtilisateurViewService();
				this.userService.update(this.userCurrent);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe modifier"));

			} catch (Exception e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue"));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe incorrect"));
		}
		

		PrimeFaces.current().ajax().update("form:messages", "form");
	}

	public UtilisateurViewService getUserService() {
		return userService;
	}

	public void setUserService(UtilisateurViewService userService) {
		this.userService = userService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Utilisateur getUserCurrent() {
		return userCurrent;
	}

	public void setUserCurrent(Utilisateur userCurrent) {
		this.userCurrent = userCurrent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}