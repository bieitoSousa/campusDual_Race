/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author BIETO
 */

// ===================================================//
//  =   Class  Race_eliminate
//  =   you can create race to eliminate car
// ===================================================//

public class Race_Elimination extends Race {

	private final int type = 1;
	private ArrayList<Car> listCarParticipe = null;

	public Race_Elimination(String name, int type, List<Garage> ListGCar, boolean isAllCarPasrticipe) throws Exception {
		super(name, type, ListGCar, isAllCarPasrticipe);
		if (type != 1) {
			throw new Exception(" Type erroneo no se puede crear la clase Race_Elimination");
		}
		// Definimos la lista de participantes
		if (isAllCarPasrticipe) {
			// Loop the gararges and take one the cart to participate
			for (Garage g : ListGCar) {
				this.listCarParticipe = ListGCar.get(ListGCar.indexOf(g)).getOneCar();
			}
		} else { // Loop the gararges and take all the cart to participate
			for (Garage g : ListGCar) {
				this.listCarParticipe = ListGCar.get(ListGCar.indexOf(g)).getAllCar();
			}
		}

	}

	// generate a eliminate race cars
	@Override
	protected void makeRace() throws Exception {

		// ================================================//
		// A = = = = Pre carrera = = = = = =
		// ================================================//
		// [A1] = = = Definimos Variables locales = = = =
		// lap , MaxLap
		int lap = 0; // inicio en vuelta 0
		int maxLap = partic.length - 1; // maximas vueltas
		int[] lapDistances = null; // distancia por vuelta
		int lapTime = 60; // cada vuelta dura 60 time
		int lapActive = 0; // numero de vuelta hasta que pase el ultimo coche por meta
		ArrayList<Car> listRace = listCarParticipe;// listRace -> tratamineto de la carrera
		ArrayList<Car> listResult = listCarParticipe;// listResult -> tratar de la clasificacion
		// [A2] = = = Definimos parametros de los coches = = = =

		for (Car c : listRace) {// speed =0 // distance = 0
			c.setSpeed(0);
			c.setDistance(0);
		}
		// ================================================//
		// B = = = = Carrera = = = = = =
		// ================================================//
		// [B1] = = = Definimos Fin de Carrera = = = =
		// cuando se terminen las vueltas
		for (int time = 1; (maxLap == lap); time++) {
			// [B2] = = = Modificamos los parametros de los coches = = = =
			// coches frena o acelera , update distance y speed
			runCar(listRace);
			// [B3] = = = Ordenamos los coches por distancia = = = =
			listRace = (ArrayList<Car>) OrderCarAsPosition(listRace);
			// [B4] = = = Actualizamos las vueltas , generamos ditancia de vuleta = = = =
			if (time == lapTime) {// = Vueltas ; cada vuelta dura --> lap = 60 t
				lapTime = +lapTime;
				// Ditancia de vulta es la distancia que recorrio el 1 al pasar 60 t
				lapDistances[lap] = listRace.get(0).getDistance();
				lap++; // Empieza la proxima vuelta
			}
			// [B5] = = = Eliminamos el ultimo coche que pase por meta = = = =
			// Cuando un coche sea eliminado la ultima vuelta activa se actualiza
			lapActive = deleteCar(listRace, lapDistances, lapActive);
		}
		// ================================================//
		// C = = = TRAS Carrera = = = = = =
		// ================================================//
		// introducimos el primer coche en la lista de resultados
		listResult.get(listRace.size() - 1) = listRace.get(listRace.size() - 1);
		// metemos la lista de resultados en la de participantes
		listCarParticipe = listResult;
		exportRace();
	}// FinmakeRace

	private int deleteCar(ArrayList<Car> listRace, int[] lapDistances, int lapActive) {
		// Eliminar coche
		// Elimino el coche cuando el ultimo coche tenga mas distancia
		// que la distancia de la vuelta
		if (lapDistances[lapActive] == listRace.get(listRace.size() - 1).getDistance()) {
			// añadimos el coche eliminado en la ultima posicion activa
			// del array resultados
			// Car a = listResult.get(listRace.size() -1);
			listResult.get(listRace.size() - 1) = listRace.get(listRace.size() - 1);
			// org.apache.commons.lang.ArrayUtils;
			// Elimino el coche
			listRace.remove(listRace.size());
			// paso el ultimo coche se focaliza la siguente vuelta
			lapActive++;
			printArray(listClasificacion);
			printArray(listRace);
		}
		return lapActive;
	}

	private void runCar(ArrayList<Car> listRace) {
		// ======= Cada Time abanzan los coches entre 1 y 5 =======//
		for (Car cc : listRace) {
			if (Math.random() > 0.5f && cc.getSpeed() > cc.getMAXSPEED()) {
				int a = cc.getSpeed();
				cc.setSpeed(a++);
			} else {
				if (cc.getSpeed() != 0) {
					int b = cc.getSpeed();
					cc.setSpeed(b--);
				}
			}
			int v = cc.getSpeed() + cc.getDistance();
			cc.setDistance(v);
		}
	}

	public int getType() {
		return type;
	}

	public ArrayList<Car> getListCarParticipe() {
		return listCarParticipe;
	}

	public void setListCarParticipe(ArrayList<Car> listCarParticipe) {
		this.listCarParticipe = listCarParticipe;
	}

}
