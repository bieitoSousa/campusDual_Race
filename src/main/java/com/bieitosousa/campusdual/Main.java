package com.bieitosousa.campusdual;

import com.bieitosousa.campusdual.DATA.*;
import com.bieitosousa.campusdual.UTILS.*;
import java.util.*;

public class Main {

static Controler c = Controler.instaceControler();

	public static void main(String[] args) {
		Controler.CreatePath();
		menu();
	}

	private static void menu() {

		try {
			view_menu_conf();
			view_menu_action();

			CommandPrompt.withCommands(
					Command.withName("1").withDescription("Menu Para Configurar APP").withMethod(Main::menu_Conf),
					Command.withName("2").withDescription("Menu de Acciones").withMethod(Main::menu_Action),
					Command.withName("0").withDescription("Salir del programa").withMethod(() -> {
						System.out.println();
						System.exit(0);
					})).withPrompt("[CampusDualRace] >> ").run();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPCIÓN: " + e.getClass().getName());
			System.out.println("Revise el registro de la aplicación");
		} finally {
			c.save();
		}
	}

	private static String get_str_menu_Conf_print_race_view() {
		return Controler.isCONSOLE_PRINT_RACE_VIEW() ? "*" : " ";
	}

	private static String get_str_menu_Conf_print_race_result() {
		return Controler.isCONSOLE_PRINT_RACE_RESULT() ? "*" : " ";
	}

	private static String get_str_menu_Conf_print_tornament_classification() {
		return Controler.isCONSOLE_PRINT_TORNAMENT_CLASSIFICATION() ? "*" : " ";
	}

	private static String get_str_menu_Conf_Race_view() {
		return Controler.isRACE_VIEW() ? "*" : " ";
	}

	private static String get_str_menu_Conf_Race_classif() {
		return Controler.isRACE_RESULT() ? "*" : " ";
	}

	private static String get_str_menu_Conf_Race_exp() {
		return Controler.isRACE_EXP() ? "*" : " ";
	}

	private static String get_str_menu_Conf_Torn_classif() {
		return Controler.isTORN_RESULT() ? "*" : " ";
	}

	private static String get_str_menu_Conf_Torn_exp() {
		return Controler.isTORN_EXP() ? "*" : " ";
	}

	private static String get_str_Garage_select() {
		if (c.getGARAGESELECT() != null) {
			return c.getGARAGESELECT().toString();
		} else
			return "";

	}

	private static String get_str_nLISTGARAGE() {
		if (c.getLISTGARAGE().size() > 0) {
			return Integer.toString(c.getLISTGARAGE().size());
		} else
			return "";

	}

	private static String get_str_LISTGARAGE() {
		if (c.getLISTGARAGE().size() > 0) {
			return c.getLISTGARAGE().toString();
		} else
			return "";

	}

	private static String get_str_Race_select() {
		if (c.getRACESELECT() != null) {
			return c.getRACESELECT().toString();
		} else
			return "";

	}

	private static String get_str_nLISTRACE() {
		if (c.getLISTRACE().size() > 0) {
			return Integer.toString(c.getLISTRACE().size());
		} else
			return "";

	}

	private static String get_str_LISTRACE() {
		if (c.getLISTRACE().size() > 0) {
			return c.getLISTRACE().toString();
		} else
			return "";

	}

	private static String get_str_Tornament_select() {
		if (c.getTORNAMENTSELECT() != null) {
			return c.getTORNAMENTSELECT().toString();
		} else
			return "";

	}

	private static String get_str_nLISTTORNAMENT() {
		if (c.getLISTTORNAMENT().size() > 0) {
			return Integer.toString(c.getLISTTORNAMENT().size());
		} else
			return "";

	}

	private static String get_str_LISTTORNAMENT() {
		if (c.getLISTTORNAMENT().size() > 0) {
			return c.getLISTTORNAMENT().toString();
		} else
			return "";

	}

