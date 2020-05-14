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

    
	private ArrayList<Car> listCarParticipe = new ArrayList<>() ;
	private String msjCoche = "";
	private String cabecera  =" RACE STANDAR 	"+this.name;

	public Race_Standar(
            String name, int type,        
            ArrayList<Garage> ListGCar,
            boolean oneCar
            
          
    ) throws Exception {
		 super(name, type, ListGCar  );
		 if (type != 0 ) {
				throw new Exception(" Type tiene que ser 0. Error no se puede crear la clase Race_Standar");
			}
			if (ListGCar.size() == 0 ) {
				throw new Exception(" La lista de garajes no contiene ningun valor, no se puede crear la clase Race_Standar");
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
  
        
        
    

    
    
    
    
// generate a standar race cars
	// generate a eliminate race cars
		@Override
		public void makeRace() throws Exception {
			
			// ================================================//
			// A = = = = Pre carrera = = = = = =
			// ================================================//
			// [A1] = = = Definimos Variables locales = = = =
			// lapTime , duration
			int lapTime = 0;
			int duration = 3*60; // maximas vueltas
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
			System.out.println("\n	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=");
			System.out.println("	=	=	=	=	=	"+cabecera+"	=	=	=	=	=	=	=");
			System.out.println("	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=	=\n");
			int cn =0;
			for (int time = 0; duration != time; time++) {
				// [B2] = = = Modificamos los parametros de los coches = = = =
				// coches frena o acelera , update distance y speed
				runCar();
				// [B3] = = = Ordenamos los coches por distancia = = = =
				listCarParticipe = (ArrayList<Car>) OrderCarAsPosition(listCarParticipe);
				// [B4] = = = Actualizamos las vueltas , generamos ditancia de vuleta = = = =
				if (time == lapTime) {// = Vueltas ; cada vuelta dura --> lap = 60 t
					System.out.print(
							"\n#	lap ["+ ( lapTime/60+1 )+"]	"
							+"	#	#	"
							+"#	Time ["+ time +" minutes]	"
							+"#	duration ["+ duration+" minutes]	"
							+"	#	#	\n\n"
							);
					msjCoche="		= RA:"+this.name+" Lap"+(lapTime/60+1) +" Car =>>";
					printList(this.listCarParticipe, msjCoche);
					lapTime += 60;
				}
			}
			// ================================================//
			// C = = = TRAS Carrera = = = = = =
			// ================================================//
			takeResultToSuperListG();
			exportRace();
		}// FinmakeRace

		

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
				
			
			
	










		private void runCar() {
			// ======= Cada Time abanzan los coches entre 1 y 5 =======//
			for (Car cc : this.listCarParticipe) {
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
		}




	public ArrayList<Car> getListCarParticipe() {
		return listCarParticipe;
	}





	public void setListCarParticipe(ArrayList<Car> listCarParticipe) {
		this.listCarParticipe = listCarParticipe;
	}

}
