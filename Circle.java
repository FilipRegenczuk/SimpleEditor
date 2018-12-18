package wersja5;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {

	private int x;
	private int y;
	private int r = 10;
	private Color color = Color.RED;
	
	public Circle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int mx, int my) {
		x += mx;
		y += my;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
	}
}
