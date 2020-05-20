package com.bieitosousa.campusdual.DATA;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import com.bieitosousa.campusdual.UTILS.*;

public class Tornament implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	ArrayList<Race> listTornRace = new ArrayList<>();
	ArrayList<Car> tornResultC = new ArrayList<>();
	ArrayList<Car[]> tornPointsC = new ArrayList<>();
	ArrayList<Car> tornPartiC = new ArrayList<>();
	ArrayList<Car[]> tornRaceR = new ArrayList<>();

	String name;
	String cabeceraT;

	public Tornament(String name, ArrayList<Race> listTornRace) throws Exception {
		this.listTornRace = listTornRace;
		this.name = name;
		this.cabeceraT = "Tornament[" + this.name + "]";
		int re = 0, rs = 0;
		for (Race r : listTornRace) {
			if (r instanceof Race_Standar) {
				r.setCabeceraT(getCabeceraT());
				re++;
			}

			else if (r instanceof Race_Elimination) {
				r.setCabeceraT(getCabeceraT());
				rs++;
			}
		}
		if (re != listTornRace.size() && rs != listTornRace.size()) {
			throw new Exception("Error al instanciar el torneo los elementos deben de pertenecer"
					+ " a Race_Elimination o Race_Standar");
		}
		if (listTornRace.size() > 10) {
			throw new Exception(
					"Error al instanciar el torneo : " + "no se pueden crear torneos de mas de 10 carreras");
		}
	}


	// ------------------------------------ BASIC OPERATIONS ADD / DELETE --> G Garage R Race
	// ----------------------------------------------------------------------//
	
	public void addG(){}
	public void delG(){}
	
	public void addR(){}
	public void delR(){}
	
	
	
	public void start() {
		try {
			for (Race r : listTornRace) {
				try {
					r.makeRace();
					Car[] array = new Car[r.getPointsC().size()];

					this.tornRaceR.add(r.getPointsC().toArray(array));

				} catch (Exception e) {
					System.err.println(
							"Error al ejecutar la carrera " + r.getCabecera() + getCabeceraT() + e.getMessage());
				}
			}

			this.takePoints();
		} catch (Exception e) {
			System.err.println("ERROR::TORNAMENT::START" + e.getMessage());

		}

	}

	public void takePoints() throws Exception {
		ArrayList<Car> resHasH = new ArrayList<Car>();
		ArrayList<Car> listTResult = new ArrayList<Car>();
		for (Race r : listTornRace) {
			listTResult.addAll((ArrayList<Car>) r.getResultC());
		}
		for (Race r : listTornRace) {
			for (Car c : r.getResultC()) {
				if (resHasH.contains(c)) {

				} else {
					resHasH.add(c);
				}
			}
		}

		// limpio distance and points in hshCar

		ArrayList<Car> resHasHBlank = new ArrayList<>();
		// Collections.copy(resHasHBlank, resHasH);
		// List<Car>resHasHBlank=resHasH.stream().collect(Collectors.toList());
		for (Car c : resHasH) {
			resHasHBlank.add((Car) c.clone());
		}
		for (Car car : resHasHBlank) {
			car.setDistance(0);
			car.setPoints(0);
			this.tornPartiC.add((Car) car.clone());
		}
		Collections.sort(this.tornPartiC);

		for (Car hc : resHasHBlank) {
			for (Car cAll : listTResult) {
				if (hc.equals(cAll)) {
					hc.setDistance(hc.getDistance() + cAll.getDistance());
					hc.setPoints(hc.getPoints() + cAll.getPoints());
				}
			}
		}
		// ArrayList<Car> list = new ArrayList<Car>(hashCar);
		Collections.sort(resHasHBlank);

		for (Car car : resHasHBlank) {
			this.tornResultC.add((Car) car.clone());
		}
		Collections.sort(this.tornResultC);

		for (int i = 0; i != 3; i++) {
			ArrayList<Car> aux = new ArrayList<>();
			for (Car car : resHasHBlank) {
				if (car.getPoints() == resHasHBlank.get(0).getPoints()) {
					aux.add((Car) car.clone());
				}
			}
			resHasHBlank.removeAll(aux);
			Car[] acar = new Car[aux.size()];
			this.tornPointsC.add((Car[]) aux.toArray(acar));
		}
	}

	// -------------------- GET/SET ----------------------------------//

	public ArrayList<Race> getListTornRace() {
		return listTornRace;
	}

	public void setListTornRace(ArrayList<Race> listTornRace) {
		this.listTornRace = listTornRace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCabeceraT() {
		return cabeceraT;
	}

	public void setCabeceraT(String cabeceraT) {
		this.cabeceraT = cabeceraT;
	}

	public ArrayList<Car> getTornResultC() {
		return tornResultC;
	}

	public void setTornResultC(ArrayList<Car> tornResultC) {
		this.tornResultC = tornResultC;
	}

	public ArrayList<Car[]> getTornPointsC() {
		return tornPointsC;
	}

	public void setTornPointsC(ArrayList<Car[]> tornPointsC) {
		this.tornPointsC = tornPointsC;
	}

	public ArrayList<Car> getTornPartiC() {
		return tornPartiC;
	}

	public void setTornPartiC(ArrayList<Car> tornPartiC) {
		this.tornPartiC = tornPartiC;
	}

	public ArrayList<Car[]> getTornRaceR() {
		return tornRaceR;
	}

	public void setTornRaceR(ArrayList<Car[]> tornRaceR) {
		this.tornRaceR = tornRaceR;
	}

//	--------------------------------------------[PRINT ]RACE_RESULTS	
// ----------------------------------------------------------------------//
	
	public void printINFO()  {
		try {
		if(getTornPartiC().size()>0) {
			printParticT();
		}
		
		if(getTornResultC().size()>0) {
			printResultT();
		}
		if(getTornPointsC().get(0).length>0) {
			printPodiumT();
		}

		} catch (Exception e) {
			System.err.println("ERRR::RACE::PRINTINFO" + getCabeceraT() + e.getMessage());
		}
		
	}
	
	
	
	
	private void printList(ArrayList<?> l, String cabecera,String operation ,String destination ) {

			if (Utilss.printInConsole()) {
				System.out.println("[[[[[	=	=	=	"+operation+"	=	=	=	]]]]");
				System.out.println(cabecera);
				l.forEach((a) -> System.out.println("\n			"  + getCabeceraT() + a + "\n"));
			} else {
				File fname= new File(destination  + getCabeceraT() +operation +".txt");
				Utilss.printONFile("[[[[[	=	=	=	"+operation+"	=	=	=	]]]]",fname);
				Utilss.printONFile(cabecera, fname);
				l.forEach((a) -> Utilss.printONFile("\n			"  + getCabeceraT() + a + "\n",
						fname));
			}
		
	}

	private void printARRAY(Car[] o, String cabecera,String operation ,String destination) {
		if (Utilss.printInConsole()) {
			System.out.println("[[[[[	=	=	=	"+operation+"	=	=	=	]]]]");
			System.out.println(cabecera);
			for (Car c : o) {
				System.out.println("\n			" + getCabeceraT() + c + "\n");
			}
		} else {
			File fname= new File(destination  + getCabeceraT() +operation +".txt");
			Utilss.printONFile("[[[[[	=	=	=	"+operation+"	=	=	=	]]]]", fname);
			Utilss.printONFile(cabecera, fname);
			for (Car c : o) {
				Utilss.printONFile("\n			" + getCabeceraT() + c + "\n",fname	);
			}
		}

	}
	
	
	private void printParticT() {
		String a = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	TORNAMENT_PARTICIPANTS	" + getCabeceraT() + "!	!	!	!	!	" + "\n	!	!	"
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";

		printList(getTornResultC(), a,"TORNAMENT_PARTICIPANTS",Controler.getT_PARTIC());
		
	}

	private void printResultT() {

		String a = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	TORNAMENT_RESULTS	" + getCabeceraT() + "!	!	!	!	!	" + "\n	!	!	"
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";

		printList(getTornResultC(), a,"TORNAMENT_RESULTS",Controler.getT_RESULT());

	}

	private void printPodiumT() {

		String first = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM_FIRST_PLACE !	!	!	!	!	" + "\n	!	!	"
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String second = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM_SECOND_PLACE !	!	!	!	!	" + "\n	!	!	"
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String third = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM_THIRD_PLACE !	!	!	!	!	" + "\n	!	!	"
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		
		printARRAY(getTornPointsC().get(0), first,"PODIUM_FIRST_PLACE",Controler.getT_PODIUM());
		printARRAY(getTornPointsC().get(1), second,"PODIUM_SECOND_PLACE",Controler.getT_PODIUM());
		printARRAY(getTornPointsC().get(2), third,"PODIUM_THIRD_PLACE",Controler.getT_PODIUM());

	}

}// END
