package com.bookmarks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-20T10:48:51.771+0200")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, Long> userId;
	public static volatile SingularAttribute<Users, String> name;
	public static volatile SingularAttribute<Users, String> lastname;
	public static volatile SingularAttribute<Users, String> emailAddress;
	public static volatile SingularAttribute<Users, String> username;
	public static volatile SingularAttribute<Users, String> password;
}
