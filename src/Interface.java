
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Interface extends JFrame {


private static final int PAD_OFFSET_X = 15;
private static final int PAD_OFFSET_Y = 85;
private static final int BUTTON_WIDTH = 50;
private static final int BUTTON_HEIGHT = 30;
private static final int HZ_MARGIN = 8;
private static final int VT_MARGIN = 8;
private static final int PAD_GRID_ROW = 6;

private static final Insets NO_INSETS = new Insets(0, 0, 0, 0);
private static final Color BG_GENERIC = new Color(217, 228, 241);
private static final int PAD_GRID_COL = 10;
private static final Font GLOBAL_FONT = new Font("Lucida Sans Unicode", Font.PLAIN, 13);
private static final Font MENU_FONT = new Font("Verdana", Font.PLAIN, 13);
private static final Font SCREEN_FONT = new Font("Cambria Math", Font.PLAIN, 24);
private static final Color BG_SCREEN = new Color(238, 242, 247);
private final Calculatrice calc = new Calculatrice();
private String numMode = null;
private final JLabel[] scrLabels = new JLabel[3];
private final JButton[] buttons = new JButton[54];
private String sysMode = null;


public static void main(String[] args) {
	new Interface().launchFrame();
}


public Interface() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(null);
	this.setResizable(false);
	this.drawVisualComponents();
	this.getContentPane().setBackground(BG_GENERIC);
	this.setLocationRelativeTo(null);
	this.setSize(608, 373);
	this.setTitle("Calculatrice scientifique");
	
}

