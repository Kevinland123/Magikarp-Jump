import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Panda {
	public double x;
	public double y;
	private double size;
	public double vx = Math.random() * 2 + 2;
	public double vy = (Math.random() * 2 + 3) * -1;
	private Image img = new Image("Panda.png");

	public Panda(double x, double y, double size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(img, x, y, size, size);
	}

	public void move(double width, double height) {
		x = x + vx;
		y = y + vy;

		if ((x + size > width || x < -10)) {
			vx = vx * -1.01;
		}

		if (y + size > height || y < -10) {
			vy = vy * -1;
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
	
	public boolean didHit(Box s) {
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

	public double gety() {
		return y;
	}

	public double getSize() {
		return size;
	}

	public double getvx() {
		return vx;
	}

	public double getvy() {
		return vy;
	}

	public void setx(double newx) {
		x = newx;
	}

	public void sety(double newy) {
		y = newy;
	}

	public void setsize(double newsize) {
		size = newsize;
	}

	public void setvx(double newvx) {
		vx = newvx;
	}

	public void setvy(double newvy) {
		vy = newvy;
	}
}
