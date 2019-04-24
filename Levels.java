import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//pseudo-randomly generates levels based on difficulty

public class Levels {
	final String difficulty;	//might not be necessary in this scope since no calculations done here require it
	final int totalScreenArea, leftEdge, rightEdge;
	double screenAreaOccupied = 0.0;
	List<Integer> xAvailable;
	List<Building> buildings;

	//params: screenWidth, screenHeight, difficulty
	public Levels(int screenWidth, int screenHeight, String difficulty) {
		this.difficulty = difficulty;
		totalScreenArea = screenWidth * screenHeight;
		leftEdge = (int)(screenWidth * .1);
		rightEdge = screenWidth - leftEdge;
		//https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java/22829036
		xAvailable = IntStream.rangeClosed(leftEdge, rightEdge).boxed().collect(Collectors.toList());
		generateBuildings(screenWidth, screenHeight);
	}

	//each building width shouldn't be greater than 20% of screen width
	//no buildings should occupy the left 10% of the screen and right 10% of the screen
	//each building height shouldn't be greater than 80% of screen height
	//total area of buildings shouldn't occupy greater than ~50% of screen area
	private void generateBuildings(int screenWidth, int screenHeight) {
		buildings = new ArrayList<Building>();
		while(screenAreaOccupied < .5) {
			//https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
			Building temp = new Building((int)(Math.random() * .2 * screenWidth), (int)(Math.random() * .8 * screenHeight),
					new Random().nextInt(rightEdge - leftEdge + 1) + leftEdge);
			if(validateBuildingPlacement(temp)) {
				buildings.add(temp);
				updateOccupiedSpace(temp);
			}
		}
	}

	private boolean validateBuildingPlacement(Building b) {
		if(b.getCenter() - b.getRadius() < leftEdge || b.getCenter() + b.getRadius() > rightEdge)
			return false;
		//https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java/22829036
		if(!xAvailable.containsAll(IntStream.rangeClosed(b.getCenter() - b.getRadius(), b.getCenter() + b.getRadius())
				.boxed().collect(Collectors.toList())))
			return false;
		return true;
	}

//	private void updateOccupiedSpace() {
//		double occupiedSpace = 0;
//		for(Building b : buildings)
//			occupiedSpace += b.getArea();
//		screenAreaOccupied = occupiedSpace / totalScreenArea;
//	}

	private void updateOccupiedSpace(Building b) {
		screenAreaOccupied += b.getArea() / totalScreenArea;
		//https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java/22829036
		xAvailable.removeAll(IntStream.rangeClosed(b.getCenter() - b.getRadius(), b.getCenter() + b.getRadius())
				.boxed().collect(Collectors.toList()));
	}

	public List<Building> getBuildings() {
		return buildings;
	}
}

class Building {
	int width, height, center, radius;

	Building(int width, int height, int center) {
		this.width = width;
		this.height = height;
		this.center = center;
		this.radius = width / 2;
	}

	double getArea() {
		return width * height;
	}

	int getCenter() {
		return center;
	}

	int getRadius() {
		return radius;
	}
}
