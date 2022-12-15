package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.MemberDAO;
import vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;
	
	// ** selectList
	public List<MemberVO> selectList() {
		return dao.selectList();
	} // selectList
	
//--------------------------------------------------
	
	// ** selectOne ( 한 명의 정보 보기 )
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	} // selectOne
	
//--------------------------------------------------
	
	// Insert
	public int insert(MemberVO vo) {
		return dao.insert(vo);
	} // insert
	
//--------------------------------------------------
	
	// Update
	public int update(MemberVO vo) {
		return dao.update(vo);
	} // update
	
//--------------------------------------------------

	// Delete
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	} // delete

} // class
