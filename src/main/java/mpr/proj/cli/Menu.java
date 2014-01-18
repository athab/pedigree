package mpr.proj.cli;

import mpr.proj.EasyIn;

public abstract class Menu {
	
	protected int getOption(){
		return EasyIn.getInt();
	}
	
	protected abstract void showOptions();
	protected abstract void selected(int sel);
}
