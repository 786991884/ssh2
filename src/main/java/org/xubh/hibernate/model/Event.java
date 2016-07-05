package org.xubh.hibernate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity 注解说明Event是一个实体，默认对应表名也为event
@Entity
public class Event {
	private Long id;

	private String title;
	private Date date;

	public Event() {
	}

	public Event(Long id, String title, Date date) {
		this.setId(id);
		this.title = title;
		this.date = date;
	}

	// @Id 注解指定表event的主键
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
