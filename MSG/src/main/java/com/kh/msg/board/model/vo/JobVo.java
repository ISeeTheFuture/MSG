package com.kh.msg.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.kh.msg.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jobCd;
	private String jobName;

}
