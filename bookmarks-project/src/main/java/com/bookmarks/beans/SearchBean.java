package com.bookmarks.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import com.bookmarks.dao.CollectionsDAO;

@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchBean implements Serializable {

	private static final long serialVersionUID = 1632796166141430468L;
	private final static Logger logger = Logger.getLogger(SearchBean.class);
	private String bookmarks;
	private String selectedCollection;
	private List<String> publicList = new ArrayList<>();;

	public List<String> getPublicList() {
		publicList.addAll(CollectionsDAO.getPublic());
		return publicList;
	}

	public void setPublicList(List<String> publicList) {
		this.publicList = publicList;
	}

	public String getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(String bookmarks) {
		this.bookmarks = bookmarks;
	}

	public String getSelectedCollection() {
		return selectedCollection;
	}

	public void setSelectedCollection(String selectedCollection) {
		this.selectedCollection = selectedCollection;
	}

	public String search() {
		return "results";
	}

}
