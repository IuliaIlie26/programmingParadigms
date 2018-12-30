package com.bookmarks.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.bookmarks.dao.BookmarksDAO;
import com.bookmarks.dao.CollectionsDAO;
import com.bookmarks.dao.UserDAO;
import com.bookmarks.entities.Collections;

@ManagedBean(name = "bookmarksBean")
@SessionScoped
public class BookmarksBean implements Serializable {

	private final static Logger logger = Logger.getLogger(BookmarksBean.class);
	private static final long serialVersionUID = 2255913338956039930L;
	private String name;
	private String link;
	private boolean shared = true;
	private String isPublic;
	private String isPrivate;
	private List<Collections> privateList;
	private List<Collections> publicList;
	private Collections selectedCollection;
	private String username;
	private Long userid;
	private String newCollection;

	@PostConstruct
	public void init() {

		username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		userid = UserDAO.getUserIdByUsername(username);
		privateList = new ArrayList<>();
		publicList = new ArrayList<>();
		privateList.addAll(CollectionsDAO.getPrivate(userid));
		publicList.addAll(CollectionsDAO.getPublic());

	}

	public Collections getSelectedCollection() {
		return selectedCollection;
	}

	public void setSelectedCollection(Collections selectedCollection) {
		this.selectedCollection = selectedCollection;
	}

	public String getNewCollection() {
		return newCollection;
	}

	public void setNewCollection(String newCollection) {
		this.newCollection = newCollection;
	}

	public List<Collections> getPrivateList() {
		return privateList;
	}

	public void setPrivateList(List<Collections> privateList) {
		this.privateList = privateList;
	}

	public List<Collections> getPublicList() {
		return publicList;
	}

	public void setPublicList(List<Collections> publicList) {
		this.publicList = publicList;
	}

	public String getIsPrivate() {
		if (shared)
			isPrivate = "block";
		else
			isPrivate = "none";

		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getIsPublic() {
		if (!shared)
			isPublic = "block";
		else
			isPublic = "none";
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public void add() {
		logger.info("Saving bookmark...");
		BookmarksDAO.insert(userid, link, name, selectedCollection);
	}

	// TODO vezi sa ia private listul cand se salveaza in el. vezi
	// https://stackoverflow.com/questions/12732257/how-to-update-the-property-member-variable-of-a-managed-bean
	public void saveCollection() {
		logger.info("Adding new Collection to list");
		CollectionsDAO.insert(newCollection, userid, shared);
		// privateList.add(new Collections(newCollection, userid, shared));
	}
	
}
