import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Box {
	private double x;
	private double y;
	private double width;
	private double height;
	
	private int red = (int) (Math.random() * 255);
	private int green = (int) (Math.random() * 255);
	private int blue = (int) (Math.random() * 255);

	public Box(double newx, double newy, double newwidth, double newheight) {
		x = newx;
		y = newy;
		width = newwidth;
		height = newheight;
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.rgb(red, green, blue));
		gc.fillRect(x, y, width, height);
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

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
	
	public void setColor(int red, int green, int blue) {
		this.red = red;
	}
}