package direction;

public abstract class Move {

	private int distanceToMove;
	
	
	public Move(int distanceToMove) {
		this.distanceToMove = distanceToMove;
	}
	
	
	public int getDistanceToMove() {
		return distanceToMove;
	}
	
	public abstract int move(int currentPosition);
	
	public abstract int nextMove();
	
}
