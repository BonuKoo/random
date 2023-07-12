package org.zerock.mapper;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
//		List<BoardVO> list =boardMapper.getList();
//		for(BoardVO vo : list) {
//			log.info("vo : {} ", vo);
		
		boardMapper.getList().forEach((vo)->{
			log.info("vo: {}", vo);
		});
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("dd");
		vo.setContent("빡시게 자바 공부중");
		vo.setWriter("남궁성");
		
		int result = boardMapper.insert(vo);
		
		log.info("result : {}", result);
	}
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		
		vo.setBno(4L);
		vo.setTitle("Spring");
		vo.setContent("멍 때리는 중.......");
		vo.setWriter("이성진");
		
		int result = boardMapper.update(vo);
		
		log.info("result : {}", result);
	}
	@Test
	public void testRead() {
		BoardVO vo = boardMapper.read(4L);
		log.info("vo: {}",vo);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("React");
		vo.setContent("리액트 공부중");
		vo.setWriter("조조");
		
		int result = boardMapper.insertSelectKey(vo);
		
		log.info("result : {} " , result);
	}
	
	@Test
	public void testGetList2() {
		boardMapper.getList().forEach((vo)->{
			log.info("vo: {}", vo);
		});
	}
	@Test
	public void testRead2() {
		BoardVO vo = boardMapper.read(5L);
		log.info("vo : {}", vo);
	}
	@Test
	public void testInsert2() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("ajax");
		vo.setContent("에이잭스 공부중");
		vo.setWriter("남궁성");
		
		int result = boardMapper.insertSelectKey(vo);
		
		log.info("result : {}", result);
		
	}
	
}

