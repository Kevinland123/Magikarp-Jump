import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ball{
	private int x;
	private int y;
	private double s;
	
	public ball(int x, int y, double size) {
		this.x = x;
		this.y = y;
		this.s = size;
	}
	
	public void draw(GraphicsContext gc) {
		gc.fillOval(x, y, s, s);
	}
}