package com.app.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="usertab")
public class User {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="pwd")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name="user_rolestab",
			joinColumns = @JoinColumn(name="id")
			)
	private Set<String> roles;

}
