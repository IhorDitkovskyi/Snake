package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class SnakeGameMain extends JPanel implements ActionListener {

	public static JFrame jframe;
	public static final int SCALE = 32;
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	public static int speed = 10;

	Snake s = new Snake(5, 6, 5, 5);
	Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1)),
			Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1)));

	Timer timer = new Timer(1000 / speed, this);

	public SnakeGameMain() {
		timer.start();
		addKeyListener(new KeyBoard());
		setFocusable(true);
	}

	public void paint(Graphics g) {
		// поле
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

		// с≥тка
		for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(x, 0, x, HEIGHT * SCALE);
		}

		// с≥тка
		for (int y = 0; y < HEIGHT * SCALE; y += SCALE) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, y, WIDTH * SCALE, y);
		}

		// вимальовуЇм зм≥йку
		for (int l = 1; l < s.length; l++) {
			g.setColor(Color.GRAY);
			g.fillRect(s.sX[l] * SCALE + 3, s.sY[l] * SCALE + 3, SCALE - 6, SCALE - 6);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(s.sX[0] * SCALE + 3, s.sY[0] * SCALE + 3, SCALE - 6, SCALE - 6);
		}

		// малюЇм €бко
		g.setColor(Color.red);
		g.fillOval(apple.posX * SCALE + 4, apple.posY * SCALE + 4, SCALE - 8, SCALE - 8);
	}

	public static void main(String[] args) {

		jframe = new JFrame("Title");
		jframe.setSize(WIDTH * SCALE + 7, HEIGHT * SCALE + 30);
		jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);

		jframe.add(new SnakeGameMain());

		jframe.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		s.move();

		// робим шоб зм≥йка зб≥льшувалась коли захаваЇ €бко
		if ((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)) {
			apple.setRandomPosition();
			s.length++;
		}

		// ф≥ксим, шоб €бко не з'€вл€лось на сам≥й зм≥йц≥
		for (int l = 1; l < s.length; l++) {

			if ((s.sX[1] == apple.posX) && (s.sY[1] == apple.posY)) {
				apple.setRandomPosition();
			}

			// пов≥домленн€ коли креш ≥ обнуленн€
			if ((s.sX[0] == s.sX[1]) && (s.sY[0] == s.sX[1])) {
				timer.stop();
				JOptionPane.showMessageDialog(null, "“и програв. ѕочати заново?");
				jframe.setVisible(false);
				s.length = 2;
				s.direction = 0;
				apple.setRandomPosition();
				jframe.setVisible(true);
				timer.start();
			}
		}

		repaint();
	}

	public class KeyBoard extends KeyAdapter {

		public void keyPressed(KeyEvent event) {
			int key = event.getKeyCode();

			if ((key == KeyEvent.VK_UP) && (s.direction != 2))
				s.direction = 0;
			if ((key == KeyEvent.VK_DOWN) && (s.direction != 0))
				s.direction = 2;
			if ((key == KeyEvent.VK_RIGHT) && (s.direction != 3))
				s.direction = 1;
			if ((key == KeyEvent.VK_LEFT) && (s.direction != 1))
				s.direction = 3;

		}
	}

}
