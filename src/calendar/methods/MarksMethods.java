package calendar.methods;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class MarksMethods {
	private static String can_path = whereI();
/*
	public static void writeMarks(String path) {
		File f = new File(path);
		JOptionPane.showMessageDialog(null, f.getPath(), "Information", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, f.getAbsolutePath(), "Information", JOptionPane.PLAIN_MESSAGE);
		try {
			JOptionPane.showMessageDialog(null, f.getCanonicalPath(), "Information", JOptionPane.PLAIN_MESSAGE);
			if(f.exists()) {
				JOptionPane.showMessageDialog(null, f.getCanonicalPath()+" существует.", "Information", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, f.getCanonicalPath()+" не существует.", "Information", JOptionPane.PLAIN_MESSAGE);
			}
		}
		catch (IOException exc) {
			
		}
	}
*/
	public static void writeMarks(String[] path, String mark) {
		path = setPathOfMark(path);
		File wr = createMark(path[3], path[2], path[1]);
		try {
			FileWriter output = new FileWriter(wr);
			for(int i = 0; i <= mark.length()-1; i++) {
				output.write(mark.charAt(i));
			}
			output.close();
		}
		catch(FileNotFoundException exc) {
			
		}
		catch(IOException exc) {
			
		}
		
	}
	public static String readMarks(String[] path) {
		path = setPathOfMark(path);
		File in = createMark(path[3], path[2], path[1]);
		String rez = "";
		int symb = 0;
		try {
			FileReader input = new FileReader(in);
			symb = input.read();
			while(symb != -1) {
				rez += (char) symb;
				symb = input.read();
			}
			input.close();
		}
		catch(FileNotFoundException exc) {
			
		}
		catch(IOException exc) {
			
		}
		return rez;
	}
	public static boolean marksIsExist(String[] path) {
		boolean rez = false;
		path = setPathOfMark(path);
		if((new File(can_path+"/"+path[3]+"/"+path[2]+"/"+path[1])).exists()) {
			rez = true;
		}
		return rez;
	}
	public static void setMarksPath(String path) {
		can_path = path;
	}
	public static String getMarksPath() {
		return can_path;
	}
	public static File selectDir() {
		JFileChooser fc = new JFileChooser();

		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int returnVal = fc.showDialog(null,"Choose");
		File f = new File("");
//		try {
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
//				JOptionPane.showMessageDialog(null, Arrays.toString(f.getCanonicalPath().split("/")), "Information", JOptionPane.PLAIN_MESSAGE);
//				readMarks(setChFile(f.getCanonicalPath().split("/")));
			} else {
				JOptionPane.showMessageDialog(null, "Open command cancelled by user.", "Choose file", JOptionPane.PLAIN_MESSAGE);
				f = new File(whereI());
			}
//		}
//		catch(IOException exc) {
//			
//		}
		
		return f;
	}
	private static String[] setChFile(String[] path) {
//		JOptionPane.showMessageDialog(null, Arrays.toString(path), "Information", JOptionPane.PLAIN_MESSAGE);
		return path;
	}
	private static File createMark(String year, String mounth, String day) {
		File mark_dir = new File("");
		File mark_file = new File("");
		try {
			if(!(new File("Заметки")).exists()) {
				(new File("Заметки")).mkdir();
				can_path = (new File("Заметки")).getCanonicalPath();
			}
			else {
				can_path = (new File(can_path)).getCanonicalPath();
			}
			mark_dir = new File(can_path+"/"+year+"/"+mounth);// - директория (год) + директория (месяц)
			mark_file = new File(mark_dir, day);// - файл (заметка)
//			JOptionPane.showMessageDialog(null, mark_dir.getCanonicalPath(), "Information", JOptionPane.PLAIN_MESSAGE);
//			JOptionPane.showMessageDialog(null, mark_file.getCanonicalPath(), "Information", JOptionPane.PLAIN_MESSAGE);
			if(!mark_dir.exists()) {
				mark_dir.mkdirs();
			}
//			else {
//				JOptionPane.showMessageDialog(null, mark_dir.getCanonicalPath()+" существует.", "Information", JOptionPane.PLAIN_MESSAGE);
//			}
			if(!mark_file.exists()) {
				mark_file.createNewFile();
			}
//			else {
//				JOptionPane.showMessageDialog(null, mark_file.getCanonicalPath()+" существует.", "Information", JOptionPane.PLAIN_MESSAGE);
//			}
		}
		catch (IOException exc) {
			
		}
		return mark_file;
	}
	private static String[] setPathOfMark(String[] path) {
//		JOptionPane.showMessageDialog(null, Arrays.toString(path), "Information", JOptionPane.PLAIN_MESSAGE);
		for(int i = 0; i <= path.length-1; i++) {
			if(path[i].charAt(path[i].length()-1) == ',') {
				path[i] = path[i].substring(0,path[i].length()-1);
			}
		}
		return path;
	}
	private static String whereI() {
		String rez = "";
		try {
			rez = (new File("Заметки")).getCanonicalPath();
		}
		catch(IOException exc) {
		
		}
		return rez;
	}
/*
	public static void main(String[] args) {
//		writeMarks("./");
		writeMarks(
			"Пятница, 5 Январь, 2018".split(" "),
			JOptionPane.showInputDialog(
				null,
				"Всё, что вы скажете будет записано во всех подробностях.",
				"Information",
				JOptionPane.PLAIN_MESSAGE
			)
		);
		JOptionPane.showMessageDialog(null,
			"Вот, что нам удалось прочесть: "+readMarks("Пятница, 5 Январь, 2018".split(" ")),
			"Information",
			JOptionPane.PLAIN_MESSAGE
		);
	}
*/
}
/*
	javac -d bin -sourcepath src src/calendar/methods/MarksMethods.java
	java -classpath bin calendar.methods.MarksMethods
*/
