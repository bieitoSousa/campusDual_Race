package com.bieitosousa.campusdual.DATA;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bieitosousa.campusdual.UTILS.JSON;
import com.bieitosousa.campusdual.UTILS.KeyboardReader;
import com.bieitosousa.campusdual.UTILS.Utilss;

public class Controler {

	// ______POINTS____________________________________________________________________
	public final static int POINTS_FIRSTS = 12;
	public final static int POINTS_SECOND = 6;
	public final static int POINTS_THIRD = 3;
	public final static int POINTS_DEFAULT = 1;

	// _______________PATH______________________________________________________________
	private final static String PUBLIC_PATH = ".//public_data//";
	private final static String PRIVATE_PATH = ".//private_data//";

	private final static String PUBLIC_GARAGE = PUBLIC_PATH + "//public_garage//";
	private final static String PRIVATE_GARAGE = PRIVATE_PATH + "//private_garage//";

	private final static String PUBLIC_RACE = PUBLIC_PATH + "//public_race//";
	private final static String PRIVATE_RACE = PRIVATE_PATH + "//private_race//";

	private final static String PUBLIC_CAR = PUBLIC_PATH + "//public_car//";
	private final static String PRIVATE_CAR = PRIVATE_PATH + "//private_car//";

	private final static String PUBLIC_TORNAMENT = PUBLIC_PATH + "//public_tornament//";
	private final static String PRIVATE_TORNAMENT = PRIVATE_PATH + "//private_tornament//";
	private final static String CONF = PRIVATE_PATH + "//private_conf//";
	// _______________FILES_______________________________________________________________

	// ______ GARAGE
	// _____________________________________________________________________

	private static String G_PARTIC = PUBLIC_GARAGE + "//garage_partic_" + Utilss.getTime() + "//" + "GARAGE_PARTIC__";
	private static String G_RESULT = PUBLIC_GARAGE + "//garage_results_" + Utilss.getTime() + "//" + "GARAGE_RESULTS__";
	private static String G_EXP = PRIVATE_GARAGE + "garage_export_";
	// ______ RACE
	// _______________________________________________________________________
	private static String R_PARTIC = PUBLIC_RACE + "//race_partic_" + Utilss.getTime() + "//" + "RACE_PARTIC__";
	private static String R_LOG = PUBLIC_RACE + "//race_log_" + Utilss.getTime() + "//" + "RACE_LOG__";
	private static String R_RESULT = PUBLIC_RACE + "//race_results_" + Utilss.getTime() + "//" + "RACE_RESULTS__";
	private static String R_EXP = PRIVATE_RACE + "race_export_";

	// ______ TORN
	// _______________________________________________________________________
	private static String T_PARTIC = PUBLIC_TORNAMENT + "//torn_partic" + Utilss.getTime() + "//" + "TORN_PARTIC__";
	private static String T_PODIUM = PUBLIC_TORNAMENT + "//torn_podium" + Utilss.getTime() + "//" + "TORN_PODIUM__";
	private static String T_RESULT = PUBLIC_TORNAMENT + "//torn_results" + Utilss.getTime() + "//" + "TORN_RESULTS__";
	private static String T_EXP = PRIVATE_TORNAMENT + "tornament_export_";
	// ______ CONTROL
	// ____________________________________________________________________
	private static String BACKUP = CONF + "backup.json";
	private static String IMPORTCAR = CONF + "car.json";

	private Car CARSELECT;
	private Garage GARAGESELECT;
	private Race RACESELECT;
	private Tornament TORNAMENTSELECT;
	private ArrayList<Garage> garageL;
	private ArrayList<Race> raceL;
	private ArrayList<Tornament> tornamentL;

	private static Controler c = null;

	public Controler() {
		this.garageL = new ArrayList<Garage>();
		this.raceL = new ArrayList<Race>();
		this.tornamentL = new ArrayList<Tornament>();
	}

	// ----------------- RECOVER
	// ---------------------------------------------------------------------//

	public static Controler getTnstace() {
		if (c == null) {
			File f = new File(Controler.BACKUP);
			if (f.exists()) {
				try {
					c = (Controler) JSON.ReadObjJsonInFile(f);
				} catch (Exception e) {
					System.err.println("the data file could not be recovered" + e.getMessage());
				}
			} else {
				c = new Controler();

			}
		}
		return c;
	}

	public void save() {
		try {
			File f = new File(Controler.getPRIVATE_PATH());
			if (!f.exists()) {
				f.mkdirs();
			}
			File fname = new File(Controler.BACKUP);
			JSON.WriteObjJsonInFile(fname, this);
		} catch (Exception e) {
			System.err.println("Error al exportar " + e.getMessage());
		}

	}

	// ------------------------------------------------EXPORT/IMPORT
	// ------------------------------------//

