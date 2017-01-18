package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

	public int contoArticoliComune(Creator a1, Creator a2) throws SQLException{
		Connection conn = DBConnect.getConnection();
		int articoli=0;
		String query="select  count(a1.eprintid) as num  "
				+ "from authorship a1, authorship a2   "
				+ "where a1.eprintid=a2.eprintid   "
				+ "and a1.id_creator<>a2.id_creator   "
				+ "and  a1.id_creator=? and a2.id_creator=?;";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, a1.getId_creator());
			st.setInt(2, a2.getId_creator());
			ResultSet res = st.executeQuery();
			if(res.next()){
				articoli = res.getInt("num");
			}
			conn.close();
			return articoli;
		}catch(SQLException e ){
			e.printStackTrace();
			return -1;
		}
	}
}
