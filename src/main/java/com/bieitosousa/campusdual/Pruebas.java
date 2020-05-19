/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bieitosousa.campusdual;

import static com.bieitosousa.campusdual.UTILS.JSON.CargarFileCar;

import java.io.File;
import java.util.*;

import com.bieitosousa.campusdual.DATA.*;
import com.bieitosousa.campusdual.UTILS.*;

/**
 *
 * @author BIE_FIJO_PC
 */
public class Pruebas {
	
	static String PRUEBANAME = "";
	static String PATH = ".//public_data/HELP//";
	
	

	static String getPath() {
		return Pruebas.PATH;
	}
	static void setPath(String s) {
		 Pruebas.PATH = s;
	}
	
	static void deleteFolder(File fileDel) {
        if(fileDel.isDirectory()){           
           
            if(fileDel.list().length == 0)
                fileDel.delete();
            else{
               
               for (String temp : fileDel.list()) {
                   File fileDelete = new File(fileDel, temp);
                   //recursive delete
                   deleteFolder(fileDelete);
               }

               //check the directory again, if empty then delete it
               if(fileDel.list().length==0)
                   fileDel.delete();
              
            }

        }else{
           
            //if file, then delete it
            fileDel.delete();           
        }
    }
	
	
	public static void printListHelp(ArrayList<?> l, String cabecera) {
		l.forEach((a) -> Utilss.printONFile("\n" + cabecera + a + "\n", new File(getPath() + "HELP__" + cabecera + ".txt")));
	}

	public static void printListHelp(Car[] o, String cabecera) {
		for (Car c : o) {
			Utilss.printONFile("\n" + cabecera + c + "\n", new File(getPath() + "HELP__" + cabecera + ".txt"));
		}
	}

