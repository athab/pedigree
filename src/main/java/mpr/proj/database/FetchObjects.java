package mpr.proj.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mpr.proj.pedigree.*;

public class FetchObjects extends Database{
	
	public Horse getHorse(long id){
		ResultSet rs = getSet("select * from horse where id = " + id);
		try{
			while(rs.next()){
				return new Horse(rs.getLong("id"), rs.getString("name"), getSex(rs.getLong("id")), getDOB(rs.getLong("id")), getColor(rs.getLong("id")), 
						getHorse(rs.getLong("sire")), getHorse(rs.getLong("dam")), getBreeder(rs.getInt("breeder")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Horse> getHorsesList(){
		ResultSet rs = getSet("select * from horse");
		List<Horse> horses = new ArrayList<Horse>();
		try{
			while(rs.next()){
				horses.add(new Horse(rs.getLong("id"), rs.getString("name"), getSex(rs.getLong("id")), getDOB(rs.getLong("id")), getColor(rs.getLong("id")), 
						getHorse(rs.getLong("sire")), getHorse(rs.getLong("dam")), getBreeder(rs.getInt("breeder"))));
			}
			return horses;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Breeder getBreeder(int id){
		ResultSet rs = getSet("select * from breeder where id = " + id);
		try{
			while(rs.next()){
				return new Breeder(rs.getLong("id"), rs.getString("name"), getCountry(id));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Color getColor(long id){
		ResultSet rs = getSet("select * from color where id = " + id);
		try{
			while(rs.next()){
				return new Color(rs.getLong("id"), rs.getString("lname"), rs.getString("sname"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	private DateOfBirth getDOB(long id){
		ResultSet rs = getSet("select dob, yearonly from horse where id = " + id);
		try{
			while(rs.next()){
				return new DateOfBirth(rs.getDate("dob"), rs.getBoolean("yearonly"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Sex getSex(long id){
		ResultSet rs = getSet("select name from sex where id = " + id);
		try{
			while(rs.next()){
				return Sex.valueOf(rs.getString("name").toUpperCase());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Country getCountry(int id){
		ResultSet rs = getSet("select * from country where id = " + id);
		try{
			while(rs.next()){
				return new Country(rs.getLong("id"), rs.getString("name"), rs.getString("code"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}