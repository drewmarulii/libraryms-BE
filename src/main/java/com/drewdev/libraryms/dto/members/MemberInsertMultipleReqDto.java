package com.drewdev.libraryms.dto.members;

import java.util.List;

public class MemberInsertMultipleReqDto {
	private List<MemberInsertReqDto> members;

	public List<MemberInsertReqDto> getMembers() {
		return members;
	}

	public void setMembers(List<MemberInsertReqDto> members) {
		this.members = members;
	}

}
