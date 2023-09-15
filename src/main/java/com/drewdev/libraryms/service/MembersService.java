package com.drewdev.libraryms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drewdev.libraryms.base.ConnHandler;
import com.drewdev.libraryms.dao.MembersDao;
import com.drewdev.libraryms.dto.DeleteResDto;
import com.drewdev.libraryms.dto.InsertResDto;
import com.drewdev.libraryms.dto.UpdateResDto;
import com.drewdev.libraryms.dto.members.MemberInsertMultipleReqDto;
import com.drewdev.libraryms.dto.members.MemberInsertReqDto;
import com.drewdev.libraryms.dto.members.MemberResDto;
import com.drewdev.libraryms.dto.members.MemberUpdateReqDto;
import com.drewdev.libraryms.model.Members;
import com.drewdev.libraryms.util.GenerateCode;

@Service
public class MembersService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private MembersDao membersDao;

	public List<MemberResDto> getAll() {
		final List<Members> members = membersDao.getAll(Members.class);
		final List<MemberResDto> memberRes = new ArrayList<>();

		for (int i = 0; i < members.size(); i++) {
			final MemberResDto member = new MemberResDto();
			member.setId(members.get(i).getId());
			member.setMemberId(members.get(i).getMemberId());
			member.setMemberEmail(members.get(i).getMemberEmail());
			member.setMemberFullname(members.get(i).getMemberFullname());
			member.setMemberGender(members.get(i).getMemberGender());
			member.setMemberAddress(members.get(i).getMemberAddress());
			member.setMemberPhone(members.get(i).getMemberPhone());
			member.setIsActive(Boolean.valueOf(members.get(i).getIsActive()).toString());
			memberRes.add(member);
		}

		return memberRes;
	}

	public MemberResDto getById(String id) {
		final Members member = membersDao.getById(Members.class, id);
		final MemberResDto memberRes = new MemberResDto();

		memberRes.setId(member.getId());
		memberRes.setMemberId(member.getMemberId());
		memberRes.setMemberEmail(member.getMemberEmail());
		memberRes.setMemberFullname(member.getMemberFullname());
		memberRes.setMemberGender(member.getMemberGender());
		memberRes.setMemberAddress(member.getMemberAddress());
		memberRes.setMemberPhone(member.getMemberPhone());
		memberRes.setIsActive(member.getIsActive().toString());

		return memberRes;
	}

	public InsertResDto insert(MemberInsertReqDto data) {
		final InsertResDto response = new InsertResDto();

		try {
			em().getTransaction().begin();
			final Members member = new Members();
			member.setMemberId(GenerateCode.generateCode());
			member.setMemberEmail(data.getMemberEmail());
			member.setMemberFullname(data.getMemberFullname());
			member.setMemberGender(data.getMemberGender());
			member.setMemberAddress(data.getMemberAddress());
			member.setMemberPhone(data.getMemberPhone());

			membersDao.save(member);

			response.setId(member.getId());
			response.setMessage("Insert Member Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Sorry, Insert Member Failed!");
		}

		return response;
	}

	public InsertResDto insertMultiple(MemberInsertMultipleReqDto datas) {
		final InsertResDto response = new InsertResDto();

		try {
			em().getTransaction().begin();

			for (int i = 0; i < datas.getMembers().size(); i++) {
				final Members member = new Members();
				member.setMemberId(GenerateCode.generateCode());
				member.setMemberEmail(datas.getMembers().get(i).getMemberEmail());
				member.setMemberFullname(datas.getMembers().get(i).getMemberFullname());
				member.setMemberGender(datas.getMembers().get(i).getMemberGender());
				member.setMemberAddress(datas.getMembers().get(i).getMemberAddress());
				member.setMemberPhone(datas.getMembers().get(i).getMemberPhone());

				membersDao.save(member);
			}

			response.setId("INSERT MEMBER");
			response.setMessage("Insert Members Success. Total " + datas.getMembers().size());
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().commit();
			e.printStackTrace();
			throw new RuntimeException("Sorri, Insert Member Failed!");
		}

		return response;
	}

	public UpdateResDto update(MemberUpdateReqDto data) {
		final Members member = membersDao.getById(Members.class, data.getId());
		final UpdateResDto response = new UpdateResDto();

		try {
			em().getTransaction().begin();
			member.setMemberEmail(data.getMemberEmail());
			member.setMemberFullname(data.getMemberFullname());
			member.setMemberGender(data.getMemberGender());
			member.setMemberAddress(data.getMemberAddress());
			member.setMemberPhone(data.getMemberPhone());

			membersDao.saveAndFlush(member);

			response.setVersion(member.getVersion());
			response.setMessage("Member has been updated");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return response;
	}

	public DeleteResDto delete(String id) {
		final DeleteResDto response = new DeleteResDto();

		try {
			em().getTransaction().begin();
			final Members member = membersDao.getById(Members.class, id);
			membersDao.deleteById(Members.class, member.getId());
			response.setMessage("Delete Member Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return response;
	}
}
