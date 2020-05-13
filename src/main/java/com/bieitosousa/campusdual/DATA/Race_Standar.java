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
 * @author BIE_FIJO_PC
 */

// ===================================================//
//  =   Class  Race_standar
//  =   you can create a race with  a duration limit 
//  =   win the car who get more distance in 3 hour
// ===================================================//

public class Race_Standar extends Race {

    
	private ArrayList<Car> listCarParticipe;


	public Race_Standar(
            String name, int type,        
            List<Garage> ListGCar,
            boolean isAllCarPasrticipe
    ) throws Exception {
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

    
    
    
    
// generate a standar race cars
	// generate a eliminate race cars
		@Override
		protected void makeRace() throws Exception {

			// ================================================//
			// A = = = = Pre carrera = = = = = =
			// ================================================//
			// [A1] = = = Definimos Variables locales = = = =
			// lapTime , duration
			int lapTime = 60;
			int duration = 30*60*60; // maximas vueltas
			// [A2] = = = Definimos parametros de los coches = = = =

			for (Car c : listCarParticipe) {// speed =0 // distance = 0
				c.setSpeed(0);
				c.setDistance(0);
			}
			// ================================================//
			// B = = = = Carrera = = = = = =
			// ================================================//
			// [B1] = = = Definimos Fin de Carrera = = = =
			// cuando se terminen las vueltas
			for (int time = 1; (duration == time); time++) {
				// [B2] = = = Modificamos los parametros de los coches = = = =
				// coches frena o acelera , update distance y speed
				runCar(listCarParticipe);
				// [B3] = = = Ordenamos los coches por distancia = = = =
				listCarParticipe = (ArrayList<Car>) OrderCarAsPosition(listCarParticipe);
				// [B4] = = = Actualizamos las vueltas , generamos ditancia de vuleta = = = =
				if (time == lapTime) {// = Vueltas ; cada vuelta dura --> lap = 60 t
					lapTime = +lapTime;
					printArray(listCarParticipe);
				}
			}
			// ================================================//
			// C = = = TRAS Carrera = = = = = =
			// ================================================//
			exportRace();
		}// FinmakeRace

		

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




	public ArrayList<Car> getListCarParticipe() {
		return listCarParticipe;
	}





	public void setListCarParticipe(ArrayList<Car> listCarParticipe) {
		this.listCarParticipe = listCarParticipe;
	}

}
