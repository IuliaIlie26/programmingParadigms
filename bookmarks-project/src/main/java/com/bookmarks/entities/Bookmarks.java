package com.bookmarks.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKMARKS")
public class Bookmarks {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "seq_bookmarks_id")
	@SequenceGenerator(name="seq_bookmarks_id", sequenceName = "seq_bookmarks_id", allocationSize=10)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LINK")
	private String link;

	@Column(name = "USERID")
	private Long userid;

	@Column(name = "VOTES")
	private Integer votes;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="bookmarks")
	private List<Collection> collection;

	public Long getId() {
		return id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<Collection> getCollection() {
		return collection;
	}

	public void setCollection(List<Collection> collection) {
		this.collection = collection;
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

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
}
