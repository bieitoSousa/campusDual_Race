/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.UTILS;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author BIE_FIJO_PC
 */
public class Utilss {
	
	public static boolean  pConsole = false;
	public static boolean printInConsole() {
		return pConsole;
	}
	public static void changePrintInConsole() {
		pConsole=!pConsole ;
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
			f.createNewFile();
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
