package mpr.proj.cli;

import java.util.ArrayList;
import java.util.List;

import mpr.proj.EasyIn;
import mpr.proj.database.FetchObjects;
import mpr.proj.pedigree.Horse;

public class PedigreeMenu {
	
	private FetchObjects fob;
	private List<Horse> horses;
	
	public PedigreeMenu(){
		int horse, gen;
		fob = new FetchObjects();
		horses = fob.getHorsesList();
		System.out.println(" -- Show pedigree -- ");
		displayAllHorses();
		System.out.print("\n\nChoose horse: ");
		horse = EasyIn.getInt() - 1;
		System.out.print("Generations: ");
		gen = EasyIn.getInt();
		displayPedigree(horse, gen);
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
	private void displayPedigree(int id, int gen){
		List<Horse> nextGen = new ArrayList<Horse>();
		nextGen.add(horses.get(id));
		System.out.println(nextGen.get(0).getName());
		int nextSize = nextGen.size();
		String tabs = "";
		for(int g = 0; g < gen; g++){
			for(int h = 0; h < nextSize; h++){
				if(nextGen.get(h).getDam() != null)
					nextGen.add(nextGen.get(h).getDam());
				if(nextGen.get(h).getSire() != null)
					nextGen.add(nextGen.get(h).getSire());
					nextGen.remove(0);
			}
			nextSize = nextGen.size();
			System.out.println(tabs + "|");
			System.out.print(tabs + "|____");
			for(int b = 0; b < nextGen.size(); b++){
				System.out.print(nextGen.get(b).getName() + " ");
			}
			System.out.println("\n");
			tabs += "\t";
		}

		System.out.println("\n");
		new PedigreeMenu();
	}
}
