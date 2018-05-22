package project;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Generator {
 
	private static ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
	
	/**
	 * Creates list of puzzles from files
	 * A puzzle is a unique initial board position
	 * @param args
	 * @throws IOException 
	 */
	public Generator(String diffLevel) throws IOException {
		/* Get files and create board from file */
//	    File[] files = new File(System.getProperty("user.dir") + "/src/project/Boards").listFiles();
//	    for (File file : files) {
//	    		if (file.getName().contains("board")) {
//		    		Puzzle p = new Puzzle(file.getName());
//		    		p.setCars(file);
//		    		puzzles.add(p);
//	    		}
//	    }
		
		Puzzle p = genPuzzle(diffLevel);
		State s = new State(null, p.getCars());
//		s.showCars();
		System.out.println("Start position:");
		s.showBoard();
		System.out.println();
//		s.moveLeft(s.getCars().get(0));
		System.out.println();
//		s.showCars();
//		s.showBoard();
		s.shufflePuzzle(diffLevel);
		System.out.println("End position:");
		s.showBoard();
		s.toFile(diffLevel);

	}
	
	public static void printCars(String title) {
		for (Puzzle p : Generator.puzzles) {
			if (p.getTitle().equals(title)) {
				for (Car c : p.getCars()) {
					System.out.printf(c.getColor() + ", ");
					for (Dimension d : c.getCoords()) {
						d.printDims();
						if (d != c.getCoords().get(c.getCoords().size() - 1)) {
							System.out.printf(", ");
						}
					}
					System.out.println(", " + c.getDirection() + ".");
				}
				
			}
		}
	}

	public static Puzzle genPuzzle(String level) {
		Puzzle p = new Puzzle("test");
		ArrayList<Car> cars = new ArrayList<Car>();
		cars.add(darkBlueCar());
		cars.add(greenCar());
		cars.add(darkGreenCar());
		cars.add(pinkCar());
		cars.add(tealCar());
		cars.add(limeCar());
		cars.add(blackCar());
		cars.add(orangeCar());
		cars.add(yellowCar());
		cars.add(brownCar());
		cars.add(blueCar());
		cars.add(darkPurpleCar());
		Collections.shuffle(cars);
		ArrayList<Car> subs = cars;
		
		// Distinguish difficulty with number of cars
		if (level.equals("Easy")) {
			subs = new ArrayList<Car>(cars.subList(0, 7));
		} else if (level.equals("Normal")) {
			subs = new ArrayList<Car>(cars.subList(0, 9));
		} else if (level.equals("Hard")){
			subs = new ArrayList<Car>(cars.subList(0, 11));
		}
		
		// To fill in gap for cars with length of 3.
		for (Car c : subs) {
//			System.out.println(c.getColor() + ", " + c.getDirection() + ", " + c.getLength());
			if (c.getLength() == 3) {
				if (c.getDirection().equals(Direction.SIDEWAYS)) {
					ArrayList<Dimension> temp = c.getCoords();
					Dimension d = new Dimension(c.getCoords().get(0).getCol() - 1, c.getCoords().get(0).getRow());
					temp.add(1, d);
					c.setCoords(temp);
				} else {
					ArrayList<Dimension> temp = c.getCoords();
					Dimension d = new Dimension(c.getCoords().get(0).getCol(), c.getCoords().get(0).getRow() - 1);
					temp.add(1, d);
					c.setCoords(temp);
				}
			}
		}
		
		subs.add(0, redCar());
		
		p.setCars(subs);
		return p;
	}

	public static Car redCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(5, 2);
		Dimension tail = new Dimension(4, 2);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Red");
		return c;
	}

	public static Car darkBlueCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(0, 0);
		Dimension tail = new Dimension(2, 0);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "DarkBlue");
		return c;
	}
	
	public static Car greenCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(0, 3);
		Dimension tail = new Dimension(1, 3);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Green");
		return c;
	}
	
	public static Car darkGreenCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(0, 5);
		Dimension tail = new Dimension(1, 5);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "DarkGreen");
		return c;
	}
	
	public static Car limeCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(3, 0);
		Dimension tail = new Dimension(3, 1);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Lime");
		return c;
	}
	
	public static Car tealCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(5, 0);
		Dimension tail = new Dimension(5, 1);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Teal");
		return c;
	}
	
	public static Car pinkCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(3, 5);
		Dimension tail = new Dimension(2, 5);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Pink");
		return c;
	}

	public static Car blackCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(4, 0);
		Dimension tail = new Dimension(4, 1);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Black");
		return c;
	}
	
	public static Car orangeCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(1, 4);
		Dimension tail = new Dimension(0, 4);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Orange");
		return c;
	}
	
	public static Car brownCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(2, 3);
		Dimension tail = new Dimension(2, 4);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Brown");
		return c;
	}
	
	public static Car blueCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(3, 3);
		Dimension tail = new Dimension(3, 4);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Blue");
		return c;
	}
	
	public static Car yellowCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(4, 3);
		Dimension tail = new Dimension(4, 5);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "Yellow");
		return c;
	}

	public static Car darkPurpleCar() {
		ArrayList<Dimension> coords = new ArrayList<Dimension>();
		Dimension head = new Dimension(5, 3);
		Dimension tail = new Dimension(5, 5);
		coords.add(head);
		coords.add(tail);
		Car c = new Car(coords, "DarkPurple");
		return c;
	}
	
}