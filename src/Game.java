import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import tsinn.ui.SimpleApp;

public class Game extends SimpleApp {
	public Paddle p;
	public Panda ball;

	public ArrayList<Box> bricks = new ArrayList<Box>();
	public ArrayList<EvilBalls> balls = new ArrayList<EvilBalls>();

	public boolean game = true;
	public int score = 0;
	public int pscore = 0;
	public int page = 1;
	public int death = 1;
	public int distance = 5;

	public boolean add1 = false;
	public boolean add2 = false;
	public boolean add3 = false;
	
	public boolean left = false;
	public boolean right = false;

	public boolean cheat = false;
	public boolean cheatcode = false;
	public int typetimer = 0;
	public int letter = 1;

	public boolean cheat1 = false;
	public boolean cheat2 = false;
	public boolean cheat3 = false;
	public boolean cheat4 = false;

	public Image spikes = new Image("spikes.png");

	public static void main(String[] args) {
		Application.launch();
	}

	public void updateAnimation(long arg0) {
		if (typetimer >= 1) {
			typetimer = typetimer - 1;
		}
	}

	public void draw(GraphicsContext gc) {
		display(gc);

		if (page == 1) {
			mechanics();
		}
	}

	public void display(GraphicsContext gc) {
		if (page == 1) {
			// Objects
			p.draw(gc);

			ball.draw(gc);
			ball.move(getWidth(), getHeight());

			for (int i = 0; i < getWidth() / spikes.getWidth(); i++) {
				gc.drawImage(spikes, 121 * i, getHeight() - spikes.getHeight() / 2);
			}

			for (Box b : bricks) {
				b.draw(gc);
			}

			for (EvilBalls b : balls) {
				b.draw(gc);
			}

			for (EvilBalls b : balls) {
				b.move(getWidth(), getHeight());
			}

			// Non-Objects
			gc.fillText("Score: " + score, getWidth() - 100, 50);
		}

		if (page == 2) {
			gc.setFont(Font.font("Verdana", 20));
			if (death == 1) {
				gc.fillText("Reason of Death: Stupidity", getWidth() / 2.5, getHeight() / 2);
			}

			if (death == 2) {
				gc.fillText("Reason of Death: Too fat to dodge", getWidth() / 2.5, getHeight() / 2);
			}
		}

		if (page == 3) {
			gc.fillText("Enter Cheat Code: " + (6 - letter) + " " + typetimer, getWidth() / 2, getHeight() / 2);
		}
	}

