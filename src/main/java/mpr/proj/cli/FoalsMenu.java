package mpr.proj.cli;

import java.util.ArrayList;
import java.util.List;

import mpr.proj.EasyIn;
import mpr.proj.database.FetchObjects;
import mpr.proj.pedigree.Horse;

public class FoalsMenu {
	
	private FetchObjects fob;
	private List<Horse> horses;
	
	public FoalsMenu(){
		fob = new FetchObjects();
		horses = fob.getHorsesList();
		System.out.println(" -- Search foals -- ");
		displayAllHorses();
		System.out.print("\n\nChoose horse: ");
		showFoals(EasyIn.getInt() - 1);
	}
	private void displayAllHorses(){
		int i = 1;
		for(Horse hr : horses){
			if(i % 15 == 0)
				System.out.println(i + "." + hr.getName() + " ");
			else
				System.out.print(i + "." + hr.getName() + " ");
			i++;
		}
	}
	
	private void showFoals(int id){
		Horse parent = horses.get(id);
		List<Horse> foals = new ArrayList<Horse>();
		for(Horse hr : horses){
			if(hr.getDam() != null)
				if(hr.getDam().getID() == parent.getID())
					foals.add(hr);
			if(hr.getSire() != null)
				if(hr.getSire().getID() == parent.getID())
					foals.add(hr);
		}
		
		if(foals.isEmpty()){
			System.out.println("\n\n" + parent.getName() + " doesn't have any foals.\n\n");
		}
		else{
			int i = 1;
			System.out.println("\n\nFoals of " + parent.getName());
			for(Horse fl : foals){
				System.out.println(i + "." + fl.getName());
				i++;
			}
			System.out.print("\n\n");
		}
		new MainMenu();
	}
}