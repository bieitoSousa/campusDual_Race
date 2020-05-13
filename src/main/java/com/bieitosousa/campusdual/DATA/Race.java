/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import com.bieitosousa.campusdual.UTILS.JSON;
import com.bieitosousa.campusdual.UTILS.Utilss.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author BIE_FIJO_PC
 */
// ===================================================//
//  =   Class to Create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
public abstract class Race {

    // name of the race
    protected String name;
    // type = 0 Estandar ; type = 1 Eliminarorias
    protected int type;
    // true --> is on the run if  result != null 
    // positions ,0--> 1ºCar ,1-->2ºCar ,2-->3ºCar 
    // store of the race result 
    protected Car[] result = null;
    // store of the race participants 
    protected Car[] partic = null;
	private int[] lapDistances;
	private Car[] arrayCars;

// ===================================================//
//  =   Class to create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
    public Race(
            String name, int type,
            List<Garage> listGarage,
            // all Cars on the garage participate true
            // one Car on the garage participate false
            boolean isAllCarPasrticipe
    ) {
        this.name = name;
        this.type = type;
      
    }

	abstract protected void makeRace() throws Exception;//FinmakeRace

    protected List<Car> OrderCarAsPosition(List<Car> listCar) {
        int cuentaintercambios = 0;
        Car[] arrayCars = null;
		listCar.toArray(arrayCars);
        //Usamos un bucle anidado, saldra cuando este ordenado el array
        for (boolean ordenado = false; !ordenado;) {
            for (int i = 0; i < arrayCars.length - 1; i++) {
                if (arrayCars[i].getDistance() > arrayCars[i + 1].getDistance()) {
                    //Intercambiamos valores
                    int vAux = arrayCars[i].getDistance();
                    arrayCars[i].setDistance(arrayCars[i + 1].getDistance());
                    arrayCars[i + 1].setDistance(vAux);
                    //indicamos que hay un cambio
                    cuentaintercambios++;
                }
            }
            //Si no hay intercambios, es que esta ordenado.
            if (cuentaintercambios == 0) {
                ordenado = true;
            }
            //Inicializamos la variable de nuevo para que empiece a contar de nuevo
            cuentaintercambios = 0;
        }
        return listCar;
    }

    public void printArray(List <Object> listObj) {
        for (Object o : listObj) {
            System.out.println(o);
        }
    }

    public boolean exportRace() {
        File f = new File(".//export//race//");
        if (!f.exists()) {
            f.mkdirs();
        }
        Date date = new Date();
        name = "race_" + this.name + date.getTime();
        File fname = new File(".//export//race//" + name);
        return JSON.WriteObjJsonInFile(fname, this);
    }

    @Override
    public String toString() {
        return "Race{" + "name=" + name
                + ", type=" + ((type == 1) ? "Eliminacion" : "Estandar")
                + ", result=" + result + ", partic=" + partic + '}';
    }

}//End race.class
