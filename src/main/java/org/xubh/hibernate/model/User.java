package org.xubh.hibernate.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 5534366436556031935L;
	private int id;
	private String name;
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public User() {
	}

	public User(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

}