	private static void view_menu_conf() {
		System.out.println(""
				+ "\n|_______________________________________________________________________________________________________________________________________________________________________________________________|"
				+ "\n[	[	[	[	[					MENU	CONFIGURACION									]	]	]	]	]"
				+ "\n|_______________________________________________________________________________________________________________________________________________________________________________________________|"
				+ "\n|	[	=	=	=	=	=	=	=	PRINTS____ IN THE CONSOLE OR IN A FILE	=	=	=	=	=	=	]				|"
				+ "\n|                                                                                                                                                                                               |"
				+ "\n|	____________PRINTS________[TRUE:CONSOL]______[FALSE:FILE]_________________________________________________										|"
				+ "\n|			" + "[" + get_str_menu_Conf_print_race_view() + "]"
				+ "					|== Print == RACE_VIEW == Select To  print  the view of each race in console							|"
				+ "\n|			" + "[" + get_str_menu_Conf_print_race_result() + "]"
				+ "					|== Print == RACE_RESULT ==  Select To  print  the result of each race in console						|"
				+ "\n|			" + "[" + get_str_menu_Conf_print_tornament_classification() + "]"
				+ "					|== Print == TORNAMENT_CLASSIF ==  Select To  print  the classification of each tornament in console				|"
				+ "\n|                                                                                                                                                                                               |"
				+ "\n|	[	=	=	=	=	=	ACTIONS ____ DO THE ACTION OR NOT DOI THE ACTION	=	=	=	=	=	=	]				|"
				+ "\n|                                                                                                                                                                                               |"
				+ "\n|	 ___________ACTIONS__________[TRUE:DO]________[FALSE:NOTDO]________________________________________________										|"
				+ "\n|					=	RACE 	_______________________________________________________________________________								|"
				+ "\n|			" + "[" + get_str_menu_Conf_Race_view() + "]"
				+ "					|== DO == RACE_VIEW == view the simulate of each race										|"
				+ "\n|			" + "[" + get_str_menu_Conf_Race_classif() + "]"
				+ "					|== DO == RACE_RESULTS ==  Select to view the Result of each race								|"
				+ "\n|			" + "[" + get_str_menu_Conf_Race_exp() + "]"
				+ "					|== DO == RACE_EXPORT ==  Select to export each race										|"
				+ "\n|	  				=	TORN 	________________________________________________________________________________							|"
				+ "\n|			" + "[" + get_str_menu_Conf_Torn_classif() + "]"
				+ "					|== DO == TORNAMENT_CLASIFFICATION  == Select to view the Result of each Tornament						|"
				+ "\n|			" + "[" + get_str_menu_Conf_Torn_exp() + "]"
				+ "					|== DO == TORNAMENT_EXPORT == Select to export each Tornament									|"
				+ "\n|________________________________________________________________________________________________________________________________________________________________________________________________");

	}

	private static void view_menu_action() {
		System.out.println(""
				+ "\n|_______________________________________________________________________________________________________________________________________________________________________________________________|"
				+ "\n[	[	[	[	[					MENU	ACCTIONS									]	]	]	]	]"
				+ "\n|_______________________________________________________________________________________________________________________________________________________________________________________________|"
				+ "\n|	[	=	=	=	=	=	=	=	GARAGE		=	=	=	=	=	=	]				|"
				+ "\n|   number of Garage  [" + get_str_nLISTGARAGE() + "]  |" + "\n|   Garage select ["
				+ get_str_Garage_select() + "]  |" + "\n|   List of Garage [" + get_str_LISTGARAGE() + "]  |"
				+ "\n|                                                                                                                                                                                               |"
				+ "\n|	[	=	=	=	=	=	=	=	RACE		=	=	=	=	=	=	]				|"
				+ "\n|   number of RACE  [" + get_str_nLISTRACE() + "]  |" + "\n|   RACE select ["
				+ get_str_Race_select() + "]  |" + "\n|   Garage IN RACE select [" + get_str_LISTRACE() + "]  |"
				+ "\n|                                                                                                                                                                                               |"
				+ "\n|	[	=	=	=	=	=	=	=	TORNAMENT		=	=	=	=	=	=	]				|"
				+ "\n|   number of TORNAMENT  [" + get_str_nLISTTORNAMENT() + "] |" + "\n|   TORNAMENT select ["
				+ get_str_Tornament_select() + "]  |" + "\n|   RACE IN TORNAMENT select [" + get_str_LISTTORNAMENT()
				+ "]  |"
				+ "\n|                                                                                                                                                                                               |"
				+ "\n|________________________________________________________________________________________________________________________________________________________________________________________________");

	}