	public void expTornamentL() {
		Utilss.expList(getTornamentL(), T_EXP);
	}

	public void importTornamentL() {
		Utilss.importList(getTornamentL(), T_EXP);
	}

	// ------------------------- ADD DELETE LIST -> ATRIBUTES
	// ------------------------------------------//

	public void addTornament(Tornament t) {
		if (!(this.tornamentL.contains(t)))
			this.tornamentL.add(t);
		else
			System.out.println("error " + t.name + "is in the list");
	}

	public void delTornament(Tornament t) {
		if ((this.tornamentL.contains(t)))
			this.tornamentL.remove(t);
		else
			System.out.println("error " + t.name + "not in the list");
	}

	public void addRace(Race r) {
		if (!(this.raceL.contains(r)))
			this.raceL.add(r);
		else
			System.out.println("error " + r.name + "is in the list");
	}

	public void delRace(Race r) {
		if ((this.raceL.contains(r)))
			this.raceL.remove(r);
		else
			System.out.println("error " + r.name + "is not in the list");
	}

	public void addGarage(Garage g) {
		if (!(this.garageL.contains(g)))
			this.garageL.add(g);
		else
			System.out.println("error " + g.name + "is in the list");
	}

	public void delGarage(Garage g) {
		if ((this.garageL.contains(g)))
			this.garageL.remove(g);
		else
			System.out.println("error " + g.name + "is not in the list");
	}

	// ------------------------- GET/SET -> ATRIBUTES
	// -------------------------------------------------//

	public Garage getGARAGESELECT() {
		return GARAGESELECT;
	}

	public void setGARAGESELECT(Garage gARAGESELECT) {
		GARAGESELECT = gARAGESELECT;
	}

	public Race getRACESELECT() {
		return RACESELECT;
	}

	public void setRACESELECT(Race rACESELECT) {
		RACESELECT = rACESELECT;
	}

	public Tornament getTORNAMENTSELECT() {
		return TORNAMENTSELECT;
	}

	public void setTORNAMENTSELECT(Tornament tORNAMENTSELECT) {
		TORNAMENTSELECT = tORNAMENTSELECT;
	}

	public ArrayList<Garage> getGarageL() {
		return garageL;
	}

	public void setGarageL(ArrayList<Garage> garageL) {
		this.garageL = garageL;
	}

	public ArrayList<Race> getRaceL() {
		return raceL;
	}

	public void setRaceL(ArrayList<Race> raceL) {
		this.raceL = raceL;
	}

	public ArrayList<Tornament> getTornamentL() {
		return tornamentL;
	}

	public void setTornamentL(ArrayList<Tornament> tornamentL) {
		this.tornamentL = tornamentL;
	}

	public static String getPUBLIC_PATH() {
		return PUBLIC_PATH;
	}

	public static String getPRIVATE_PATH() {
		return PRIVATE_PATH;
	}

	public static String getBACKUP() {
		return BACKUP;
	}

	public static String getPUBLIC_RACE() {
		return PUBLIC_RACE;
	}

	public static String getPRIVATE_RACE() {
		return PRIVATE_RACE;
	}

	public static String getPUBLIC_CAR() {
		return PUBLIC_CAR;
	}

	public static String getPRIVATE_CAR() {
		return PRIVATE_CAR;
	}

	public static String getPUBLIC_TORNAMENT() {
		return PUBLIC_TORNAMENT;
	}

	public static String getPRIVATE_TORNAMENT() {
		return PRIVATE_TORNAMENT;
	}

	public static String getR_LOG() {
		return R_LOG;
	}

	public static void setR_LOG(String r_LOG) {
		R_LOG = r_LOG;
	}

	public static String getR_EXP() {
		return R_EXP;
	}

	public static void setR_EXP(String r_EXP) {
		R_EXP = r_EXP;
	}

	public static String getR_RESULT() {
		return R_RESULT;
	}

	public static void setR_RESULT(String r_RESULT) {
		R_RESULT = r_RESULT;
	}

	public static String getT_EXP() {
		return T_EXP;
	}

	public static void setT_EXP(String t_EXP) {
		T_EXP = t_EXP;
	}

	public static String getT_PODIUM() {
		return T_PODIUM;
	}

	public static void setT_PODIUM(String t_LOG) {
		T_PODIUM = t_LOG;
	}

	public static String getT_RESULT() {
		return T_RESULT;
	}

	public static void setT_RESULT(String t_RESULT) {
		T_RESULT = t_RESULT;
	}

	public static String getCONF() {
		return BACKUP;
	}

	public static void setBACKUP(String bACKUP) {
		BACKUP = bACKUP;
	}

	public static String getIMPORTCAR() {
		return IMPORTCAR;
	}

