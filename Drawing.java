package wersja5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Drawing extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// zegar dzialajacy w osobnym watku
	JTextField clock = new JTextField(4);
	JButton buttonRestart = new JButton("Restart");
	public int time = 0;
	
	// zliczacz odcinkow i kol
	JTextField lineCounter = new JTextField(5);
	JTextField circleCounter = new JTextField(5);
	private int lc = 0;
	private int cc = 0;
	
	// zmienne poruszajacego sie kola
	private int x = 170;
	private int y = 170;
	private int r = 20;
	
	// listy
	private List<Line> lines;
	private List<Circle> circles;
	
	public void moveCircle() {
		x++;
		
		if (x == 400) {
			x = 0;
		}
	}
	
	public Drawing() {
		lines = new LinkedList<Line>();
		circles = new LinkedList<Circle>();
		
		createCounters();
	}
	
	public void createClock() {
		add(clock);
		clock.setEditable(false);
		clock.setText("" + time);
		
		add(buttonRestart);
		buttonRestart.addActionListener(this);
		
	}

	private void createCounters() {
		add(lineCounter);
		add(circleCounter);
		
		lineCounter.setText("Lines: " + lc);
		circleCounter.setText("Circles: " + cc);
		lineCounter.setEditable(false);
		circleCounter.setEditable(false);
	}
	
	
	public Line[] getLines() {
		Line[] array = new Line[0];
		return lines.toArray(array);
	}
	
	
	public Circle[] getCircles() {
		Circle[] array = new Circle[0];
		return circles.toArray(array);
	}
	
	
	public void deleteElements() {
		lines = new LinkedList<Line>();
		circles = new LinkedList<Circle>();
		lc = 0;
		cc = 0;
		lineCounter.setText("Lines: " + lc);
		circleCounter.setText("Circles: " + cc);
	}
	
	
	public void addLine(Line line) {
		lines.add(line);
		lc++;
		lineCounter.setText("Lines: " + lc);
	}
	
	
	public void addCircle(Circle circle) {
		circles.add(circle);
		cc++;
		circleCounter.setText("Circles: " + cc);
	}
	
	
	
	public void draw(Graphics g) {
		// rysowanie kola
		g.setColor(Color.ORANGE);
		g.fillOval(x-r, y-r, 2*r, 2*r);
		
		// rysowanie odcinkow
		for (Line line : lines) {
			line.draw(g);
		}
		
		// rysowanie kol
		for (Circle circle : circles) {
			circle.draw(g);
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == buttonRestart) {
			time = 0;
		}
		
	}
	
	
}
