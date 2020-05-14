/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import com.bieitosousa.campusdual.UTILS.JSON;
import com.bieitosousa.campusdual.UTILS.Utilss.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

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

// ===================================================//
//  =   Class to create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
	public Race(String name, int type, ArrayList<Garage> listGarageG
	// all Cars on the garage participate true
	// one Car on the garage participate false
	) {
		this.name = name;
		this.type = type;
		this.ParticG = listGarageG;

	}

	abstract public void makeRace() throws Exception;// FinmakeRace

	protected List<Car> OrderCarAsPosition(List<Car> listCar) throws Exception {
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

	public void printList(ArrayList<?> l) {
		l.forEach((a) -> System.out.println(a));
	}

	public void printList(ArrayList<?> l, String cabecera) {
		l.forEach((a) -> System.out.println(cabecera + a));
	}

	public void printResultGrupByGarage() throws Exception {
		ArrayList<Car> listCarInGarage = new ArrayList<>();

		System.out.println("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
				+ "\n	!	!	RESULTADOS	GRUP BY GARAGE	!	!	!	!	!	"
				+ "\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	");
		for (Garage g : ParticG) {
			System.out.println("	=	=	=	GARAGE	[[	" + g.name + "	]]	=	=	=	=	=");

			for (Car c : g.listGCar) {
				if (c.getDistance() != 0) {
					listCarInGarage.add(c);
				}
			}
			OrderCarAsPosition(listCarInGarage);
			for (Car cInGarage : listCarInGarage) {
				System.out.println("	=			" + g.name + "	[[ " + cInGarage + "]]");
			}
			listCarInGarage.clear();
		} // Fin Garage

	}

	public void printResultGrupByCar() throws Exception {
		ArrayList<Car> listCarInGarage = new ArrayList<>();
		System.out.println("\n	!	!	!	!	!	!	!	!	!	!	!	!	!	!	!	"
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
			System.out.println("[" + (cc++) + "]	=	=	=	=	[[ " + cInGarage + "]]");
		}
		listCarInGarage.clear();
	}

	public boolean exportRace() {
		File f = new File(".//export//race//");
		if (!f.exists()) {
			f.mkdirs();
		}
		Date date = new Date();
		name = "race_" + this.name + date.getTime();
		File fname = new File(".//export//race//" + name);
		return JSON.WriteObjJsonInFile(fname, this);
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

	public void setParticG(List<Garage> partic) {
		this.ParticG.clear();
		for (Garage g : partic) {
			this.ParticG.add(g);
		}
	}

}// End race.class
