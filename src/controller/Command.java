package controller;

public abstract class Command {
	protected Controller controller;
	
	public Command(Controller ctrl) {
		controller=ctrl;
	}
	
	public abstract void execute(String options []);
		
}
