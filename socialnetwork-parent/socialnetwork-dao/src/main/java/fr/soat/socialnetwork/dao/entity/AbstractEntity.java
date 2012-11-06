package fr.soat.socialnetwork.dao.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3408841062688357646L;

	public abstract Integer getId();
}