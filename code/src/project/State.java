package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class State {
	private State prevState;
	private ArrayList<Car> cars;
	private int[][] board;
	
	/**
	 * Initial constructor, creates board inside
	 * @param prevState
	 * @param cars
	 */
	public State(State prevState, ArrayList<Car> cars) {
		super();
		this.cars = cars;
		this.board = new int[6][6];
		this.createBoard();
	}

	/**
	 * Creates a board by placing cars.
	 * @return
	 */
	void createBoard () {
		/* Place the rest of the cars */
		if (cars != null) {
			for (Car car : cars) {
				placeCar (car);
			}
		}
	}
	
	/**
	 * Places car on board, checking for overlaps and common errors
	 * @param car
	 * @param board
	 * @return True if all cars are placed correctly
	 */
	public boolean placeCar (Car car) {

		/* Use different ints based on color, helps for display in backend debugging */
		int color = carColorToInt(car.getColor());
		if (color == 0) {
			//invalid color
			return false;
		}
		/* Place the car */
		Dimension c = null;
		for (Dimension carCoord : car.getCoords()) {
			c = carCoord;
			if (this.board[carCoord.getRow()][carCoord.getCol()] == 0) {
				this.board[carCoord.getRow()][carCoord.getCol()] = color;
			} else {
				//Should be thrown if collision occurs
				return false;
			}
		}
		
		/* For cars length 3, this fills in their middle square */
		if (car.getLength() == 3) { 
			if (car.getDirection() == Direction.valueOf("SIDEWAYS")) {
				this.board[c.getRow()][c.getCol() + 1] = color;
			} else {
				this.board[c.getRow() + 1][c.getCol()] = color;
			}
		}
		
		return true;
	}
	
	/**
	 * Takes in a carColor string and matches it to an int value
	 * Used just for the backend display
	 * @param carColor
	 * @return Returns an int based on the color
	 */
	int carColorToInt (String carColor) {
		int color;
		switch (carColor) {
		case "Red":
			color = 1;
			break;
		case "DarkBlue":
			color = 2;
			break;
		case "LightBlue":
			color = 3;
			break;
		case "Yellow":
			color = 4;
			break;
		case "Purple":
			color = 5;
			break;
		case "Orange":
			color = 6;
			break;
		case "Green":
			color = 7;
			break;
		case "Teal":
			color = 8;
			break;
		case "Black":
			color = 9;
			break;
		case "DarkPurple":
			color = -1;
			break;
		case "Pink":
			color = -2;
			break;
		case "Lime":
			color = -3;
			break;
		case "Brown":
			color = -4;
			break;
		case "DarkGreen":
			color = -5;
			break;
		case "Blue":
			color = -6;
			break;
		default: color = 0;
		}
		
		return color;
	}
	
	/**
	 * Writes all board positions of the car to 0
	 * @param car
	 */
	public void removeCarFromBoard (Car car) {
		for (Dimension carCoord : car.getCoords()) {
			this.board[carCoord.getCol()][carCoord.getRow()] = 0;
		}
	}
	
	/**
	 * Returns true if redCar's coordinates contain (2, 5)
	 * @param board
	 * @param redCar
	 * @return 
	 */
	public boolean isGameOver (Car redCar) {
		return redCar.isAt(new Dimension (2, 5));
	}
	
	/**
	 * The backend runs on 'states', each move you make you call this function
	 * and set the current state equal to it.
	 * currState = makeMove(loc, dir, dist);
	 * @param car
	 * @param dir
	 * @param dist
	 * @return currState if unsuccessful, returns nextState if successful
	 */
	public State makeMove (Dimension loc, Direction dir, int dist) {
		/* Create a new carArray for the next state */
		Car car = findCarFromLoc (loc, this.getCars());
		if (car != null) {
			ArrayList<Car> nextCars = (ArrayList<Car>) this.getCars().clone();
			
			//Because java passes method args by value, the result of changeCarDim 
			//needs to be added to the array. That would result in an extra car
			//so we remove it here
			nextCars.remove(car);
			car = changeCarDim(car, loc, dir, dist);
			
			if (car == null) {
				return this;
			}
			
			nextCars.add(car);
			State nextState = new State (this, nextCars);
			return nextState;
		}

		return this;

	}
	
	/**
	 * Given a location and a list of cars, finds the car at that location
	 * @param loc
	 * @param cars
	 * @return Null if none found
	 */
	Car findCarFromLoc (Dimension loc, ArrayList<Car> cars) {
		for (Car car : cars) {
			if (car.isAt(loc)) {
				return car;
			}
		}
		return null;
	}
	
	/**
	 * Changes the row or column of a car by dist blocks
	 * @param car
	 * @param loc
	 * @param dir
	 * @param dist
	 * @return Returns null if invalid move. Returns a modified car if successful.
	 */
	public Car changeCarDim (Car car, Dimension loc, Direction dir, int dist) {
		/* Inc/dec all x coords if car wants to move sideways, same for up with y coords */
		if (car.getDirection() == Direction.UPDOWN) {
			if (dir == Direction.UP) {
				for (Dimension coord : car.getCoords()) {
					coord.setCol(coord.getCol() + dist);
					if (coord.getCol() > 5 || coord.getCol() < 0) {
						return null; //out of bounds
					}
				}
			} else if (dir == Direction.DOWN) {
				for (Dimension coord : car.getCoords()) {
					coord.setCol(coord.getCol() - dist);
					if (coord.getCol() > 5 || coord.getCol() < 0) {
						return null;
					}
				}
			} else {
				return null; //car's direction and movement direction don't sync
			}
		} else if (car.getDirection() == Direction.SIDEWAYS) {
			if (dir == Direction.RIGHT) {
				for (Dimension coord : car.getCoords()) {
					coord.setRow(coord.getRow() + dist);
					if (coord.getRow() > 5 || coord.getRow() < 0) {
						return null;
					}
				}
			} else if (dir == Direction.LEFT) {
				for (Dimension coord : car.getCoords()) {
					coord.setRow(coord.getRow() - dist);
					if (coord.getRow() > 5 || coord.getRow() < 0) {
						return null; 
					}
				}
			} else {
				return null; 
			}
		}
		
		return car;
	}
	
	/**
	 * Checks if a cars coordinates are out of bounds
	 * @param car
	 * @return False if out of bounds
	 */
	public boolean checkCarPosValid (Car car) {
		for (Dimension coord : car.getCoords()) {
			if (coord.getRow() > -1 && coord.getRow() < 6
					&& coord.getCol() > -1 && coord.getCol() < 6) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets previousState, can be used for undo
	 * @return
	 */
	public State getPrevState() {
		return prevState;
	}

	public void setPrevState(State prevState) {
		this.prevState = prevState;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public void showBoard() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(" " + board[i][j]);
			}
			System.out.println();
		}
	}

	public void moveRed(ArrayList<Car> cars) {
		for (Car c : cars) {
			if (c.getColor().equals("Red")) {
				if (canMove(c) == 0) { 
					moveLeft(c);
				} else if (canMove(c) == 1) {
					moveRight(c);
				}
				return;
			}
		}
	}
	
	public void moveLeft(Car c) {
		int row = c.getCoords().get(0).getRow();
		int col = c.getCoords().get(0).getCol();
		int colStart;
		int colNum;
		
		if (c.getLength() == 2) {
			colStart = col - 2;
			colNum = colStart + 2;
		} else {
			colStart = col - 3;
			colNum = colStart + 3;
		}
		for (int i = colStart; i >= 0; i--) {
			if (board[row][i] == 0) {
				c.moveLeft();
				board[row][colNum] = 0;
				board[row][i] = carColorToInt(c.getColor());
				colNum--;
			} else {
				return;
			}
		}
	}
	
	public void moveRight(Car c) {
		int row = c.getCoords().get(c.getLength() - 1).getRow();
		int col = c.getCoords().get(c.getLength() - 1).getCol();
		int colStart;
		int colNum;
		
		if (c.getLength() == 2) {
			colStart = col + 2;
			colNum = colStart - 2;
		} else {
			colStart = col + 3;
			colNum = colStart - 3;
		}
		for (int i = colStart; i <= 5; i++) {
			if (board[row][i] == 0) {
				c.moveRight();
				board[row][colNum] = 0;
				board[row][i] = carColorToInt(c.getColor());
				colNum++;
			} else {
				return;
			}
		}
	}
	
	public void moveUp(Car c) {
		int row = c.getCoords().get(0).getRow();
		int col = c.getCoords().get(0).getCol();
		int rowStart;
		int rowNum;
		
		if (c.getLength() == 2) {
			rowStart = row - 2;
			rowNum = rowStart + 2;
		} else {
			rowStart = row - 3;
			rowNum = rowStart + 3;
		}
		for (int i = rowStart; i >= 0; i--) {
			if (board[i][col] == 0) {
				c.moveUp();
				board[rowNum][col] = 0;
				board[i][col] = carColorToInt(c.getColor());
				rowNum--;
			} else {
				return;
			}
		}
	}
	
	public void moveDown(Car c) {
		int row = c.getCoords().get(c.getLength() - 1).getRow();
		int col = c.getCoords().get(c.getLength() - 1).getCol();
		int rowStart;
		int rowNum;
		
		if (c.getLength() == 2) {
			rowStart = row + 2;
			rowNum = rowStart - 2;
		} else {
			rowStart = row + 3;
			rowNum = rowStart - 3;
		}
		for (int i = rowStart; i <= 5; i++) {
			if (board[i][col] == 0) {
				c.moveDown();
				board[rowNum][col] = 0;
				board[i][col] = carColorToInt(c.getColor());;
				rowNum++;
			} else {
				return;
			}
		}
	}
	
	// Returns 0 if can move left, 1 for right, 2 for up, 3 for down, 4 for can't move.
	public int canMove(Car c) {
		if(c.getDirection().equals(Direction.SIDEWAYS)) {
			int row = c.getCoords().get(0).getRow();
			int lCol = c.getCoords().get(0).getCol();
			int rCol = c.getCoords().get(c.getLength() - 1).getCol();
			if (c.getLength() == 2) {
				lCol = lCol - 2;
				rCol = rCol + 2;
			} else {
				lCol = lCol - 3;
				rCol = rCol + 3;
			}
			if (lCol >= 0) {
				if (board[row][lCol] == 0) {
					return 0;
				}
			} else if (rCol <= 5) {
				if (board[row][rCol] == 0) {
					return 1;
				}
			}
		} else if(c.getDirection().equals(Direction.UPDOWN)) {
			int col = c.getCoords().get(0).getCol();
			int uRow = c.getCoords().get(0).getRow();
			int dRow = c.getCoords().get(c.getLength() - 1).getRow();
			if (c.getLength() == 2) {
				uRow = uRow - 2;
				dRow = dRow + 2;
			} else {
				uRow = uRow - 3;
				dRow = dRow + 3;
			}
			if (uRow >= 0) {
				if (board[uRow][col] == 0) {
					return 2;
				}
			} else if (dRow <= 5) {
				if (board[dRow][col] == 0) {
					return 3;
				}
			}
		}
		return 4;
	}
	
	public void showCars() {
		for (Car c : cars) {
//			c.printDims();
			System.out.printf(c.getColor() + ", " + c.getDirection() + ", colour = " + carColorToInt(c.getColor()) + ", length = " + c.getLength() + ", canMove = " + canMove(c) + ", ");
//			c.printDims();
		}
	}
	
	public void shufflePuzzle(String level) {
//		Car red = cars.get(0);
//		cars.remove(cars.get(0));
//		Collections.reverse(cars);
		int i = 0;
		int j = 0;
		
		if (level.equals("Easy")) {
			i = 30;
		} else if (level.equals("Normal")) {
			i = 45;
		} else if (level.equals("Hard")) {
			i = 60;
		}
		
		while (j < i) {
			for (Car c : cars) {
//				System.out.println(canMove(c) + ", " + c.getColor());
				if (c.getDirection().equals(Direction.SIDEWAYS)) {
					if (canMove(c) == 0) {
						moveLeft(c);
					} else if(canMove(c) == 1) {
						moveRight(c);
					}	
				} else if (c.getDirection().equals(Direction.UPDOWN)) {
					if(canMove(c) == 2) {
						moveUp(c);
					} else if(canMove(c) == 3) {
						moveDown(c);
					}
				} else { 
					if(canMove(c) == 4) {
						continue;
					}
				}
//				System.out.println();
				showBoard();
				System.out.println(canMove(c) + ", " + c.getColor());
				System.out.println();
//				showCars();
			}
//			showCars();
			moveRed(cars);
			Collections.shuffle(cars);
			j++;
		}
	}
	
	public void toFile(String level) throws IOException {
		    File files = new File(System.getProperty("user.dir") + "/src/project/Boards");
		    BufferedWriter writer = new BufferedWriter(new FileWriter(files + "/" + level + ".txt"));
		    for (Car c : cars) {
		    		writer.write(c.getColor() + ", " + c.coords() + ", " + c.getDirection());
		    		writer.newLine();
		    }
		     
		    writer.close();
	}
	
}
