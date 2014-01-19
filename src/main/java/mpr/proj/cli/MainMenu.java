package mpr.proj.cli;

public class MainMenu extends Menu {
	public MainMenu() {
		showOptions();
		selected(getOption());
	}
	public void showOptions(){
		System.out.println(" -- Main menu -- ");
		System.out.println("1. Show pedigree");
		System.out.println("2. Search foals");
		System.out.println("3. Generate PDF");
		System.out.println("4. Edit database");
	}
	protected void selected(int sel){
		switch(sel){
			case 1: new PedigreeMenu();
					break;
			case 2: new FoalsMenu();
					break;
			case 4: new DbManageMenu();
					break;
		}
	}
}
