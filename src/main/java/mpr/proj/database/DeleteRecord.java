package mpr.proj.database;

import java.sql.*;
import mpr.proj.EasyIn;
import mpr.proj.cli.DbManageMenu;

public class DeleteRecord extends Database{
	public DeleteRecord(){
		System.out.println("-- Delete record --");
		showEntities();
		System.out.println("5. <- Back");
		selected(getOption());
	}
	
	private void selected(int sel){
		switch(sel){
			case 1: deleteHorse();
					break;
			case 2: deleteBreeder();
					break;
			case 3: deleteColor();
					break;
			case 4: deleteCountry();
					break;
			case 5: new DbManageMenu();
					break;
		}
	}
	
	private void deleteHorse(){
		int select;
		ResultSet rs = getSet("select id, name from horse");
		System.out.println(" -- Delete horse -- ");
		System.out.print("[ ");
		int i = 1;
		try{
			while(rs.next()){
				if(i % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		String query = "update horse set dam=null where dam = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		query = "update horse set sire=null where sire = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		query = "delete from horse where id = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
			EasyIn.pause("Horse deleted");
			new DeleteRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}
	private void deleteBreeder(){
		int select;
		ResultSet rs = getSet("select id, name from breeder");
		System.out.println(" -- Delete breeder -- ");
		System.out.print("[ ");
		int i = 1;
		try{
			while(rs.next()){
				if(i % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		String query = "update horse set breeder=null where breeder = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		query = "delete from breeder where id = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
			EasyIn.pause("Breeder deleted");
			new DeleteRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}
	private void deleteColor(){
		int select;
		ResultSet rs = getSet("select id, lname from color");
		System.out.println(" -- Delete color -- ");
		System.out.print("[ ");
		int i = 1;
		try{
			while(rs.next()){
				if(i % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("lname") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("lname") + "; ");
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		String query = "delete from color where id = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
			EasyIn.pause("Color deleted");
			new DeleteRecord();
		}catch(SQLException e){
			System.out.println("Operation refused");
			System.out.println("Horses exist with selected color");
			new DeleteRecord();
		}
	
	}
	private void deleteCountry(){
		int select;
		ResultSet rs = getSet("select id, name from country");
		System.out.println(" -- Delete country -- ");
		System.out.print("[ ");
		int i = 1;
		try{
			while(rs.next()){
				if(i % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		String query = "update breeder set country=null where country = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		query = "delete from country where id = ?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, select);
			st.executeUpdate();
			EasyIn.pause("Country deleted");
			new DeleteRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