	private static void menu_Conf() {

		try {
			view_menu_conf();
			CommandPrompt.withCommands(Command.withName("1").withDescription("[" + get_str_menu_Conf_print_race_view()
					+ "] == Print ==	RACE_VIEW	== change to print the view of each race in [true = console/ False = File]")
					.withMethod(Controler::changeCONSOLE_PRINT_RACE_VIEW).addSeparator(),

					Command.withName("2").withDescription("[" + get_str_menu_Conf_print_race_result()
							+ "] == Print == RACE_RESULT == Select To  print  the result of each race in [true = console/ False = File]")
							.withMethod(Controler::changeCONSOLE_PRINT_RACE_RESULT).addSeparator(),

					Command.withName("3").withDescription("[" + get_str_menu_Conf_print_tornament_classification()
							+ "] == Print == TORNAMENT_CLASSIF == Select To  print  the classification of each tornament in [true = console/ False = File]")
							.withMethod(Controler::changeCONSOLE_PRINT_TORNAMENT_CLASSIFICATION).addSeparator(),
					Command.withName("4").withDescription("[" + get_str_menu_Conf_Race_view()
							+ "] == DO == RACE_VIEW ==   Select to view the simulate of each race [true = DO/ False = NOT DO]")
							.withMethod(Controler::changeRACE_VIEW).addSeparator(),

					Command.withName("5").withDescription("[" + get_str_menu_Conf_Race_classif()
							+ "] == DO == RACE_RESULTS ==   Select to view the Result of each race [true = DO/ False = NOT DO]")
							.withMethod(Controler::changeRACE_RESULT).addSeparator(),

					Command.withName("6").withDescription("[" + get_str_menu_Conf_Race_exp()
							+ "] == DO == RACE_EXPORT ==   Select to export each race [true = DO/ False = NOT DO]")
							.withMethod(Controler::changeRACE_EXP).addSeparator().addSeparator().addSeparator(),

					Command.withName("7").withDescription("[" + get_str_menu_Conf_Torn_classif()
							+ "] == DO == TORNAMENT_CLASIFFICATION  ==   Select to view the Result of each Tornament [true = DO/ False = NOT DO]")
							.withMethod(Controler::changeTORN_RESULT).addSeparator(),

					Command.withName("8")
							.withDescription("[" + get_str_menu_Conf_Torn_exp()
									+ "] == DO == TORNAMENT_EXPORT ==  Select to export each Tornament")
							.withMethod(Controler::changeTORN_EXP).addSeparator(),

					Command.withName("0").withDescription("Atras").withMethod(Main::menu))
					.withPrompt("[Menu_Config] >> ").run();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("EXCEPCIÓN: " + e.getClass().getName());
			System.out.println("Revise el registro de la aplicación");
		} finally {
			// guardo la app en un arxhivo
		}
	}

	private static void menu_Action() {

		view_menu_action();

		try {
			CommandPrompt.withCommands(
					Command.withName("1").withDescription("Select a GARAGE").withMethod(Main::selectGARAGE),
					Command.withName("2").withDescription("Select a RACE").withMethod(Main::selectRACE),
					Command.withName("3").withDescription("Select a TORNAMENT").withMethod(Main::selectTORNAMENT),

					Command.withName("4").withDescription("CREATE A NEW GARAGE").withMethod(Main::addGARAGE),
					Command.withName("5").withDescription("Add a new car to c.getGARAGESELECT")
							.withMethod(Main::addCarGARAGESELECT),
					Command.withName("6").withDescription("delete a car from c.getGARAGESELECT")
							.withMethod(Main::delCarGARAGESELECT),
					Command.withName("7").withDescription("Add a list of imported cars to c.getGARAGESELECT")
							.withMethod(Main::ImportCarGARAGESELECT),

					Command.withName("10").withDescription("Add the GARAGE_SELECT to c.getLISTGARAGE ")
							.withMethod(Main::addLISTGARAGE),
					Command.withName("11").withDescription("Delete the GARAGE_SELECT of c.getLISTGARAGE")
							.withMethod(Main::delLISTGARAGE),


					Command.withName("12").withDescription("Create a new RACE_Elimination WHIT ListGarage")
							.withMethod(Main::newRaceElimination),
					Command.withName("13").withDescription("Create a new RACE_Standar WHIT ListGarage")
							.withMethod(Main::newRaceStandar),

					Command.withName("15").withDescription("Add the RACE_SELECT to c.getLISTRACE ")
							.withMethod(Main::addLISTRACE),
					Command.withName("16").withDescription("Delete the RACE_SELECT to c.getLISTRACE t")
							.withMethod(Main::delLISTRACE),

					Command.withName("17").withDescription("Create a new TORNAMENT WHIT c.getLISTRACE")
							.withMethod(Main::newTornament),
					

					Command.withName("19").withDescription("START select_TORNAMENT")
							.withMethod(Main::select_TORNAMENT_Start),
					Command.withName("20").withDescription("START select_Race").withMethod(Main::select_Race_Start),

					Command.withName("0").withDescription("Atras").withMethod(Main::menu))
					.withPrompt("[Menu_Action] >> ").run();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPCIÓN: " + e.getClass().getName());
			System.out.println("Revise el registro de la aplicación");
		} finally {
			// guardo la app en un arxhivo
		}
	}

