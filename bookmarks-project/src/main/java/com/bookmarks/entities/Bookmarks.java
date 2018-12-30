package com.bookmarks.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKMARKS")
@NamedQuery(name = "Bookmarks.findAll", query = "SELECT b FROM Bookmarks b")
public class Bookmarks {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bookmarks_id")
	@SequenceGenerator(name = "seq_bookmarks_id", sequenceName = "seq_bookmarks_id", allocationSize = 10)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LINK")
	private String link;

	@Column(name = "USERID")
	private Long userid;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "COLLECTIONS_BOOKMARKS", joinColumns = {
			@JoinColumn(name = "BOOKMARKID") }, inverseJoinColumns = { @JoinColumn(name = "COLLECTIONID") })
	private List<Collections> collections;

	public Long getId() {
		return id;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getUserid() {
		return userid;
	}

	public List<Collections> getCollection() {
		return collections;
	}

	public void setCollection(List<Collections> collections) {
		this.collections = collections;
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
}
