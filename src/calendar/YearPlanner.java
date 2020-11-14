package calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import static calendar.CalendarPlanner.getTabPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.TextArea;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import static calendar.methods.DateData.k;
import static calendar.methods.DateData.mounth_title;
import static calendar.MounthPlanner.getDateMassive;
import static calendar.MounthPlanner.getNumbMounth;
import static calendar.methods.DateData.getDayTitle;
import static calendar.methods.DateData.getMounthTitleRU;
import static calendar.methods.DateData.getMounthTitle;
import static calendar.methods.DateData.defineDOW;
import static calendar.methods.DateData.rightDOW;
import static calendar.methods.MarksMethods.writeMarks;
import static calendar.methods.MarksMethods.readMarks;
import static calendar.methods.MarksMethods.marksIsExist;
import static calendar.methods.MarksMethods.setMarksPath;
import static calendar.methods.MarksMethods.getMarksPath;
import static calendar.methods.MarksMethods.selectDir;
import java.io.IOException;
public class YearPlanner {
	private JLabel[][] mounth = new JLabel[12][];
	private int year;
	private boolean flag;
	private String[] date_massive;
	private static TextArea txtar;
	private static String day = "";
	private static int s_year;
	
	public YearPlanner(int year, boolean flag) {
		this.year = year;
		this.flag = flag;
		initYear();
	}
	
	public void initYear() {
		for(int i = 0; i <= mounth.length-1; i++) {
			mounth[i] = new JLabel[k[i]];
			for(int j = 0; j <= mounth[i].length-1; j++) {
				mounth[i][j] = new JLabel(""+(j+1));
				mounth[i][j].setPreferredSize(new Dimension(50,50));
				mounth[i][j].addMouseListener(new MSList());
			}
		}
		if(!flag) {
			mounth[1] = new JLabel[k[1]-1];
			for(int j = 0; j <= mounth[1].length-1; j++) {
				mounth[1][j] = new JLabel(""+(j+1));
				mounth[1][j].setPreferredSize(new Dimension(50,50));
				mounth[1][j].addMouseListener(new MSList());
			}
		}
	}
	public JLabel[][] getMounth() {
		return mounth;
	}
	public int getYear() {
		return year;
	}
	public boolean getFlag() {
		return flag;
	}
	public String[] getMounthTitle() {
		return mounth_title;
	}
	private boolean isExistTab(JTabbedPane jtab, String title) {
		boolean rez = true;
		for(int i = 0; i <= jtab.getTabCount()-1; i++) {
			jtab.setSelectedIndex(i);
			if(title.equals(	jtab.getTitleAt(jtab.getSelectedIndex())	)) {
				rez = false;
			}
		}
		return rez;
	}
	private int findExistTab(JTabbedPane jtab, String title) {
		int rez = 0;
		for(int i = 0; i <= jtab.getTabCount()-1; i++) {
			jtab.setSelectedIndex(i);
			if(title.equals(	jtab.getTitleAt(jtab.getSelectedIndex())	)) {
				rez = jtab.getSelectedIndex();
			}
		}
		return rez;
	}
	public JPanel createPlannerPanel(JPanel jpnl, String day, JTabbedPane jtab) {
		this.day = day;
		this.s_year = this.getYear();
		JPanel[] pl_pn = new JPanel[1];
		jpnl.setLayout(new GridBagLayout());
		GridBagConstraints pen = new GridBagConstraints();
		pen.fill = GridBagConstraints.BOTH;
		pen.insets = new Insets(2,2,2,2);
		
//		init
		JButton save_marks = new JButton("Сохранить");
		JButton close_tab = new JButton("Закрыть");
		JButton choose_spath = new JButton("...");
		JLabel spath = new JLabel();
		txtar = new TextArea();
		if(marksIsExist(	(defineDOW(Integer.parseInt(day), getNumbMounth(), s_year)).split(" ")	)) {
			txtar.setText(
				readMarks(
					(defineDOW(Integer.parseInt(day), getNumbMounth(), s_year)).split(" ")
				)
			);
		}
		for(int i = 0; i <= pl_pn.length-1; i++) {
			pl_pn[i] = new JPanel();
		}
//		end init
//		set
		save_marks.setToolTipText("Сохранить заметку на "+Integer.parseInt(day));
		close_tab.setToolTipText("Закрыть заметку на "+Integer.parseInt(day));
		txtar.setPreferredSize(new Dimension(550,500));
		pl_pn[0].add(save_marks);
		pl_pn[0].add(close_tab);
		spath.setText("<html>По умолчанию заметки сохраняются в:<br>"+getMarksPath());
		
		save_marks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeMarks(
					(defineDOW(Integer.parseInt(day), getNumbMounth(), s_year)).split(" "),
					txtar.getText()
				);
			}
		});
		close_tab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtab.remove(jtab.getSelectedIndex());
			}
		});
		choose_spath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setMarksPath(	(selectDir()).getCanonicalPath()	);
					spath.setText("<html>По умолчанию заметки сохраняются в:<br>"+getMarksPath());
				}
				catch(IOException exc) {
					
				}
			}
		});
//		end set
		pen.gridx = 0;
		pen.gridy = 0;
		jpnl.add(new JLabel(
				defineDOW(
					Integer.parseInt(day),
					getNumbMounth(),
					this.getYear()
				)
			),
			pen
		);
		pen.gridx = 0;
		pen.gridy = 1;
		jpnl.add(txtar, pen);
		pen.gridx = 0;
		pen.gridy = 2;
		jpnl.add(auxPanel(new JPanel(), choose_spath, spath), pen);
		pen.gridx = 0;
		pen.gridy = 3;
		jpnl.add(pl_pn[0], pen);
		
		return jpnl;
	}
	private JPanel auxPanel(JPanel jpnl, JButton jbtn, JLabel jlbl) {
		jpnl.add(jlbl);
		jpnl.add(jbtn);
		return jpnl;
	}
	private class MSList extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			for(int i = 0;i <= mounth.length-1; i++) {
				for(int j = 0;j <= mounth[i].length-1; j++) {
					if(mounth[i][j] == (JLabel) e.getSource()) {
						JOptionPane.showMessageDialog(
							null,
							"Покажу заметки на "+mounth[i][j].getText()+" число.",
							"Information",
							JOptionPane.PLAIN_MESSAGE
						);
						if(isExistTab((getTabPane()), mounth[i][j].getText())) {
							(getTabPane()).addTab(
								mounth[i][j].getText(),
								createPlannerPanel(
									new JPanel(),
									mounth[i][j].getText(),
									(getTabPane())
								)
							);
							if(txtar.getText() != null && !(txtar.getText()).equals("")) {
								txtar.setText(
									readMarks(
										(defineDOW(Integer.parseInt(day), getNumbMounth(), s_year)).split(" ")
									)
								);
							}
							(getTabPane()).setSelectedIndex(	findExistTab((getTabPane()), mounth[i][j].getText())	);
						}
						else {
							(getTabPane()).setSelectedIndex(	findExistTab((getTabPane()), mounth[i][j].getText())	);
						}
					}
				}
			}
		}
	}
}
