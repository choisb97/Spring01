package service;

import java.util.List;

import util.BoardDAO;
import vo.BoardVO;

public class BoardService {

	BoardDAO dao = new BoardDAO();
	
	// ** selectList
	public List<BoardVO> selectList() {
		return dao.selectList();
	} // selectList
	
//--------------------------------------------------
	
	// ** selectOne ( 한 명의 글 자료 보기 )
	public BoardVO selectOne(BoardVO vo) {
		return dao.selectOne(vo);
	} // selectOne
	
//--------------------------------------------------
	
	// Insert
	public int insert(BoardVO vo) {
		return dao.insert(vo);
	} // insert
	
//--------------------------------------------------
	
	// Update
	public int update(BoardVO vo) {
		return dao.update(vo);
	} // update
	
//--------------------------------------------------

	// Delete
	public int delete(BoardVO vo) {
		return dao.delete(vo);
	} // delete

//--------------------------------------------------

	// 조회수 증가
	public int countUp(BoardVO vo) {
		return dao.countUp(vo);
	} // countUp

//--------------------------------------------------

	// 답글 등록
	public int rinsert(BoardVO vo) {
		return dao.rinsert(vo);
	} // rinsert


} // class
