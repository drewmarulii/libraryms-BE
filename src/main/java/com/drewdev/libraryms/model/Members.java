package com.drewdev.libraryms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "m_members", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"member_email", "member_address", "member_phone"}) 
})
public class Members extends BaseEntity {

	@Column(name="member_email", length=50, nullable=false, unique=true)
	private String memberEmail;
	
	@Column(name="member_fullname", length=30, nullable=false)
	private String memberFullname;
	
	@Column(name="member_gender", length=8, nullable=false)
	private String memberGender;
	
	@Column(name="member_address", nullable=false, unique=true)
	private String memberAddress;
	
	@Column(name="member_phone", length=15, nullable=false, unique=true)
	private String memberPhone;

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberFullname() {
		return memberFullname;
	}

	public void setMemberFullname(String memberFullname) {
		this.memberFullname = memberFullname;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

}
