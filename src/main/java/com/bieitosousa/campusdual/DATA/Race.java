/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import com.bieitosousa.campusdual.UTILS.Controler;
import com.bieitosousa.campusdual.UTILS.JSON;
import com.bieitosousa.campusdual.UTILS.Utilss;

import java.io.File;
import java.util.*;

/**
 *
 * @author BIE_FIJO_PC
 */
// ===================================================//
//  =   Class to Create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
public abstract class Race {

	// name of the race
	protected String name;
	// type = 0 Estandar ; type = 1 Eliminarorias
	protected int type;
	// true --> is on the run if result != null
	// positions ,0--> 1ºCar ,1-->2ºCar ,2-->3ºCar
	// store of the race result
	// store of the race participants
	protected ArrayList<Garage> ParticG = new ArrayList<>();
	protected String cabeceraR = "";
	protected String cabeceraT = "";

// ===================================================//
//  =   Class to create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
	public Race(String name, int type, ArrayList<Garage> listGarageG, String cabeceraR
	// all Cars on the garage participate true
	// one Car on the garage participate false
	) throws Exception {
		this.name = name;
		this.type = type;
		if(listGarageG.size()>0) {
			for (Garage g :listGarageG) {
				if (g.getAllCar().size()>0) {
					throw new Exception ("One or more Gargarages not content cars");
					
				}
			}
		}else {
			throw new Exception ("ListGarage not contein garages" );
			
		}
		this.ParticG = listGarageG;
		this.cabeceraR = cabeceraR;

	}

	abstract public void makeRace() throws Exception;// FinmakeRace

	public void start() {
		int cc= 0;
			try {
				this.makeRace();
				if (Controler.isRACE_RESULT()) {
					this.printResultGrupByCar();
				}
				if (Controler.isRACE_EXP()) {
					this.exportRace();
				}
			} catch (Exception e) {
				System.err.println("Error al ejecutar la carrera " + cabeceraR + e.getMessage());
			}
		}
		
	
	public static List<Car> OrderCarAsPosition(List<Car> listCar) throws Exception {
		int cuentaintercambios = 0;
		Car[] arrayCars = new Car[listCar.size()];
		listCar.toArray(arrayCars);
		// Usamos un bucle anidado, saldra cuando este ordenado el array
		for (boolean ordenado = false; !ordenado;) {
			for (int i = 0; i < arrayCars.length - 1; i++) {
				if (arrayCars[i].getDistance() > arrayCars[i + 1].getDistance()) {
					// Intercambiamos valores
					int vAux = arrayCars[i].getDistance();
					arrayCars[i].setDistance(arrayCars[i + 1].getDistance());
					arrayCars[i + 1].setDistance(vAux);
					// indicamos que hay un cambio
					cuentaintercambios++;
				}
			}
			// Si no hay intercambios, es que esta ordenado.
			if (cuentaintercambios == 0) {
				ordenado = true;
			}
			// Inicializamos la variable de nuevo para que empiece a contar de nuevo
			cuentaintercambios = 0;
		}
		Collections.reverse(listCar);
		return listCar;
	}

	public static int POINTSFIRSTS = 12;
	public static int POINTS_SECOND = 6;
	public static int POINTS_THIRD = 3;
	public static int POINTS_DEFAULT = 1;

