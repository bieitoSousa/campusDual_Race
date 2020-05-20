package com.bieitosousa.campusdual.DATA;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bieitosousa.campusdual.UTILS.JSON;
import com.bieitosousa.campusdual.UTILS.Utilss;

public class Controler {
	


	// ______POINTS____________________________________________________________________
	public final static int POINTS_FIRSTS = 12;
	public final static int POINTS_SECOND = 6;
	public final static int POINTS_THIRD = 3;
	public final static int POINTS_DEFAULT = 1;

	// _______________PATH______________________________________________________________
	private final static String PUBLIC_PATH = ".//public_data//";
	private final static String PRIVATE_PATH = ".//private_data//";

	private final static String PUBLIC_GARAGE = PUBLIC_PATH + "//public_garage//";
	private final static String PRIVATE_GARAGE = PRIVATE_PATH + "//private_garage//";
	
	private final static String PUBLIC_RACE = PUBLIC_PATH + "//public_race//";
	private final static String PRIVATE_RACE = PRIVATE_PATH + "//private_race//";

	private final static String PUBLIC_CAR = PUBLIC_PATH + "//public_car//";
	private final static String PRIVATE_CAR = PRIVATE_PATH + "//private_car//";

	private final static String PUBLIC_TORNAMENT = PUBLIC_PATH + "//public_tornament//";
	private final static String PRIVATE_TORNAMENT = PRIVATE_PATH + "//private_tornament//";
	private final static String CONF = PRIVATE_PATH + "//private_conf//";
	// _______________FILES_______________________________________________________________
    
	
	
	// ______ GARAGE _____________________________________________________________________
	
	private static String G_PARTIC = PUBLIC_GARAGE + "//garage_partic_"+Utilss.getTime()+"//"+"GARAGE_PARTIC__";
	private static String G_RESULT = PUBLIC_GARAGE + "//garage_results_"+Utilss.getTime()+"//"+"GARAGE_RESULTS__";
	private static String G_EXP = PRIVATE_GARAGE + "garage_export_";
	// ______ RACE _______________________________________________________________________
	private  static String R_PARTIC = PUBLIC_RACE + "//race_partic_"+Utilss.getTime()+"//"+"RACE_PARTIC__";
	private  static String R_LOG = PUBLIC_RACE + "//race_log_"+Utilss.getTime()+"//"+"RACE_LOG__";
	private  static String R_RESULT = PUBLIC_RACE + "//race_results_"+Utilss.getTime()+"//"+"RACE_RESULTS__";
	private  static String R_EXP = PRIVATE_RACE + "race_export_";
	

	// ______ TORN _______________________________________________________________________
	private  static String T_PARTIC = PUBLIC_TORNAMENT +  "//torn_partic"+Utilss.getTime()+"//"+"TORN_PARTIC__";
	private  static String T_PODIUM = PUBLIC_TORNAMENT +  "//torn_podium"+Utilss.getTime()+"//"+"TORN_PODIUM__";
	private  static String T_RESULT = PUBLIC_TORNAMENT+ "//torn_results"+Utilss.getTime()+"//"+"TORN_RESULTS__";
	private  static String T_EXP = PRIVATE_TORNAMENT + "tornament_export_";
	// ______ CONTROL ____________________________________________________________________
	private  static String BACKUP = CONF + "backup.json";
	private  static String IMPORTCAR = CONF + "car.json";

	private Garage GARAGESELECT;
	private Race RACESELECT;
	private Tornament TORNAMENTSELECT;
	private ArrayList<Garage> garageL;
	private ArrayList<Race> raceL;
	private ArrayList<Tornament> tornamentL;

	private static Controler c = null;

	public Controler() {
		this.garageL = new ArrayList<Garage>();
		this.raceL = new ArrayList<Race>();
		this.tornamentL = new ArrayList<Tornament>();
	}

	// ----------------- RECOVER ---------------------------------------------------------------------//

	public static Controler getTnstace() {
		if (c == null) {
			File f = new File(Controler.BACKUP);
			if (f.exists()) {
				try {
					c = (Controler) JSON.ReadObjJsonInFile(f);
				} catch (Exception e) {
					System.err.println("the data file could not be recovered" + e.getMessage());
				}
			} else {
				c = new Controler();

			}
		}
		return c;
	}

	public void save() {
		try {
			File f = new File(Controler.getPRIVATE_PATH());
			if (!f.exists()) {
				f.mkdirs();
			}
			File fname = new File(Controler.BACKUP);
			JSON.WriteObjJsonInFile(fname, this);
		} catch (Exception e) {
			System.err.println("Error al exportar " + e.getMessage());
		}

	}

	// ------------------------------------------------EXPORT/IMPORT ------------------------------------//

	public void importGarageL(){
		Utilss.importList(getGarageL(), G_EXP);
	}
	public void importRaceL(){
		Utilss.importList(getRaceL(), R_EXP);
	}
	public void importTornamentL(){
		Utilss.importList(getTornamentL(), T_EXP);
	}
	public void expGarageL(){
		Utilss.expList(getGarageL(), G_EXP);	
	}
	public void expRaceL(){
		Utilss.expList(getRaceL(), R_EXP);
	}
	public void expTornamentL(){
		Utilss.expList(getTornamentL(), T_EXP);
	}
	
	
	
	// ------------------------- ADD DELETE LIST -> ATRIBUTES  ------------------------------------------//

	public void addTornament(Tornament t) {
		if (!(this.tornamentL.contains(t)))
			this.tornamentL.add(t);
		else
			System.out.println("error " + t.name + "is in the list");
	}

	public void delTornament(Tornament t) {
		if ((this.tornamentL.contains(t)))
			this.tornamentL.remove(t);
		else
			System.out.println("error " + t.name + "not in the list");
	}

