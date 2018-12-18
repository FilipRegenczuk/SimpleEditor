package wersja5;

import java.awt.Color;
import java.awt.Graphics;

public class Line {

	private int x;
	private int y;
	private int dx;
	private int dy;
	private Color color = Color.BLACK;
	
	public Line(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public void move(int mx, int my) {
		x += mx;
		y += my;
		dx += mx;
		dy += my;
	}
	

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x, y, dx, dy);
	}
}
