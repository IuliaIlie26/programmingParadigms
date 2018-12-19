package com.bookmarks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-18T15:44:17.962+0200")
@StaticMetamodel(Collection.class)
public class Collection_ {
	public static volatile SingularAttribute<Collection, Long> id;
	public static volatile SingularAttribute<Collection, String> name;
	public static volatile SingularAttribute<Collection, Long> userid;
	public static volatile ListAttribute<Collection, Bookmarks> bookmarks;
}
