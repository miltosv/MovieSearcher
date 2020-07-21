package controller;

import java.io.IOException;

public class CommandFactory {
	
	private Controller ctrl;
	public CommandFactory(Controller ctr) {
		ctrl=ctr;
	}

	public Command createCommand(String type) {
		switch(type) {
		case "SearchCommand": 
			return new Searcher(ctrl);
		
		

		default: return null;
		}
	}
	
}
