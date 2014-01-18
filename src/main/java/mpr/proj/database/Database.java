package mpr.proj.database;

import java.sql.*;
import mpr.proj.EasyIn;

public class Database{
	
	protected Connection con;
	
	public Database(){
		try{
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/pedigree","sa","");
		}catch(SQLException e){
			System.out.println("Database error!");
		}
	}
	
	protected int getOption(){
		return EasyIn.getInt();
	}
	
	protected ResultSet getSet(String query){
		ResultSet rs = null;
		try{
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs;
	}
	protected void showEntities(){
		System.out.println("1. Horse");
		System.out.println("2. Breeder");
		System.out.println("3. Color");
		System.out.println("4. Country");
		System.out.println("5. Sex");
	}
}