	public void mechanics() {
		// Moving paddles
		if (left == true) {
			p.left(distance);
		}
		
		if (right == true) {
			p.right(distance);
		}
		
		// Death
		if (ball.gety() > getHeight() - spikes.getHeight() / 2 && cheat1 == false) {
			page = 2;
			death = 1;
		}

		// Paddle hit ball
		if (ball.didHit(p) == true) {
			ball.setvy(-ball.getvy());

			if (ball.getx() > (p.getX() + p.getWidth() / 2)) {
				//ball.setvx(Math.abs(ball.getvx()));
			}

			if (ball.getx() < (p.getX() + p.getWidth() / 2)) {
				//ball.setvx(Math.abs(ball.getvx()) * -1);
			}
		}

		// Bricks hit ball
		for (int i = 0; i < bricks.size(); i++) {
			if (ball.didHit(bricks.get(i))) {
				if ((ball.getx() + ball.getSize() < bricks.get(i).getX() || (ball.getx() > bricks.get(i).getX() + bricks.get(i).getWidth())) 
						&& (ball.gety() < bricks.get(i).getY() + bricks.get(i).getHeight() && ball.gety() + ball.getSize() < bricks.get(i).getY())) {
					ball.setvx(-ball.getvx());
				} else {
					ball.setvy(-ball.getvy());
				}
				
				score++;
				bricks.remove(i);
			}
		}

		// Balls hit paddle
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).didHit(p) && cheat1 == false) {
				page = 2;
				death = 2;
			}
		}

		// Adding evil balls
		if (score >= 10 && add1 == false) {
			addBall();
			add1 = true;
		}

		if (score >= 30 && add2 == false) {
			addBall();
			add2 = true;
		}

		if (score >= 60 && add3 == false) {
			addBall();
			add3 = true;
		}
		
		// Color flashing
		if (cheat1 == true) {
			for (Box b : bricks) {
				b.setColor((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
			}
		}
	}

	public void drawBricks() {
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 12; i++) {
				// bricks.add(new Box(65 * i + 10, 35 * j + 10, 60, 30));
				bricks.add(new Box(getWidth() / 14 * i + 100, getHeight() / 18 * j + 10, getWidth() / 15,
						getHeight() / 20));
			}
		}
	}

	public void addBall() {
		balls.add(new EvilBalls(Math.random() * getWidth(), 20));
	}

	public void setupApp(GraphicsContext arg0) {
		p = new Paddle(getWidth() / 2 - 75, getHeight() * 9 / 10, 150, 20);
		ball = new Panda(getWidth() / 2, getHeight() * 4 / 5, 40);

		drawBricks();
	}

	public void onMousePressed(MouseEvent m) {
		if (page == 2) {
			bricks.clear();
			balls.clear();
			drawBricks();
			score = 0;

			p.setX(getWidth() / 2 - 75);
			p.setY(getHeight() * 9 / 10);
			ball.setx(getWidth() / 2);
			ball.sety(getHeight() * 4 / 5);
			ball.setvx(Math.random() + 3);
			ball.setvy((Math.random() + 3) * -1);
			page = 1;
		}
	}
	
	public void onKeyReleased(KeyEvent key) {
		if (page == 1 || page == 2) {
			left = false;
			right = false;
		}
	}

	public void onKeyPressed(KeyEvent key) {
		if (page == 1 || page == 2) {
			if (key.getCode() == KeyCode.A) {
				left = true;
			}

			if (key.getCode() == KeyCode.D) {
				right = true;
			}

			if (key.getCode() == KeyCode.SPACE) {
				page = 3;
				typetimer = 20;
			}

			// Cheating
			if (cheat == true) {
				if (key.getCode() == KeyCode.DIGIT1 && cheat1 == false) {
					cheat1 = true;
				} else if (key.getCode() == KeyCode.DIGIT1 && cheat1 == true) {
					cheat1 = false;
				}

				if (key.getCode() == KeyCode.DIGIT2 && cheat2 == false) {
					cheat2 = true;
					distance = 10;
				} else if (key.getCode() == KeyCode.DIGIT2 && cheat2 == true) {
					cheat2 = false;
					distance = 5;
				}

				if (key.getCode() == KeyCode.DIGIT3 && cheat3 == false) {
					cheat3 = true;
					p.setWidth(300);
				} else if (key.getCode() == KeyCode.DIGIT3 && cheat3 == true) {
					cheat3 = false;
					p.setWidth(150);
				}

				if (key.getCode() == KeyCode.DIGIT4 && cheat4 == false) {
					cheat4 = true;
					death = 1;
				} else if (key.getCode() == KeyCode.DIGIT4 && cheat4 == true) {
					cheat4 = false;
					death = 2;
				}
			}
		}

		if (page == 3) {
			if (typetimer < 1) {
				if (letter == 1) {
					if (key.getCode() == KeyCode.P && typetimer < 1) {
						letter++;
						typetimer = 10;
					} else {
						letter = 1;
						page = 1;
					}
				}
				if (typetimer < 1) {
					if (letter == 2) {
						if (key.getCode() == KeyCode.A && typetimer < 1) {
							letter++;
							typetimer = 10;
						} else {
							letter = 1;
							page = 1;
						}
					}
					if (typetimer < 1) {
						if (letter == 3) {
							if (key.getCode() == KeyCode.N && typetimer < 1) {
								letter++;
								typetimer = 10;
							} else {
								letter = 1;
								page = 1;
							}
						}

						if (typetimer < 1) {
							if (letter == 4) {
								if (key.getCode() == KeyCode.D && typetimer < 1) {
									letter++;
									typetimer = 10;
								} else {
									letter = 1;
									page = 1;
								}
							}

							if (typetimer < 1) {
								if (letter == 5) {
									if (key.getCode() == KeyCode.A && typetimer < 1) {
										cheat = true;
										page = 1;
										typetimer = 10;
									} else {
										letter = 1;
										page = 1;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}