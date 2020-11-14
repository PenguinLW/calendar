package clock;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import static calendar.methods.DateData.getDayTitle;
import static calendar.methods.DateData.getMounthTitleRU;
import java.awt.Font;
public class Clock extends JPanel implements Runnable {
	private JLabel[] jlbl = new JLabel[2];
	private String[] date_massive;
	public Clock() {
		initComponents();
	}
	private void initComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints pen = new GridBagConstraints();
		pen.fill = GridBagConstraints.BOTH;
		pen.insets = new Insets(2,2,2,2);
		
//		init comp
		for(int i = 0; i <= jlbl.length-1; i++) {
			jlbl[i] = new JLabel();
//			jlbl[i].setPreferredSize(new Dimension(50, 50));
			jlbl[i].setFont(new Font("Verdana", Font.ITALIC, 20));
			jlbl[i].setVerticalAlignment(JLabel.CENTER);
			jlbl[i].setHorizontalAlignment(JLabel.CENTER);
		}
//		end init
//		set comp
		date_massive = ((String) (new Date()).toString()).split(" ");
//		JOptionPane.showMessageDialog(null, Arrays.toString(date_massive), "Information", JOptionPane.PLAIN_MESSAGE);
		jlbl[1].setText(
			getDayTitle(date_massive[0])
			+", "+Integer.parseInt(date_massive[2])
			+" "+getMounthTitleRU(date_massive[1])
			+" "+date_massive[5]
		);
		jlbl[0].setText(date_massive[3]);
//		end set
		
		pen.gridx = 0;
		pen.gridy = 0;
		this.add(jlbl[0], pen);
		pen.gridx = 0;
		pen.gridy = 1;
		pen.gridwidth = 2;
		this.add(jlbl[1], pen);
//		this.setPreferredSize(new Dimension(150, 150));
	}
	private void updateDate() {
		jlbl[0].setText(
			getDayTitle((((String) (new Date()).toString()).split(" "))[0])
			+", "+Integer.parseInt(
				((	(String) (new Date()).toString()	).split(" "))[2]
			)
			+" "+getMounthTitleRU(	((	(String) (new Date()).toString()	).split(" "))[1]	)
			+" "+((	(String) (new Date()).toString()	).split(" "))[5]
		);
		jlbl[1].setText(
			(((String) (new Date()).toString()).split(" "))[3]
		);
	}
	public void run() {
		while(true) {
			try {
				updateDate();
				Thread.sleep(1000);
			} catch(InterruptedException exc) {
				JOptionPane.showMessageDialog(null, ""+exc, "Information", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
