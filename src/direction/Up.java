package direction;


public class Up extends Move {

	public Up(int distanceToMove) {
		super(distanceToMove);
	}
	
	
	public int move(int currentPosition) {
		return currentPosition - this.getDistanceToMove();
	}
	
	public int nextMove() {
		return - this.getDistanceToMove();
	}
}
