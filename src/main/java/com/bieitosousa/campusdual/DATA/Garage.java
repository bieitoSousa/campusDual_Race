/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import static com.bieitosousa.campusdual.UTILS.JSON.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import com.bieitosousa.campusdual.UTILS.*;

/**
 *
 * @author BIE_FIJO_PC
 */
// ===================================================//
//  =   Class to house cars 
//  =   you can add, delete and selecct a car of him
// ===================================================//
public class Garage {

	String name; // name of the garage
	// List of cars
	ArrayList<Car> listGCar = new ArrayList<>();

	public Garage(String name) {
		this.name = name;
	}

	// add a car to the garage
	public void addCar(Car c) {
		try {
			listGCar.add(c);
		} catch (Exception e) {
			System.err.println(" not add the car"+c.getId());
		}
	}

	public void addCar(List<Car> listc) {
		try {
			for (Car c : listc) {
				listGCar.add(c);
			}
		} catch (Exception e) {
			for (Car c : listc) {
				System.err.println(" not add the car" + c.getId());
			}
		}
	}

	// delete a car on the garage
	public void deleteCar(Car c) {
		try {
			listGCar.remove(c);
		} catch (Exception e) {
			System.err.println(" not remove the car" + c.getId());
		}

	}

	// return the list of cars
	public ArrayList<Car> getAllCar() {
		return listGCar;

	}

	// return one random car on this garage
	public ArrayList<Car> getOneCar() {
		ArrayList<Car> auxArry = new ArrayList<>();
		auxArry.add(listGCar.get(HelpsRandomNumber(0, listGCar.size())));
		return auxArry;
	}

	// take a ramdom number betwin two numbers
	public int HelpsRandomNumber(int inicio, int fin) {
		return (int) (Math.random() * fin + inicio);
	}

	// import a list of cars in a file
	public void ImportCars() {
		List<Car> importlistCar = CargarFileCar(new File(Controler.getIMPORTCAR()));
		for (Car c : importlistCar) {
			listGCar.add(c);
		}
	}

	@Override
	public String toString() {
		return "Garage_" + name + " : {" + listGCar + "}";
	}

}// fin Garage
