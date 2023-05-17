package ml.satgrie.pl.ue.utils;

import java.util.List;

public class PolygoneModelCercle {
	private String pays;
	private String region;
	private String cercle;
	private String commune;
	private List<String> polygoneLatLong;
	
	
	public PolygoneModelCercle(String pays, String region, String cercle, String commune,List<String> polygoneLatLong) {
		super();
		this.pays = pays;
		this.region = region;
		this.cercle = cercle;
		this.polygoneLatLong = polygoneLatLong;
		this.commune = commune;
	}
	public PolygoneModelCercle() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the cercle
	 */
	public String getCercle() {
		return cercle;
	}
	/**
	 * @param cercle the cercle to set
	 */
	public void setCercle(String cercle) {
		this.cercle = cercle;
	}
	/**
	 * @return the polygoneLatLong
	 */
	public List<String> getPolygoneLatLong() {
		return polygoneLatLong;
	}
	/**
	 * @param polygoneLatLong the polygoneLatLong to set
	 */
	public void setPolygoneLatLong(List<String> polygoneLatLong) {
		this.polygoneLatLong = polygoneLatLong;
	}
	/**
	 * @return the commune
	 */
	public String getCommune() {
		return commune;
	}
	/**
	 * @param commune the commune to set
	 */
	public void setCommune(String commune) {
		this.commune = commune;
	}
	
}
