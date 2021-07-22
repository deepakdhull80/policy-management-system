package com.imo.policy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consumer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDetails {

	@Id
	@Column(name = "ID")
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(name = "Name")
	private String name;

	@NotBlank
	@Size(max = 20)
	@Column(name = "DOB")
	private String dob;

	@NotBlank
	@Size(max = 12)
	@Column(name = "PAN_Details")
	private String pandetails;

	@NotBlank
	@Size(max = 50)
	@Column(name = "Email")
	private String email;

	@NotBlank
	@Size(max = 10)
	@Column(name = "Phone")
	private String phone;

	@NotBlank
	@Size(max = 50)
	@Column(name = "Agent_Name")
	private String agentname;

	@NotNull
	@Column(name = "Agent_ID")
	private Long agentid;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "consumer_id")
	private List<BusinessDetails> business;
}