	protected void takePoints(ArrayList<Car> resRace) {
		// ORDENO

		try {
			resRace = (ArrayList<Car>) OrderCarAsPosition(resRace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// PUNTUO
		for (int i = 0; i < resRace.size(); i++) {
			switch (i) {
			case 0:
				resRace.get(i).setPoints( Controler.getPOINTS_FIRSTS());
				break;
			case 1:
				resRace.get(i).setPoints( Controler.getPOINTS_SECOND());
				break;
			case 2:
				resRace.get(i).setPoints( Controler.getPOINTS_THIRD());
				break;
			default:
				resRace.get(i).setPoints(Controler.getPOINTS_DEFAULT());

			}
		}
		// lOS METO EN LA LISTA DE GARAJES
		ArrayList<Garage> superListG = getParticG();
		for (Garage supgarlist : superListG) {
			for (Car sgc : supgarlist.listGCar) {
				for (Car pc : resRace) {
					if (sgc.equals(pc)) {
						sgc.setDistance(pc.getDistance());
						sgc.setPoints( pc.getPoints());
					}
				}
			}
		}
		setParticG(superListG);
	}

	// = = = = [PRINT ]RACE_VIEW = = = =

	public void print(String mnj) {
		if (Controler.isRACE_VIEW()) {
		if (Controler.isCONSOLE_PRINT_RACE_VIEW()) {
			System.out.println(mnj);
		} else {
			Utilss.printONFile(mnj, new File(Controler.getR_LOG() + getCabecera() + getCabeceraT()+".txt"));
		}
		}
	}

	public void printList(ArrayList<?> l) {
		if (Controler.isRACE_VIEW()) {
		if (Controler.isCONSOLE_PRINT_RACE_VIEW()) {
			l.forEach((a) -> System.out.println("\n" + a + "\n"));
		} else {
			l.forEach((a) -> Utilss.printONFile("\n" + a + "\n",
					new File(Controler.getR_LOG() + getCabecera() + getCabeceraT()+".txt")));
		}
		}
	}

	public void printList(ArrayList<?> l, String cabecera) {
		if (Controler.isRACE_VIEW()) {
		if (Controler.isCONSOLE_PRINT_RACE_VIEW()) {
			l.forEach((a) -> System.out.println("\n" + cabecera + a + "\n"));
		} else {
			l.forEach((a) -> Utilss.printONFile("\n" + cabecera + a + "\n",
					new File(Controler.getR_LOG() + getCabecera() + getCabeceraT()+".txt")));
		}
		}
	}

//	=	=	=	=	[PRINT ]RACE_RESULTS	=	=	=	=
	public void printRes(String mnj) {
		if (Controler.isRACE_RESULT()) {
		if (Controler.isCONSOLE_PRINT_RACE_RESULT()) {
			System.out.println(mnj);
		} else {
			Utilss.printONFile(mnj, new File(Controler.getR_RESULT() + getCabecera() + getCabeceraT()+".txt"));
		}
		}
	}

//	=	=	=	=	[PRINT ]END	=	=	=	=
	
	public void printResultGrupByGarage(int i) throws Exception {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		printRes("\n	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
				+"\n	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY GARAGE	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		for (Garage g : ParticG) {
			printRes("	=	=	=	GARAGE	[[	" + g.name + "	]]	=	=	=	=	=");
			for (Car c : g.listGCar) {
				if (c.getDistance() != 0) {
					listCarInGarage.add(c);
				}
			}
			OrderCarAsPosition(listCarInGarage);
			for (Car cInGarage : listCarInGarage) {
				printRes("\n	=			" + g.name + "	[[ " + cInGarage + "]]");
			}
			listCarInGarage.clear();
		} // Fin Garage

	}

	public void printResultGrupByCar(int i ) throws Exception {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		printRes("\n	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
				+"	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		for (Garage g : ParticG) {
			for (Car c : g.listGCar) {
				if (c.getDistance() != 0) {
					listCarInGarage.add(c);
				}
			}
		} // Fin Garage
		OrderCarAsPosition(listCarInGarage);
		int cc = 0;
		for (Car cInGarage : listCarInGarage) {
			printRes("\n[" + (cc++) + "]	=	=	=	=	[[ " + cInGarage + "]]");
		}
		listCarInGarage.clear();
	}
	public void printResultGrupByCar( ) throws Exception {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		printRes("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		for (Garage g : ParticG) {
			for (Car c : g.listGCar) {
				if (c.getDistance() != 0) {
					listCarInGarage.add(c);
				}
			}
		} // Fin Garage
		OrderCarAsPosition(listCarInGarage);
		int cc = 0;
		for (Car cInGarage : listCarInGarage) {
			printRes("\n[" + (cc++) + "]	=	=	=	=	[[ " + cInGarage + "]]");
		}
		listCarInGarage.clear();
	}

	public void exportRace(int i) {
		try {
			File f = new File(Controler.getPRIVATE_RACE());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
			name = "race_" + this.name + date.getTime();
			File fname = new File(Controler.getR_EXP() + name);
			JSON.WriteObjJsonInFile(fname, this);
			System.out.println("se a exportado la carrea en ::" + fname);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraR + e.getMessage());
		}
	}
	public void exportRace() {
		try {
			File f = new File(Controler.getPRIVATE_RACE());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
			name = "race_" + this.name + date.getTime();
			File fname = new File(Controler.getR_EXP() + name);
			JSON.WriteObjJsonInFile(fname, this);
			System.out.println("se a exportado la carrea en ::" + fname);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraR + e.getMessage());
		}
	}

	public void exportRace(String cabecera) {
		try {
			File f = new File(Controler.getPRIVATE_RACE());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
			name = "race_" + this.name + cabecera + date.getTime();
			File fname = new File(Controler.getR_EXP() + name);
			JSON.WriteObjJsonInFile(fname, this);
			System.out.println("se a exportado la carrea en ::" + fname);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraR + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Race{" + "name=" + name + ", type=" + ((type == 1) ? "Eliminacion" : "Estandar") + ", ParticG="
				+ ParticG + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCabecera() {
		return this.cabeceraR;
	}

	public String getCabeceraT() {
		return this.cabeceraT;
	}

	public String setCabeceraT(String cabeceraT) {
		return this.cabeceraT;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<Garage> getParticG() {
		ArrayList<Garage> listA = new ArrayList<>(ParticG);
		return listA;
	}

	public List<Car> getParticC() throws Exception {
		List<Car> listA = new ArrayList<>();
		for (Garage g : ParticG) {
			listA.addAll(g.listGCar);
		}
		listA = (ArrayList<Car>) OrderCarAsPosition(listA);
		return listA;
	}

	public void setParticG(List<Garage> partic) {
		this.ParticG.clear();
		for (Garage g : partic) {
			this.ParticG.add(g);
		}
	}

}// End race.class