	public void addRace(Race r) {
		if (!(this.raceL.contains(r)))
			this.raceL.add(r);
		else
			System.out.println("error " + r.name + "is in the list");
	}

	public void delRace(Race r) {
		if ((this.raceL.contains(r)))
			this.raceL.remove(r);
		else
			System.out.println("error " + r.name + "is not in the list");
	}

	public void addGarage(Garage g) {
		if (!(this.garageL.contains(g)))
			this.garageL.add(g);
		else
			System.out.println("error " + g.name + "is in the list");
	}

	public void delGarage(Garage g) {
		if ((this.garageL.contains(g)))
			this.garageL.remove(g);
		else
			System.out.println("error " + g.name + "is not in the list");
	}

	// ------------------------- GET/SET -> ATRIBUTES -------------------------------------------------//

	public Garage getGARAGESELECT() {
		return GARAGESELECT;
	}

	public void setGARAGESELECT(Garage gARAGESELECT) {
		GARAGESELECT = gARAGESELECT;
	}

	public Race getRACESELECT() {
		return RACESELECT;
	}

	public void setRACESELECT(Race rACESELECT) {
		RACESELECT = rACESELECT;
	}

	public Tornament getTORNAMENTSELECT() {
		return TORNAMENTSELECT;
	}

	public void setTORNAMENTSELECT(Tornament tORNAMENTSELECT) {
		TORNAMENTSELECT = tORNAMENTSELECT;
	}

	public ArrayList<Garage> getGarageL() {
		return garageL;
	}

	public void setGarageL(ArrayList<Garage> garageL) {
		this.garageL = garageL;
	}

	public ArrayList<Race> getRaceL() {
		return raceL;
	}

	public void setRaceL(ArrayList<Race> raceL) {
		this.raceL = raceL;
	}

	public ArrayList<Tornament> getTornamentL() {
		return tornamentL;
	}

	public void setTornamentL(ArrayList<Tornament> tornamentL) {
		this.tornamentL = tornamentL;
	}


	public static String getPUBLIC_PATH() {
		return PUBLIC_PATH;
	}


	public static String getPRIVATE_PATH() {
		return PRIVATE_PATH;
	}

	

	public static String getBACKUP() {
		return BACKUP;
	}

	
	public static String getPUBLIC_RACE() {
		return PUBLIC_RACE;
	}


	public static String getPRIVATE_RACE() {
		return PRIVATE_RACE;
	}

	

	public static String getPUBLIC_CAR() {
		return PUBLIC_CAR;
	}


	public static String getPRIVATE_CAR() {
		return PRIVATE_CAR;
	}

	

	public static String getPUBLIC_TORNAMENT() {
		return PUBLIC_TORNAMENT;
	}

	

	public static String getPRIVATE_TORNAMENT() {
		return PRIVATE_TORNAMENT;
	}

	

	public static String getR_LOG() {
		return R_LOG;
	}

	public static void setR_LOG(String r_LOG) {
		R_LOG = r_LOG;
	}

	public static String getR_EXP() {
		return R_EXP;
	}

	public static void setR_EXP(String r_EXP) {
		R_EXP = r_EXP;
	}

	public static String getR_RESULT() {
		return R_RESULT;
	}

	public static void setR_RESULT(String r_RESULT) {
		R_RESULT = r_RESULT;
	}

	public static String getT_EXP() {
		return T_EXP;
	}

	public static void setT_EXP(String t_EXP) {
		T_EXP = t_EXP;
	}

	public static String getT_PODIUM() {
		return T_PODIUM;
	}

	public static void setT_PODIUM(String t_LOG) {
		T_PODIUM = t_LOG;
	}

	public static String getT_RESULT() {
		return T_RESULT;
	}

	public static void setT_RESULT(String t_RESULT) {
		T_RESULT = t_RESULT;
	}

	public static String getCONF() {
		return BACKUP;
	}

	public static void setBACKUP(String bACKUP) {
		BACKUP = bACKUP;
	}

	public static String getIMPORTCAR() {
		return IMPORTCAR;
	}

	public static void setIMPORTCAR(String iMPORTCAR) {
		IMPORTCAR = iMPORTCAR;
	}

	public static String getG_PARTIC() {
		return G_PARTIC;
	}

	public static void setG_PARTIC(String g_PARTIC) {
		G_PARTIC = g_PARTIC;
	}

	public static String getG_RESULT() {
		return G_RESULT;
	}

	public static void setG_RESULT(String g_RESULT) {
		G_RESULT = g_RESULT;
	}

	public static String getG_EXP() {
		return G_EXP;
	}

	public static void setG_EXP(String g_EXP) {
		G_EXP = g_EXP;
	}

	public static String getR_PARTIC() {
		return R_PARTIC;
	}

	public static void setR_PARTIC(String r_PARTIC) {
		R_PARTIC = r_PARTIC;
	}

	public static String getT_PARTIC() {
		return T_PARTIC;
	}

	public static void setT_PARTIC(String t_PARTIC) {
		T_PARTIC = t_PARTIC;
	}
	
	

	// ------------ PATH -----------------//
//
//	public static void CreatePath() {
//		new File(PUBLIC_PATH).mkdirs();
//		new File(PRIVATE_PATH).mkdirs();
//		new File(PUBLIC_RACE).mkdirs();
//		new File(PRIVATE_RACE).mkdirs();
//		new File(PUBLIC_CAR).mkdirs();
//		new File(PRIVATE_CAR).mkdirs();
//		new File(PUBLIC_TORNAMENT).mkdirs();
//		new File(PRIVATE_TORNAMENT).mkdirs();
//		
//	}

}
