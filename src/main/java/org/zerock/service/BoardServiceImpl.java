package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	
	//자동 주입 ( autowired 안써도됨 )
	private final BoardMapper mapper;
	
	@Override
	public void register(BoardVO vo) {
		log.info("register : {} ", vo);
		mapper.insertSelectKey(vo);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get :  {}" , bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO vo) {
		
		log.info("modify : {} ", vo);
		
		return mapper.update(vo)==1 ? true : false;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove : {}", bno);
		return mapper.delete(bno)==1? true : false;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList with Criteria" + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	
	
}
