package com.bieitosousa.campusdual.DATA;

import java.io.File;
import java.util.ArrayList;

import com.bieitosousa.campusdual.UTILS.JSON;

public class Controler {
	

		// ______POINTS_____________________________________________
		private static int POINTS_FIRSTS = 12;
		private static int POINTS_SECOND = 6;
		private static int POINTS_THIRD = 3;
		private static int POINTS_DEFAULT = 1;

		// _______________PATH_________________________________
		private static String PUBLIC_PATH = ".//public_data//";
		private static String PRIVATE_PATH = ".//private_data//";

		private static String PUBLIC_RACE = PUBLIC_PATH + "//race//";
		private static String PRIVATE_RACE = PRIVATE_PATH + "//race//";

		private static String PUBLIC_CAR = PUBLIC_PATH + "//car//";
		private static String PRIVATE_CAR = PRIVATE_PATH + "//car//";

		private static String PUBLIC_TORNAMENT = PUBLIC_PATH + "//tornament//";
		private static String PRIVATE_TORNAMENT = PRIVATE_PATH + "//tornament//";
		
		private static String HELP = ".//public_data/HELP//help";

		// _______________FILES_________________________________

		// ______ RACE _________________________
		private static String R_LOG = PUBLIC_RACE + "view_carrera";
		private static String R_EXP = PRIVATE_RACE + "race_export";
		private static String R_RESULT = PUBLIC_RACE + "clasification_race";

		// ______ TORN _________________________
		private static String T_EXP = PRIVATE_TORNAMENT + "tornament_export";
		private static String T_LOG = PUBLIC_TORNAMENT + "view_tornament";
		private static String T_RESULT = PUBLIC_TORNAMENT + "clasification_tornament";;

		// ______ CONTROL _________________________
		private static String BACKUP = PRIVATE_PATH + "backup.json";
		private static String IMPORTCAR = PRIVATE_PATH + "car.json";

		private  Garage GARAGESELECT;
		private  Race RACESELECT;
		private  Tornament TORNAMENTSELECT;
		private ArrayList<Garage> garageL;
		private ArrayList<Race> raceL;
		private ArrayList<Tornament>  tornamentL;


		
		private static Controler c = null ;
		
		
		
		public Controler(){
			this.garageL = new ArrayList<Garage>();
			this.raceL = new ArrayList<Race>();
			this.tornamentL = new ArrayList<Tornament>();
		}
		
		
		//----------------- RECOVER -------------------------------------------//
		
