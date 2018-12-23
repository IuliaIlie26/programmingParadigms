package com.bookmarks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-20T11:09:45.024+0200")
@StaticMetamodel(Collections.class)
public class Collections_ {
	public static volatile SingularAttribute<Collections, Long> id;
	public static volatile SingularAttribute<Collections, String> name;
	public static volatile SingularAttribute<Collections, Long> userid;
	public static volatile ListAttribute<Collections, Bookmarks> bookmarks;
}
