package com.bieitosousa.campusdual.DATA;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.bieitosousa.campusdual.UTILS.*;

public class Tornament {

	ArrayList<Race> listTornRace = new ArrayList<>();
	String name;
	String cabeceraT;

	public Tornament(String name, ArrayList<Race> listTornRace) throws Exception {
		this.listTornRace = listTornRace;
		this.name = name;
		this.cabeceraT = "Tornament[" + this.name + "]";
		int re = 0, rs = 0;
		for (Race r : listTornRace) {
			if (r instanceof Race_Standar) {
				r.setCabeceraT("Tornament[" + this.name + "]");
				re++;
			}

			else if (r instanceof Race_Elimination) {
				r.setCabeceraT("Tornament[" + this.name + "]");
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



	public void start() {
		int cc = 0;
		for (Race r : listTornRace) {
			cc++;
			try {
				r.makeRace();
				if (Controler.isRACE_RESULT()) {
					r.printResultC(cc);
				}
				if (Controler.isRACE_EXP()) {
					r.exportRace(cc);
				}
			} catch (Exception e) {
				System.err.println("Error al ejecutar la carrera " + r.cabeceraR + this.cabeceraT + e.getMessage());
			}
		}
		if (Controler.isTORN_RESULT()) {
			try {
				this.printResultC();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		if (Controler.isTORN_EXP()) {
			this.exporTorn();
			;
		}

	}

	public static List<Car> OrderCarAsPoints(List<Car> listofCar) { // this method sorts the lists of participants by
																	// the traveled distance
		Car[] arrayCars = new Car[listofCar.size()];
		listofCar.toArray(arrayCars);
		listofCar.clear();
		for (int i = 0; i < arrayCars.length; i++) {
			for (int j = i; j < arrayCars.length; j++) {
				if (arrayCars[i].getPoints() < arrayCars[j].getPoints()) {
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



	public List<Car> getParticC() throws Exception {
		ArrayList<Car> resultC = new ArrayList<>();
		ArrayList<Car> resultCCopy = new ArrayList<>();
		for (Race r : getListTornRace()) {
			for (Car car : r.getParticC()) {
				if (resultC.contains(car)) {
				}else {
					resultC.add(car);
				}
			}
		}
		Collections.copy(resultCCopy,resultC);
		return resultCCopy;
	}


	public List<Car> getResultC() throws Exception {
		
		ArrayList<Car> ParticipantsCopy = new ArrayList<Car>();
		ArrayList<Car> listTResult = new ArrayList<Car>();
		
		// limpio distance and points in hshCar
		Collections.copy(ParticipantsCopy,getParticC());
		for(Car car : ParticipantsCopy ) {
			car.setDistance(0);
			car.setPoints(0);
		}
		for (Race rr : listTornRace) {
				for (Car cc : rr.getResultC()) {
					listTResult.add(cc);// all_Car
				}
		}
		for (Car hc : ParticipantsCopy) {
			for (Car cAll : listTResult) {
				if (hc.equals(cAll)) {
					hc.setDistance(hc.getDistance() + cAll.getDistance());
					hc.setPoints(hc.getPoints() + cAll.getPoints());
				}
			}
		}
		//ArrayList<Car> list = new ArrayList<Car>(hashCar);
		Race.printListHelp(ParticipantsCopy, this.cabeceraT + "hashSET");
		return ParticipantsCopy;
	}
	
	
	public HashMap<String, ArrayList<Car>> getPodiumC() throws Exception {
		ArrayList<Car> primeroL = new ArrayList<>();
		ArrayList<Car> segundoL = new ArrayList<>();
		ArrayList<Car> terceroL = new ArrayList<>();
		primeroL.add(getResultC().get(0));
		int cnt = 1;
		int maxcnt = 0;
		for (Car car : getResultC()) {
			if (primeroL.get(0).getPoints() == getResultC().get(cnt).getPoints()) {
				primeroL.add(getResultC().get(cnt));
				maxcnt = cnt;
			}
		}
		segundoL.add(getResultC().get(maxcnt++));
		int cnt2 = maxcnt++;
		for (Car car : getResultC()) {
			if (segundoL.get(0).getPoints() == getResultC().get(cnt2).getPoints()) {
				segundoL.add(getResultC().get(cnt2));
				maxcnt = cnt;
			}
		}
		terceroL.add(getResultC().get(maxcnt++));
		int cnt3 = maxcnt++;
		for (Car car : getResultC()) {
			if (segundoL.get(0).getPoints() == getResultC().get(cnt3).getPoints()) {
				segundoL.add(getResultC().get(cnt3));
				maxcnt = cnt;
			}
		}

		HashMap<String, ArrayList<Car>> podium = new HashMap<>();
		podium.put("primero", primeroL);
		podium.put("segundo", segundoL);
		podium.put("tercero", terceroL);
		return podium;
	}

//	=	=	=	=	[PRINT ]RACE_CLASIFICATION	=	=	=	=
	public void printResT(String mnj) {
		if (Controler.isCONSOLE_PRINT_TORNAMENT_CLASSIFICATION()) {
			System.out.println(mnj);
		} else {
			Utilss.printONFile(mnj, new File(Controler.getT_RESULT() + this.cabeceraT + ".txt"));
		}
	}

	

	public void printResultC() {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		printResT("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	!	!	!	!	!	" + "\n	!	!	" + this.cabeceraT
				+ "	!	!	!	!	!	" + "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		try {
			listCarInGarage = (ArrayList<Car>) getParticC();
		} catch (Exception e) {
		}
		Race.printListHelp(listCarInGarage, this.cabeceraT + "preTodo");
		try {
			listCarInGarage = (ArrayList<Car>) Race.OrderCarAsPosition(listCarInGarage);
			Race.printListHelp(listCarInGarage, this.cabeceraT + "ORDENPOSICION");
			listCarInGarage = (ArrayList<Car>) OrderCarAsPoints(listCarInGarage);
			Race.printListHelp(listCarInGarage, this.cabeceraT + "ORDENPOINTS");
		} catch (Exception e) {
			System.err.println("Error al ordenar los coches por posicion y distancia" + e.getMessage());

		}
		int cc = 0;
		for (Car cInGarage : listCarInGarage) {
			printResT("\n[" + (cc++) + "]	=	=	=	=	[[ " + cInGarage + "]]");
		}
		
	}

	public void ExportList(ArrayList<?> l, String cabecera) {
		try {
			File f = new File(Controler.getPRIVATE_TORNAMENT());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
			name = "race_" + this.name + this.cabeceraT + date.getTime() + ".txt";
			File fname = new File(Controler.getT_EXP() + name);
			Utilss.printONFile(cabecera, fname);
			l.forEach((a) -> Utilss.printONFile("\n					= 	=	=	=	=	" + a + "\n", fname));
			System.out.println("is export the race in ::" + fname);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraT + e.getMessage());
		}
	}

	public void exporTorn() {
		String cabeceraPart = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PARTICIPANTES 	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraResult = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS 	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraPodium = "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM 	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";

		try {
			ExportList((ArrayList<Car>) getParticC(), cabeceraPart);
			ExportList((ArrayList<Car>) getResultC(), cabeceraResult);
			ExportList((ArrayList<Car>) getPodiumC().get("primero"), cabeceraPodium);
			ExportList((ArrayList<Car>) getPodiumC().get("segundo"), cabeceraPodium);
			ExportList((ArrayList<Car>) getPodiumC().get("tercero"), cabeceraPodium);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraT + e.getMessage());
		}
	}

	public void exportTorn(int i) {
		String cabeceraPart = "\n	[" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i
				+ "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "]"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PARTICIPANTES 	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraResult = "\n	[" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i
				+ "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "]"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS 	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";
		String cabeceraPodium = "\n	[" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "][" + i
				+ "][" + i + "][" + i + "][" + i + "][" + i + "][" + i + "]"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	PODIUM 	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	";

		try {
			ExportList((ArrayList<Car>) getParticC(), cabeceraPart);
			ExportList((ArrayList<Car>) getResultC(), cabeceraResult);
			ExportList((ArrayList<Car>) getPodiumC().get("primero"), cabeceraPodium);
			ExportList((ArrayList<Car>) getPodiumC().get("segundo"), cabeceraPodium);
			ExportList((ArrayList<Car>) getPodiumC().get("tercero"), cabeceraPodium);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraT + e.getMessage());
		}
	}

}
