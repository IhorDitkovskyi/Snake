package game;

public class Apple {

	public int posY;
	public int posX;

	public Apple(int x, int y) {
		posX = x;
		posY = y;
	}

	public void setRandomPosition() {
		posX = Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1));
		posX = Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1));
	}
}
