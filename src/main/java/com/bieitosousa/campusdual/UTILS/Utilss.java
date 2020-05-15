/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.UTILS;

import com.bieitosousa.campusdual.DATA.Car;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BIE_FIJO_PC
 */
public class Utilss {

	// ____________PRINTS________[TRUE:CONSOL]______[FALSE:FILE]_______________________
	// print a simulation of the races: [ true; console/false : in file ]
	public static boolean CONSOLE_PRINT_RACE_VIEW = false;
	// print the result of the race when finish : [ true; console/false : in file]
	public static boolean CONSOLE_PRINT_RACE_RESULT = false;
	// print the classification of the tournament : [true; console/false : in file]
	public static boolean CONSOLE_PRINT_TORNAMENT_CLASIFICATION = true;

	// ___________ACTIONS__________[TRUE:DO]________[FALSE:NOTDO]
	// ______ RACE _________________________
	// = view the simulation of the races
	public static boolean RACE_VIEW = false;
	// = view the result of the races
	public static boolean RACE_RESULT = true;
	// = Export the list of participants, results of the races
	public static boolean RACE_EXP = true;

	// ______ TORN _________________________
	// = view the classification of the tournament
	public static boolean TORN_RESULT = true;
	// = Export the list of participants, results and classification of the race
	public static boolean TORN_EXP = true;

	// ______POINTS_____________________________________________
	public static int POINTS_FIRSTS = 12;
	public static int POINTS_SECOND = 6;
	public static int POINTS_THIRD = 3;
	public static int POINTS_DEFAULT = 1;

	// _______________PATH_________________________________
	public static String PUBLIC_PATH = ".//public_data//";
	public static String PRIVATE_PATH = ".//private_data//";

	public static String PUBLIC_RACE = PUBLIC_PATH + "//race//";
	public static String PRIVATE_RACE = PRIVATE_PATH + "//race//";

	public static String PUBLIC_CAR = PUBLIC_PATH + "//car//";
	public static String PRIVATE_CAR = PRIVATE_PATH + "//car//";

	public static String PUBLIC_TORNAMENT = PUBLIC_PATH + "//car//";
	public static String PRIVATE_TORNAMENT = PRIVATE_PATH + "//car//";

	// _______________FILES_________________________________

	// ______ RACE _________________________
	public static String R_LOG = PUBLIC_RACE + "view_carrera";
	public static String R_EXP = PRIVATE_RACE + "race_export";
	public static String R_RESULT = PUBLIC_RACE + "clasification_race";

	// ______ TORN _________________________
	public static String T_EXP = PRIVATE_TORNAMENT + "tornament_export";
	public static String T_LOG = PUBLIC_TORNAMENT + "view_tornament";
	public static String T_RESULT = PUBLIC_TORNAMENT + "clasification_tornament";;

	// ______ CONTROL _________________________
	public static String CONF = PRIVATE_PATH + "conf.json";
	public static String IMPORTCAR = PRIVATE_PATH + "car.json";

	public static void CreatePath() {
		new File(PUBLIC_PATH).mkdirs();
		new File(PRIVATE_PATH).mkdirs();
		new File(PUBLIC_RACE).mkdirs();
		new File(PRIVATE_RACE).mkdirs();
		new File(PUBLIC_CAR).mkdirs();
		new File(PRIVATE_CAR).mkdirs();
		new File(PUBLIC_TORNAMENT).mkdirs();
		new File(PRIVATE_TORNAMENT).mkdirs();
	}

	public static void printONFile(String mnj) {
		FileWriter writer = null;
		BufferedWriter bw = null;
		try {
			File a = new File(".//seguimientoCarrera//");
			if (!a.exists())
				a.mkdir();
			File b = new File(".//seguimientoCarrera//Racelog.txt");
			writer = new FileWriter(b, true);
			bw = new BufferedWriter(writer);
			bw.write(mnj);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void printONFile(String mnj, File f) {
		FileWriter writer = null;
		BufferedWriter bw = null;
		try {
			File a = f.getParentFile();
			if (!a.exists())
				a.mkdir();
			writer = new FileWriter(f, true);
			bw = new BufferedWriter(writer);
			bw.write(mnj);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
