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
import java.util.Date;
import java.util.List;


/**
 *
 * @author BIE_FIJO_PC
 */
public class Utilss {
	
	public static boolean  pConsole = false;
//------------------------------------------- TIME ------------------------------------------------------//	
	
	public static String getTime() {
		return Long.toString( new Date().getTime());
	}
	
	
	
//------------------------------------------- PRINT ------------------------------------------------------//	
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
	
	// ------------------------------------------------[GENERIC ] EXPORT/IMPORT ------------------------------------//

//	[EXAMPLE TO  PASS TO A GENERIC METH]
//	public void exportGarageL() {
//		try {
//			File f = new File(G_EXP);
//			if (!f.exists()) {
//				f.mkdirs();
//			}
//			File fname = new File(Controler.BACKUP);
//			JSON.WriteObjJsonInFile(fname, this);
//		} catch (Exception e) {
//			System.err.println("Error al exportar " + e.getMessage());
//		}
//	}
	
	public static <T>  void expList(List<T> list, String path) {
		try {
			File f = new File(path);
			File fPath= f.getParentFile();
			if(!fPath.exists()) {
				fPath.mkdirs();
			}
			if (!f.exists()) {
				f.createNewFile();
			}else {
			JSON.WriteObjJsonInFile(f, list);
			}
		} catch (Exception e) {
			System.err.println("Error al exportar " + e.getMessage());
		}
	}
//	[EXAMPLE TO  PASS TO A GENERIC METH]
//	public void importGarageL() {
//		garageL.clear();
//		try {
//			File f = new File(G_EXP);
//			File fPath= f.getParentFile();
//			if(!fPath.exists()) {
//				fPath.mkdirs();
//			}
//			if (!f.exists()) {
//				f.createNewFile();
//			}else {
//
//				garageL = (ArrayList<Garage>) JSON.ReadObjJsonInFile(f);
//				System.out.println(" List of Garage data has been exported to " + f);
//			}
//		} catch (Exception e) {
//			System.err.println("the data file could not be recovered" + e.getMessage());
//		}
//
//	}
	
	public static <T> void importList(List<T> list, String path) {
		list.clear();
		try {
			File f = new File(path);
			File fPath= f.getParentFile();
			if(!fPath.exists()) {
				fPath.mkdirs();
			}
			if (!f.exists()) {
				f.createNewFile();
			}else {
				
				list =  (List<T>) JSON.ReadObjJsonInFile(f);
				System.out.println(" List of Garage data has been exported to " + f);
			}
		} catch (Exception e) {
			System.err.println("the data file could not be recovered" + e.getMessage());
		}
		
	}

	
	


	
	
	

}
