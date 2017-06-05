import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EvilBalls {
	public double x;
	public double y = 0;
	private double size;
	public double vy = Math.random() * 5 + 1;
	private Image img = new Image("devil.png");

	public EvilBalls(double x, double size) {
		this.x = x;
		this.size = size;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(img, x, y, size, size);
	}

	public void move(double w, double h) {
		y = y + vy;

		if (y > h) {
			y = 0;
			x = Math.random() * w;
		}
	}

	public boolean didHit(Paddle s) {
		double sx = s.getX() + (s.getWidth() / 2);
		double sy = s.getY() + (s.getHeight() / 2);
		double tx = this.getx() + (this.getSize() / 2);
		double ty = this.gety() + (this.getSize() / 2);

		if (Math.abs(sx - tx) < (s.getWidth() + this.getSize()) / 2
				&& Math.abs(sy - ty) < (s.getHeight() + this.getSize() / 2)) {
			return true;

		}
		return false;
	}

	public double getx() {
		return x;
	}

	public void setx(double newx) {
		x = newx;
	}

	public double gety() {
		return y;
	}

	public void sety(double newy) {
		y = newy;
	}

	public double getSize() {
		return size;
	}

	public void setsize(double newsize) {
		size = newsize;
	}

	public double getvy() {
		return vy;
	}

	public void setvy(double newvy) {
		vy = newvy;
	}
}