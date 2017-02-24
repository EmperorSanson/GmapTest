package it.tbridge.maptest.war.beans.session;

import javax.faces.bean.SessionScoped;

import it.tbridge.maptest.war.models.Userinfo;

@SessionScoped
public class SessionManagerBean {
	
	public String logout(){
		return "";
	}

	public Userinfo getUserinfo(){
		return new Userinfo();
	}
	
}
