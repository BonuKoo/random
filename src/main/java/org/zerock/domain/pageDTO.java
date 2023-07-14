package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class pageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total; //데이터베이스 전체 레코드 개수
	private Criteria cri;
	
	public pageDTO(Criteria cri,int total) {
		this.total = total;
		this.cri = cri;
		
		this.endPage=(int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage=endPage - 9;
	
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		if(realEnd < endPage) {
			endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage<realEnd; //this.endPage에서 this.의 유무
	}
}
