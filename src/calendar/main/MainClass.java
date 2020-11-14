package calendar.main;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;
import calendar.CalendarPlanner;
public class MainClass extends JFrame {
	public MainClass() {
		initComponents();
	}
	private void initComponents() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Календарь");
		this.setLayout(new GridBagLayout());
		GridBagConstraints pen = new GridBagConstraints();
		pen.fill = GridBagConstraints.BOTH;
		pen.insets = new Insets(2,2,2,2);
		
//	init comp
		
//	end init
//	set comp
		
//	end set
		pen.gridx = 0;
		pen.gridy = 0;
		this.add(new CalendarPlanner(	((String) (new Date()).toString()).split(" ")	),pen);
		this.setSize(new Dimension(1000,700));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new MainClass().setVisible(true);
	}
}
/*
	javac -d bin -sourcepath src src/calendar/main/MainClass.java
	java -classpath bin calendar.main.MainClass
*/
