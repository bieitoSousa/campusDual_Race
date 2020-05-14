/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.UTILS;

import com.bieitosousa.campusdual.DATA.Car;
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

	public static List<Car> CargarFileCar(File f) {
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
			e.printStackTrace();
		}
		return laux;
	}

}