private void drawVisualComponents() {
	JRadioButton[] radioButtons = new JRadioButton[4];
	JPanel scrPanel = new JPanel();
	ButtonGroup buttonGroup = null;
	JPanel hexPanel = new JPanel();
	
	String[] texts = {							Calculatrice.DGA, Calculatrice.MCX, Calculatrice.MRX, Calculatrice.MSX, Calculatrice.MPX, Calculatrice.MMX,
		Calculatrice.XXX, Calculatrice.NLG, Calculatrice.BR1, Calculatrice.BR2, Calculatrice.DGB, Calculatrice.BSP, Calculatrice.CEX, Calculatrice.CXX, Calculatrice.NEG, Calculatrice.SRT,
		Calculatrice.AVG, Calculatrice.SIN, Calculatrice.FCT, Calculatrice.SQR, Calculatrice.DGC, Calculatrice.DG7, Calculatrice.DG8, Calculatrice.DG9, Calculatrice.DIV, Calculatrice.PER,
		Calculatrice.SUM, Calculatrice.COS, Calculatrice.CRT, Calculatrice.CUB, Calculatrice.DGD, Calculatrice.DG4, Calculatrice.DG5, Calculatrice.DG6, Calculatrice.MUL, Calculatrice.REC,
		Calculatrice.LST, Calculatrice.TAN, Calculatrice.INT, Calculatrice.POW, Calculatrice.DGE, Calculatrice.DG1, Calculatrice.DG2, Calculatrice.DG3, Calculatrice.SUB, Calculatrice.EQU,
		Calculatrice.CLS, Calculatrice.LOG, Calculatrice.MOD, Calculatrice.PWT, Calculatrice.DGF, Calculatrice.DG0, Calculatrice.DOT, Calculatrice.ADD};
	
	
	
	scrPanel.setBackground(BG_SCREEN);
	scrPanel.setLocation(PAD_OFFSET_X, PAD_OFFSET_X);
	scrPanel.setSize((BUTTON_WIDTH + HZ_MARGIN) * PAD_GRID_COL - HZ_MARGIN,
		PAD_OFFSET_Y - scrPanel.getY() - VT_MARGIN);
	scrPanel.setBorder(new LineBorder(Color.GRAY, 1));
	scrPanel.setLayout(null);
	
	
	
	hexPanel.setBackground(BG_GENERIC);
	hexPanel.setLayout(null);
	hexPanel.setLocation(PAD_OFFSET_X, PAD_OFFSET_Y);
	hexPanel.setBorder(new LineBorder(Color.GRAY, 1));
	hexPanel.setSize((BUTTON_WIDTH + HZ_MARGIN) * 4 - HZ_MARGIN, BUTTON_HEIGHT);
	
	buttonGroup = new ButtonGroup();
	for (int i = 0; i < radioButtons.length; i++) {
		radioButtons[i] = new JRadioButton(i == 0 ? Calculatrice.HEX :
			i == 1 ? Calculatrice.DEC : i == 2 ? Calculatrice.OCT : Calculatrice.BIN);
		radioButtons[i].setBackground(BG_GENERIC);
		radioButtons[i].setFocusPainted(false);
		radioButtons[i].setFont(new Font(GLOBAL_FONT.getName(),
			GLOBAL_FONT.getStyle(), GLOBAL_FONT.getSize() - 2));
		radioButtons[i].setMargin(NO_INSETS);
		radioButtons[i].setSize(hexPanel.getWidth() / 4 - 4, 20);
		radioButtons[i].setLocation(radioButtons[i].getWidth() * i + 9, 5);
		
		
		
		if (i == 1) radioButtons[i].setSelected(true);
		buttonGroup.add(radioButtons[i]);
		hexPanel.add(radioButtons[i]);
	}
	
	for (int i = 0, x = 4, y = 0; i < buttons.length; i++) {
		buttons[i] = new JButton(texts[i]);
		buttons[i].setMargin(NO_INSETS);buttons[i].setFocusPainted(false);
		buttons[i].setFont(GLOBAL_FONT);
		buttons[i].setLocation(PAD_OFFSET_X + (BUTTON_WIDTH + HZ_MARGIN) * x,
				PAD_OFFSET_Y + (BUTTON_HEIGHT + VT_MARGIN) * y);
		buttons[i].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		
		if (i > 0 && i < 6) buttons[i].setBackground(BG_GENERIC);
		
		if (texts[i] == Calculatrice.XXX || texts[i] == Calculatrice.PER) {
			buttons[i].setBackground(BG_GENERIC);
			buttons[i].setEnabled(false);
		} else if (texts[i] == Calculatrice.EQU) {
			buttons[i].setSize(BUTTON_WIDTH, BUTTON_HEIGHT * 2 + VT_MARGIN);
		} else if (texts[i] == Calculatrice.DG0) {
			buttons[i].setSize(BUTTON_WIDTH * 2 + HZ_MARGIN, BUTTON_HEIGHT);
			x++;
		}
		
	
		
		this.getContentPane().add(buttons[i]);
		if (++x >= PAD_GRID_COL) {
			x = 0; y++;
		}
	}
	
	this.getContentPane().add(scrPanel);
	this.getContentPane().add(hexPanel);
}


private void setDecEnabled(boolean b) {
	int[] arr = {21, 22, 23, 31, 32, 33, 42, 43};
	for (int i : arr) buttons[i].setEnabled(b);
}


private void setHexEnabled(boolean b) {
	for (int i = 0; i < buttons.length; i += 10)
		buttons[i].setEnabled(b);
}

public void launchFrame() {
	//this.pack();
	setSysMode(Calculatrice.SCI);
	setNumMode(Calculatrice.DEC);
	this.setVisible(true);
}

private void setNumMode(String mode) {
	switch (mode) {
		case Calculatrice.HEX:
			setDecEnabled(true);
			setHexEnabled(true);
			break;
	}
	buttons[52].setEnabled(mode == Calculatrice.DEC);
	numMode = mode;
	
}

private void setSysMode(String mode) {
	boolean b = mode == Calculatrice.SCI;
	for (int i = 6; i < buttons.length; i += 10)
		for (int j = i > 6 ? i : 7; j < i + 4; j++)	// Exclude XXX
			buttons[j].setEnabled(b);
	sysMode = mode;
	
}
}
