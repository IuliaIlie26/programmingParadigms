package com.bookmarks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COLLECTIONS")
public class Collection {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_collections_id")
	@SequenceGenerator(name = "seq_collections_id", sequenceName = "seq_collections_id", allocationSize = 10)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OWNER")
	private Long userid;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "COLLECTIONS_BOOKMARKS", joinColumns = {
			@JoinColumn(name = "COLLECTIONID") }, inverseJoinColumns = { @JoinColumn(name = "BOOKMARKID") })
	private List<Bookmarks> bookmarks;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long owner) {
		this.userid = owner;
	}

	public List<Bookmarks> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<Bookmarks> bookmarks) {
		this.bookmarks = bookmarks;
	}

}
