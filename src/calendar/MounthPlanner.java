package calendar;
import javax.swing.JPanel;
import calendar.YearPlanner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JOptionPane;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static calendar.CalendarPlanner.getTabPane;
import static calendar.methods.DateData.numbMounth;
import static calendar.CalendarPlanner.getButtonYear;
import clock.Clock;
//import java.util.Arrays;
public class MounthPlanner extends JPanel {
	private YearPlanner year;
	private GridBagConstraints pen;
	private static String[] date_massive;
	private static int mounth;
	private Clock clock;
	private Thread th_clock;
	public MounthPlanner(YearPlanner year, String[] date_massive) {
		this.year = year;
		this.date_massive = date_massive;
//		JOptionPane.showMessageDialog(null, Arrays.toString(date_massive), "Information", JOptionPane.PLAIN_MESSAGE);
		initPanel();
	}
	private void initPanel() {
		this.setLayout(new GridBagLayout());
		pen = new GridBagConstraints();
		pen.fill = GridBagConstraints.BOTH;
		pen.insets = new Insets(2,2,2,2);
		
		addInMounth(numbMounth(date_massive[1]));
	}		
//	покажи числа в каждом месяце года
	private void showOnMounth() {
		JOptionPane.showMessageDialog(null, "В году "+(year.getMounth()).length+" месяцев.", "Information", JOptionPane.PLAIN_MESSAGE);
		for(int i = 0; i <= (year.getMounth()).length-1; i++) {
			JOptionPane.showMessageDialog(
				null,
				"В "+(year.getMounthTitle())[i].toLowerCase()+" "+(year.getMounth())[i].length+" дней.",
				"Information",
				JOptionPane.PLAIN_MESSAGE
			);
		}
	}
//	покажи числа во всех месяцах года
	private void addAll() {
		GridBagConstraints gbc = pen;
		for(int i = 0, k = 0; i <= (year.getMounth()).length-1; i++) {
			this.add(	new JLabel((year.getMounthTitle())[i]), gbc);
			for(int j = 0; j <= (year.getMounth())[i].length-1; j++) {
				pen.gridy = k;
				this.add((year.getMounth())[i][j], pen);
			}
			k++;
			gbc.gridy = k;
		}
	}
//	покажи числа в выбранном месяце года
	private void addInMounth(int mounth) {
		this.mounth = mounth;
		JPanel[] jpnl = new JPanel[3];
		JButton[] year_choose = getButtonYear();
		JButton[] mounth_choose = new JButton[2];

		jpnl[0] = new JPanel();
		jpnl[0].setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		
//		init comp
		mounth_choose[0] = new JButton("", new ImageIcon(MounthPlanner.class.getResource("icon/back.png")));
		mounth_choose[1] = new JButton("", new ImageIcon(MounthPlanner.class.getResource("icon/next.png")));
		
		jpnl[1] = new JPanel();
		
		jpnl[2] = new JPanel();
		
		clock = new Clock();
		th_clock = new Thread(clock);
//		end init
//		set comp
		for(int j = 0, k = 0, n = 0; j <= (year.getMounth())[mounth].length-1; j++) {
			if(k < 7) {
				gbc.gridy = n;
				jpnl[0].add((year.getMounth())[mounth][j], gbc);
				k++;
			}
			else {
				k = 0;
				n++;
				gbc.gridy = n;
				jpnl[0].add((year.getMounth())[mounth][j], gbc);
				k++;
			}
		}
		
		jpnl[1].add(year_choose[0]);
		jpnl[1].add(new JLabel(year.getYear()+" год"));
		jpnl[1].add(setFlag(new JCheckBox(),year.getFlag()));
		jpnl[1].add(new JLabel("Високосный"));
		jpnl[1].add(year_choose[1]);
		
		jpnl[2].add(mounth_choose[0]);
		jpnl[2].add(auxPanel(	new JLabel((year.getMounthTitle())[mounth])	));
		jpnl[2].add(mounth_choose[1]);
		
		th_clock.start();
//		end set
		int h = 0;
		pen.gridy = h;
		this.add(auxPanel(clock,150,150), pen);
		pen.gridy = ++h;
		this.add(jpnl[1], pen);
		pen.gridy = ++h;
		this.add(jpnl[2], pen);
//		pen.gridy = ++h;
//		this.add(	new JLabel(getDayTitle(date_massive[0])+", "+todayDate()), pen);
		pen.gridy = ++h;
		this.add(auxPanel(jpnl[0]), pen);
		mounth_choose[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MounthPlanner.this.removeAll();
					(getTabPane()).removeAll();
					addInMounth(mounth-1);
					MounthPlanner.this.revalidate();
					MounthPlanner.this.repaint();
				}
				catch(ArrayIndexOutOfBoundsException exc) {
					JOptionPane.showMessageDialog(
						null,
						"Недопустимая операция",
						"Information",
						JOptionPane.WARNING_MESSAGE
					);
					(getTabPane()).removeAll();
					addInMounth(mounth);
					MounthPlanner.this.revalidate();
					MounthPlanner.this.repaint();
				}
			}
		});
		mounth_choose[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MounthPlanner.this.removeAll();
					(getTabPane()).removeAll();
					addInMounth(mounth+1);
					MounthPlanner.this.revalidate();
					MounthPlanner.this.repaint();
				}
				catch(ArrayIndexOutOfBoundsException exc) {
					JOptionPane.showMessageDialog(
						null,
						"Недопустимая операция",
						"Information",
						JOptionPane.WARNING_MESSAGE
					);
					(getTabPane()).removeAll();
					addInMounth(mounth);
					MounthPlanner.this.revalidate();
					MounthPlanner.this.repaint();
				}
			}
		});
	}
	private JCheckBox setFlag(JCheckBox ch, boolean flag) {
		ch.setSelected(flag);
		return ch;
	}
	private int todayDate() {
		return Integer.parseInt(date_massive[2]);
	}
	private JPanel auxPanel(JLabel jlbl) {
		JPanel jpnl = new JPanel();
		jpnl.add(jlbl);
		jpnl.setPreferredSize(new Dimension(175, 25));
		return jpnl;
	}
	private JPanel auxPanel(JPanel pnl) {
		JPanel jpnl = new JPanel();
		jpnl.add(pnl);
		jpnl.setPreferredSize(new Dimension(350,250));
		return jpnl;
	}
	private JPanel auxPanel(JPanel pnl, int width, int height) {
		JPanel jpnl = new JPanel();
		jpnl.add(pnl);
		if(width != 0 && height != 0) {
			jpnl.setPreferredSize(new Dimension(width, height));
		}
		return jpnl;
	}
	public static String[] getDateMassive() {
//		JOptionPane.showMessageDialog(null, Arrays.toString(date_massive), "Information", JOptionPane.PLAIN_MESSAGE);
		return date_massive;
	}
	public static int getNumbMounth() {
		return mounth;
	}
}
/*
		jpnl[0].setPreferredSize(new Dimension(350,250));
		if(	((year.getMounthTitle())[mounth]).equalsIgnoreCase("Февраль")) {
			jpnl[0].setPreferredSize(new Dimension(350,200));
		}
*/
