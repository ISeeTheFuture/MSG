package com.kh.msg.leave.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.msg.leave.model.dao.LeaveDAO;
import com.kh.msg.leave.model.vo.Leave;
import com.kh.msg.leave.model.vo.LeaveInfoPlus;
import com.kh.msg.leave.model.vo.LeavePlus;
import com.kh.msg.leave.model.vo.LeaveSet;
import com.kh.msg.leave.model.vo.MyLeave;
import com.kh.msg.member.model.vo.Member;



@Service
public class LeaveServiceImpl implements LeaveService {
     
	@Autowired
	LeaveDAO leaveDAO;
	
	@Override
	public List<Leave> selectLeaveList() {
		
		  return leaveDAO.selectLeaveList();
	}

	@Override
	public List<LeaveSet> selectLeaveList2() {
		
		return leaveDAO.selectLeaveList2();
	}

	@Override
	public List<LeavePlus> selectLeaveList3() {
		
		return leaveDAO.selectLeaveList3();
	}

	@Override
	public List<MyLeave> selectLeaveList4(Member member) {
		
		return leaveDAO.selectLeaveList4(member);
	}

	@Override
	public List<LeaveInfoPlus> selectleaveListInfoPlus(Member member) {
		
		return leaveDAO.selectleaveListInfoPlus(member);
	}


}
