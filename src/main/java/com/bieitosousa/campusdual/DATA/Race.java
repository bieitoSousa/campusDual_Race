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
	public Race(String name, int type, String cabeceraR
	// all Cars on the garage participate true
	// one Car on the garage participate false
	) {
		try {
			this.name = name;
			this.type = type;
			this.cabeceraR = cabeceraR;
		} catch (Exception e) {
			System.err.println("ERRR::RACE::CONSTRUCTOR" + getCabecera() + e.getMessage());
		}
	}

	// ------------------------------------ BASIC OPERATIONS ADD / DELETE
	// ----------------------------------------------------------------------//

	public void addG(ArrayList<Garage> listGarageG) {
		try {
			if (this.particG.containsAll(listGarageG)) {
				throw new Exception("List Garage content this garage");
			}
			else if (listGarageG.size() > 0) {
				for (Garage g : listGarageG) {
					if (g.getAllCar().size() == 0) {
						throw new Exception("One or more Gargarages not content cars");
					}
				}
				if (getParticC().size()>0) {
					throw new Exception("you can only add garages  if the race  is not started");		
				}
				for (Garage g :listGarageG) {
					this.particG.add( (Garage)g.clone());
				}
				
				
			} else {
				throw new Exception("ListGarage not contein garages");
			}

		} catch (Exception e) {
			System.err.println("ERRR::RACE::ADD" + getCabecera() + e.getMessage());
		}
	}

	public void addG(Garage GarageG) {
		try {
			if (this.particG.contains(GarageG)) {
				throw new Exception("List Garage content this garage");
			}

			else if (GarageG.getAllCar().size() == 0) {
				throw new Exception("One or more Gargarages not content cars");
			}
			else if (getParticC().size()>0) {
				throw new Exception("you can only add garages  if the race  is not started");		
			}
			
			else {
				this.particG.add((Garage) GarageG.clone());
				}


		} catch (Exception e) {
			System.err.println("ERRR::RACE::ADD" + getCabecera() + e.getMessage());
		}
	}

	public void delG(ArrayList<Garage> listGarageG) {
		try {
			if (!(this.particG.containsAll(listGarageG))) {
				throw new Exception("List Garage not content this garage");
			}

			if (listGarageG.size() > 0) {
				for (Garage g : listGarageG) {
					if (g.getAllCar().size() == 0) {
						throw new Exception("One or more Gargarages not content cars");
					}
				}
				if (getParticC().size()>0) {
					throw new Exception("you can only delete garages  if the race  is not started");		
				}else {
					this.particG.removeAll(listGarageG);
					}
				
				
			} else {
				throw new Exception("ListGarage not contein garages");

			}

			this.particG.removeAll(listGarageG);

		} catch (Exception e) {
			System.err.println("ERRR::RACE::ADD" + getCabecera() + e.getMessage());
		}
	}

	public void delG(Garage GarageG) {
		try {
			if (!(this.particG.contains(GarageG))) {
				throw new Exception("List Garage content this garage");
			}else if (GarageG.getAllCar().size() == 0) {
				throw new Exception("One or more Gargarages not content cars");
			}else if (getParticC().size()>0) {
				throw new Exception("you can only delete garages  if the race  is not started");		
			}else {

			this.particG.remove(GarageG);
			}

		} catch (Exception e) {
			System.err.println("ERRR::RACE::ADD" + getCabecera() + e.getMessage());
		}
	}

	// --------------------------------------- INTERNAL OPERATIONS
	// ----------------------------------------------------------------------//

	abstract public void makeRace() throws Exception;

	public void start() {
		try {
			this.makeRace();

		} catch (Exception e) {
			System.err.println("ERRR::RACE::MAKE" + getCabecera() + e.getMessage());
		}
	}

	// ----------------------------------------- PROCCES DATA CAR LIST
	// ----------------------------------------------------------------------//
	protected List<Car> takePartC() throws Exception {
		// Loop the gararges and take one the cart to participate
		try {
		if (getParticC().size()==0) {
		if (getParticG().size() == 1) {
			for (Garage g : getParticG()) {
				for (Car gc : g.getAllCar()) {
					this.particC.add((Car) gc.clone());
				}
			}
		} else {
			for (Garage g : getParticG()) {
				for (Car gc : g.getOneCar()) {
					this.particC.add((Car) gc.clone());
				}
			}

		}
		}else {
			throw new Exception("Error Participants is define");
			
		}
		
		} catch (Exception e) {
			System.err.println("ERRR::RACE::TAKEPARTC" + getCabecera() + e.getMessage());
		}
		
			return getParticC();
		
		
	}
	
	
	protected void takePoints(ArrayList<Car> resRace) {
		try {
			// ORDENO
			int error = resRace.size(); // control for not duplications
			try {
				Collections.sort(resRace);
				ArrayList<Car> listTBlank = new ArrayList<Car>();
				for (Car c : resRace) {
					if (c.getDistance() > 0) {

					} else {
						listTBlank.add((Car) c.clone());
					}
				}
				for (Car cblank : listTBlank) {
					cblank.setDistance(0);
					cblank.setPoints(0);
				}
				Collections.sort(listTBlank);
				this.particC = listTBlank;

			} catch (Exception e) {
				e.printStackTrace();
			}

			// PUNTUO
			for (int i = 0; i < resRace.size(); i++) {
				switch (i) {
				case 0:
					resRace.get(i).setPoints(Controler.POINTS_FIRSTS);
					break;
				case 1:
					resRace.get(i).setPoints(Controler.POINTS_SECOND);
					break;
				case 2:
					resRace.get(i).setPoints(Controler.POINTS_THIRD);
					break;
				default:
					resRace.get(i).setPoints(Controler.POINTS_DEFAULT);

				}
			}

			ArrayList<Car> superListC = getParticC();
			for (Car car : resRace) {
				if (superListC.contains(car)) {

				} else {
					if (car.getDistance() != 0) {
						superListC.add(car);
					}
				}
			}

			if (superListC.size() != error) {
				throw new Exception(
						"takePoints :: malformed list, size increments" + error + "-->" + superListC.size());
			}
			Collections.sort(superListC);

			setResultC(superListC);

			setPointsC(superListC);
		} catch (Exception e) {
			System.err.println("ERRR::RACE::TAKEPOINTS" + getCabecera() + e.getMessage());
		}
	}

	// ------------------------------------------------ PRINT
	// ----------------------------------------------------------------------//

	protected void print(String mnj) {
		if (Utilss.printInConsole()) {
			if (Utilss.printInConsole()) {
				System.out.println(mnj);
			} else {
				Utilss.printONFile(mnj, new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt"));
			}
		}
	}

	protected void printList(ArrayList<?> l) {
		if (Utilss.printInConsole()) {
			if (Utilss.printInConsole()) {
				l.forEach((a) -> System.out.println("\n" + a + "\n"));
			} else {
				l.forEach((a) -> Utilss.printONFile("\n" + a + "\n",
						new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt")));
			}
		}
	}
	protected void printList(ArrayList<?> l,String cabecera) {
		if (Utilss.printInConsole()) {
			if (Utilss.printInConsole()) {
				l.forEach((a) -> System.out.println("\n"+cabecera + a + "\n"));
			} else {
				l.forEach((a) -> Utilss.printONFile("\n" +cabecera+ a + "\n",
						new File(Controler.getR_LOG() + getCabecera() + getCabeceraT() + ".txt")));
			}
		}
	}

	private void printList(ArrayList<?> l, String cabecera,String operation ,String destination ) {

		if (Utilss.printInConsole()) {
			System.out.println("[[[[[	=	=	=	"+operation+"	=	=	=	]]]]");
			System.out.println(cabecera);
			l.forEach((a) -> System.out.println("\n			"  +getCabecera()+ getCabeceraT() + a + "\n"));
		} else {
			File fname= new File(destination + getCabecera()+ getCabeceraT() +operation +".txt");
			Utilss.printONFile("[[[[[	=	=	=	"+operation+"	=	=	=	]]]]",fname);
			Utilss.printONFile(cabecera, fname);
			l.forEach((a) -> Utilss.printONFile("\n			"  +getCabecera()+ getCabeceraT() + a + "\n",
					fname));
		}
	
}

//	--------------------------------------------[PRINT ]RACE_RESULTS	
// ----------------------------------------------------------------------//

	public void printINFO()  {
		try {
		if(getParticC().size()>0) {
			printParticC();
		}
		
		if(getResultC().size()>0) {
			printResultC();
		}

		} catch (Exception e) {
			System.err.println("ERRR::RACE::PRINTINFO" + getCabecera() + e.getMessage());
		}
		
	}
	
	private void printParticC() throws Exception {

		String a = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PARTICIPANTS	+ getCabecera() + getCabeceraT() +	!	!	!	!	!	"
				+ "\\n!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		printList((ArrayList<Car>) getResultC(), a,"PRINT_RACE_PARTICIPANTS",Controler.getR_PARTIC());
	}
	private void printResultC() throws Exception {
		
		String a = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTS	+ getCabecera() + getCabeceraT() +	!	!	!	!	!	"
				+ "\\n!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		printList((ArrayList<Car>) getResultC(), a,"PRINT_RACE_RESULTS",Controler.getR_PARTIC());
	}


	// ------ GET/SET [PARTICIPE,RESULT,POINTS] --> {C CAR G GARAGE}

	public ArrayList<Garage> getParticG() {
		return particG;
	}

	protected void setParticG(List<Garage> list) {
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

	// ----------------------------------------------------BASICS 
	// ----------------------------------------------------------------------//

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
