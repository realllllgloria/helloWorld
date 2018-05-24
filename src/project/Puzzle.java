package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Puzzle {

	String title;
	private ArrayList<Car> cars = new ArrayList<Car>();
	
	Puzzle(String title) {
		this.title = title;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}
	
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
	
	public void addCar(Car c) {
		cars.add(c);
	}
	
	public void retrieveCars(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			ArrayList<Dimension> coords = new ArrayList<Dimension>();
			String arr[] = line.split(", ");
			String colour = arr[0];
			String head[] = arr[1].split("-");
			Dimension hCoord = new Dimension(Integer.parseInt(head[1]), Integer.parseInt(head[0]));
			coords.add(hCoord);
			String tail[] = arr[2].split("-");
			Dimension tCoord = new Dimension(Integer.parseInt(tail[1]), Integer.parseInt(tail[0]));
			coords.add(tCoord);
			Car c = new Car(coords, colour);
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
			cars.add(c);
		}
		sc.close();
	}
	
	public void shuffleCars() {
		Car first = cars.get(0);
		cars.remove(0);
		Collections.shuffle(cars);
		cars.add(0, first);
	}
	
	public String getTitle() {
		return title;
	}
	
}