	private static void selectGARAGE() {
		if (c.getLISTGARAGE().size() > 0) {
			int id = -2;
			do {
				System.out.println("selected one garage");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Garage g : c.getLISTGARAGE()) {
					System.out.println("[" + (cn++) + "]" + g);
					id = KeyboardReader.readInt("put  the number of the garage  (-1 to exit): ", "Error");
					try {

						if (id > -1) {
							c.setGARAGESELECT( c.getLISTGARAGE().get(id));
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getLISTGARAGE().size() || id < -1);
		} else {
			System.out.println("Firts create one garage");
		}

	}

	private static void selectRACE() {

		if (c.getLISTRACE().size() > 0) {
			int id = -2;
			do {
				System.out.println("selected one RACE");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Race r : c.getLISTRACE()) {
					System.out.println("[" + (cn++) + "]" + r);
					id = KeyboardReader.readInt("put  the number of the RACE  (-1 to exit): ", "Error");
					try {

						if (id > -1) {
							c.setRACESELECT( c.getLISTRACE().get(id));
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getLISTRACE().size() || id < -1);
		} else {
			System.out.println("Firts create one RACE");
		}

	}

	private static void selectTORNAMENT() {

		if (c.getLISTTORNAMENT().size() > 0) {
			int id = -2;
			do {
				System.out.println("selected one TORNAMENT");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Tornament t : c.getLISTTORNAMENT()) {
					System.out.println("[" + (cn++) + "]" + t);
					id = KeyboardReader.readInt("enter the number of the TORNAMENT  (-1 to exit): ",
							"wrong number entered");
					try {

						if (id > -1) {
							c.setTORNAMENTSELECT(c.getLISTTORNAMENT().get(id));
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getLISTTORNAMENT().size() || id < -1);
		} else {
			System.out.println("Firts create one TORNAMENT");
		}

	}

	private static void addGARAGE() {
		String name = KeyboardReader.readString("Enter the name of the garage ", "wrong name entered", false);
		try {
			Garage g =new Garage(name);
			System.out.println("the garage "+g+"is create");
			c.setGARAGESELECT( g);
			System.out.println("the garage "+g+"is put in garage_select");
			c.getLISTGARAGE().add(g);
			System.out.println("the garage "+g+"is put into "+c.getLISTGARAGE());
		} catch (Exception e) {
			System.err.println(" error :: not instantiated the garage " + e.getMessage());

		}
	}

	private static void addCarGARAGESELECT() {
		if (c.getGARAGESELECT() != null) {
		String model = KeyboardReader.readString("Enter the model of the car ", "wrong name entered", false);
		String mark = KeyboardReader.readString("Enter the mark of the car ", "wrong name entered", false);
		try {
			Car car= new Car(mark, model);
			System.out.println("the Car "+car+"is create");
			c.getGARAGESELECT().addCar(car);
			System.out.println("the Car "+car+"is put into "+c.getGARAGESELECT());
		} catch (Exception e) {
			System.err.println(" error :: not instantiated the garage " + e.getMessage());

		}
		}else {
			System.err.println("First select a Garage");
		}
		
	}

	private static void delCarGARAGESELECT() {
		if (c.getGARAGESELECT() != null) {
			
		if (c.getGARAGESELECT().getAllCar().size()>0) {
			int id = -2;
			do {
				System.out.println("selected one car");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Car car : c.getGARAGESELECT().getAllCar()) {
					System.out.println("[" + (cn++) + "]" + car);
					id = KeyboardReader.readInt("enter the number of the car  (-1 to exit): ",
							"wrong number entered");
					try {
						if (id > -1) {
							c.getGARAGESELECT().getAllCar().remove(id);
							System.out.println("the Car "+car+"is removed");
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getGARAGESELECT().getAllCar().size() || id < -1);
		
		}else {System.err.println("First add one car to the garage");}
	}else {System.err.println("First select a Garage");}
	}

	private static void addLISTGARAGE() {
		if (c.getGARAGESELECT() != null) {
				try {
					if (c.getLISTGARAGE().contains(c.getGARAGESELECT())) {
						System.err.println(
								"the garage "+c.getGARAGESELECT()
								+"is already contained in the list");
					}else {
					c.getLISTGARAGE().add(c.getGARAGESELECT());
					}
				
				} catch (Exception e) {
					System.err.println();
				}
				}else {System.err.println("First select a Garage");}
	}

	private static void delLISTGARAGE() {
		if (c.getGARAGESELECT() != null) {
			if(c.getLISTGARAGE().size()>0) {
				try {
					if (c.getLISTGARAGE().contains(c.getGARAGESELECT())) {
						c.getLISTGARAGE().remove(c.getGARAGESELECT());
						
					}else {
						System.err.println(
								"the garage "+c.getGARAGESELECT()
								+"not contained in the list");
					}
				
				} catch (Exception e) {
					System.err.println();
				}
			}else {System.err.println("First add one car to the garage");}
		}else {System.err.println("First select a Garage");}
	}
	
	

	private static void ImportCarGARAGESELECT() {
		if (c.getGARAGESELECT() != null) {
			try {
			c.getGARAGESELECT().ImportCars();
			}catch(Exception e) {
				System.err.println(
						"ERROR :: loading the car list "
						+e.getMessage()
						);
			}
		}else {System.err.println("First select a Garage");}
	}

	private static void newRaceElimination() {
		try {
		if(c.getLISTGARAGE().size()>0) {
			for (Garage g :c.getLISTGARAGE()) {
				if (g.getAllCar().size()>0) {
					throw new Exception ("One or more Gargarages not content cars");
					
				}
				
			}
			String name = KeyboardReader.readString("Enter the model of the car ", "wrong name entered", false);
			Race r = new Race_Elimination(name,1,c.getLISTGARAGE());
			System.out.println("the race "+r+"is create");
			c.getLISTRACE().add(r);
			System.out.println("the race "+r+"is put in to "+c.getLISTGARAGE());
			c.setRACESELECT(r);
			System.out.println("the race "+r+"is put in to selectRace"+c.getRACESELECT());
		}else {System.err.println("First add one car to the garage");}
		}catch(Exception e) {
			System.err.println(
					"ERROR :: Intance Race "
					+e.getMessage()
					);
		}
	}
	private static void newRaceStandar() {
		try {
			
				String name = KeyboardReader.readString("Enter the model of the car ", "wrong name entered", false);
				Race r = new Race_Elimination(name,0,c.getLISTGARAGE());
				System.out.println("the race "+r+"is create");
				c.getLISTRACE().add(r);
				System.out.println("the race "+r+"is put in to "+c.getLISTRACE());
				c.setRACESELECT(r);
				System.out.println("the race "+r+"is put in to selectRace"+c.getRACESELECT());
		}catch(Exception e) {
			System.err.println(
					"ERROR :: Intance Race "
							+e.getMessage()
					);
		}
	}

	private static void addLISTRACE() {
		if (c.getRACESELECT() != null) {
			try {
				if (c.getLISTRACE().contains(c.getRACESELECT())) {
					System.err.println(
							"the RACE "+c.getRACESELECT()
							+"is already contained in the list");
				}else {
				c.getLISTRACE().add(c.getRACESELECT());
				}
			
			} catch (Exception e) {
				System.err.println();
			}
			}else {System.err.println("First select a RACE");}
	}

	private static void delLISTRACE() {
		if (c.getRACESELECT() != null) {
			if(c.getLISTRACE().size()>0) {
				try {
					if (c.getLISTRACE().contains(c.getRACESELECT())) {
						c.getLISTRACE().remove(c.getRACESELECT());
						
					}else {
						System.err.println(
								"the garage "+c.getRACESELECT()
								+"not contained in the list");
					}
				
				} catch (Exception e) {
					System.err.println();
				}
			}else {System.err.println("First add RACE to the LISTRACE");}
		}else {System.err.println("First select a RACE");}
	}
	

	private static void newTornament() {

	}



	private static void select_Race_Start() {
		if (c.getRACESELECT() != null) {
			try {
			c.getRACESELECT().start();
			} catch (Exception e) {
				System.err.println();
			}
		}else {System.err.println("First select a RACE");}
	}

	private static void select_TORNAMENT_Start() {
		if (c.getTORNAMENTSELECT() != null) {
		try {
			c.getTORNAMENTSELECT().start();
		} catch (Exception e) {
			System.err.println();
		}
		}else {System.err.println("First select a RACE");}
	}

}
