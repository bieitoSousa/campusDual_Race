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
public class Main {

	public static void main(String[] args) {
		// ver los coches
		ArrayList<Car> listC = (ArrayList<Car>) CargarFileCar(new File(".//cars.json"));

		ArrayList<Garage> listG = new ArrayList<>();
		listG.add(new Garage("Garage_01"));
		listG.add(new Garage("Garage_02"));
		listG.add(new Garage("Garage_03"));
		listG.add(new Garage("Garage_04"));
		listG.add(new Garage("Garage_05"));
		listG.add(new Garage("Garage_06"));
		for (Garage g : listG) {
			g.addCar(CargarFileCar(new File(".//cars.json")));
		}
		
		// PRUEBAS CONSTRUCTOR RACE STANDAR
		Race_Standar rs_01_true = null;
		Race_Standar rs_02_false = null;
		
		try {

			rs_01_true = new Race_Standar("re_01_true", 0, listG,true);
			rs_02_false = new Race_Standar("re_02_false", 0, listG,false);
			
			System.out.println("[OK] : Race_Standar : rs_01_true : Constructor" + rs_01_true);
			System.out.println("[OK] : Race_Standar : rs_01_true : Nº Participantes"+ rs_01_true.getListCarParticipe().size());
			System.out.println("[OK] : Race_Standar : rs_01_true : Participantes"+ rs_01_true.getListCarParticipe());
			
			System.out.println("[OK] : Race_Standar : rs_02_false : Constructor"+ rs_02_false);
			System.out.println("[OK] : Race_Standar : rs_02_false : Nº Participantes" + rs_02_false.getListCarParticipe().size());
			
		} catch (Exception e) {
			System.err.println("Race_Standar:Constructor -> ERROR : " + e.getMessage());
		}

		
		try {
			Race_Standar rs_01_fitro00 = new Race_Standar("rs_01_fitro00 ", 1, listG,true);

		} catch (Exception e) {
			System.out.println("[OK] Race_Standar:Constructor  -> Filtro00  bien definido");
		}
		try {
			ArrayList <Garage>listA = new ArrayList<>() ;
			Collections.copy(listA,listG);
			listA.clear();
			Race_Standar rs_01_fitro01 = new Race_Standar("rs_01_fitro01 ", 0, listA, true);
			
		} catch (Exception e) {
			System.out.println("[OK] Race_Standar:Constructor  -> Filtro01 bien definido");
		}
		
		
		
		// PRUEBAS CONSTRUCTOR RACE ELIMINATION
		Race_Elimination re_01_true = null; 
		Race_Elimination re_02_false = null; 
		
		try {

			re_01_true = new Race_Elimination("re_01_true", 1, listG,true);
			re_02_false = new Race_Elimination("re_02_false", 1, listG, false);
			
			System.out.println("[OK] : Race_Elimination : Constructor"+re_01_true);
			System.out.println("[OK] : Race_Elimination : N PARTICIPE "+re_01_true.getListCarParticipe().size());
			System.out.println("[OK] : Race_Elimination : PARTICIPE "+re_01_true.getListCarParticipe());
			
			System.out.println("[OK] : Race_Elimination : Constructor"+re_02_false);
			System.out.println("[OK] : Race_Elimination : N PARTICIPE "+re_02_false.getListCarParticipe().size());
			
			
		} catch (Exception e) {
			System.err.println("Race_Elimination :Constructor -> ERROR : " + e.getMessage());
		}

		try {
			Race_Elimination re_01_fitro01  = new Race_Elimination("re_01_fitro01 ", 0, listG, true);

		} catch (Exception e) {
			System.out.println("[OK] Race_Elimination:Constructor -> Filtro00 para  bien definido");
		}
		try {
			ArrayList <Garage>listA = new ArrayList<>() ;
			Collections.copy(listA,listG);
			listA.clear();
			Race_Elimination re_01_fitro01  = new Race_Elimination("re_01_fitro01 ", 1, listA,true);
			
		} catch (Exception e) {
			System.out.println("[OK] Race_Elimination:Constructor -> Filtro01 para  bien definido");
		}
		
	
		
		try {
		//Race_Standar rs_01_true;
		//Race_Standar rs_02_false;
//		rs_01_true.makeRace();
//		rs_01_true.printResultGrupByGarage();
//		rs_01_true.printResultGrupByCar();
//		rs_02_false.makeRace();
//		rs_02_false.printResultGrupByGarage();
//		rs_02_false.printResultGrupByCar();
		
		//Race_Elimination re_01_true; 
		//Race_Elimination re_02_false; 
		re_01_true.makeRace();
		re_01_true.printResultGrupByGarage();
		re_01_true.printResultGrupByCar();
		re_02_false.makeRace();
		re_02_false.printResultGrupByGarage();
		re_02_false.printResultGrupByCar();
		
		
//		System.out.println(listC);
		System.out.println(listG);

		} catch (Exception e) {
			System.out.println("fallo al realizara la carrera "+ e.getMessage());
		}
		
	}

}