	public static void setIMPORTCAR(String iMPORTCAR) {
		IMPORTCAR = iMPORTCAR;
	}

	public static String getG_PARTIC() {
		return G_PARTIC;
	}

	public static void setG_PARTIC(String g_PARTIC) {
		G_PARTIC = g_PARTIC;
	}

	public static String getG_RESULT() {
		return G_RESULT;
	}

	public static void setG_RESULT(String g_RESULT) {
		G_RESULT = g_RESULT;
	}

	public static String getG_EXP() {
		return G_EXP;
	}

	public static void setG_EXP(String g_EXP) {
		G_EXP = g_EXP;
	}

	public static String getR_PARTIC() {
		return R_PARTIC;
	}

	public static void setR_PARTIC(String r_PARTIC) {
		R_PARTIC = r_PARTIC;
	}

	public static String getT_PARTIC() {
		return T_PARTIC;
	}

	public static void setT_PARTIC(String t_PARTIC) {
		T_PARTIC = t_PARTIC;
	}

	// ------------ MAIN -----------------//

	// metodos para el main

	public static void main(String[] args) {
		Controler c = new Controler();
		c.menu();
	}

	public void menu() {
		try {
			int op = -1;
			do {
				System.out.println("\nMENU PRINCIPAL" + "\n===================" + "\n	1.- Gestionar garajes"
						+ "\n	2.- Gestionar carreras" + "\n	3.- Gestionar torneos" + "\n	0.- Salir"

				);

				op = KeyboardReader.readInt("put  the number of option  (0 to exit): ", "Error");
				switch (op) {
				case 0:
					break;
				case 1:
					menu_GARAGE();
					break;
				case 2:
					menu_RACE();
					break;
				case 3:
					menu_TORNAMENT();
					break;
				}
			} while (op != 0);
		} catch (Exception e) {
			System.err.println(" menu_TORNAMENT:: " + e.getMessage());
		}
	}

// -------------- GESTION GARAGE---------------------------------------------//
	// G[0] AUXILIAR
	private void selectGARAGECAR() {
		try {
			if (GARAGESELECT.getAllCar().size() > 0) {
				int id = -2;
				do {
					System.out.println("selected one car");
					System.out.println("[-1] exit ");
					int cn = 0;
					for (Car c : GARAGESELECT.getAllCar()) {
						System.out.println("[" + (cn++) + "]" + c);
						id = KeyboardReader.readInt("put  the number of the garage  (-1 to exit): ", "Error");
						try {

							if (id > -1) {
								CARSELECT = GARAGESELECT.getAllCar().get(id);
							}
						} catch (Exception e) {
							System.err.println();
						}
					}
				} while (id > c.getGarageL().size() || id < -1);
			} else {
				System.out.println("Firts create one garage");
			}
		} catch (Exception e) {
			System.err.println("ERROR::AUXILIAR::SELECT_GARAGE_CAR::" + e.getMessage());

		}
	}

	private void addCAR() {
		try {
			String model = KeyboardReader.readString("Enter the model of the car ", "wrong name entered", false);
			String mark = KeyboardReader.readString("Enter the mark of the car ", "wrong name entered", false);

			Car car = new Car(mark, model);
			System.out.println("the Car " + car + "is create");
			CARSELECT = car;
			System.out.println("the Car " + car + "is put into car_selected");
		} catch (Exception e) {
			System.err.println("ERROR::AUXILIAR::ADD_CAR::" + e.getMessage());

		}
	}

	// --------------------------------------------------------MENU GARAGE

