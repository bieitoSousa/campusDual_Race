/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.campusdual.DATA;

import java.io.Serializable;

/**
 *
 * @author BIE_FIJO_PC
 */

// ===================================================//
//  =   Class to make cars
//  =   you can define the properties of a car
//  =   mark, model and autogenerate a id of them
// ===================================================//

public class Car implements Cloneable, Comparable<Car>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// max sped is 5
	private final int MAXSPEED = 5;
	// String to identificate a car
	// private int id;
	// Company make the car
	private String mark;
	// name of the model of a car
	private String model;
	// quantity of units the car move
	private int speed;
	// units the car move
	private int distance;
	private int points;

	public Car(String mark, String model) {
		this.mark = mark;
		this.model = model;
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

	public int getMAXSPEED() {
		return MAXSPEED;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}

// equal mark, model, id

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public int compareTo(Car o) {
		if (points > o.points) {
			return -1;
		} else if (points < o.points) {
			return 1;
		} else if (points == o.points) {
			if (distance > o.distance) {
				return -1;
			} else if (distance < o.distance) {
				return 1;
			}
		}
		return 0;
	}

	public String getName() {
		return "Car_" + model + "_" + mark;
	}

	@Override
	public String toString() {
		return "Car_" + model + "_" + mark + "_Points_[" + points + "]_:_{distance=" + distance + ",speed= " + speed
				+ '}';
	}

}
