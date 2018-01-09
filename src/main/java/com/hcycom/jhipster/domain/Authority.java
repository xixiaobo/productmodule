package com.hcycom.jhipster.domain;

public class Authority {
	private String uuid;
	private String authority_name;
	private String authority_type;
	private String foreign_uuid;
	private String authority_url;
	private int authority_status;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAuthority_name() {
		return authority_name;
	}
	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}
	public String getAuthority_type() {
		return authority_type;
	}
	public void setAuthority_type(String authority_type) {
		this.authority_type = authority_type;
	}
	
	public String getForeign_uuid() {
		return foreign_uuid;
	}
	public void setForeign_uuid(String foreign_uuid) {
		this.foreign_uuid = foreign_uuid;
	}
	public String getAuthority_url() {
		return authority_url;
	}
	public void setAuthority_url(String authority_url) {
		this.authority_url = authority_url;
	}
	public int getAuthority_status() {
		return authority_status;
	}
	public void setAuthority_status(int authority_status) {
		this.authority_status = authority_status;
	}
	@Override
	public String toString() {
		return "Authority [uuid=" + uuid + ", authority_name=" + authority_name + ", authority_type=" + authority_type
				+ ", foreign_uuid=" + foreign_uuid + ", authority_url=" + authority_url + ", authority_status="
				+ authority_status + "]";
	}
	
	
}
