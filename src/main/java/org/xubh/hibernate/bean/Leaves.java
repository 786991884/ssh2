package org.xubh.hibernate.bean;

import java.io.Serializable;

public class Leaves implements Serializable {
	private static final long serialVersionUID = -4006034454589775632L;
	private int id;
	private String color;
	private Tree tree;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

}
