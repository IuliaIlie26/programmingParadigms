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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COLLECTIONS")
@NamedQuery(name = "Collections.findAll", query = "SELECT c FROM Collections c")
public class Collections {

	public Collections() {
	}

	public Collections(String name, Long userid, boolean shared) {
		super();
		this.name = name;
		this.userid = userid;
		this.shared = shared;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_collections_id")
	@SequenceGenerator(name = "seq_collections_id", sequenceName = "seq_collections_id", allocationSize = 10)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OWNER")
	private Long userid;

	@Column(name = "SHARED")
	private boolean shared;
	
	@Column(name = "VOTES")
	private Integer votes;


	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "collections")
	private List<Bookmarks> bookmarks;

	public Long getId() {
		return id;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
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
	
	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Collections [id=" + id + ", name=" + name + ", userid=" + userid + ", bookmarks=" + bookmarks + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
