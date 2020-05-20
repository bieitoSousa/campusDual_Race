/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import java.util.*;

/**
 *
 * @author BIETO
 */

// ===================================================//
//  =   Class  Race_eliminate
//  =   you can create race to eliminate car
// ===================================================//

public class Race_Elimination extends Race {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Car> listCarParticipe =null;
	private String msjCoche = "";

	public Race_Elimination(String name, int type) throws Exception {
		super(name, type, "RACE_ELIMINATE_[" + name + "]");
		try {
		ArrayList<Garage> ListGCarCopy =new ArrayList<>();
		
		
		//Collections.copy(ListGCarCopy, ListGCar);
		// Filtramos
		if (type != 1) {
			throw new Exception(" Type erroneo no se puede crear la clase Race_Elimination");
		}
		if (ListGCarCopy.size() == 0) {
			throw new Exception(
					" La lista de garajes no contiene ningun valor, no se puede crear la clase Race_Elimination");
		}
		this.listCarParticipe = new ArrayList<>();

	}catch(Exception e) {
		System.err.println(	"ERRR::RACE_ELIMINATE::CONSTRUCTOR "+this.name+e.getMessage());
		}
		
	}

	// generate a eliminate race cars
	@Override
	public void makeRace() throws Exception {
		try {
		for(Car c: takePartC()) {
			this.listCarParticipe.add((Car)c.clone());
		}
		}catch (Exception e) {
			System.err.println(
					" EEROR::RACE_ELIMINA::MAKE_RACE unloaded participants"+e.getMessage()
					);
		}

		// ================================================//
		// A = = = = Pre carrera = = = = = =
		// ================================================//
		// [A1] = = = Definimos Variables locales = = = =
		// lap , MaxLap
		int lap = 0; // inicio en vuelta 0
		int maxLap = listCarParticipe.size() - 1; // maximas vueltas
		int[] lapDistances = new int[maxLap + 1]; // distancia por vuelta
		int lapTime = 60; // cada vuelta dura 60 time
		int lapActive = 0; // numero de vuelta hasta que pase el ultimo coche por meta
		ArrayList<Car> listRace = new ArrayList<>(this.listCarParticipe);// listRace -> tratamineto de la carrera
		ArrayList<Car> listResult = new ArrayList<>();// listResult -> tratar de la clasificacion
		// [A2] = = = Definimos parametros de los coches = = = =

		for (Car c : listRace) {// speed =0 // distance = 0
			c.setSpeed(0);
			c.setDistance(0);
		}
		// ================================================//
		// B = = = = Carrera = = = = = =
		// ================================================//
		print("\n	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=");
		print("	=	=	=	=	=	" + getCabeceraT() + getCabecera() + "	=	=	=	=	=	=	=");
		print("	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=\n");
		// [B1] = = = Definimos Fin de Carrera = = = =
		// cuando se terminen las vueltas
		for (int time = 1; (maxLap != lap); time++) {
			// [B2] = = = Modificamos los parametros de los coches = = = =
			// coches frena o acelera , update distance y speed
			listRace = runCar(listRace);
			// [B3] = = = Ordenamos los coches por distancia = = = =
			  Collections.sort(listRace);
			// [B4] = = = Actualizamos las vueltas , generamos ditancia de vuleta = = = =
			if (time == lapTime) {// = Vueltas ; cada vuelta dura --> lap = 60 t
				// Ditancia de vulta es la distancia que recorrio el 1 al pasar 60 t
				int dist = listRace.get(0).getDistance();
				lapDistances[lap] = dist;
				lap++; // Empieza la proxima vuelta
				lapTime += 60;
				lapActive = deleteCar(listRace, listResult, lapDistances, lapActive, time, maxLap);
			}
			// [B5] = = = Eliminamos el ultimo coche que pase por meta = = = =
			// Cuando un coche sea eliminado la ultima vuelta activa se actualiza
			// if (lapDistances.length == lap) {

		}

		// ================================================//
		// C = = = TRAS Carrera = = = = = =
		// ================================================//
		// introducimos el primer coche en la lista de resultados
		Car p = listRace.get(listRace.size() - 1);
		listResult.add(p);
		// metemos la lista de resultados en la de participantes
		this.listCarParticipe = listResult;
		ArrayList<Car> RaceEliminateResults = new ArrayList<>();
		for (Car cr: listResult) {
			RaceEliminateResults.add((Car) cr.clone());
		}
		takePoints(RaceEliminateResults);
	}// FinmakeRace

	private int deleteCar(ArrayList<Car> listRace, ArrayList<Car> listResult, int[] lapDistances, int lapActive,
			int time, int maxLap) {
		// Eliminar coche
		// Elimino el coche cuando el ultimo coche tenga mas distancia
		// que la distancia de la vuelta
//		int distanciaVuelta = lapDistances[lapActive];
//		int distanciaUltimoCoche= listRace.get(listRace.size() - 1).getDistance();
//		if (distanciaUltimoCoche > distanciaVuelta) {
		// añadimos el coche eliminado en la ultima posicion activa
		// del array resultados
		Car rcc = listRace.get(listRace.size() - 1);
		listResult.add(rcc);
		// array1[array2.lenght -1] = array2[array1.lenght -1]
		// listResult.get(listRace.size() - 1) = listRace.get(listRace.size() - 1);
		// org.apache.commons.lang.ArrayUtils;
		// Elimino el coche
		listRace.remove(listRace.size() - 1);
		// paso el ultimo coche se focaliza la siguente vuelta
		print("\n#	lap [" + (lapActive + 1) + "]	" + "	#	#	" + "#	Time [" + time + " minutes]	"
				+ "#	duration [" + (maxLap + 1) + " lap]	" + "	#	#	\n\n");
		msjCoche = getCabeceraT() + getCabecera() + "		= RA:" + this.name + " Lap" + (lapActive + 1) + " Car =>>";
		String msjCocheEliminado = "	ELIMINADO	= RA:" + this.name + " Car =>>";
		printList(listResult, msjCocheEliminado);
		printList(listRace, msjCoche);
		lapActive++;

		return lapActive;
	}

	private ArrayList<Car> runCar(ArrayList<Car> listRace) {
		// ======= Cada Time abanzan los coches entre 1 y 5 =======//
		for (Car cc : listRace) {
			if (Math.random() > 0.5f && cc.getSpeed() < cc.getMAXSPEED()) {
				int a = cc.getSpeed();
				a++;
				cc.setSpeed(a);
			} else {
				if (cc.getSpeed() != 0) {
					int b = cc.getSpeed();
					b--;
					cc.setSpeed(b--);
				}
			}
			int v = cc.getSpeed() + cc.getDistance();
			cc.setDistance(v);
		}
		return listRace;
	}

	public int getType() {
		return type;
	}

	public ArrayList<Car> getListCarParticipe() {
		ArrayList<Car> a = new ArrayList<>();
		try {
			a = (ArrayList<Car>) getParticC();
			if (!(a.size() > 0)) {
				throw new Exception(" Lista mal creada");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	public void setListCarParticipe(ArrayList<Car> listCarParticipe) {
		this.listCarParticipe = listCarParticipe;
	}

}
