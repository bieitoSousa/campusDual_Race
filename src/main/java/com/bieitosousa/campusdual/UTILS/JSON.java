/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.UTILS;

import com.bieitosousa.campusdual.DATA.Car;
import com.bieitosousa.campusdual.DATA.Garage;
import com.bieitosousa.campusdual.DATA.Race;
import com.bieitosousa.campusdual.DATA.Race_Elimination;
import com.bieitosousa.campusdual.DATA.Race_Standar;
import com.bieitosousa.campusdual.DATA.Tornament;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import static com.bieitosousa.campusdual.UTILS.JSON.CargarFileCar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BIE_FIJO_PC
 */
public class JSON {

	public static boolean WriteObjJsonInFile(File objectFile, Object obj) {
		Gson gson = new Gson();

		// Java objects to String
		// String json = gson.toJson(staff);
		// Java objects to File
		try (FileWriter writer = new FileWriter(objectFile)) {
			gson.toJson(obj, writer);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		// System.out.println(gson.toJson(obj));
	}

	public static Object ReadObjJsonInFile(File objectFile) {
		Gson gson = new Gson();
		Object obj = null;
		try (Reader reader = new FileReader(objectFile)) {
			// Convert JSON File to Java Object
			obj = gson.fromJson(reader, Object.class);
			// print staff object
			System.out.println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static boolean WritedArryListJsonInFile(File objectFile, Object obj) {
		Gson gson = new Gson();

		// Java objects to String
		// String json = gson.toJson(staff);
		// Java objects to File
		try (FileWriter writer = new FileWriter(objectFile)) {
			gson.toJson(obj, writer);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		// System.out.println(gson.toJson(obj));
	}

	public static List<Car> CargarFileCar(File f) {
		try {
			File a = f.getParentFile();
			if (!a.exists())
				a.mkdir();
			f.createNewFile();
		} catch (Exception ex) {
			System.err.println("Fallo GSON::ARCHIVO" + ex.getMessage());
		}
		List<Car> laux = new ArrayList<>();
		Gson gson = new Gson();
		ArrayList<Car> objList = new ArrayList<>();
		BufferedReader in;

		try (Reader reader = (in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8")))) {
			objList = gson.fromJson(reader, new TypeToken<ArrayList<Car>>() {
			}.getType());

			if (objList == null || objList.size() < 16) {
				throw new Exception("No se han cargado correctamente " + f.getPath());
			}

			for (int i = 0; i < objList.size(); i++) {
				laux.add(new Car(objList.get(i).getMark(), objList.get(i).getModel()));
			}

		} catch (Exception e) {
			System.err.println("Fallo GSON::DATOS" + e.getMessage());

		}
		return laux;
	}

	public static List<Garage> CargarFileGarage(File f) {
		try {
			File a = f.getParentFile();
			if (!a.exists())
				a.mkdir();
			f.createNewFile();
		} catch (Exception ex) {
			System.err.println("Fallo GSON::ARCHIVO" + ex.getMessage());
		}
		List<Garage> laux = new ArrayList<>();
		Gson gson = new Gson();
		ArrayList<Garage> objList = new ArrayList<>();
		BufferedReader in;

		try (Reader reader = (in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8")))) {
			objList = gson.fromJson(reader, new TypeToken<ArrayList<Garage>>() {
			}.getType());

			if (objList == null) {
				throw new Exception("No se han cargado correctamente " + f.getPath());
			}

			for (int i = 0; i < objList.size(); i++) {
				laux.add(new Garage(objList.get(i).getName(), objList.get(i).getListGCar()));
			}

		} catch (Exception e) {
			System.err.println("Fallo GSON::DATOS" + e.getMessage());

		}
		return laux;
	}

	public static List<Race> CargarFileRace(File f) {
		List<Race> laux = new ArrayList<>();
		laux.addAll((ArrayList<Race>) CargarFileRaceElimination(f));

		laux.addAll((ArrayList<Race>) CargarFileRaceStandar(f));
		return laux;
	}

	public static List<Race> CargarFileRaceElimination(File f) {
		try {
			File a = f.getParentFile();
			if (!a.exists())
				a.mkdir();
			f.createNewFile();
		} catch (Exception ex) {
			System.err.println("Fallo GSON::ARCHIVO" + ex.getMessage());
		}
		List<Race> laux = new ArrayList<>();
		Gson gson = new Gson();
		ArrayList<Race> objList = new ArrayList<>();
		BufferedReader in;

		try (Reader reader = (in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8")))) {
			objList = gson.fromJson(reader, new TypeToken<ArrayList<Race_Elimination>>() {
			}.getType());

			if (objList == null) {
				throw new Exception("No se han cargado correctamente " + f.getPath());
			}

			for (int i = 0; i < objList.size(); i++) {
				if (objList.get(0).getType() == 0) {

					laux.add(new Race_Standar(objList.get(i).getName(), objList.get(i).getType(),
							objList.get(i).getParticG(), objList.get(i).getParticC(), objList.get(i).getResultC(),
							objList.get(i).getPointsC(), objList.get(i).getCabeceraR()));
				} else {
					laux.add(new Race_Elimination(objList.get(i).getName(), objList.get(i).getType(),
							objList.get(i).getParticG(), objList.get(i).getParticC(), objList.get(i).getResultC(),
							objList.get(i).getPointsC(), objList.get(i).getCabeceraR()));

				}
			}

		} catch (Exception e) {
			System.err.println("Fallo GSON::DATOS" + e.getMessage());

		}
		return laux;
	}

	public static List<Race> CargarFileRaceStandar(File f) {
		try {
			File a = f.getParentFile();
			if (!a.exists())
				a.mkdir();
			f.createNewFile();
		} catch (Exception ex) {
			System.err.println("Fallo GSON::ARCHIVO" + ex.getMessage());
		}
		List<Race> laux = new ArrayList<>();
		Gson gson = new Gson();
		ArrayList<Race> objList = new ArrayList<>();
		BufferedReader in;

		try (Reader reader = (in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8")))) {
			objList = gson.fromJson(reader, new TypeToken<ArrayList<Race_Elimination>>() {
			}.getType());

			if (objList == null) {
				throw new Exception("No se han cargado correctamente " + f.getPath());
			}

			for (int i = 0; i < objList.size(); i++) {
				if (objList.get(0).getType() == 0) {

					laux.add(new Race_Standar(objList.get(i).getName(), objList.get(i).getType(),
							objList.get(i).getParticG(), objList.get(i).getParticC(), objList.get(i).getResultC(),
							objList.get(i).getPointsC(), objList.get(i).getCabeceraR()));
				} else {
					laux.add(new Race_Elimination(objList.get(i).getName(), objList.get(i).getType(),
							objList.get(i).getParticG(), objList.get(i).getParticC(), objList.get(i).getResultC(),
							objList.get(i).getPointsC(), objList.get(i).getCabeceraR()));

				}
			}

		} catch (Exception e) {
			System.err.println("Fallo GSON::DATOS" + e.getMessage());

		}
		return laux;
	}

	public static List<Tornament> CargarFileTornament(File f) {
		try {
			File a = f.getParentFile();
			if (!a.exists())
				a.mkdir();
			f.createNewFile();
		} catch (Exception ex) {
			System.err.println("Fallo GSON::ARCHIVO" + ex.getMessage());
		}
		List<Tornament> laux = new ArrayList<>();
		Gson gson = new Gson();
		ArrayList<Tornament> objList = new ArrayList<>();
		BufferedReader in;

		try (Reader reader = (in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8")))) {
			objList = gson.fromJson(reader, new TypeToken<ArrayList<Tornament>>() {
			}.getType());

			if (objList == null) {
				throw new Exception("No se han cargado correctamente " + f.getPath());
			}

			for (int i = 0; i < objList.size(); i++) {

				laux.add(new Tornament(objList.get(i).getListTornRace(), objList.get(i).getTornResultC(),
						objList.get(i).getTornPointsC(), objList.get(i).getTornPartiC(), objList.get(i).getTornPartiG(),
						objList.get(i).getTornRaceR(), objList.get(i).getName(), objList.get(i).getCabeceraT()));

			}

		} catch (Exception e) {
			System.err.println("Fallo GSON::DATOS" + e.getMessage());

		}
		return laux;
	}

}

//	public Tornament(ArrayList<Race> listTornRace, ArrayList<Car> tornResultC, ArrayList<Car[]> tornPointsC,
//			ArrayList<Car> tornPartiC, ArrayList<Garage> tornPartiG, ArrayList<Car[]> tornRaceR, String name,
//			String cabeceraT) {