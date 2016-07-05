package org.xubh.hibernate.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tree implements Serializable {
	private static final long serialVersionUID = -6280228712517826263L;
	private int id;
	private int age;
	private int height;
	private Set<Leaves> leaves = new HashSet<Leaves>();
	List<Leaves> a=new ArrayList<Leaves>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Set<Leaves> getLeaves() {
		return leaves;
	}

	public void setLeaves(Set<Leaves> leaves) {
		this.leaves = leaves;
	}
}
