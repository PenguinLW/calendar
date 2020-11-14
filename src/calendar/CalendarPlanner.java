package calendar;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import calendar.YearPlanner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import calendar.MounthPlanner;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import static calendar.methods.DateData.getMounthTitleEN;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import static calendar.MounthPlanner.getNumbMounth;
public class CalendarPlanner extends JPanel {
	private YearPlanner year;
	private static JTabbedPane jtab;
	private static JButton[] year_choose;
	private JPanel jpnl;
	
	public CalendarPlanner(String[] date_massive) {
		addPlanner(date_massive);
	}
	public void addPlanner(String[] date_massive) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints pen = new GridBagConstraints();
		pen.fill = GridBagConstraints.BOTH;
		pen.insets = new Insets(2,2,2,2);
		
//	set init
		year_choose = new JButton[2];
		year = new YearPlanner(Integer.parseInt(date_massive[5]), isLeapYear(Integer.parseInt(date_massive[5])));
		
		jtab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		jpnl = new JPanel();
		
		
//	end init
//	set comp
		jtab.addTab(""+Integer.parseInt(date_massive[2]), year.createPlannerPanel(new JPanel(), date_massive[2], jtab));
		jtab.setPreferredSize(new Dimension(600,650));
		year_choose[0] = new JButton("", new ImageIcon(CalendarPlanner.class.getResource("icon/back.png")));
		year_choose[1] = new JButton("", new ImageIcon(CalendarPlanner.class.getResource("icon/next.png")));
//	end set
		
		pen.gridx = 0;
		pen.gridy = 0;
		this.add(new MounthPlanner(year, date_massive), pen);
		pen.gridx = 1;
		pen.gridy = 0;
		this.add(jtab, pen);
		year_choose[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/*
//				[Tue, Jan, 02, 21:28:34, IRKT, 2018]
//				year = new YearPlanner(2017, isFlag(2017));
				JOptionPane.showMessageDialog(
					null,
					"Годом ранее, "+getMounthTitleEN((year.getMounthTitle())[getNumbMounth()]),
					"Information",
					JOptionPane.PLAIN_MESSAGE
				);
*/
				CalendarPlanner.this.removeAll();
				(getTabPane()).removeAll();
				addPlanner(new String[]{
					date_massive[0],
					getMounthTitleEN((year.getMounthTitle())[getNumbMounth()]),
					"01",
					"00:00:00",
					date_massive[4],
					""+(Integer.parseInt(date_massive[5])-1)
				});
				CalendarPlanner.this.revalidate();
				CalendarPlanner.this.repaint();
			}
		});
		year_choose[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/*
//				[Tue, Jan, 02, 21:28:34, IRKT, 2018]
//				year = new YearPlanner(2017, isFlag(2017));
				JOptionPane.showMessageDialog(
					null,
					"Годом позднее",
					"Information",
					JOptionPane.PLAIN_MESSAGE
				);
*/
				CalendarPlanner.this.removeAll();
				(getTabPane()).removeAll();
				addPlanner(new String[]{
					date_massive[0],
					getMounthTitleEN((year.getMounthTitle())[getNumbMounth()]),
					"01",
					"00:00:00",
					date_massive[4],
					""+(Integer.parseInt(date_massive[5])+1)
				});
				CalendarPlanner.this.revalidate();
				CalendarPlanner.this.repaint();
			}
		});
	}
	private boolean isLeapYear(int year) {
		return (year%4==0)?((year%100!=0)?true:((year%400==0)?true:false)):false;
	}
	public static JButton[] getButtonYear() {
		return year_choose;
	}
	public static JTabbedPane getTabPane() {
		return jtab;
	}
}
