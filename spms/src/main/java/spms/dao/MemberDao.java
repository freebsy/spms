package spms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public class MemberDao {
	 Connection connection;
	 
	 public void setConnetion(Connection connection) {
		 this.connection = connection; 
	 }
	 
	 public List<Member> selectList() throws Exception {
		 	Statement stmt = null;
			ResultSet rs = null;
		 try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(
						"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
						" FROM MEMBERS" +
						" ORDER BY MNO ASC");
				
				ArrayList<Member> members = new ArrayList<Member>();
				
				// �����ͺ��̽����� ȸ�� ������ ������ Member�� ��´�.
				// �׸��� Member��ü�� ArrayList�� �߰��Ѵ�.
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
		}
		 return null; 
	 }
}
