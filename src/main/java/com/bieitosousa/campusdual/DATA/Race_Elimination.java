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
	private ArrayList<Car> listCarParticipe = new ArrayList<>();
	private String msjCoche="";

	public Race_Elimination(String name, int type, ArrayList<Garage> ListGCar, boolean oneCar) throws Exception {
		super(name, type, ListGCar);
		// Filtramos 
		if (type != 1 ) {
			throw new Exception(" Type erroneo no se puede crear la clase Race_Elimination");
		}
		if (ListGCar.size() == 0 ) {
			throw new Exception(" La lista de garajes no contiene ningun valor, no se puede crear la clase Race_Elimination");
		}
		// Definimos la lista de participantes
			// Loop the gararges and take one the cart to participate
		if (oneCar) {	
		for (Garage g : ListGCar) {
				for (Car ca : g.getOneCar()) {
				 this.listCarParticipe.add(ca);
				}
			}
		}else {
			for (Garage g : ListGCar) {
				for (Car ca : g.getAllCar()) {
				 this.listCarParticipe.add(ca);
				}
			}
			
			
		}
		
	

	}

	// generate a eliminate race cars
	@Override
	public void makeRace() throws Exception {

		// ================================================//
		// A = = = = Pre carrera = = = = = =
		// ================================================//
		// [A1] = = = Definimos Variables locales = = = =
		// lap , MaxLap
		int lap = 0; // inicio en vuelta 0
		int maxLap = listCarParticipe.size() - 1; // maximas vueltas
		List <Integer> lapDistances = new ArrayList <>(); // distancia por vuelta
		int lapTime = 60; // cada vuelta dura 60 time
		int lapActive = 0; // numero de vuelta hasta que pase el ultimo coche por meta
		ArrayList<Car> listRace= new ArrayList<>(this.listCarParticipe);// listRace -> tratamineto de la carrera
		ArrayList<Car> listResult = new ArrayList<>();// listResult -> tratar de la clasificacion
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
		for (int time = 1; (maxLap != lap); time++) {
			// [B2] = = = Modificamos los parametros de los coches = = = =
			// coches frena o acelera , update distance y speed
			listRace=runCar(listRace);
			// [B3] = = = Ordenamos los coches por distancia = = = =
			listRace = (ArrayList<Car>) OrderCarAsPosition(listRace);
			// [B4] = = = Actualizamos las vueltas , generamos ditancia de vuleta = = = =
			if (time == lapTime) {// = Vueltas ; cada vuelta dura --> lap = 60 t
				lapTime += lapTime;
				// Ditancia de vulta es la distancia que recorrio el 1 al pasar 60 t
				int dist = listRace.get(0).getDistance();
				lapDistances.add( dist);
				lap++; // Empieza la proxima vuelta
			}
			// [B5] = = = Eliminamos el ultimo coche que pase por meta = = = =
			// Cuando un coche sea eliminado la ultima vuelta activa se actualiza
			if (lap !=0) {
			lapActive = deleteCar(listRace, listResult, lapDistances, lapActive,time,maxLap);
			}
			}
		// ================================================//
		// C = = = TRAS Carrera = = = = = =
		// ================================================//
		// introducimos el primer coche en la lista de resultados
		Car p = listResult.get(listRace.size() -1);
		listRace.set(listRace.size() - 1 , p) ;
		// metemos la lista de resultados en la de participantes
		listCarParticipe = listResult;
		takeResultToSuperListG();
		exportRace();
	}// FinmakeRace
	

	private int deleteCar(ArrayList<Car> listRace,ArrayList<Car> listResult, List <Integer>lapDistances, int lapActive,int time, int maxLap) {
		// Eliminar coche
		// Elimino el coche cuando el ultimo coche tenga mas distancia
		// que la distancia de la vuelta
		int distanciaVuelta = lapDistances.get(lapActive);
		int distanciaUltimoCoche= listRace.get(listRace.size() - 1).getDistance();
		if (distanciaUltimoCoche > distanciaVuelta) {
			// añadimos el coche eliminado en la ultima posicion activa
			// del array resultados
			Car rcc = listRace.get(listRace.size() -1);
			listResult.add(rcc) ;
			//array1[array2.lenght -1] = array2[array1.lenght -1]
			//listResult.get(listRace.size() - 1) = listRace.get(listRace.size() - 1);
			// org.apache.commons.lang.ArrayUtils;
			// Elimino el coche
			listRace.remove(listRace.size()-1);
			// paso el ultimo coche se focaliza la siguente vuelta
			lapActive++;
			System.out.print(
					"\n#	lap ["+ ( lapActive+1 )+"]	"
					+"	#	#	"
					+"#	Time ["+ time +" minutes]	"
					+"#	duration ["+ maxLap+" minutes]	"
					+"	#	#	\n\n"
					);
			msjCoche="		= RA:"+this.name+" Lap"+(lapActive+1) +" Car =>>";
			String msjCocheEliminado="	ELIMINADO	= RA:"+this.name+" Car =>>";
			printList(listResult, msjCocheEliminado);
			printList(listRace, msjCoche);
		}
		return lapActive;
	}

	private ArrayList <Car> runCar(ArrayList <Car>listRace) {
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


	private void takeResultToSuperListG() { 
		ArrayList<Garage> superListG = getParticG();
		for (Garage supgarlist : superListG) {
					for (Car sgc : supgarlist.listGCar) {
						for (Car pc : this.listCarParticipe) {
							if(sgc.equals(pc)) {
							sgc.setDistance(pc.getDistance());	
							}
							}
					}
				}
		setParticG(superListG);
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
