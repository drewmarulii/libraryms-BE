package com.drewdev.libraryms.dto.members;

public class MemberInsertReqDto {
	private String memberId;
	private String memberEmail;
	private String memberFullname;
	private String memberGender;
	private String memberAddress;
	private String memberPhone;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

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