		public static  Controler getTnstace() {
			if (c == null) {
				File f = new File(Controler.BACKUP );
				if (f.exists()) {
					try {
					c= (Controler)JSON.ReadObjJsonInFile(f);
					}catch(Exception e) {
						System.err.println(
								"the data file could not be recovered"
								+e.getMessage()
								);
					}
				}else {
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
				File fname = new File(Controler.BACKUP );
				JSON.WriteObjJsonInFile(fname, this);
			} catch (Exception e) {
				System.err.println("Error al exportar " + e.getMessage());
			}
			
		}
		
		
		//------------------------- ADD DELETE LIST -> ATRIBUTES ------------------------//
	

		public void addTornament(Tornament t){
			if(!(this.tornamentL.contains(t)))
			this.tornamentL.add(t);
			else System.out.println("error "+t.name+"is in the list");
		}
		public void delTornament(Tornament t){
			if((this.tornamentL.contains(t)))
				this.tornamentL.remove(t);
			else System.out.println("error "+t.name+"not in the list");
		}

		public void addRace(Race r){
			if(!(this.raceL.contains(r)))
				this.raceL.add(r);
				else System.out.println("error "+r.name+"is in the list");
		}
		public void delRace(Race r){
			if((this.raceL.contains(r)))
				this.raceL.remove(r);
			else System.out.println("error "+r.name+"is not in the list");
		}


		public void addGarage(Garage g){
			if(!(this.garageL.contains(g)))
				this.garageL.add(g);
				else System.out.println("error "+g.name+"is in the list");
		}
		
		public void delGarage(Garage g){
			if((this.garageL.contains(g)))
				this.garageL.remove(g);
			else System.out.println("error "+g.name+"is not in the list");
		}
		
		
		//------------------------- GET/SET -> ATRIBUTES ------------------------//
		
		
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



		public static int getPOINTS_FIRSTS() {
			return POINTS_FIRSTS;
		}

		public static void setPOINTS_FIRSTS(int pOINTS_FIRSTS) {
			POINTS_FIRSTS = pOINTS_FIRSTS;
		}

		public static int getPOINTS_SECOND() {
			return POINTS_SECOND;
		}

		public static void setPOINTS_SECOND(int pOINTS_SECOND) {
			POINTS_SECOND = pOINTS_SECOND;
		}

		public static int getPOINTS_THIRD() {
			return POINTS_THIRD;
		}

		public static void setPOINTS_THIRD(int pOINTS_THIRD) {
			POINTS_THIRD = pOINTS_THIRD;
		}

		public static int getPOINTS_DEFAULT() {
			return POINTS_DEFAULT;
		}

		public static void setPOINTS_DEFAULT(int pOINTS_DEFAULT) {
			POINTS_DEFAULT = pOINTS_DEFAULT;
		}

		public static String getPUBLIC_PATH() {
			return PUBLIC_PATH;
		}

		public static void setPUBLIC_PATH(String pUBLIC_PATH) {
			PUBLIC_PATH = pUBLIC_PATH;
		}

		public static String getPRIVATE_PATH() {
			return PRIVATE_PATH;
		}

		public static String getHELP() {
			return HELP;
		}


		public static void setHELP(String hELP) {
			HELP = hELP;
		}


		public static String getBACKUP() {
			return BACKUP;
		}


		public static void setPRIVATE_PATH(String pRIVATE_PATH) {
			PRIVATE_PATH = pRIVATE_PATH;
		}

		public static String getPUBLIC_RACE() {
			return PUBLIC_RACE;
		}

		public static void setPUBLIC_RACE(String pUBLIC_RACE) {
			PUBLIC_RACE = pUBLIC_RACE;
		}

		public static String getPRIVATE_RACE() {
			return PRIVATE_RACE;
		}

		public static void setPRIVATE_RACE(String pRIVATE_RACE) {
			PRIVATE_RACE = pRIVATE_RACE;
		}

		public static String getPUBLIC_CAR() {
			return PUBLIC_CAR;
		}

		public static void setPUBLIC_CAR(String pUBLIC_CAR) {
			PUBLIC_CAR = pUBLIC_CAR;
		}

		public static String getPRIVATE_CAR() {
			return PRIVATE_CAR;
		}

		public static void setPRIVATE_CAR(String pRIVATE_CAR) {
			PRIVATE_CAR = pRIVATE_CAR;
		}

		public static String getPUBLIC_TORNAMENT() {
			return PUBLIC_TORNAMENT;
		}

		public static void setPUBLIC_TORNAMENT(String pUBLIC_TORNAMENT) {
			PUBLIC_TORNAMENT = pUBLIC_TORNAMENT;
		}

		public static String getPRIVATE_TORNAMENT() {
			return PRIVATE_TORNAMENT;
		}

		public static void setPRIVATE_TORNAMENT(String pRIVATE_TORNAMENT) {
			PRIVATE_TORNAMENT = pRIVATE_TORNAMENT;
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

		public static String getT_LOG() {
			return T_LOG;
		}

		public static void setT_LOG(String t_LOG) {
			T_LOG = t_LOG;
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

		// ------------ PATH -----------------//
		
		public static void CreatePath() {
			new File(PUBLIC_PATH).mkdirs();
			new File(PRIVATE_PATH).mkdirs();
			new File(PUBLIC_RACE).mkdirs();
			new File(PRIVATE_RACE).mkdirs();
			new File(PUBLIC_CAR).mkdirs();
			new File(PRIVATE_CAR).mkdirs();
			new File(PUBLIC_TORNAMENT).mkdirs();
			new File(PRIVATE_TORNAMENT).mkdirs();
			new File(HELP).mkdirs();
		}
		
		
		
		
}
