/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import java.util.Objects;

/**
 *
 * @author BIE_FIJO_PC
 */

// ===================================================//
//  =   Class to make cars
//  =   you can define the properties of a car
//  =   mark, model and autogenerate a id of them
// ===================================================//

public class Car {
    // max sped is 5 
    private final int MAXSPEED = 5;
    // String to identificate a car
    static int sumid;
    private int id;
    // Company make the car
    private String mark;
    // name of the model of a car
    private String model;
    // quantity of units the car move 
    private int speed;
    // units the car move
    private int distance;

    public Car(String mark, String model) {
        this.mark = mark;
        this.model = model;
        this.id = sumid++;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public int getMAXSPEED() {
        return MAXSPEED;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.mark);
        hash = 47 * hash + Objects.hashCode(this.model);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.mark, other.mark)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car_" + id +"_"+model+"_"+mark
        		+ " { distance=" + distance +", speed = "+speed+ '}';
    }


}
