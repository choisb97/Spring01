package memberDI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ms")
public class MemberService {

	@Autowired
	// => 해당 객체(DAO)는 이미 생성되어 있어야 함.
	//    @, xml 생성 모두 가능
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
