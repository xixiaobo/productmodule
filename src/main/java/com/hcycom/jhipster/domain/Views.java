package com.hcycom.jhipster.domain;

public class Views {
	private String uuid;
	private String views_name_cn;
	private String views_name_en;
	private String views_super;
	private String views_icon;
	private String views_level;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getViews_name_cn() {
		return views_name_cn;
	}
	public void setViews_name_cn(String views_name_cn) {
		this.views_name_cn = views_name_cn;
	}
	public String getViews_name_en() {
		return views_name_en;
	}
	public void setViews_name_en(String views_name_en) {
		this.views_name_en = views_name_en;
	}
	public String getViews_super() {
		return views_super;
	}
	public void setViews_super(String views_super) {
		this.views_super = views_super;
	}
	public String getViews_icon() {
		return views_icon;
	}
	public void setViews_icon(String views_icon) {
		this.views_icon = views_icon;
	}
	public String getViews_level() {
		return views_level;
	}
	public void setViews_level(String views_level) {
		this.views_level = views_level;
	}
	@Override
	public String toString() {
		return "Views [uuid=" + uuid + ", views_name_cn=" + views_name_cn + ", views_name_en=" + views_name_en
				+ ", views_super=" + views_super + ", views_icon=" + views_icon + ", views_level=" + views_level + "]";
	}
	

}
