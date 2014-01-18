package mpr.proj.database;

import java.sql.*;

import mpr.proj.EasyIn;
import mpr.proj.cli.DbManageMenu;

public class AddRecord extends Database{
	public AddRecord(){
		System.out.println("-- Add record --");
		showEntities();
		System.out.println("6. <- Back");
		selected(getOption());
	}
	
	private void selected(int sel){
		switch(sel){
			case 1: addHorse();
					break;
			case 2: addBreeder();
					break;
			case 3: addColor();
					break;
			case 4: addCountry();
					break;
			//case 5: addSex();
			//		break;
			case 6: new DbManageMenu();
					break;
		}
	}
	
	private void addHorse(){
		ResultSet rs;
		String name, date;
		int sex, sire, dam, breeder, color;
		Boolean yearOnly;
		System.out.println(" -- Add horse -- ");
		System.out.print("Name: ");
		name = EasyIn.getString();
		System.out.println("Sex: ");
		System.out.println("[0:Mare; 1:Stallion; 2:Gelding;]");
		sex = EasyIn.getInt();
		System.out.print("Date of birth: ");
		System.out.println("[YYYY; YYYY-MM-DD;]");
		date = EasyIn.getString();
		if(date.length() == 4){
			yearOnly = true;
			date += "-01-01";
		}
		else
			yearOnly = false;
		System.out.println("Color: ");
		System.out.print("[ ");
		rs = getSet("select id, lname from color");
		try{
			while(rs.next()){
				System.out.print(rs.getInt("id") + ":" + rs.getString("lname") + "; ");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		color = EasyIn.getInt();
		System.out.println("Sire: ");
		System.out.print("[ ");
		rs = getSet("select id, name from horse where sex = 1");
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
		sire = EasyIn.getInt();
		System.out.println("Dam: ");
		System.out.print("[ ");
		rs = getSet("select id, name from horse where sex = 0");
		i = 1;
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
		dam = EasyIn.getInt();
		System.out.println("Breeder: ");
		System.out.print("[ ");
		rs = getSet("select id, name from breeder");
		try{
			while(rs.next()){
				System.out.print(rs.getInt("id") + ":" + rs.getString("name") + "; ");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		breeder = EasyIn.getInt();
		String query = "insert into horse (name, sex, color, dob, yearonly, dam, sire, breeder) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, name);
			st.setInt(2, sex);
			st.setInt(3, color);
			st.setString(4, date);
			st.setBoolean(5, yearOnly);
			st.setInt(6, dam);
			st.setInt(7, sire);
			st.setInt(8, breeder);
			st.executeUpdate();
			EasyIn.pause("Horse added");
			new AddRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	private void addBreeder(){
		ResultSet rs;
		String name;
		int country;
		System.out.println(" -- Add breeder -- ");
		System.out.print("Name: ");
		name = EasyIn.getString();
		System.out.print("Country: ");
		System.out.print("[ ");
		rs = getSet("select id, code from country");
		try{
			int i = 1;
			while(rs.next()){
				if(i % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("code") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("code") + "; ");
				i++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		country = EasyIn.getInt();
		String query = "insert into breeder (name, country) values (?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, name);
			st.setInt(2, country);
			st.executeUpdate();
			EasyIn.pause("Breeder added");
			new AddRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	private void addColor(){
		String lname, sname;
		System.out.println(" -- Add color -- ");
		System.out.print("Long name: ");
		lname = EasyIn.getString();
		System.out.print("Short name: ");
		sname = EasyIn.getString();
		
		String query = "insert into color (lname, sname) values (?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, lname);
			st.setString(2, sname);
			st.executeUpdate();
			EasyIn.pause("Color added");
			new AddRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	private void addCountry(){
		String name, code;
		System.out.println(" -- Add country -- ");
		System.out.print("Name: ");
		name = EasyIn.getString();
		System.out.print("Code: ");
		code = EasyIn.getString();
		
		String query = "insert into country (name, code) values (?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, code);
			st.executeUpdate();
			EasyIn.pause("Country added");
			new AddRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

