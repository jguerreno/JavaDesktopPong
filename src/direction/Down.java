package direction;


public class Down  extends Move {
	
	public Down(int distanceToMove) {
		super(distanceToMove);
	}
	
	
	public int move(int currentPosition) {
		return currentPosition + this.getDistanceToMove();
	}
	
	public int nextMove() {
		return this.getDistanceToMove();
	}
	
}
