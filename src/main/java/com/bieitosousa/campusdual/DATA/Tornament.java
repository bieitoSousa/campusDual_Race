package com.bieitosousa.campusdual.DATA;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
	ArrayList<Car>tornResultC = new ArrayList<>();
	ArrayList<Car[]>tornPointsC = new ArrayList<>();
	ArrayList<Car>tornPartiC = new ArrayList<>();
	ArrayList<Car[]>tornRaceR = new ArrayList<>();
	
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



	public void start() {
		try {
		int cc = 0;
		for (Race r : listTornRace) {
			try {
				r.makeRace();
				Car[] array= new Car[r.getPointsC().size()];
						
				this.tornRaceR.add(r.getPointsC().toArray(array));
				
			} catch (Exception e) {
				System.err.println("Error al ejecutar la carrera " + r.getCabecera() + getCabeceraT() + e.getMessage());
			}
		}
		
		this.takePoints();
		}catch(Exception e) {
			System.err.println("ERROR::TORNAMENT::START"+e.getMessage());
			
		}

	}

	


	public List<Car> getParticC() throws Exception {
		ArrayList<Car> resultC = new ArrayList<>();
		for (Race r : getListTornRace()) {
			for (Car car : r.getParticC()) {
				if (resultC.contains(car)) {
				}else {
					resultC.add(car);
				}
			}
		}
		return resultC;
	}


	public void takePoints() throws Exception {
		int ccnt=0;
		ArrayList<Car> resHasH = new ArrayList<Car>();
		ArrayList<Car> listTResult = new ArrayList<Car>();
		for(Race r : listTornRace) {
			listTResult.addAll((ArrayList<Car>)r.getResultC());
		}
		for(Race r : listTornRace) {
			for(Car c : r.getResultC()) {
				if (resHasH.contains(c)) {
					
				}else {
					resHasH.add(c);
				}
			}
		}

		// limpio distance and points in hshCar
		
		
		ArrayList<Car> resHasHBlank = new ArrayList<>();
		//Collections.copy(resHasHBlank, resHasH);
		//List<Car>resHasHBlank=resHasH.stream().collect(Collectors.toList());
		for(Car c : resHasH) {
			resHasHBlank.add((Car)c.clone());
		}
		for(Car car : resHasHBlank ) {
			car.setDistance(0);
			car.setPoints(0);
			this.tornPartiC.add((Car)car.clone());
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
		//ArrayList<Car> list = new ArrayList<Car>(hashCar);
		 Collections.sort(resHasHBlank);

		 for(Car car : resHasHBlank ) {
				this.tornResultC.add((Car)car.clone());
			}
			Collections.sort(this.tornResultC);
		 
		
			for(int i=0;i!=3;i++) {
				ArrayList<Car> aux = new ArrayList<>();
			 for(Car car : resHasHBlank ) {
				 if (car.getPoints()== resHasHBlank.get(0).getPoints() ){
					 aux.add((Car)car.clone());
				 }
				}
			 resHasHBlank.removeAll(aux);
			 Car[] acar = new Car [aux.size()];
			 this.tornPointsC.add((Car[]) aux.toArray(acar));
			}
	}
	
	

//	=	=	=	=	[PRINT ]RACE_CLASIFICATION	=	=	=	=
	public void printResT(String mnj) {
		if (Controler.isCONSOLE_PRINT_TORNAMENT_CLASSIFICATION()) {
			System.out.println(mnj);
		} else {
			Utilss.printONFile(mnj, new File(Controler.getT_RESULT() + getCabeceraT() + ".txt"));
		}
	}

	

	public void printResultC() {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		printResT("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	"+getCabeceraT()+"!	!	!	!	!	" + "\n	!	!	" 
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		try {
			listCarInGarage = (ArrayList<Car>) getTornResultC();
		} catch (Exception e) {
		}
		try {
			 Collections.sort(listCarInGarage);
		} catch (Exception e) {
			System.err.println("Error al ordenar los coches por posicion y distancia" + e.getMessage());

		}
		int cc = 0;
		for (Car cInGarage : listCarInGarage) {
			printResT("\n[" + (cc++) + "]	=	=	=	=	[[ " + cInGarage + "]]");
		}
		
	}



}