	public static void main(String[] args) {
		
		
		//Eliminamos la carpeta public
		deleteFolder(new File(".//public_data"));
		
		boolean prueba_race_contruct = true;// need probar_recuperacion --> false 
		boolean pruebas_race_metodos = true;// need probar_recuperacion --> false 
		boolean pruebas_torneo_AllGarage = true;// need probar_recuperacion --> false 
		boolean pruebas_torneo_OneGarage = true;// need probar_recuperacion --> false 
		boolean pruebas_save = true;  // need probar_recuperacion --> false 
		boolean probar_recuperacion =true;

		if (!probar_recuperacion) {

		// GARAGES
		Garage garage_02 =null;
		Garage garage_03 =null;
		Garage garage_04 =null;
		Garage garage_05 =null;
		Garage garage_06 =null;
		
		// PRUEBAS CONSTRUCTOR RACE STANDAR
		Race_Standar rs_01_true = null;
		Race_Standar rs_02_true = null;
		Race_Standar rs_03_true = null;
		Race_Standar rs_04_true = null;
		Race_Standar rs_05_true = null;
		Race_Standar rs_01_false = null;
		Race_Standar rs_02_false = null;
		Race_Standar rs_03_false = null;
		Race_Standar rs_04_false = null;
		Race_Standar rs_05_false = null;

		// PRUEBAS CONSTRUCTOR RACE ELIMINATION
		Race_Elimination re_01_true = null;
		Race_Elimination re_02_true = null;
		Race_Elimination re_03_true = null;
		Race_Elimination re_04_true = null;
		Race_Elimination re_05_true = null;
		Race_Elimination re_01_false = null;
		Race_Elimination re_02_false = null;
		Race_Elimination re_03_false = null;
		Race_Elimination re_04_false = null;
		Race_Elimination re_05_false = null;

		// PRUEBAS TORNEO_ALL_GARAGES RACE ELIMINATION
		Race_Standar torn_rs_02_true = null;
		Race_Standar torn_rs_03_true = null;
		Race_Standar torn_rs_04_true = null;
		Race_Standar torn_rs_05_true = null;
		Race_Standar torn_rs_01_false = null;
		Race_Standar torn_rs_02_false = null;
		Race_Standar torn_rs_03_false = null;
		Race_Standar torn_rs_04_false = null;
		Race_Standar torn_rs_05_false = null;
		Race_Elimination torn_re_01_true = null;
		Race_Elimination torn_re_02_true = null;
		Race_Elimination torn_re_03_true = null;
		Race_Elimination torn_re_04_true = null;
		Race_Elimination torn_re_05_true = null;
		Race_Elimination torn_re_01_false = null;
		Race_Elimination torn_re_02_false = null;
		Race_Elimination torn_re_03_false = null;
		Race_Elimination torn_re_04_false = null;
		Race_Elimination torn_re_05_false = null;

		// PRUEBAS TORNEO_ONE_GARAGES RACE ELIMINATION
		Race_Standar torn_OneGarage_rs_02_true = null;
		Race_Standar torn_OneGarage_rs_03_true = null;
		Race_Standar torn_OneGarage_rs_04_true = null;
		Race_Standar torn_OneGarage_rs_05_true = null;
		Race_Standar torn_OneGarage_rs_01_false = null;
		Race_Standar torn_OneGarage_rs_02_false = null;
		Race_Standar torn_OneGarage_rs_03_false = null;
		Race_Standar torn_OneGarage_rs_04_false = null;
		Race_Standar torn_OneGarage_rs_05_false = null;
		Race_Elimination torn_OneGarage_re_01_true = null;
		Race_Elimination torn_OneGarage_re_02_true = null;
		Race_Elimination torn_OneGarage_re_03_true = null;
		Race_Elimination torn_OneGarage_re_04_true = null;
		Race_Elimination torn_OneGarage_re_05_true = null;
		Race_Elimination torn_OneGarage_re_01_false = null;
		Race_Elimination torn_OneGarage_re_02_false = null;
		Race_Elimination torn_OneGarage_re_03_false = null;
		Race_Elimination torn_OneGarage_re_04_false = null;
		Race_Elimination torn_OneGarage_re_05_false = null;
		
		//TORNEOS

		Tornament tornEOneG=null;
		Tornament tornOneStandar=null;
		Tornament tS=null;
		Tornament tE=null;
		
		
		
		Controler.CreatePath();
		// ver los coches
		ArrayList<Car> listC = (ArrayList<Car>) CargarFileCar(new File(".//cars.json"));

		ArrayList<Garage> listGOne = new ArrayList<>();
		listGOne.add(new Garage("Garage_01"));
		for (Garage g : listGOne) {
			g.addCar(CargarFileCar(new File(".//cars.json")));
		}
		 garage_02 =new Garage("garage_02");
		 garage_03 =new Garage("garage_03");
		 garage_04 =new Garage("garage_04");
		 garage_05 =new Garage("garage_05");
		 garage_06 =new Garage("garage_06");
		ArrayList<Garage> listG = new ArrayList<>();
		listG.add(garage_02);
		listG.add(garage_03);
		listG.add(garage_04);
		listG.add(garage_05);
		listG.add(garage_06);
		for (Garage gg : listG) {
			gg.addCar(CargarFileCar(new File(".//cars.json")));
		}

		
		if (prueba_race_contruct) {
			setPath( ".//public_data/HELP//"+"RACECONSTRUCTOR//");

			try {

				rs_01_true = new Race_Standar("rs_01_true", 0, listG);
				rs_02_true = new Race_Standar("rs_02_true", 0, listG);
				rs_03_true = new Race_Standar("rs_03_true", 0, listG);
				rs_04_true = new Race_Standar("rs_04_true", 0, listG);
				rs_05_true = new Race_Standar("rs_05_true", 0, listG);

				rs_01_false = new Race_Standar("rs_01_false", 0, listG);
				rs_02_false = new Race_Standar("rs_02_false", 0, listG);
				rs_03_false = new Race_Standar("rs_03_false", 0, listG);
				rs_04_false = new Race_Standar("rs_04_false", 0, listG);
				rs_05_false = new Race_Standar("rs_05_false", 0, listG);

				System.out.println("[OK] : Race_Standar : rs_01_true : Constructor" + rs_01_true);
				

				System.out.println("[OK] : Race_Standar : rs_02_false : Constructor" + rs_02_false);
				

			} catch (Exception e) {
				System.err.println("Race_Standar:Constructor -> ERROR : " + e.getMessage());
			}

			try {
				Race_Standar rs_01_fitro00 = new Race_Standar("rs_01_fitro00 ", 1, listG);

			} catch (Exception e) {
				System.out.println("[OK] Race_Standar:Constructor  -> Filtro00  bien definido");
			}
			try {
				
				Race_Standar rs_01_fitro01 = new Race_Standar("rs_01_fitro01 ", 0, listG);

			} catch (Exception e) {
				System.out.println("[OK] Race_Standar:Constructor  -> Filtro01 bien definido");
			}

		}
		if (pruebas_race_metodos) {
			setPath( ".//public_data/HELP//"+"RACEMETH//");
		

			try {

				re_01_true = new Race_Elimination("re_01_true", 1, listG);
				re_02_true = new Race_Elimination("re_02_true", 1, listG);
				re_03_true = new Race_Elimination("re_03_true", 1, listG);
				re_04_true = new Race_Elimination("re_04_true", 1, listG);
				re_05_true = new Race_Elimination("re_05_true", 1, listG);

				re_01_false = new Race_Elimination("re_01_false", 1, listG);
				re_02_false = new Race_Elimination("re_02_false", 1, listG);
				re_03_false = new Race_Elimination("re_03_false", 1, listG);
				re_04_false = new Race_Elimination("re_04_false", 1, listG);
				re_05_false = new Race_Elimination("re_05_false", 1, listG);

				System.out.println("[OK] : Race_Elimination : Constructor" + re_01_true);
				System.out.println("[OK] : Race_Elimination : N PARTICIPE " + re_01_true.getListCarParticipe().size());
				System.out.println("[OK] : Race_Elimination : PARTICIPE " + re_01_true.getListCarParticipe());

				System.out.println("[OK] : Race_Elimination : Constructor" + re_02_false);
				System.out.println("[OK] : Race_Elimination : N PARTICIPE " + re_02_false.getListCarParticipe().size());

			} catch (Exception e) {
				System.err.println("Race_Elimination :Constructor -> ERROR : " + e.getMessage());
			}

			try {
				Race_Elimination re_01_fitro01 = new Race_Elimination("re_01_fitro01 ", 0, listG);

			} catch (Exception e) {
				System.out.println("[OK] Race_Elimination:Constructor -> Filtro00 para  bien definido");
			}
			try {
				ArrayList<Garage> listA = new ArrayList<>();
				
				Race_Elimination re_01_fitro01 = new Race_Elimination("re_01_fitro01 ", 1, listG);

			} catch (Exception e) {
				System.out.println("[OK] Race_Elimination:Constructor -> Filtro01 para  bien definido");
			}

			try {
//	=	=	=	 Carreras Standar	=	=	=	=
				// Race_Standar rs_01_true;
				// Race_Standar rs_02_false;
				re_01_true.start();
				re_02_true.start();

				List<Car> re_01_true_partic = re_01_true.getParticC();
				Pruebas.printListHelp(re_01_true.getParticC(), "RACE_re_01_true_partic_prueba00");
				List<Car> re_01_true_result = re_01_true.getResultC();
				Pruebas.printListHelp(re_01_true.getParticC(), "RACE_re_01_true_result_prueba01");

				List<Car> re_02_true_partic = re_02_true.getParticC();
				Pruebas.printListHelp(re_01_true.getParticC(), "RACE_re_02_true_partic_prueba03");
				List<Car> re_02_true_result = re_02_true.getResultC();
				Pruebas.printListHelp(re_01_true.getParticC(), "RACE_re_02_true_result_prueba04");

			} catch (Exception e) {
				System.err.println("fallo al realizara la carrera " + e.getMessage());
			}

		}
		if (pruebas_torneo_AllGarage) {
			setPath( ".//public_data/HELP//"+"TORNALLMETH//");


			try {
				torn_rs_02_true = new Race_Standar("rs_02_true", 0, listG);
				torn_rs_03_true = new Race_Standar("rs_03_true", 0, listG);
				torn_rs_04_true = new Race_Standar("rs_04_true", 0, listG);
				torn_rs_05_true = new Race_Standar("rs_05_true", 0, listG);
				torn_rs_01_false = new Race_Standar("rs_01_false", 0, listG);
				torn_rs_02_false = new Race_Standar("rs_02_false", 0, listG);
				torn_rs_03_false = new Race_Standar("rs_03_false", 0, listG);
				torn_rs_04_false = new Race_Standar("rs_04_false", 0, listG);
				torn_rs_05_false = new Race_Standar("rs_05_false", 0, listG);
				torn_re_01_true = new Race_Elimination("re_01_true", 1, listG);
				torn_re_02_true = new Race_Elimination("re_02_true", 1, listG);
				torn_re_03_true = new Race_Elimination("re_03_true", 1, listG);
				torn_re_04_true = new Race_Elimination("re_04_true", 1, listG);
				torn_re_05_true = new Race_Elimination("re_05_true", 1, listG);
				torn_re_01_false = new Race_Elimination("re_01_false", 1, listG);
				torn_re_02_false = new Race_Elimination("re_02_false", 1, listG);
				torn_re_03_false = new Race_Elimination("re_03_false", 1, listG);
				torn_re_04_false = new Race_Elimination("re_04_false", 1, listG);
				torn_re_05_false = new Race_Elimination("re_05_false", 1, listG);
			} catch (Exception e) {
				System.err.println("fallo al Constructor carrera antes de torneo" + e.getMessage());
			}

			try {
//			=	=	=	 Torneos Standar	=	=	=	=
				ArrayList<Race> tornS = new ArrayList<>();

				tornS.add(torn_rs_02_true);
				tornS.add(torn_rs_03_true);
//			tornS.add( rs_03_true); 
//			tornS.add( rs_04_true); 
//			tornS.add( rs_05_true); 
//			tornS.add( rs_01_false); 
//			tornS.add( rs_02_false); 
//			tornS.add( rs_03_false); 
//			tornS.add( rs_04_false); 
//			tornS.add( rs_05_false); 

				 tS = new Tornament("Torneo_Standar", tornS);
				tS.start();
				tS.getTornPartiC();
				Pruebas.printListHelp((ArrayList<Car>) tS.getTornPartiC(), "Torneo_Standar_Participantes_____00");
				System.out.println(tS.getCabeceraT() + "Participantes" + tS.getTornPartiC().size());
				int cnnt = 0;
				for (Car[] a : tS.getTornRaceR()) {
					Pruebas.printListHelp(a, "Torneo_Standar_RACE[[" + (cnnt++) + "]]_____00");
				}
				;
				tS.getTornResultC();
				Pruebas.printListHelp((ArrayList<Car>) tS.getTornResultC(), "Torneo_Standar_Resultados_____00");
				System.out.println(tS.getCabeceraT() + "resultados" + tS.getTornResultC().size());
				tS.getTornPointsC();
				Pruebas.printListHelp(tS.getTornPointsC().get(0), "Torneo_Standar_Podium_____[1]");
				System.out.println(tS.getCabeceraT() + "resultados" + tS.getTornPointsC().get(0));
				Pruebas.printListHelp(tS.getTornPointsC().get(1), "Torneo_Standar_Podium_____[2]");
				System.out.println(tS.getCabeceraT() + "resultados" + tS.getTornPointsC().get(1));
				Pruebas.printListHelp(tS.getTornPointsC().get(2), "Torneo_Standar_Podium_____[3]");
				System.out.println(tS.getCabeceraT() + "resultados" + tS.getTornPointsC().get(2));
			} catch (Exception e) {
				System.err.println("fallo al realizara El Torneo_Standar_ALL metodos" + e.getMessage());
			}
			try {
////			=	=	=	 Torneos eliminatorios	=	=	=	=
				ArrayList<Race> tornE = new ArrayList<>();

				tornE.add(torn_re_01_true);
				tornE.add(torn_re_02_true);
//		tornS.add( rs_03_true); 
//		tornS.add( rs_04_true); 
//		tornS.add( rs_05_true); 
//		tornS.add( rs_01_false); 
//		tornS.add( rs_02_false); 
//		tornS.add( rs_03_false); 
//		tornS.add( rs_04_false); 
//		tornS.add( rs_05_false); 

				 tE = new Tornament("Torneo_Elimination", tornE);
				tE.start();
				tE.getTornPartiC();
				Pruebas.printListHelp((ArrayList<Car>) tE.getTornPartiC(), "Torneo_Elimination_Participantes_____00");
				System.out.println(tE.getCabeceraT() + "Participantes" + tE.getTornPartiC().size());
				tE.getTornResultC();
				Pruebas.printListHelp((ArrayList<Car>) tE.getTornResultC(), "Torneo_Elimination_Resultados_____00");
				System.out.println(tE.getCabeceraT() + "resultados" + tE.getTornResultC().size());
				tE.getTornPointsC();
				int cnnt = 0;
				for (Car[] a : tE.getTornRaceR()) {
					Pruebas.printListHelp(a, "Torneo_Elimination_RACE[[" + (cnnt++) + "]]_____00");
				}
				;
				Pruebas.printListHelp(tE.getTornPointsC().get(0), "Torneo_Elimination_Podium_____[1]");
				System.out.println(tE.getCabeceraT() + "resultados" + tE.getTornPointsC().get(0));
				Pruebas.printListHelp(tE.getTornPointsC().get(1), "Torneo_Elimination_Podium_____[2]");
				System.out.println(tE.getCabeceraT() + "resultados" + tE.getTornPointsC().get(1));
				Pruebas.printListHelp(tE.getTornPointsC().get(2), "Torneo_Elimination_Podium_____[3]");
				System.out.println(tE.getCabeceraT() + "resultados" + tE.getTornPointsC().get(2));
			} catch (Exception e) {
				System.err.println("fallo al realizara El Torneo_Elimination_ALL metodos" + e.getMessage());
			}

		}

		if (pruebas_torneo_OneGarage) {
			setPath( ".//public_data/HELP//"+"TORNONEMETH//");
			try {
				torn_OneGarage_rs_02_true = new Race_Standar("rs_02_true", 0, listGOne);
				torn_OneGarage_rs_03_true = new Race_Standar("rs_03_true", 0, listGOne);
				torn_OneGarage_rs_04_true = new Race_Standar("rs_04_true", 0, listGOne);
				torn_OneGarage_rs_05_true = new Race_Standar("rs_05_true", 0, listGOne);
				torn_OneGarage_rs_01_false = new Race_Standar("rs_01_false", 0, listGOne);
				torn_OneGarage_rs_02_false = new Race_Standar("rs_02_false", 0, listGOne);
				torn_OneGarage_rs_03_false = new Race_Standar("rs_03_false", 0, listGOne);
				torn_OneGarage_rs_04_false = new Race_Standar("rs_04_false", 0, listGOne);
				torn_OneGarage_rs_05_false = new Race_Standar("rs_05_false", 0, listGOne);
				torn_OneGarage_re_01_true = new Race_Elimination("re_01_true", 1, listGOne);
				torn_OneGarage_re_02_true = new Race_Elimination("re_02_true", 1, listGOne);
				torn_OneGarage_re_03_true = new Race_Elimination("re_03_true", 1, listGOne);
				torn_OneGarage_re_04_true = new Race_Elimination("re_04_true", 1, listGOne);
				torn_OneGarage_re_05_true = new Race_Elimination("re_05_true", 1, listGOne);
				torn_OneGarage_re_01_false = new Race_Elimination("re_01_false", 1, listGOne);
				torn_OneGarage_re_02_false = new Race_Elimination("re_02_false", 1, listGOne);
				torn_OneGarage_re_03_false = new Race_Elimination("re_03_false", 1, listGOne);
				torn_OneGarage_re_04_false = new Race_Elimination("re_04_false", 1, listGOne);
				torn_OneGarage_re_05_false = new Race_Elimination("re_05_false", 1, listGOne);
			} catch (Exception e) {
				System.err.println("fallo al Constructor carrera antes de torneo ONE " + e.getMessage());
			}

			try {
				torn_OneGarage_rs_02_true = new Race_Standar("rs_02_true", 0, listGOne);
				torn_OneGarage_rs_03_true = new Race_Standar("rs_03_true", 0, listGOne);
				torn_OneGarage_rs_04_true = new Race_Standar("rs_04_true", 0, listGOne);
				torn_OneGarage_rs_05_true = new Race_Standar("rs_05_true", 0, listGOne);
				torn_OneGarage_rs_01_false = new Race_Standar("rs_01_false", 0, listGOne);
				torn_OneGarage_rs_02_false = new Race_Standar("rs_02_false", 0, listGOne);
				torn_OneGarage_rs_03_false = new Race_Standar("rs_03_false", 0, listGOne);
				torn_OneGarage_rs_04_false = new Race_Standar("rs_04_false", 0, listGOne);
				torn_OneGarage_rs_05_false = new Race_Standar("rs_05_false", 0, listGOne);
				torn_OneGarage_re_01_true = new Race_Elimination("re_01_true", 1, listGOne);
				torn_OneGarage_re_02_true = new Race_Elimination("re_02_true", 1, listGOne);
				torn_OneGarage_re_03_true = new Race_Elimination("re_03_true", 1, listGOne);
				torn_OneGarage_re_04_true = new Race_Elimination("re_04_true", 1, listGOne);
				torn_OneGarage_re_05_true = new Race_Elimination("re_05_true", 1, listGOne);
				torn_OneGarage_re_01_false = new Race_Elimination("re_01_false", 1, listGOne);
				torn_OneGarage_re_02_false = new Race_Elimination("re_02_false", 1, listGOne);
				torn_OneGarage_re_03_false = new Race_Elimination("re_03_false", 1, listGOne);
				torn_OneGarage_re_04_false = new Race_Elimination("re_04_false", 1, listGOne);
				torn_OneGarage_re_05_false = new Race_Elimination("re_05_false", 1, listGOne);
			} catch (Exception e) {
				System.err.println("fallo al Constructor carrera antes de torneo ONE metodos" + e.getMessage());
			}

			try {
//	=	=	=	 Torneos Standar	=	=	=	=
				ArrayList<Race> tornS = new ArrayList<>();

				tornS.add(torn_OneGarage_rs_02_true);
				tornS.add(torn_OneGarage_rs_03_true);
//	tornS.add( rs_03_true); 
//	tornS.add( rs_04_true); 
//	tornS.add( rs_05_true); 
//	tornS.add( rs_01_false); 
//	tornS.add( rs_02_false); 
//	tornS.add( rs_03_false); 
//	tornS.add( rs_04_false); 
//	tornS.add( rs_05_false); 

				 tornOneStandar = new Tornament("Torneo_Standar_ONE_GARAGE", tornS);
				tornOneStandar.start();
				tornOneStandar.getTornPartiC();
				Pruebas.printListHelp((ArrayList<Car>) tornOneStandar.getTornPartiC(),
						"Torneo_Standar_ONE_GARAGE_Participantes_____00");
				System.out.println(tornOneStandar.getCabeceraT() + "Participantes" + tornOneStandar.getTornPartiC().size());
				int cnnt = 0;
				for (Car[] a : tornOneStandar.getTornRaceR()) {
					Pruebas.printListHelp(a, "Torneo_Standar_ONE_GARAGE_RACE[[" + (cnnt++) + "]]_____00");
				}
				;
				tornOneStandar.getTornResultC();
				Pruebas.printListHelp((ArrayList<Car>) tornOneStandar.getTornResultC(),
						"Torneo_Standar_ONE_GARAGE_Resultados_____00");
				System.out.println(tornOneStandar.getCabeceraT() + "resultados" + tornOneStandar.getTornResultC().size());
				tornOneStandar.getTornPointsC();
				Pruebas.printListHelp(tornOneStandar.getTornPointsC().get(0), "Torneo_Standar_ONE_GARAGE_Podium_____[1]");
				System.out.println(tornOneStandar.getCabeceraT() + "resultados" + tornOneStandar.getTornPointsC().get(0));
				Pruebas.printListHelp(tornOneStandar.getTornPointsC().get(1), "Torneo_Standar_ONE_GARAGE_Podium_____[2]");
				System.out.println(tornOneStandar.getCabeceraT() + "resultados" + tornOneStandar.getTornPointsC().get(1));
				Pruebas.printListHelp(tornOneStandar.getTornPointsC().get(2), "Torneo_Standar_ONE_GARAGE_Podium_____[3]");
				System.out.println(tornOneStandar.getCabeceraT() + "resultados" + tornOneStandar.getTornPointsC().get(2));
			} catch (Exception e) {
				System.err.println("fallo al realizara ETorneo_Standar_ONE_GARAGE" + e.getMessage());
			}

			try {
////=	=	=	 Torneos eliminatorios one garage 	=	=	=	=
				ArrayList<Race> rOneG = new ArrayList<>();

				rOneG.add(torn_OneGarage_re_01_true);
				rOneG.add(torn_OneGarage_re_02_true);
//tornS.add( rs_03_true); 
//tornS.add( rs_04_true); 
//tornS.add( rs_05_true); 
//tornS.add( rs_01_false); 
//tornS.add( rs_02_false); 
//tornS.add( rs_03_false); 
//tornS.add( rs_04_false); 
//tornS.add( rs_05_false); 
 tornEOneG = new Tornament("TORNEO_ELIMINATE_ONE_GARAGE_", rOneG);
				tornEOneG.start();
				tornEOneG.getTornPartiC();
				Pruebas.printListHelp((ArrayList<Car>) tornEOneG.getTornPartiC(),
						"TORNEO_ELIMINATE_ONE_GARAGE_ParticipantornEOneGs_____00");
				System.out.println(tornEOneG.getCabeceraT() + "ParticipantornEOneGs" + tornEOneG.getTornPartiC().size());
				tornEOneG.getTornResultC();
				Pruebas.printListHelp((ArrayList<Car>) tornEOneG.getTornResultC(),
						"TORNEO_ELIMINATE_ONE_GARAGE_Resultados_____00");
				System.out.println(tornEOneG.getCabeceraT() + "resultados" + tornEOneG.getTornResultC().size());
				tornEOneG.getTornPointsC();
				int cnnt = 0;
				for (Car[] a : tornEOneG.getTornRaceR()) {
					Pruebas.printListHelp(a, "TORNEO_ELIMINATE_ONE_GARAGE_RACE[[" + (cnnt++) + "]]_____00");
				}
				;
				Pruebas.printListHelp(tornEOneG.getTornPointsC().get(0), "TORNEO_ELIMINATE_ONE_GARAGE_Podium_____[1]");
				System.out.println(tornEOneG.getCabeceraT() + "resultados" + tornEOneG.getTornPointsC().get(0));
				Pruebas.printListHelp(tornEOneG.getTornPointsC().get(1), "TORNEO_ELIMINATE_ONE_GARAGE_Podium_____[2]");
				System.out.println(tornEOneG.getCabeceraT() + "resultados" + tornEOneG.getTornPointsC().get(1));
				Pruebas.printListHelp(tornEOneG.getTornPointsC().get(2), "TORNEO_ELIMINATE_ONE_GARAGE_Podium_____[3]");
				System.out.println(tornEOneG.getCabeceraT() + "resultados" + tornEOneG.getTornPointsC().get(2));
			} catch (Exception e) {
				System.err.println("fallo al realizara El TORNEO_ELIMINATE_ONE_GARAGE metodos" + e.getMessage());
			}
		}	
			if (pruebas_torneo_OneGarage) {
				
				setPath( ".//public_data/HELP//"+"CONTROLER//");
			try {
				
				// Race_Standar rs_02_false;
				Controler c = Controler.getTnstace();

				c.addTornament(tornEOneG);
				c.addTornament(tornOneStandar);
				c.addTornament(tS);
				c.addTornament(tE);
				c.addRace(rs_01_true);
				c.addRace(rs_02_true);
				c.addRace(re_01_true);
				c.addRace(re_02_true);
				c.addGarage(garage_02);
				c.addGarage(garage_03);
				c.addGarage(garage_04);
				c.addGarage(garage_05);
				c.addGarage(garage_06);
				c.save();
			} catch (Exception e) {
				System.err.println("fallo CONTROLER metodos" + e.getMessage());
			}
			}
			
		}
		if(probar_recuperacion) {
		
			try {
					Controler a = Controler.getTnstace();	
					a.getTornamentL().size();
					a.getRaceL().size();
					a.getGarageL().size();
					a.getTornamentL().get(0).getTornResultC();
					a.getRaceL().get(0).getResultC();
				
			
			} catch (Exception e) {
				System.err.println("fallo CONTROLER metodos" + e.getMessage());
			}
		}
			
		
	}
}