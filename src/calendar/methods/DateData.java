package calendar.methods;
import java.util.Calendar;
public class DateData {
	public static int[] k = {31,29,31,30,31,30,31,31,30,31,30,31};
	public static String[] mounth_title = {
		"Январь",
		"Февраль",
		"Март",
		"Апрель",
		"Май",
		"Июнь",
		"Июль",
		"Август",
		"Сентябрь",
		"Октябрь",
		"Ноябрь",
		"Декабрь"
	};
	public static String[] title_of_day = {"Пн","Вт","Ср","Чт","Пт","Сб","Вс"};
	public static String[] mounth_titleEN = {
		"Jan",
		"Feb",
		"Mar",
		"Apr",
		"May",
		"Jun",
		"Jul",
		"Aug",
		"Sen",
		"Okt",
		"Nov",
		"Dec"
	};
	
	

	public static int numbMounth(String title) {
		int numb = 0;
		switch(title) {
			case "Jan":
				numb = 0;
			break;
			case "Feb":
				numb = 1;
			break;
			case "Mar":
				numb = 2;
			break;
			case "Apr":
				numb = 3;
			break;
			case "May":
				numb = 4;
			break;
			case "Jun":
				numb = 5;
			break;
			case "Jul":
				numb = 6;
			break;
			case "Aug":
				numb = 7;
			break;
			case "Sen":
				numb = 8;
			break;
			case "Okt":
				numb = 9;
			break;
			case "Nov":
				numb = 10;
			break;
			case "Dec":
				numb = 11;
			break;
		}
		return numb;
	}
	public static String getDayTitle(String name) {
		String title = "";
		switch(name) {
			case "Mon":
				title = title_of_day[0];
			break;
			case "Tue":
				title = title_of_day[1];
			break;
			case "Wed":
				title = title_of_day[2];
			break;
			case "Thu":
				title = title_of_day[3];
			break;
			case "Fri":
				title = title_of_day[4];
			break;
			case "Sat":
				title = title_of_day[5];
			break;
			case "Sun":
				title = title_of_day[6];
			break;
		}
		return title;
	}
	public static String getMounthTitleEN(String mounth) {
		String rez = "";
		for(int i = 0; i <= mounth_titleEN.length-1; i++) {
			if(mounth_title[i].equals(mounth))
				rez = mounth_titleEN[i];
		}
		return rez;
	}
	public static String getMounthTitleRU(String mounth) {
		String rez = "";
		for(int i = 0; i <= mounth_title.length-1; i++) {
			if(mounth_titleEN[i].equals(mounth))
				rez = mounth_title[i];
		}
		return rez;
	}
	
	public static String defineDOW(int day, int mounth, int year) {
		Calendar c = Calendar.getInstance();
		c.set(year, (mounth), day);
		return rightDOW(c.get(Calendar.DAY_OF_WEEK))+", "+day+" "+getMounthTitle(mounth)+", "+c.get(Calendar.YEAR);//+", "+c.get(Calendar.WEEK_OF_YEAR)
	}
	public static String rightDOW(int day) {
		String rez = "";
		switch(day) {
			case 1:
				rez = "Воскресенье";
			break;
			case 2:
				rez = "Понедельник";
			break;
			case 3:
				rez = "Вторник";
			break;
			case 4:
				rez = "Среда";
			break;
			case 5:
				rez = "Четверг";
			break;
			case 6:
				rez = "Пятница";
			break;
			case 7:
				rez = "Суббота";
			break;
		}
		return rez;
	}
	public static String getMounthTitle(int mounth) {
		return mounth_title[mounth];
	}
}
