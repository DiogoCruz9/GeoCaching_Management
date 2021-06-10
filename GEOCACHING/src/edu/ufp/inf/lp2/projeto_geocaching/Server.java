package edu.ufp.inf.lp2.projeto_geocaching;


import edu.ufp.inf.lp2.projeto_geocaching.Gui.MainController;

/**
 * Classe Server que é usado como main da interface grafica
 */
public class Server {

	private Server(String[] args) {

		//Database database = new Database();
		//UserService studentService = new UserServiceImpl(database);
		MainController.start_gui(args);

	}

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {

		new Server(args);
	}

}
