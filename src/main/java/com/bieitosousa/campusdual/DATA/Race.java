/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import com.bieitosousa.campusdual.UTILS.*;


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
				if (g.getAllCar().size()==0) {
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
			try {
				this.makeRace();
				if (Controler.isRACE_RESULT()) {
					this.printResultC();
				}
//				if (Controler.isRACE_EXP()) {
//					this.exportRace();
//				}
			} catch (Exception e) {
				System.err.println("Error al ejecutar la carrera " + cabeceraR + e.getMessage());
			}
		}
	public  static List<Car> OrderCarAsPosition(List<Car> listofCar) { // this method sorts the lists of participants by the traveled distance
		Car[] arrayCars = new Car[listofCar.size()];
		listofCar.toArray(arrayCars);
		listofCar.clear();
		for (int i = 0; i < arrayCars.length; i++) {
            for (int j = i; j < arrayCars.length; j++) {
                if (arrayCars[i].getDistance() < arrayCars[j].getDistance() ) {
                    Car aux = arrayCars[i];
                    arrayCars[i] = arrayCars[j];
                    arrayCars[j] = aux;
                }
            }	
		}
		for (Car car : arrayCars) {
			listofCar.add(car);
		}
		return listofCar;
	}
	
//	public static List<Car> OrderCarAsPosition(List<Car> listofCar) throws Exception {
//		int cuentaintercambios = 0;
//		Car[] arrayCars = new Car[listofCar.size()];
//		listofCar.toArray(arrayCars);
//		listofCar.clear();
//		// Usamos un bucle anidado, saldra cuando este ordenado el array
//		for (boolean ordenado = false; !ordenado;) {
//			for (int i = 0; i < arrayCars.length - 1; i++) {
//				if (arrayCars[i].getDistance() > arrayCars[i + 1].getDistance()) {
//					// Intercambiamos valores
//					Car vAux = arrayCars[i];
//					Car vAux2 =arrayCars[i + 1];
//					arrayCars[i + 1]= vAux;
//					arrayCars[i ]=vAux2;
//					// indicamos que hay un cambio
//					cuentaintercambios++;
//				}
//			}
//			// Si no hay intercambios, es que esta ordenado.
//			if (cuentaintercambios == 0) {
//				ordenado = true;
//			}
//			// Inicializamos la variable de nuevo para que empiece a contar de nuevo
//			cuentaintercambios = 0;
//		}
//		listofCar= Arrays.asList(arrayCars);
//		Collections.reverse(listofCar);
//		return listofCar;
//	}

	public static int POINTSFIRSTS = 12;
	public static int POINTS_SECOND = 6;
	public static int POINTS_THIRD = 3;
	public static int POINTS_DEFAULT = 1;

	protected void takePoints(ArrayList<Car> resRace) {
		// ORDENO

		try {
			//resRace = (ArrayList<Car>) OrderCarAsPosition(resRace);
		} catch (Exception e) {
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
		setResultG(superListG);
		//Race.printListHelp(getParticG(),this.cabeceraR+this.cabeceraT+"asignacionRace");
		
	}
	

	private ArrayList<Car> getPodiumC() throws Exception{
		return (ArrayList<Car>) getResultC();
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

	public static void printListHelp(ArrayList<?> l, String cabecera) {
			l.forEach((a) -> Utilss.printONFile("\n" + cabecera + a + "\n",
					new File(Controler.getHELP() + cabecera+".txt")));
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
	
//	public void printResultGrupByGarage(int i) throws Exception {
//		ArrayList<Car> listCarInGarage = new ArrayList<>();
//		printRes("\n	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
//				+"\n	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
//				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
//				+ "\n	!	!	RESULTADOS	GRUP BY GARAGE	!	!	!	!	!	"
//				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
//		for (Garage g : ParticG) {
//			printRes("	=	=	=	GARAGE	[[	" + g.name + "	]]	=	=	=	=	=");
//			for (Car c : g.listGCar) {
//				if (c.getDistance() != 0) {
//					listCarInGarage.add(c);
//				}
//			}
//			OrderCarAsPosition(listCarInGarage);
//			for (Car cInGarage : listCarInGarage) {
//				printRes("\n	=			" + g.name + "	[[ " + cInGarage + "]]");
//			}
//			listCarInGarage.clear();
//		} // Fin Garage
//
//	}

	

	
	public void printResultC(int i ) throws Exception {
		String fname=this.cabeceraT+this.cabeceraR;
		printRes("\n	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
				+"	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]	["+i+"]"
				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	!	!	!	!	!	"
				+ "\n	!	!	["+fname+"]!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		int cc = 0;
		for (Car car : getResultC()) {
			printRes("\n[" + (cc++) + "]	=	=	=	=	[[ " + car + "]]");
		}
	}
	public void printResultC( ) throws Exception {
		String fname= getCabecera()+ getCabeceraT();
		printRes(
				"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	!	!	!	!	!	"
				+ "\n	!	!	["+fname+"]!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		int cc = 0;
		for (Car car : getResultC()) {
			printRes("\n[" + (cc++) + "]	=	=	=	=	[[ " + car + "]]");
		}
	}
	
	public void ExportList(ArrayList<?> l, String cabecera) {
		try {
			File f = new File(Controler.getPRIVATE_RACE());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
		name =  getCabecera()+ getCabeceraT()+ date.getTime()+".txt";
		File fname = new File(Controler.getR_EXP() + name);
				System.out.println(cabecera);
			l.forEach((a) -> Utilss.printONFile("\n	=	=	=	=	=	= " + a + "\n"
					,fname));
			System.out.println("is export the race in ::" + fname);
		} catch (Exception e) {
			System.err.println("Error al exportar " + getCabecera()+ getCabeceraT() + e.getMessage());
		}
	}

	
	public void exportRace() {
		String cabeceraPart = 				
				"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PARTICIPANTES 	"+ getCabecera() +getCabeceraT()+"	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraResult = 				
				"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS 	"+ getCabecera() +getCabeceraT()+"!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraPodium = 				
				"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM 	"+ getCabecera() +getCabeceraT()+"!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		try {
			ExportList((ArrayList<Car>) getParticC(),cabeceraPart);
			ExportList((ArrayList<Car>) getResultC(),cabeceraResult);
			ExportList((ArrayList<Car>) getPodiumC(),cabeceraPodium);
		} catch (Exception e) {	
			System.err.println("Error al exportar " + getCabecera() +getCabeceraT() +e.getMessage());
		}
	}
	
	public void exportRace(int i) {
		String cabeceraPart = 			
				"\n	["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]"
				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PARTICIPANTES 	"+ getCabecera() +getCabeceraT()+"	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraResult = 
				"\n	["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]"
				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS 	"+ getCabecera() +getCabeceraT()+"	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraPodium =
				"\n	["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]["+i+"]"
				+"\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM 	"+ getCabecera() +getCabeceraT()+"	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		try {
			ExportList((ArrayList<Car>)getParticC(),cabeceraPart);
			ExportList((ArrayList<Car>)getResultC(),cabeceraResult);
			ExportList(getPodiumC(),cabeceraPodium);
		} catch (Exception e) {	
			System.err.println("Error al exportar " +getCabecera()+ getCabeceraT()+ e.getMessage());
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
		return (ArrayList<Garage>) cloneListG(listA);
	}

	public List<Car> getResultC() throws Exception {
		List<Car> listA = new ArrayList<>();
		ArrayList<Car> listACopy = new ArrayList<>();
		for (Garage g : ParticG) {
			listA.addAll(g.listGCar);
		}
		listACopy=(ArrayList<Car>) cloneList(listA);
		listACopy = (ArrayList<Car>) OrderCarAsPosition(listACopy);
		//Collections.copy(listACopy,listA);
		return listACopy;
	}
	public List<Car> getParticC() throws Exception {
		ArrayList<Car> listA = new ArrayList<>();
		ArrayList<Car> listACopy = new ArrayList<>();
		for (Garage g : ParticG) {
			listA.addAll(g.listGCar);
		}
		//ArrayList<Car> listACopy = new ArrayList<>(listA);
		listACopy=(ArrayList<Car>) cloneList(listA);
		for(Car cc :listACopy) {
			cc.setDistance(0);
			cc.setPoints(0);
		}
		return listACopy;
	}
	public List<Garage> getResultG() throws Exception {
		ArrayList<Garage> listA = new ArrayList<>();
		
		
		for (Garage g : ParticG) {
			listA.add(g);
		}
		//ArrayList<Garage> listACopy = new ArrayList<>(listA);
		//Collections.copy(listACopy,listA);
		return (ArrayList<Garage>) cloneListG(listA);
	}

	public void setResultG(List<Garage> partic) {
		this.ParticG.clear();
		for (Garage g : partic) {
			this.ParticG.add(g);
		}
	}
	
	public static List<Car> cloneList(List<Car> list) {
	    List<Car> clone = new ArrayList<Car>(list.size());
	    for (Car item : list) clone.add((Car) item.clone());
	    return clone;
	}
	public static List<Garage> cloneListG(List<Garage> list) {
		List<Garage> clone = new ArrayList<Garage>(list.size());
		for (Garage item : list) clone.add((Garage) item.clone());
		return clone;
	}

}// End race.class
