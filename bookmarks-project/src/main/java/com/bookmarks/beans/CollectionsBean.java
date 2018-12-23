package com.bookmarks.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import com.bookmarks.dao.CollectionsDAO;

@ManagedBean(name = "collectionsBean")
@SessionScoped
public class CollectionsBean implements Serializable{

	private static final long serialVersionUID = -9090888186346496212L;
	private final static Logger logger = Logger.getLogger(CollectionsBean.class);
	private static CollectionsDAO dao = new CollectionsDAO();
}
