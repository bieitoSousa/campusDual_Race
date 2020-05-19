/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import com.bieitosousa.campusdual.UTILS.*;

import java.io.File;
import java.io.Serializable;
import java.util.*;


/**
 *
 * @author BIE_FIJO_PC
 */
// ===================================================//
//  =   Class to Create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
public abstract class Race implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// name of the race
	protected String name;
	// type = 0 Estandar ; type = 1 Eliminarorias
	protected int type;
	// true --> is on the run if result != null
	// positions ,0--> 1ºCar ,1-->2ºCar ,2-->3ºCar
	// store of the race result
	// store of the race participants
	protected ArrayList<Garage> particG = new ArrayList<>();
	protected ArrayList<Garage> resultG = new ArrayList<>();
	protected ArrayList<Car> particC = new ArrayList<>();
	protected ArrayList<Car> resultC = new ArrayList<>();
	protected ArrayList<Car> pointsC = new ArrayList<>();

	protected String cabeceraR = "";
	protected String cabeceraT = "";


// ===================================================//
//  =   Class to create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
	public Race(String name, int type, ArrayList<Garage> listGarageG, String cabeceraR
	// all Cars on the garage participate true
	// one Car on the garage participate false
	) {
		try {
		this.name = name;
		this.type = type;
		//List<Garage>ListGCarCopy=listGarageG.stream().collect(Collectors.toList());
		
		if (listGarageG.size() > 0) {
			for (Garage g : listGarageG) {
				if (g.getAllCar().size() == 0) {
					throw new Exception("One or more Gargarages not content cars");

				}
			}
		} else {
			throw new Exception("ListGarage not contein garages");

		}
		for (Garage gc :listGarageG) {
			this.particG.add((Garage)gc.clone());
		}
		this.cabeceraR = cabeceraR;
		}catch(Exception e) {
			System.err.println(	"ERRR::RACE::CONSTRUCTOR"+e.getMessage());
			}
	}

	// ------------------	OPERATIONS -------------------------------------//

	abstract public void makeRace() throws Exception;// FinmakeRace

	public void start() {
		try {
			this.makeRace();
			
		} catch (Exception e) {
			System.err.println("Error al ejecutar la carrera " + cabeceraR + e.getMessage());
		}
	}

	// ----------------- 	PROCCES DATA  CAR LIST --------------------//
	// ------------------------------------------------------------//

	protected void takePoints(ArrayList<Car> resRace) throws Exception {
		// ORDENO
		int error = resRace.size(); // control for not duplications
		try {
		 Collections.sort(resRace);
		 ArrayList<Car> listTBlank = new ArrayList<Car>();
		 for(Car c : resRace) {
				if(c.getDistance()>0) {
					
				}else {
					listTBlank.add((Car) c.clone());
				}
			}
		 for(Car cblank : listTBlank) {
			 cblank.setDistance(0);
			 cblank.setPoints(0);
		 }
		 Collections.sort(listTBlank);
		 this.particC=listTBlank;
		 
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		// PUNTUO
		for (int i = 0; i < resRace.size(); i++) {
			switch (i) {
			case 0:
				resRace.get(i).setPoints(Controler.getPOINTS_FIRSTS());
				break;
			case 1:
				resRace.get(i).setPoints(Controler.getPOINTS_SECOND());
				break;
			case 2:
				resRace.get(i).setPoints(Controler.getPOINTS_THIRD());
				break;
			default:
				resRace.get(i).setPoints(Controler.getPOINTS_DEFAULT());

			}
		}


		ArrayList<Car> superListC = getParticC();
			for (Car car : resRace) {
				if (superListC.contains(car)){
					
				}else {
					if (car.getDistance()!=0) {
				superListC.add(car);
					}
				}
			}
			
			if (superListC.size() != error) {
				throw new Exception("takePoints :: malformed list, size increments" + error + "-->" + superListC.size());
			}	
		 Collections.sort(superListC);

		setResultC(superListC);
		
		setPointsC(superListC);


	}


	// -------------------- PRINT-------------------------------//
	// ------------------------------------------------------- //

	public void print(String mnj) {
		if (Utilss.printInConsole()) {
			if (Utilss.printInConsole()) {
				System.out.println(mnj);
			} else {
				Utilss.printONFile(mnj, new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt"));
			}
		}
	}

	public void printList(ArrayList<?> l) {
		if (Utilss.printInConsole()) {
			if (Utilss.printInConsole()) {
				l.forEach((a) -> System.out.println("\n" + a + "\n"));
			} else {
				l.forEach((a) -> Utilss.printONFile("\n" + a + "\n",
						new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt")));
			}
		}
	}

	public void printList(ArrayList<?> l, String cabecera) {
		if (Utilss.printInConsole()) {
			if (Utilss.printInConsole()) {
				System.out.println(cabecera);
				l.forEach((a) -> System.out.println("\n			" + getCabecera() + getCabeceraT()+  a + "\n"));
			} else {
				Utilss.printONFile(cabecera,
						new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt"));
				l.forEach((a) -> Utilss.printONFile("\n			" + getCabecera() + getCabeceraT()+  a + "\n",
						new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt")));
			}
		}
	}


	
//	=	=	=	=	[PRINT ]RACE_RESULTS	=	=	=	=
	

	public void printResultC() throws Exception {
		
		String a ="\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"	
				+ "\n	!	!	RESULTADOS	+ getCabecera() + getCabeceraT() +	!	!	!	!	!	"	
				+ "\\n!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		printList((ArrayList<Car>)getResultC(),a);
	}

	
	
	

	

	// ------ GET/SET [PARTICIPE,RESULT,POINTS] --> {C CAR G GARAGE}

	public ArrayList<Garage> getParticG() {
		return particG;
	}

	public void setParticG(List<Garage> list) {
		if (this.particG.size() > 0) {
			this.particG = (ArrayList<Garage>) list;
		} else {
			particG.clear();
			particG = (ArrayList<Garage>) list;
		}
	}

	public ArrayList<Car> getParticC() throws Exception {

		return (particC);
	}

	public void setParticC(List<Car> list) {
		if (this.particC.size() > 0) {
			particC = (ArrayList<Car>) list;
		} else {
			particC.clear();
			particC = (ArrayList<Car>) list;
		}
	}

	public List<Car> getResultC() throws Exception {
		return resultC;
	}

	private void setResultC(List<Car> list) {
		if (this.resultC.size() > 0) {
			resultC = (ArrayList<Car>) list;
		} else {
			resultC.clear();
			resultC = (ArrayList<Car>) list;
		}
	}

	public List<Garage> getResultG() throws Exception {

		return resultG;
	}


	private void setPointsC(List<Car> list) {
		this.pointsC.clear();
		this.pointsC = (ArrayList<Car>) list;
	}

	public List<Car> getPointsC() {

		return pointsC;
	}




	// ------------------ BASICS ------------------------------//

		@Override
		public String toString() {
			return "Race{" + "name=" + name + ", type=" + ((type == 1) ? "Eliminacion" : "Estandar") + ", ParticG="
					+ particG + '}';
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

}// End race.class
