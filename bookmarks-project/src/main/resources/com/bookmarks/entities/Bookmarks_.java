package com.bookmarks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-18T15:44:17.909+0200")
@StaticMetamodel(Bookmarks.class)
public class Bookmarks_ {
	public static volatile SingularAttribute<Bookmarks, Long> id;
	public static volatile SingularAttribute<Bookmarks, String> name;
	public static volatile SingularAttribute<Bookmarks, String> link;
	public static volatile SingularAttribute<Bookmarks, Long> userid;
	public static volatile SingularAttribute<Bookmarks, Integer> votes;
	public static volatile ListAttribute<Bookmarks, Collection> collection;
}
