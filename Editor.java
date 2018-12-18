package wersja5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Editor extends JFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private static String AUTHOR = "Filip Regeñczuk";
	private static String INSTRUCTION = "Instrukcja";
	
	Drawing drawing = new Drawing();
	
	// elementy menu
	JMenuBar menu = new JMenuBar();
	JMenu menuFile = new JMenu("File");
	JMenu menuAboutProgram = new JMenu("About program");
	JMenuItem menuNew = new JMenuItem("New");
	JMenuItem menuExit = new JMenuItem("Exit");
	JMenuItem menuInstruction = new JMenuItem("Instruction");
	JMenuItem menuAuthor = new JMenuItem("Author");
	
	
	
	// wsp myszki
	private int mouseX = 0;
	private int mouseY = 0;
	
	// wsp odcinka
	private int lineX = 0;
	private int lineY = 0;
	
	// sprawdza czy shift zostal wcisniety
	private boolean isShiftDown;
	
	public static void main(String[] args) {
		Editor editor = new Editor();
		editor.runThread();
		editor.runThread2();
	}
	
	
	public Editor() {
		super(AUTHOR);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		setLocationRelativeTo(null);
		
		addMouseListener(this);
		addKeyListener(this);
		createMenu();
		drawing.createClock();
		
		setFocusable(true);
		requestFocus();
		
		setContentPane(drawing);
		setVisible(true);
	}
	
	
	private void createMenu() {
		menu.add(menuFile);
		menu.add(menuAboutProgram);
		menuFile.add(menuNew);
		menuFile.addSeparator();
		menuFile.add(menuExit);
		menuAboutProgram.add(menuInstruction);
		menuAboutProgram.add(menuAuthor);
		
		menuNew.addActionListener(this);
		menuExit.addActionListener(this);
		menuInstruction.addActionListener(this);
		menuAuthor.addActionListener(this);
		
		setJMenuBar(menu);
	}

	
	private void runThread() {
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				while (true) {
					drawing.moveCircle();
					drawing.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	
	private void runThread2() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					drawing.clock.setText("" + drawing.time);
					drawing.time++;
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	
	public void move(int x, int y) {
		for (Circle circle : drawing.getCircles()) {
			circle.move(x, y);
		}
		for (Line line : drawing.getLines()) {
			line.move(x, y);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == menuNew) {
			
		}
		if (source == menuExit) {
			System.exit(0);
		}
		if (source == menuInstruction) {
			JOptionPane.showMessageDialog(this, INSTRUCTION, "Instruction", JOptionPane.PLAIN_MESSAGE);
		}
		if (source == menuAuthor) {
			JOptionPane.showMessageDialog(this, AUTHOR, "Author", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		if (e.getButton() == 1) {
			requestFocus();
			
			if (isShiftDown){
				lineX = e.getX()-3;
				lineY = e.getY()-50;
				isShiftDown = false;
			}
			else if (lineX == 0 && lineY == 0) {
				lineX = e.getX()-3;
				lineY = e.getY()-50;
				
			}
			else {
				mouseX = e.getX()-3;
				mouseY = e.getY()-50;
				
				drawing.addLine(new Line(lineX, lineY, mouseX, mouseY));
				lineX = mouseX; 
				lineY = mouseY;
			}
		}
		
		if (e.getButton() == 3) {
			requestFocus();
			
			mouseX = e.getX()-3;
			mouseY = e.getY()-50;
			
			drawing.addCircle(new Circle(mouseX, mouseY));
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.isShiftDown()) {
			isShiftDown = true;
		}
		else {
			isShiftDown = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			drawing.deleteElements();
		}
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_LEFT:
			move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			move(1,0);
			break;
		case KeyEvent.VK_UP:
			move(0,-1);
			break;
		case KeyEvent.VK_DOWN:
			move(0,1);
			break;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