	public void menu_GARAGE() {
		try {
			int op = -1;
			do {
				System.out.println("\n= Gestion Garajes =" + "\n====================" + "\n*	1.- Seleccionar garajes"
						+ "\n*	2.- Añadir garajes" + "\n*	3.- Eliminar garajes"
						+ "\n*	4.- Listar coches de un garaje" + "\n*	5.- A�adir coche a un garaje"
						+ "\n*	6.- Eliminar coche de un garaje" + "\n*	7.- Exportar datos" + "\n*	8.- Importar datos"
						+ "\n*	0.- Atras");

				op = KeyboardReader.readInt("put  the number of option  (0 to exit): ", "Error");
				switch (op) {
				case 0:
					break;
				case 1:
					selectGARAGE();
					break;
				case 2:
					addGARAGE();
					break;
				case 3:
					delGARAGE();
					break;
				case 4:
					viewGARAGE();
					break;
				case 5:
					updateGARAGEaddCar();
					break;
				case 6:
					updateGARAGEdelCar();
					break;
				case 7:
					exportGARAGE();
					break;
				case 8:
					importGARAGE();
					break;
				case 9:
					ImportCarINGARAGE();
					break;
				}
			} while (op != 0);
		} catch (Exception e) {
			System.err.println(" MENU_GARAGE:: " + e.getMessage());

		}
	}

//--------------------------------------------------------GESTION GARAGE
//G[00] SELECT::GARAGE OF A LIST
	private void selectGARAGE(List<Garage> list) {
		try {
			if (getGarageL().size() == 0) {
				throw new Exception("List garage sinze = 0");
			}
			int id = -2;
			do {
				System.out.println("selected one garage");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Garage g : list) {
					System.out.println("[" + (cn++) + "]" + g);
					id = KeyboardReader.readInt("put  the number of the garage  (-1 to exit): ", "Error");
					try {

						if (id > -1) {
							GARAGESELECT = list.get(id);
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getGarageL().size() || id < -1);

		} catch (Exception e) {
			System.err.println("ERROR::SELECT_OF_LIST::" + e.getMessage());

		}
	}

//G[1] SELECT::GARAGE
	private void selectGARAGE() {
		try {
			if (getGarageL().size() == 0) {
				throw new Exception("List garage sinze = 0");
			}
			int id = -2;
			do {
				System.out.println("selected one garage");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Garage g : c.getGarageL()) {
					System.out.println("[" + (cn++) + "]" + g);
					id = KeyboardReader.readInt("put  the number of the garage  (-1 to exit): ", "Error");
					try {

						if (id > -1) {
							c.setGARAGESELECT(c.getGarageL().get(id));
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getGarageL().size() || id < -1);

		} catch (Exception e) {
			System.err.println("ERROR::GRARAGE::SELECT::GARAGE" + e.getMessage());

		}
	}

//G[2] ADD_GARAGE
	private void addGARAGE() {
		String name = KeyboardReader.readString("Enter the name of the garage ", "wrong name entered", false);
		try {
			Garage g = new Garage(name);
			System.out.println("the garage " + g + "is create");

			if (getGarageL().contains(g)) {
				throw new Exception("List garage content garage");
			}
			c.setGARAGESELECT(g);
			System.out.println("the garage " + g + "is put in garage_select");
			c.getGarageL().add(g);
			System.out.println("the garage " + g + "is put into " + c.getGarageL());
		} catch (Exception e) {
			System.err.println("ERROR::GRARAGE::ADD_GARAGE::" + e.getMessage());

		}
	}

//G[3] DEL_GARAGE
	private void delGARAGE() {
		try {
			GARAGESELECT = null;
			selectGARAGE();
			if (!(getGarageL().contains(GARAGESELECT))) {
				throw new Exception("list garage no contenst the gerage ");
			}
			getGarageL().remove(GARAGESELECT);
			System.out.println("the garage " + GARAGESELECT + "is remove for " + c.getGarageL());
		} catch (Exception e) {
			System.err.println("ERROR::GRARAGE::DEL_GARAGE::" + e.getMessage());

		} finally {
			GARAGESELECT = null;
		}
	}

//G[4] VIEW_GARAGE
	private void viewGARAGE() {
		try {
			GARAGESELECT = null;
			selectGARAGE();
			if (GARAGESELECT == null) {
				throw new Exception("Not Garage selected");
			}

			for (Car c : GARAGESELECT.getAllCar()) {

				System.out.println(c);
			}

		} catch (Exception e) {
			System.err.println("ERROR::GRARAGE::VIEW_GARAGE::" + e.getMessage());

		}
	}

//G[5] 	::UPDASTE_GARAGE_ADD_CAR::

	private void updateGARAGEaddCar() {
		try {
			if (GARAGESELECT == null) {
				throw new Exception("Firts select a Garage");
			}
			addCAR();
			if (CARSELECT == null) {
				throw new Exception("Firts select a Car");
			} else if (GARAGESELECT.getAllCar().contains(CARSELECT)) {
				throw new Exception("Garage contenst the car " + CARSELECT);
			} else {
				GARAGESELECT.addCar(CARSELECT);
				System.out.println("the car " + CARSELECT + "add to" + GARAGESELECT.name);
			}
		} catch (Exception e) {
			System.err.println("ERROR::GRARAGE::UPDASTE_GARAGE_ADD_CAR::" + e.getMessage());

		}
	}

//G[6] 	:UPDATE_GARAGE_DEL_CAR::

	private void updateGARAGEdelCar() {
		try {
			if (GARAGESELECT == null) {
				throw new Exception("Firts select a Garage");
			}
			selectGARAGECAR();
			if (CARSELECT == null) {
				throw new Exception("Firts select a Car");
			}
			for (Car c : GARAGESELECT.getAllCar()) {

				System.out.println(c);
			}
		} catch (Exception e) {
			System.err.println("ERROR::GRARAGE::UPDATE_GARAGE_DEL_CAR:: " + e.getMessage());

		} finally {
			CARSELECT = null;
		}
	}
	// G[7] GRARAGE::EXPORT_GARAGE::

	public void exportGARAGE() {
		try {
			Utilss.expList(getGarageL(), G_EXP);
		} catch (Exception e) {
			System.err.println(" ERROR::GRARAGE::EXPORT_GARAGE:: " + e.getMessage());

		}
	}

	// G[8] GRARAGE::IMPORT_GARAGE::
	public void importGARAGE() {
		try {
			Utilss.importList(getGarageL(), G_EXP);
		} catch (Exception e) {
			System.err.println(" ERROR::GRARAGE::IMPORT_GARAGE:: " + e.getMessage());

		}
	}

	// G[9] GARAGE::IMPORT_CAR_IN_GARAGE::
	private void ImportCarINGARAGE() {
		try {
			if (GARAGESELECT == null) {
				throw new Exception("Firts select a Garage");
			}
			GARAGESELECT.ImportCars();
		} catch (Exception e) {
			System.err.println(" ERROR::GRARAGE::IMPORT_CAR_IN_GARAGE:: " + e.getMessage());
		}

	}
	// --------------------------------------------------------MENU RACE

	public void menu_RACE() {
		try {
			int op = -1;
			do {
				System.out.println("\n= Gestion Carreras =" + "\n===================="
						+ "\n*	1.- Seleccionar carreras" + "\n*	2.- Informacion de una carrera"
						+ "\n*	3.- Añadir carrera Estandar" + "\n*	4.- Añadir carrera Eliminatoria"
						+ "\n*	5.- Eliminar carrera" + "\n*	6.- Agregar garaje a una carrera"
						+ "\n*	7.- Eliminar garaje de una carrera" + "\n*	8.- Empezar carrera"
						+ "\n*	9.- Exportar datos" + "\n*	10.- Importar datos" + "\n*	0.- Atras"

				);

				op = KeyboardReader.readInt("put  the number of option  (0 to exit): ", "Error");
				switch (op) {
				case 0:
					break;
				case 1:
					selectRACE();
					break;
				case 2:
					infoRACE();
					break;
				case 3:
					addRACEStandar();
					break;
				case 4:
					addRACEElimination();
					break;
				case 5:
					delRACE();
					break;
				case 6:
					updateRACEaddGarage();
					break;
				case 7:
					updateRACEdelGarage();
					break;
				case 8:
					startRACE();
					break;
				case 9:
					expRace();
					break;
				case 10:
					importRACE();
					break;
				}
			} while (op != 0);
		} catch (Exception e) {
			System.err.println(" MENU_GARAGE:: " + e.getMessage());

		}
	}

//-------------------------------------------------------- GESTION  RACE	

	// R[00] RACE::SELECT::RACE::FRON LIST
	private void selectRACE(List<Race> list) {
		try {
			if (c.getRaceL().size() == 0) {
				throw new Exception("List race sinze = 0");
			}
			int id = -2;
			do {
				System.out.println("selected one RACE");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Race r : list) {
					System.out.println("[" + (cn++) + "]" + r);
					id = KeyboardReader.readInt("put  the number of the RACE  (-1 to exit): ", "Error");
					try {
						if (id > -1) {
							RACESELECT = list.get(id);
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getRaceL().size() || id < -1);

		} catch (Exception e) {
			System.err.println(" RROR::RACE::SELECT_LIST::RACE:: " + e.getMessage());
		}
	}

	// R[1] RACE::SELECT::RACE::
	private void selectRACE() {
		try {
			if (c.getRaceL().size() == 0) {
				throw new Exception("List race sinze = 0");
			}
			int id = -2;
			do {
				System.out.println("selected one RACE");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Race r : c.getRaceL()) {
					System.out.println("[" + (cn++) + "]" + r);
					id = KeyboardReader.readInt("put  the number of the RACE  (-1 to exit): ", "Error");
					try {
						if (id > -1) {
							c.setRACESELECT(c.getRaceL().get(id));
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getRaceL().size() || id < -1);

		} catch (Exception e) {
			System.err.println(" RROR::RACE::SELECT::RACE:: " + e.getMessage());
		}
	}

	// R[2] INFO::RACE::
	private void infoRACE() {
		try {
			RACESELECT = null;
			selectRACE();
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			RACESELECT.printINFO();

		} catch (Exception e) {
			System.err.println(" RROR::RACE::INFO::RACE:: " + e.getMessage());
		}
	}

	// R[3] ADD_RACE_STANDAR
	private void addRACEStandar() {
		String name = KeyboardReader.readString("Enter the name of the RACE ", "wrong name entered", false);
		try {
			Race_Standar r = new Race_Standar(name, 0);
			System.out.println("the Race " + r.name + "is create");

			if (raceL.contains(r)) {
				throw new Exception("List race content race");
			}
			RACESELECT = r;
			System.out.println("the Race " + r.name + "is put in race_select");
			raceL.add(r);
			System.out.println("the race " + r + "is put into List Race ");
		} catch (Exception e) {
			System.err.println("ERROR::RACE::ADD_RACE_STANDAR::::" + e.getMessage());

		}
	}

	// R[4] ADD_RACE_ELIMINATORIA
	private void addRACEElimination() {
		String name = KeyboardReader.readString("Enter the name of the RACE ", "wrong name entered", false);
		try {
			Race_Elimination r = new Race_Elimination(name, 1);
			System.out.println("the Race " + r.name + "is create");
			if (raceL.contains(r)) {
				throw new Exception("List race content race");
			}
			RACESELECT = r;
			System.out.println("the Race " + r.name + "is put in race_select");
			raceL.add(r);
			System.out.println("the race " + r + "is put into List Race ");
		} catch (Exception e) {
			System.err.println("RROR::RACE::ADD_RACE_ELIMINATORIA::" + e.getMessage());

		}
	}

	// R[5] ADD::DEL_RACE_
	private void delRACE() {
		try {
			RACESELECT = null;
			selectRACE();
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			if (!(raceL.contains(RACESELECT))) {
				throw new Exception("Firts select a RACE");
			}
			raceL.remove(RACESELECT);

		} catch (Exception e) {
			System.err.println(" RROR::RACE::DEL_RACE:: " + e.getMessage());
		} finally {
			RACESELECT = null;
		}
	}

	// R[6] UPDATE_RACE_ADD_GARAGE
	private void updateRACEaddGarage() {
		try {
			GARAGESELECT = null;
			addGARAGE();
			if (GARAGESELECT == null) {
				throw new Exception("Firts select a garage");
			}
			RACESELECT = null;
			selectRACE();
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			if ((RACESELECT.getParticG().contains(GARAGESELECT))) {
				throw new Exception("RACE contais the garage");
			}
			RACESELECT.addG(GARAGESELECT);

		} catch (Exception e) {
			System.err.println(" RROR::RACE::UPDATE_RACE_ADD_GARAGE:: " + e.getMessage());
		}
	}

	// R[7] UPDATE_RACE_DEL_GARAGE
	private void updateRACEdelGarage() {
		try {
			RACESELECT = null;
			selectRACE();

			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			GARAGESELECT = null;
			selectGARAGE((List<Garage>) RACESELECT);
			if (GARAGESELECT == null) {
				throw new Exception("Firts select a RACE");
			}

			if (!(RACESELECT.getResultG().contains(GARAGESELECT))) {
				throw new Exception("Firts select a RACE then a Garage");
			}
			RACESELECT.getResultG().remove(GARAGESELECT);

		} catch (Exception e) {
			System.err.println(" RROR::RACE::DEL_RACE:: " + e.getMessage());
		} finally {
			RACESELECT = null;
			GARAGESELECT = null;
		}
	}

	// R[8] START_RACE
	private void startRACE() {
		try {
			RACESELECT = null;
			selectRACE();

			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}

			RACESELECT.start();

		} catch (Exception e) {
			System.err.println(" RROR::RACE::START_RACE:: " + e.getMessage());
		} finally {
			RACESELECT = null;
		}
	}

	// R[9] RACE::IMPORT_RACE::
	public void importRACE() {
		try {
			Utilss.importList(getRaceL(), R_EXP);
		} catch (Exception e) {
			System.err.println(" RROR::RACE::IMPORT_RACE:: " + e.getMessage());

		}
	}

	// R[10] RACE::EXPORT_RACE::
	private void expRace() {
		try {
			Utilss.expList(getRaceL(), R_EXP);
		} catch (Exception e) {
			System.err.println(" RROR::RACE::EXPORT_RACE:::: " + e.getMessage());
		}

	}

	// --------------------------------------------------------MENU TORNAMENT
	public void menu_TORNAMENT() {
		try {
			int op = -1;
			do {
				System.out.println("\n= Gesti�n Torneos =" + "\n===================" + "\n*	1.- Seleccionar torneo"
						+ "\n*	2.- Informacion de un torneo"
						+ "\n*	3.- Añadir un torneo"
						+ "\n*	4.- Eliminar un torneo" 
						+ "\n*	5.- Añadir carrera de Eliminacion al torneo"
						+ "\n*	6.- Añadir carrera Standar al torneo"
						+"\n 7.- Eliminar carrera del torneo"
						+ "\n*	8.- Agregar garaje a un torneo"
						+ "\n*	9.- Eliminar garaje de un torneo"
						+ "\n*	10.- Añadir coches a los garages en el torneo"
						+ "\n*	11.-Eliminar coches de los garages en el torneo"
						+ "\n*	12.- Empezar torneo"
						+ "\n*	13.- Exportar datos" + "\n*	14.- Importar datos" 
						+ "\n*	0.- Atras"

				);

				op = KeyboardReader.readInt("put  the number of option  (0 to exit): ", "Error");
				switch (op) {
				case 0:
					break;
				case 1:
					selectTORNAMENT();
					break;
				case 2:
					infoRTORNAMENT();
					break;
				case 3:
					addTORNAMENT();
					break;
				case 4:
					delTORNAMENT();
					break;
				case 5:
					updateTORNAMENTaddRACEeliminate();
					break;
				case 7:
					updateTORNAMENTaddRACEstandar();
				case 6:
					updateTORNAMENTdelRACE();
					break;
				case 8:
					updateTORNAMENTaddGarage();
					break;
				case 9:
					updateTORNAMENTdelGarage();
					break;
				case 10:
					updateTORNAMENTaddCarToGarage();
					break;
				case 11:
					updateTORNAMENTdelCarToGarage();
					break;
				case 12:
					startTORNAMENT();
					break;
				case 13:
					expTORNAMENT();
					break;
				case 14:
					importTORNAMENT();
					break;
				}
			} while (op != 0);
		} catch (Exception e) {
			System.err.println(" menu_TORNAMENT:: " + e.getMessage());

		}
	}

	// --------------------------------------------------------GESTION TORNAMENT

	// T[1] RACE::SELECT::TORNAMEN::
	private void selectTORNAMENT() {
		try {
			if (c.getTornamentL().size() > 0) {
				throw new Exception("Firts create one TORNAMENT");

			}
			int id = -2;
			do {
				System.out.println("selected one TORNAMENT");
				System.out.println("[-1] exit ");
				int cn = 0;
				for (Tornament t : c.getTornamentL()) {
					System.out.println("[" + (cn++) + "]" + t);
					id = KeyboardReader.readInt("enter the number of the TORNAMENT  (-1 to exit): ",
							"wrong number entered");
					try {

						if (id > -1) {
							c.setTORNAMENTSELECT(c.getTornamentL().get(id));
						}
					} catch (Exception e) {
						System.err.println();
					}
				}
			} while (id > c.getTornamentL().size() || id < -1);

		} catch (Exception e) {
			System.err.println(" RROR:: TORNAMEN::SELECT_TORNAMENE:: " + e.getMessage());

		}
	}

	// T[2] INFO::TORNAMENT::
	private void infoRTORNAMENT() {
		try {
			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			TORNAMENTSELECT.printINFO();

		} catch (Exception e) {
			System.err.println(" RROR:: TORNAMEN::INFO::TORNAMENT:: " + e.getMessage());
		}
	}

	// T[4] ADD_TORNAMENT
	private void addTORNAMENT() {
		String name = KeyboardReader.readString("Enter the name of the TORNAMENT ", "wrong name entered", false);
		try {
			Tornament t = new Tornament(name);
			System.out.println("the Race " + t.name + "is create");
			if (tornamentL.contains(t)) {
				throw new Exception("List tornament content tornament");
			}
			TORNAMENTSELECT = t;
			System.out.println("the Tornament " + t.name + "is put in tornament_select");
			tornamentL.add(t);
			System.out.println("the Tornament " + t + "is put into List Tornament ");
		} catch (Exception e) {
			System.err.println("RROR:: TORNAMEN::ADD_TORNAMENT::" + e.getMessage());

		}
	}

	// T[5] DEL_TORNAMENT_
	private void delTORNAMENT() {
		try {
			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			if (!(tornamentL.contains(TORNAMENTSELECT))) {
				throw new Exception("non conten in List Tornament");
			}
			tornamentL.remove(TORNAMENTSELECT);

		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::DEL_TORNAMENT:: " + e.getMessage());
		} finally {
			RACESELECT = null;
		}
	}

	// T[5] UPADTE_TORNAMENT_ADD_RACE_ELIMINATE
	public void updateTORNAMENTaddRACEeliminate() {
		try {

			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			
			addRACEStandar();
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			
			if (TORNAMENTSELECT.listTornRace.size() != 0) {
				 if (TORNAMENTSELECT.listTornRace.get(0).getType()!= 1) {
				throw new Exception("PLease select a Tornament of  Elimination Race");
				 }
			}
			if (RACESELECT.getType()!= 1) {
					throw new Exception("PLease select  Elimination Race");
			}
			TORNAMENTSELECT.addRE(RACESELECT);
			
			
		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::UPADTE_TORNAMENT_ADD_RACE_ELIMINATE:: " + e.getMessage());
		}
	};
	// T[6] UPADTE_TORNAMENT_ADD_RACE_STANDAR
	public void updateTORNAMENTaddRACEstandar() {
		try {
			
			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			
			addRACEStandar();
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			
			if (TORNAMENTSELECT.listTornRace.size() != 0) {
				if (TORNAMENTSELECT.listTornRace.get(0).getType()!= 0) {
					throw new Exception("PLease select a Tornament of  Elimination Race");
				}
			}
			if (RACESELECT.getType()!= 0) {
				throw new Exception("PLease select  Elimination Race");
			}
			TORNAMENTSELECT.addRS(RACESELECT);
			
			
		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::UPADTE_TORNAMENT_ADD_RACE_ELIMINATE:: " + e.getMessage());
		}
	};

	// T[7] UPADTE_TORNAMENT_DEL_RACE
	public void updateTORNAMENTdelRACE() {
		try {
			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			RACESELECT=null;
			selectRace(TORNAMENTSELECT.listTornRace);
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			if ((RACESELECT.particG.contains(GARAGESELECT))) {
			TORNAMENTSELECT.listTornRace.remove(RACESELECT);
			}

		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::UPADTE_TORNAMENT_DEL_RACE:: " + e.getMessage());
		}finally {
			GARAGESELECT=null;
			RACESELECT=null;
			TORNAMENTSELECT=null;
			
		}
	};

	// T[8] UPADTE_TORNAMENT_ADD_GARAGE
	public void updateTORNAMENTaddGarage() {
		try {

			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			RACESELECT=null;
			selectRace(TORNAMENTSELECT.listTornRace);
			if (RACESELECT == null) {
				throw new Exception("Firts select a RACE");
			}
			GARAGESELECT = null;
			addGARAGE();
			if (GARAGESELECT == null) {
				throw new Exception("Firts select a GARAGE");
			}
			if (!(RACESELECT.particG.contains(GARAGESELECT))) {
			RACESELECT.particG.add(GARAGESELECT);
		}
			
		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::UPADTE_TORNAMENT_ADD_GARAGE:: " + e.getMessage());
		}
	};

	// T[9] UPADTE_TORNAMENT_DEL_GARAGE
	public void updateTORNAMENTdelGarage() {
		try {

			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			GARAGESELECT=null;
			selectGARAGE(TORNAMENTSELECT.tornPartiG);
			for (Race r : TORNAMENTSELECT.listTornRace) {
				if(r.particG.contains(GARAGESELECT)) {
					r.particG.remove(GARAGESELECT);
				}
			}
			
		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::UPADTE_TORNAMENT_DEL_GARAGE:: " + e.getMessage());
		}finally {
			GARAGESELECT=null;
			CARSELECT=null;
			TORNAMENTSELECT=null;
			
		}
	};

	// T[10] UPADTE_TORNAMENT_ADD_CAR_TO_GARAGE
	public void updateTORNAMENTaddCarToGarage() {
		try {

			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			GARAGESELECT=null;
			selectCAR(TORNAMENTSELECT.tornPartiC);
			if (CARSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			
			for (Race r : TORNAMENTSELECT.listTornRace) {
				if(!(r.particC.contains(CARSELECT))) {
					r.particC.add(CARSELECT);
				}
			}
			
		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::UPADTE_TORNAMENT_ADD_CAR_TO_GARAGE:: " + e.getMessage());
		}
	};

	// T[11] UPADTE_TORNAMENT_DEL_CAR_TO_GARAGE
	public void updateTORNAMENTdelCarToGarage() {
		try {
			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			GARAGESELECT=null;
			selectCAR(TORNAMENTSELECT.tornPartiC);
			if (CARSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			
			for (Race r : TORNAMENTSELECT.listTornRace) {
				if(r.particC.contains(CARSELECT)) {
					r.particC.remove(CARSELECT);
				}
			}
			
		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::DEL_TORNAMENT:: " + e.getMessage());
		}finally {
			GARAGESELECT=null;
			CARSELECT=null;
			TORNAMENTSELECT=null;
		}
	};

	// T[12] START_TORNAMENT

	public void startTORNAMENT() {
		try {
			TORNAMENTSELECT = null;
			selectTORNAMENT();
			if (TORNAMENTSELECT == null) {
				throw new Exception("Firts select a TORNAMENT");
			}
			TORNAMENTSELECT.start();

		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::START_TORNAMENT:: " + e.getMessage());
		}
	};

	// T[13] EXPORT_TORNAMENT
	public void expTORNAMENT() {
		try {

			Utilss.expList(getTornamentL(), T_EXP);

		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::EXPORT_TORNAMENT:: " + e.getMessage());
		}
	};
	// T[14] IMPORT_TORNAMENT

	public void importTORNAMENT() {
		try {
			Utilss.importList(getTornamentL(), T_EXP);

		} catch (Exception e) {
			System.err.println("ERROR:: TORNAMEN::IMPORT_TORNAMENT:: " + e.getMessage());
		}
	};

}// END