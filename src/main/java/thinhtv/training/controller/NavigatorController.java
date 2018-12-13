package thinhtv.training.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigatorController")
@RequestScoped
public class NavigatorController implements Serializable {

	private static final long serialVersionUID = -2055816072306781378L;

	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	public String navi_PageID () {
		return getPageId();
	}
	
	public String navi_Movies() {
		return "MOVIES";
	}

	public String navi_MovieDetails() {
		return "MOVIEDETAIL";
	}

	/* getter / setter */
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}
