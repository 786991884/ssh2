package org.xubh.hibernate.bean;

import java.io.Serializable;

public class IdCard implements Serializable {
	private static final long serialVersionUID = -1577364136819872520L;
	private int id;
	private String cardId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
