package org.zerock.domain;

import java.util.Date;

import lombok.Data;

//create table tbl_board(
//		bno NUMBER(10,0),
//		title VARCHAR2(200) not null,
//		content VARCHAR2(2000) not null,
//		writer VARCHAR2(50) not null,
//		regdate date DEFAULT sysdate,
//		updatedate date DEFAULT sysdate
//		);

@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
}
