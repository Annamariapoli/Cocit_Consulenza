package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.AutoreAutoreArticolo;
import bean.Creator;

public class Dao {
	
	public List<Creator> getAllAutori() throws SQLException{
		Connection conn = DBConnect.getConnection();
		String query="select * from creator";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			List<Creator> autori= new LinkedList<Creator>();
			ResultSet res = st.executeQuery();
			while(res.next()){
				Creator c = new Creator(res.getInt("id_creator"), res.getString("family_name"), res.getString("given_name"));
				autori.add(c);
			}
			conn.close();
			return autori;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	
	public List<AutoreAutoreArticolo> getCoppie() throws SQLException{  //ok
		Connection conn = DBConnect.getConnection();
		String query="     select a1.id_creator as idCreator1, a2.id_creator as idCreator2 , count(distinct a1.eprintid)  as num "
				+ "        from authorship a1, authorship a2   "
				+ "        where a1.eprintid= a2.eprintid "
				+ "        and a1.id_creator<>a2.id_creator  "
				+ "        group by a1.id_creator, a2.id_creator";
		List<AutoreAutoreArticolo> aaa= new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				AutoreAutoreArticolo c = new AutoreAutoreArticolo(res.getInt("idCreator1"), res.getInt("idCreator2"), res.getInt("num"));
				aaa.add(c);
			}
			conn.close();
			System.out.println(aaa);
			return aaa;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public Creator getCreatorById(int idCreator) throws SQLException{   //ok
		Connection conn = DBConnect.getConnection();
		String query="select c.id_creator, c.family_name, c.given_name  "
				+ "from creator c   where c.id_creator=?";
		Creator c = null;
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,  idCreator);
			ResultSet res = st.executeQuery();
			if(res.next()){
				 c = new Creator (res.getInt("id_creator"), res.getString("family_name"), res.getString("given_name"));
			}
			conn.close();
			System.out.println(c);
			return c;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String [] args) throws SQLException{
		Dao dao = new Dao();
		//dao.getCreatorById(85);
		dao.getCoppie();
	}
}