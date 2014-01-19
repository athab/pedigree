package mpr.proj.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mpr.proj.EasyIn;
import mpr.proj.cli.DbManageMenu;

public class UpdateRecord extends Database{
	public UpdateRecord(){
		System.out.println("-- Update record --");
		showEntities();
		System.out.println("5. <- Back");
		selected(getOption());
	}
	
	private void selected(int sel){
		switch(sel){
			case 1: updateHorse();
					break;
			case 2: updateBreeder();
					break;
			case 3: updateColor();
					break;
			case 4: updateCountry();
					break;
			case 5: new DbManageMenu();
					break;
		}
	}
	protected void updateHorse(){
		int select, sex, color, sire, dam, breeder;
		String name, date;
		boolean yearOnly;
		
		ResultSet rs = getSet("select * from horse order by id asc");
		System.out.println(" -- Update horse -- ");
		int i = 1;
		try{
			System.out.println("ID \t Name \t Sex \t Color \t Date of birth \t Year only \t Dam \t Sire \t Breeder");
			while(rs.next()){
					System.out.print(rs.getInt("id") + ".\t" + rs.getString("name") + "\t" + rs.getString("sex") + "\t" + rs.getString("color") + "\t" );
					if(rs.getBoolean("yearonly"))
						System.out.print(rs.getDate("dob").toString().substring(0, 4) + "      \t");
					else
						System.out.print(rs.getDate("dob") + "\t");
					System.out.println(rs.getBoolean("yearonly") + "\t\t" + rs.getInt("dam") + "\t" + rs.getInt("sire") + "\t" + rs.getInt("breeder"));
					
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
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
		int h = 1;
		try{
			while(rs.next()){
				if(h % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("name") + "; ");
				h++;
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
		String query = "update horse set name=?, sex=?, color=?, dob=?, yearonly=?, dam=?, sire=?, breeder=? where id = " + select;
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
			EasyIn.pause("Horse updated");
			new UpdateRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	protected void updateBreeder(){
		int select, country;
		String name;
		ResultSet rs = getSet("select * from breeder order by id asc");
		System.out.println(" -- Update breeder -- ");
		try{
			System.out.println("ID \t Name \t Country");
			while(rs.next()){
					System.out.println(rs.getInt("id") + ".\t" + rs.getString("name") + "\t" + rs.getString("country"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		System.out.print("Name: ");
		name = EasyIn.getString();
		rs = getSet("select id, code from country");
		System.out.print("[ ");
		int h = 1;
		try{
			while(rs.next()){
				if(h % 15 == 0)
					System.out.println(rs.getInt("id") + ":" + rs.getString("code") + "; ");
				else
					System.out.print(rs.getInt("id") + ":" + rs.getString("code") + "; ");
				h++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("]");
		System.out.print("Country ID: ");
		country = EasyIn.getInt();
		String query = "update breeder set name=?, country=? where id=" + select;
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, name);
			st.setInt(2, country);
			st.executeUpdate();
			EasyIn.pause("Breeder updated");
			new UpdateRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	protected void updateColor(){
		int select;
		String lname, sname;
		ResultSet rs = getSet("select * from color order by id asc");
		System.out.println(" -- Update color -- ");
		try{
			System.out.println("ID \t Short name \t Long name");
			while(rs.next()){
					System.out.println(rs.getInt("id") + ".\t" + rs.getString("sname") + "\t\t" + rs.getString("lname"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		System.out.print("Long name: ");
		lname = EasyIn.getString();
		System.out.print("Short name: ");
		sname = EasyIn.getString();
		String query = "update color set lname=?, sname=? where id=" + select;
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, lname);
			st.setString(2, sname);
			st.executeUpdate();
			EasyIn.pause("Color updated");
			new UpdateRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	protected void updateCountry(){
		int select;
		String name, code;
		ResultSet rs = getSet("select * from country order by id asc");
		System.out.println(" -- Update country -- ");
		try{
			System.out.println("ID \t Code \t\t Name");
			while(rs.next()){
					System.out.println(rs.getInt("id") + ".\t" + rs.getString("code") + "\t\t" + rs.getString("name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.print("Choose ID: ");
		select = EasyIn.getInt();
		System.out.print("Code: ");
		code = EasyIn.getString();
		System.out.print("Name: ");
		name = EasyIn.getString();
		String query = "update country set code=?, name=? where id=" + select;
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, code);
			st.setString(2, name);
			st.executeUpdate();
			EasyIn.pause("Country updated");
			new UpdateRecord();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
