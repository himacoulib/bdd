package ml.satgrie.pl.ue.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import ml.satgrie.pl.ue.model.Devise;
import ml.satgrie.pl.ue.model.Photo;
import ml.satgrie.pl.ue.services.DeviseViewService;
import ml.satgrie.pl.ue.services.PhotoViewService;
import ml.satgrie.pl.ue.utils.HibernateUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "gallerieControllerView")
@ViewScoped
public class GallerieControllerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Photo> photos;

	private PhotoViewService photoService;

	@PostConstruct
	public void init() {
		this.photoService = new PhotoViewService();
		this.photos = this.photoService.findAll();

	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public PhotoViewService getPhotoService() {
		return photoService;
	}

	public void setPhotoService(PhotoViewService photoService) {
		this.photoService = photoService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}