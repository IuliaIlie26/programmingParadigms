package com.bookmarks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-18T15:44:17.972+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> emailAddress;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> password;
}
