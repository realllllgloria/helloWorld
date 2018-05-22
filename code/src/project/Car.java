package project;

import java.util.ArrayList;

public class Car {
	private ArrayList<Dimension> coords;
	private int length;
	private Direction dir;
	private String color;
	
	/**
	 * Length and direction are set after error checking
	 * @param head
	 * @param tail
	 */
	public Car(ArrayList<Dimension> coords, String color) {
		if (coords.size() < 2) {
			System.out.println ("Cars must have at least 2 coordinates.");
			throw new IllegalArgumentException();
		} else if (coords.size() > 3) {
			System.out.println("Cars must have at most 3 coordinates.");
			throw new IllegalArgumentException();
		}
		
		for (Dimension coord : coords) {
			if (coord.getCol() < 0 || coord.getCol() > 5 || coord.getRow() < 0 
					|| coord.getRow() > 5) {
				throw new IllegalArgumentException();
			}
		}
		
		this.color = color;
		this.coords = coords;
		if (color.equals("DarkPurple") || color.equals("DarkBlue") || color.equals("Yellow") || color.equals("Purple")) {
			this.length = 3;
		} else {
			this.length = 2;
		}
		this.setDirection();
		
		// Used to order the coordinates such that moving the cars works.
		
		if (this.getDirection().equals(Direction.SIDEWAYS)) {
			if(this.coords.get(0).getCol() < this.coords.get(1).getCol()) {
				Dimension head = this.coords.get(0);
				this.coords.remove(0);
				this.coords.add(head);
			}
		} else {
			if(this.coords.get(0).getRow() < this.coords.get(1).getRow()) {
				Dimension head = this.coords.get(0);
				this.coords.remove(0);
				this.coords.add(head);
			}
		}
	}
	
	public String getColor() {
		return color;
	}
	
	public Direction getDirection() {
		return dir;
	}
	
	/**
	 * Checks if car is UPDOWN or SIDEWAYS, and setsDirection
	 */
	public void setDirection() {
		if (this.coords.get(0).getRow() == this.coords.get(1).getRow()) {
			this.dir = Direction.SIDEWAYS;
		} else if (this.coords.get(0).getCol() == this.coords.get(1).getCol()) {
			this.dir = Direction.UPDOWN;
		}
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public ArrayList<Dimension> getCoords() {
		return coords;
	}

	public void setCoords(ArrayList<Dimension> coords) {
		this.coords = coords;
	}
	
	/**
	 * Returns true if cars coordinate arrayList contains input
	 * @param place
	 * @return Boolean
	 */
	public boolean isAt(Dimension place) {
		for (Dimension coord : this.coords) {
			if (coord.getCol() == place.getCol() && coord.getRow() == place.getRow()) {
				return true;
			}
		}
		
		return false;
	}
	
	public String coords() {
		StringBuilder str = new StringBuilder();
		int i = 0;
		for (Dimension coord : this.coords) {
			if (i == 1 && !coord.equals(this.coords.get(this.coords.size() - 1))) {
				i++;
				continue;
			}
			str.append(coord.getDims());
			if (coord != this.coords.get(this.coords.size() - 1)) {
				str.append(", ");
			}
			i++;
		}
		return str.toString();
	}
	
//	public void printDims() {
//		for (Dimension coord : this.coords) {
//			coord.printDims();
//			if (coord != this.coords.get(this.coords.size() - 1)) {
//				System.out.printf(", ");
//			}
//		}
//		System.out.println();
//	}
	
	public void moveLeft() {
		for (Dimension coord : this.coords) {
//			System.out.println(coord.getCol() - 1);
			coord.setCol(coord.getCol() - 1);
		}
	}
	
	public void moveRight() {
		for (Dimension coord : this.coords) {
//			System.out.println(coord.getCol() - 1);
			coord.setCol(coord.getCol() + 1);
		}
	}

	public void moveUp() {
		for (Dimension coord : this.coords) {
//			System.out.println(coord.getCol() - 1);
			coord.setRow(coord.getRow() - 1);
		}
	}

	public void moveDown() {
		for (Dimension coord : this.coords) {
//			System.out.println(coord.getCol() - 1);
			coord.setRow(coord.getRow() + 1);
		}
	}
	
}