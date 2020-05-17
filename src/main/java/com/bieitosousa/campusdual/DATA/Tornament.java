package com.bieitosousa.campusdual.DATA;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.bieitosousa.campusdual.UTILS.Controler;
import com.bieitosousa.campusdual.UTILS.JSON;
import com.bieitosousa.campusdual.UTILS.Utilss;

public class Tornament {

	ArrayList<Race> listTornRace = new ArrayList<>();
	String name;
	String cabeceraT ;

	public Tornament(String name, ArrayList<Race> listTornRace) throws Exception {
		this.listTornRace = listTornRace;
		this.name = name;
		this.cabeceraT="Tornament[" + this.name + "]";
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

	public void start() {
		int cc= 0;
		for (Race r : listTornRace) {
			cc++;
			try {
				r.makeRace();
				if (Controler.isRACE_RESULT()) {
					r.printResultGrupByCar(cc);
				}
				if (Controler.isRACE_EXP()) {
					r.exportRace(cc);
				}
			} catch (Exception e) {
				System.err.println("Error al ejecutar la carrera " + r.cabeceraR + this.cabeceraT + e.getMessage());
			}
		}
		if (Controler.isTORN_RESULT()) {
			this.printResultGrupByCar();
		}
		if (Controler.isTORN_EXP()) {
			this.exportTorn();
			;
		}

	}

	public static List<Car> OrderCarAsPoints(List<Car> listCar) throws Exception {
		int cuentaintercambios = 0;
		Car[] arrayCars = new Car[listCar.size()];
		listCar.toArray(arrayCars);
		// Usamos un bucle anidado, saldra cuando este ordenado el array
		for (boolean ordenado = false; !ordenado;) {
			for (int i = 0; i < arrayCars.length - 1; i++) {
				if (arrayCars[i].getPoints() > arrayCars[i + 1].getPoints()) {
					// Intercambiamos valores
					int vAux = arrayCars[i].getPoints();
					arrayCars[i].setPoints(arrayCars[i + 1].getPoints());
					arrayCars[i + 1].setPoints(vAux);
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

	public ArrayList<Garage> getParticG() {
		HashSet<Garage> hashGarage = new HashSet<Garage>();
		for (Race r : listTornRace) {
			for (Garage g : r.getParticG()) {
				hashGarage.add(g);
			}

		}
		for (Race r : listTornRace) {
			for (Garage g : r.getParticG()) {
				for (Car c : g.getAllCar()) {
					for (Garage hg : hashGarage) {
						for (Car hc : hg.listGCar)
							if (hc.equals(c)) {
								hc.setDistance(hc.getDistance() + c.getDistance());
								hc.setPoints(hc.getPoints() + c.getPoints());
							}
					}
				}
			}

		}
		ArrayList<Garage> list = new ArrayList<Garage>(hashGarage);
		return list;
	}

	public List<Car> getParticC() throws Exception {
		HashSet<Car> hashCar = new HashSet<Car>();
		for (Race r : listTornRace) {
			for (Car c : r.getParticC()) {
				hashCar.add(c);
			}
		}
		for (Race r : listTornRace) {
			for (Car c : r.getParticC()) {
				for (Car hc : hashCar) {
					if (hc.equals(c)) {
						hc.setDistance(hc.getDistance() + c.getDistance());
						hc.setPoints(hc.getPoints() + c.getPoints());
					}
				}
			}
		}

		ArrayList<Car> list = new ArrayList<Car>(hashCar);
		return list;
	}

//	=	=	=	=	[PRINT ]RACE_CLASIFICATION	=	=	=	=
	public void printResT(String mnj) {
		if (Controler.isCONSOLE_PRINT_TORNAMENT_CLASSIFICATION()) {
			System.out.println(mnj);
		} else {
			Utilss.printONFile(mnj, new File(Controler.getT_RESULT() + this.cabeceraT+".txt"));
		}
	}

	public void printResultGrupByGarage() throws Exception {
		ArrayList<Car> listCarInGarage = new ArrayList<>();

		printResT("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY GARAGE	!	!	!	!	!	"
				+ "\n	!	!	"+this.cabeceraT+"	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		for (Garage g : this.getParticG()) {
			printResT("	=	=	=	GARAGE	[[	" + g.name + "	]]	=	=	=	=	=");

			for (Car c : g.listGCar) {
				if (c.getDistance() != 0) {
					listCarInGarage.add(c);
				}
			}
			listCarInGarage = (ArrayList<Car>) Race.OrderCarAsPosition(listCarInGarage);
			listCarInGarage = (ArrayList<Car>) OrderCarAsPoints(listCarInGarage);
			for (Car cInGarage : listCarInGarage) {
				printResT("	=			" + g.name + "	[[ " + cInGarage + "]]");
			}
			listCarInGarage.clear();
		} // Fin Garage

	}

	public void printResultGrupByCar() {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		printResT("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY CAR	!	!	!	!	!	"
				+ "\n	!	!	"+this.cabeceraT+"	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		for (Garage g : this.getParticG()) {

			for (Car c : g.listGCar) {
				if (c.getDistance() != 0) {
					listCarInGarage.add(c);
				}
			}

		} // Fin Garage
		try {
			listCarInGarage = (ArrayList<Car>) Race.OrderCarAsPosition(listCarInGarage);
			listCarInGarage = (ArrayList<Car>) OrderCarAsPoints(listCarInGarage);
		} catch (Exception e) {
			System.err.println("Error al ordenar los coches por posicion y distancia" + e.getMessage());

		}
		int cc = 0;
		for (Car cInGarage : listCarInGarage) {
			printResT("[" + (cc++) + "]	=	=	=	=	[[ " + cInGarage + "]]");
		}
		listCarInGarage.clear();
	}

	public void exportTorn() {
		try {
			File f = new File(Controler.getPRIVATE_TORNAMENT());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
			name = cabeceraT + date.getTime();
			File fname = new File(Controler.getT_EXP() + name);
			JSON.WriteObjJsonInFile(fname, this);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraT + e.getMessage());
		}
	}

	public void exportTorn(String cabecera) {
		try {
			File f = new File(Controler.getPRIVATE_TORNAMENT());
			if (!f.exists()) {
				f.mkdirs();
			}
			Date date = new Date();
			name = cabeceraT + cabecera + date.getTime();
			File fname = new File(Controler.getT_EXP() + name);
			JSON.WriteObjJsonInFile(fname, this);
		} catch (Exception e) {
			System.err.println("Error al exportar " + this.cabeceraT + e.getMessage());
		}
	}

}
