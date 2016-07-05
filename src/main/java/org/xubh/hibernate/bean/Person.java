package org.xubh.hibernate.bean;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 5700634796035778410L;
	private int id;
	private String name;
	private IdCard idCard;

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

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
}
