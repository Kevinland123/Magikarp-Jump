import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Paddle {
	private double x;
	private double y;
	private double width;
	private double height;

	private Image i = new Image("Rectangle.png");

	public Paddle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(i, x, y, width, height);
	}
	
	public void left(int d) {
		this.x = this.x - d;
	}
	
	public void right(int d) {
		this.x = this.x + d;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}