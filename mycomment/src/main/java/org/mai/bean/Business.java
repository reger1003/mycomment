package org.mai.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Business extends BaseBean{

	private Long id;
	private String imgFileName;
	private String title;
	private String subtitle;
	private Double price;
	private Integer distance;
	private Integer number;
	private String descsort;
	private String city;
	private String category;
	private Long star;
	
	private Dic categoryDic;
	private Dic cityDic;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDescsort() {
		return descsort;
	}
	public void setDescsort(String descsort) {
		this.descsort = descsort;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getStar() {
		return star;
	}
	public void setStar(Long star) {
		this.star = star;
	}
	public Dic getCategoryDic() {
		return categoryDic;
	}
	public void setCategoryDic(Dic categoryDic) {
		this.categoryDic = categoryDic;
	}
	public Dic getCityDic() {
		return cityDic;
	}
	public void setCityDic(Dic cityDic) {
		this.cityDic = cityDic;
	}
	
}
