package mpr.proj.cli;

public class DbManageMenu extends Menu{
	
	public DbManageMenu(){
		showOptions();
		selected(getOption());
	}
	
	@Override
	public void showOptions() {
		System.out.println(" -- Manage database -- ");
		System.out.println("1. Add record");
		System.out.println("2. Update record");
		System.out.println("3. Delete record");
		System.out.println("4. <- Back");
	}
	
	protected void selected(int sel){
		switch(sel){
			//case 1: new AddRecord();
			//		break;
			//case 3: new UpdateRecord();
			//		break;
			//case 3: new DeleteRecord();
			//		break;
			case 4: new MainMenu();
					break;
		}
	}
}
