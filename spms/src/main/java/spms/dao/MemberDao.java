package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import spms.util.DBConnectionPool;
import spms.vo.Member;

public class MemberDao {
	
	BasicDataSource ds;
	 
	public void setDs(BasicDataSource ds) {
		this.ds = ds;
	}
	
	 
	 //회원 로그인
	 
	 
	 //회원정보 변경
	 public int update(Member member) throws Exception {
		 Connection connection = null;	
		 PreparedStatement stmt = null;
		 
		 try{
			 connection = ds.getConnection();	
			 stmt = connection.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
					+ " WHERE MNO=?");
			stmt.setString(1,member.getEmail());
			stmt.setString(2,member.getName());
			stmt.setInt(3,member.getNo());
			return stmt.executeUpdate();
		
		} catch (Exception e) {
			throw e;
		}finally{
		try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	 }
	 
	 
	 
	 //회원 상세 정보 조회
	 public Member selectOne(int no) throws Exception {
		 Connection connection = null;	
		 Statement stmt = null;
		 ResultSet rs = null;
		 
		 try{
			 connection = ds.getConnection();	
			 stmt = connection.createStatement();
				rs = stmt.executeQuery(
					"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
					" WHERE MNO=" + no);	
				if (rs.next()) {
				return new Member()
					.setNo(rs.getInt("MNO"))
					.setEmail(rs.getString("EMAIL"))
					.setName(rs.getString("MNAME"))
					.setCreatedDate(rs.getDate("CRE_DATE"));
					
				} else {
					throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
				}
			 
			 
		 } catch (Exception e) {
			 throw e;
		 }finally{
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			if (connection != null)  {connection.close();}
		 }
	 }
	 //회원 삭제
	 public int delete(int no) throws Exception {
		 Connection connection = null;	
		 Statement stmt = null;
		 
		 try {
			 connection = ds.getConnection();	
			 stmt = connection.createStatement();
			 return stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO="+ 
						no);
			 	
		 } catch (Exception e){
			 throw e;
		 } finally {
			 try {if (stmt != null) stmt.close();} catch(Exception e) {}
			 if (connection != null)  {connection.close();}
		 }  
	 }
	 
	 //회원 추가
	 public int insert(Member member) throws Exception {
			 Connection connection = null;	
			 PreparedStatement stmt = null;
	 		
		 try {
			 connection = ds.getConnection();	
			 stmt = connection.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
					+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1,member.getEmail());
			stmt.setString(2,member.getPassword());
			stmt.setString(3,member.getName());
			return stmt.executeUpdate();			
		 
		 } catch (Exception e){
		 		throw e;
		 	} finally {
		 	try {if (stmt != null) stmt.close();} catch(Exception e) {}
		 	if (connection != null) {connection.close();}
		 	}
	 }
	 //회원 리스트
	 public List<Member> selectList() throws Exception {
		 	Connection connection = null;
		 	Statement stmt = null;
			ResultSet rs = null;
			ArrayList<Member> members = new ArrayList<Member>();
		 try {
			 connection = ds.getConnection();	
			 stmt = connection.createStatement();
			 rs = stmt.executeQuery(
						"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
						" FROM MEMBERS" +
						" ORDER BY MNO ASC");
				// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
				// 그리고 Member객체를 ArrayList에 추가한다.
			while(rs.next()) {
				members.add(new Member()
					.setNo(rs.getInt("MNO"))
					.setName(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreatedDate(rs.getDate("CRE_DATE"))	);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}	
			if (connection != null) {connection.close();}
		}
		 return members; 
	 }
}
