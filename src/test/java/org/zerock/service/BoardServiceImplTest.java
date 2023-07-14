package org.zerock.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.slf4j.Slf4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class BoardServiceImplTest {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testGetList() {
		log.info("---------------GetList");
		//service.getList().forEach(
		service.getList(new Criteria(3,10)).forEach(
				(list)->{
					log.info("list : {}",list);
				}
				);
	}
	@Test
	public void testRemove() {
		log.info("--------------testRemove");
		service.remove(2L);
	}
	
	@Test
	public void testModify() {
		log.info("-----------------testModify");
		BoardVO vo = new BoardVO();
		vo.setTitle("Jsp");
		vo.setContent("Jsp 마스터중.......");
		vo.setWriter("성윤정");
		vo.setBno(3L);
		
		log.info("modify : {}", service.modify(vo));
		
	}
	
	@Test
	public void testReister() {
		log.info("----------testRegister");
		BoardVO vo = new BoardVO();
		vo.setTitle("Ajax");
		vo.setContent("aJax마스터중..");
		vo.setWriter("정혜민");
		
		service.register(vo);
		log.info("register: {} ",vo);
	}
	@Test
	public void testSelectRead() {
		log.info("-------------testSelectRead");
		service.get(3L);
	}
	
	public void testGetCount() {
		log.info("total count : {}", service.getTotal(new Criteria(1,10)));
	}
	
}
