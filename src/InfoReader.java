import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class InfoReader {
	private AdultInfo ali;
	private FileReader fr;
	private BufferedReader br;

	public InfoReader(String fileName) {
		ali = new AdultInfo();
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AdultInfo getNextInfo() {
		String line = "";
		ali.clear();
		
		try {
			int cc = 0;
			while ((line = br.readLine()) != null) {
				if (cc > 10) {
					System.out.println("Errors too many...");
					System.exit(1);
				}
				if (parseInfo(line)) {
					ali.tag = true;
					break;
				}
				cc ++;
			} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return ali;
	}
	
	public void close() {
		try {
			if (br != null) br.close();
			if (fr != null) fr.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean parseInfo(String content) {
		String[] info = content.split(",");
		if (info.length != 15) {
			System.out.println("Error at parseInfo in Class InfoReader: Info format error...");
			return false;
		}
		
		if (info[0].equals("?")) ali.age=null;
		else ali.age = Integer.parseInt(info[0]);
		
		/**
		 * workclass: Private, Self-emp-not-inc, Self-emp-inc, Federal-gov, 
		 * Local-gov, State-gov, Without-pay, Never-worked.
		 */
		if (info[1].equals("?")) ali.wrc = null;
		else if (info[1].equals("Private")) ali.wrc = WorkClass.Private;
		else if (info[1].equals("Self-emp-not-inc")) ali.wrc = WorkClass.Self_emp_not_inc;
		else if (info[1].equals("Self-emp-inc")) ali.wrc = WorkClass.Self_emp_inc;
		else if (info[1].equals("Federal-gov")) ali.wrc = WorkClass.Federal_gov;
		else if (info[1].equals("Local-gov")) ali.wrc = WorkClass.Local_gov;
		else if (info[1].equals("State-gov")) ali.wrc = WorkClass.State_gov;
		else if (info[1].equals("Without-pay")) ali.wrc = WorkClass.Without_pay;
		else if (info[1].equals("Never-worked")) ali.wrc = WorkClass.Never_worked;
		else {
			System.out.println("Error at InfoReader > parseInfo > workclass: " + info[1]);
			return false;
		}
		
		if (info[2].equals("?")) ali.fnlwgt = null;
		else ali.fnlwgt = Integer.parseInt(info[2]);
		
		/**
		 * education: Bachelors, Some-college, 11th, HS-grad, Prof-school, 
		 * Assoc-acdm, Assoc-voc, 9th, 7th-8th, 12th, Masters, 1st-4th, 10th, 
		 * Doctorate, 5th-6th, Preschool.
		 */
		if (info[3].equals("?")) ali.edu = null;
		else if (info[3].equals("Bachelors")) ali.edu = Education.Bachelors;
		else if (info[3].equals("Some-college")) ali.edu = Education.Some_college;
		else if (info[3].equals("11th")) ali.edu = Education.A11th;
		else if (info[3].equals("HS-grad")) ali.edu = Education.HS_grad;
		else if (info[3].equals("Prof-school")) ali.edu = Education.Prof_school;
		else if (info[3].equals("Assoc-acdm")) ali.edu = Education.Assoc_acdm;
		else if (info[3].equals("Assoc-voc")) ali.edu = Education.Assoc_voc;
		else if (info[3].equals("9th")) ali.edu = Education.A9th;
		else if (info[3].equals("7th-8th")) ali.edu = Education.A7th_8th;
		else if (info[3].equals("12th")) ali.edu = Education.A12th;
		else if (info[3].equals("Masters")) ali.edu = Education.Masters;
		else if (info[3].equals("1st-4th")) ali.edu = Education.A1st_4th;
		else if (info[3].equals("10th")) ali.edu = Education.A10th;
		else if (info[3].equals("Doctorate")) ali.edu = Education.Doctorate;
		else if (info[3].equals("5th-6th")) ali.edu = Education.A5th_6th;
		else if (info[3].equals("Preschool")) ali.edu = Education.Preschool;
		else {
			System.out.println("Error at InfoReader > parseInfo > education;");
			return false;
		}
			
		if (info[4].equals("?")) ali.edu_num = null;
		else ali.edu_num = Integer.parseInt(info[4]);
		
		/**
		 * marital-status: Married-civ-spouse, Divorced, Never-married, Separated, 
		 * Widowed, Married-spouse-absent, Married-AF-spouse.
		 */
		if (info[5].equals("?")) ali.mar = null;
		else if (info[5].equals("Married-civ-spouse")) ali.mar = Marital_status.Married_civ_spouse;
		else if (info[5].equals("Divorced")) ali.mar = Marital_status.Divorced;
		else if (info[5].equals("Never-married")) ali.mar = Marital_status.Never_married;
		else if (info[5].equals("Separated")) ali.mar = Marital_status.Separated;
		else if (info[5].equals("Widowed")) ali.mar = Marital_status.Widowed;
		else if (info[5].equals("Married-spouse-absent")) ali.mar = Marital_status.Married_spouse_absent;
		else if (info[5].equals("Married-AF-spouse")) ali.mar = Marital_status.Married_AF_spouse;
		else {
			System.out.println("Error at InfoReader > parseInfo > marital-status;");
			return false;
		}
		
		/**
		 * occupation: Tech-support, Craft-repair, Other-service, Sales, 
		 * Exec-managerial, Prof-specialty, Handlers-cleaners, Machine-op-inspct, 
		 * Adm-clerical, Farming-fishing, Transport-moving, Priv-house-serv, 
		 * Protective-serv, Armed-Forces.
		 */
		if (info[6].equals("?")) ali.ocp = null;
		else if (info[6].equals("Tech-support")) ali.ocp = Occupation.Tech_support;
		else if (info[6].equals("Craft-repair")) ali.ocp = Occupation.Craft_repair;
		else if (info[6].equals("Other-service")) ali.ocp = Occupation.Other_service;
		else if (info[6].equals("Sales")) ali.ocp = Occupation.Sales;
		else if (info[6].equals("Exec-managerial")) ali.ocp = Occupation.Exec_managerial;
		else if (info[6].equals("Prof-specialty")) ali.ocp = Occupation.Prof_specialty;
		else if (info[6].equals("Handlers-cleaners")) ali.ocp = Occupation.Handlers_cleaners;
		else if (info[6].equals("Machine-op-inspct")) ali.ocp = Occupation.Machine_op_inspct;
		else if (info[6].equals("Adm-clerical")) ali.ocp = Occupation.Adm_clerical;
		else if (info[6].equals("Farming-fishing")) ali.ocp = Occupation.Farming_fishing;
		else if (info[6].equals("Transport-moving")) ali.ocp = Occupation.Transport_moving;
		else if (info[6].equals("Priv-house-serv")) ali.ocp = Occupation.Priv_house_serv;
		else if (info[6].equals("Protective-serv")) ali.ocp = Occupation.Protective_serv;
		else if (info[6].equals("Armed-Forces")) ali.ocp = Occupation.Armed_Forces;
		else {
			System.out.println("Error at InfoReader > parseInfo > Occpation;"); 
			return false;
		}
		
		/**
		 * relationship: Wife, Own-child, Husband, Not-in-family, Other-relative, Unmarried.
		 */
		if (info[7].equals("?")) ali.rls = null;
		else if (info[7].equals("Wife")) ali.rls = Relationship.Wife;
		else if (info[7].equals("Own-child")) ali.rls = Relationship.Own_child;
		else if (info[7].equals("Husband")) ali.rls = Relationship.Husband;
		else if (info[7].equals("Not-in-family")) ali.rls = Relationship.Not_in_family;
		else if (info[7].equals("Other-relative")) ali.rls = Relationship.Other_relative;
		else if (info[7].equals("Unmarried")) ali.rls = Relationship.Unmarried;
		else {
			System.out.println("Error at InfoReader > parseInfo > relationship;");
			return false;
		}
		
		/**
		 * race: White, Asian-Pac-Islander, Amer-Indian-Eskimo, Other, Black.
		 */
		if (info[8].equals("?")) ali.race = null;
		else if (info[8].equals("White")) ali.race = Race.White;
		else if (info[8].equals("Asian-Pac-Islander")) ali.race = Race.Asian_Pac_Islander;
		else if (info[8].equals("Amer-Indian-Eskimo")) ali.race = Race.Amer_Indian_Eskimo;
		else if (info[8].equals("Other")) ali.race = Race.Other;
		else if (info[8].equals("Black")) ali.race = Race.Black;
		else {
			System.out.println("Error at InfoReader > parseInfo > race;");
			return false;
		}
		
		/**
		 * sex: Female, Male.
		 */
		if (info[9].equals("?")) ali.sex = null;
		else if (info[9].equals("Female")) ali.sex = Sex.Female;
		else if (info[9].equals("Male")) ali.sex = Sex.Male;
		else {
			System.out.println("Error ar InfoReader > parseInfo > race;");
			return false;
		}
		
		if (info[10].equals("?")) ali.capgain = null;
		else ali.capgain = Integer.parseInt(info[10]);
		
		if (info[11].equals("?")) ali.caploss = null;
		else ali.caploss = Integer.parseInt(info[11]);
		
		if (info[12].equals("?")) ali.hpw = null;
		else ali.hpw = Integer.parseInt(info[12]);
		
		/**
		 * native-country: United-States, Cambodia, England, Puerto-Rico, Canada, Germany, 
		 * Outlying-US(Guam-USVI-etc), India, Japan, Greece, South, China, Cuba, Iran, 
		 * Honduras, Philippines, Italy, Poland, Jamaica, Vietnam, Mexico, Portugal, 
		 * Ireland, France, Dominican-Republic, Laos, Ecuador, Taiwan, Haiti, Columbia, 
		 * Hungary, Guatemala, Nicaragua, Scotland, Thailand, Yugoslavia, El-Salvador, 
		 * Trinadad&Tobago, Peru, Hong, Holand-Netherlands.
		 */
		if (info[13].equals("?")) ali.ntc = null;
		else if (info[13].equals("United-States")) ali.ntc = Native_country.United_States;
		else if (info[13].equals("Cambodia")) ali.ntc = Native_country.Cambodia;
		else if (info[13].equals("England")) ali.ntc = Native_country.England;
		else if (info[13].equals("Puerto-Rico")) ali.ntc = Native_country.Puerto_Rico;
		else if (info[13].equals("Canada")) ali.ntc = Native_country.Canada;
		else if (info[13].equals("Germany")) ali.ntc = Native_country.Germany;
		else if (info[13].equals("Outlying-US(Guam-USVI-etc)")) ali.ntc = Native_country.Outlying_US;
		else if (info[13].equals("India")) ali.ntc = Native_country.India;
		else if (info[13].equals("Japan")) ali.ntc = Native_country.Japan;
		else if (info[13].equals("Greece")) ali.ntc = Native_country.Greece;
		else if (info[13].equals("South")) ali.ntc = Native_country.South;
		else if (info[13].equals("China")) ali.ntc = Native_country.China;
		else if (info[13].equals("Cuba")) ali.ntc = Native_country.Cuba;
		else if (info[13].equals("Iran")) ali.ntc = Native_country.Iran;
		else if (info[13].equals("Honduras")) ali.ntc = Native_country.Honduras;
		else if (info[13].equals("Philippines")) ali.ntc = Native_country.Philippines;
		else if (info[13].equals("Italy")) ali.ntc = Native_country.Italy;
		else if (info[13].equals("Poland")) ali.ntc = Native_country.Poland;
		else if (info[13].equals("Jamaica")) ali.ntc = Native_country.Jamaica;
		else if (info[13].equals("Vietnam")) ali.ntc = Native_country.Vietnam;
		else if (info[13].equals("Mexico")) ali.ntc = Native_country.Mexico;
		else if (info[13].equals("Portugal")) ali.ntc = Native_country.Portugal;
		else if (info[13].equals("Ireland")) ali.ntc = Native_country.Ireland;
		else if (info[13].equals("France")) ali.ntc = Native_country.France;
		else if (info[13].equals("Dominican-Republic")) ali.ntc = Native_country.Dominican_Republic;
		else if (info[13].equals("Laos")) ali.ntc = Native_country.Laos;
		else if (info[13].equals("Ecuador")) ali.ntc = Native_country.Ecuador;
		else if (info[13].equals("Taiwan")) ali.ntc = Native_country.Taiwan;
		else if (info[13].equals("Haiti")) ali.ntc = Native_country.Haiti;
		else if (info[13].equals("Columbia")) ali.ntc = Native_country.Columbia;
		else if (info[13].equals("Hungary")) ali.ntc = Native_country.Hungary;
		else if (info[13].equals("Guatemala")) ali.ntc = Native_country.Guatemala;
		else if (info[13].equals("Nicaragua")) ali.ntc = Native_country.Nicaragua;
		else if (info[13].equals("Scotland")) ali.ntc = Native_country.Scotland;
		else if (info[13].equals("Thailand")) ali.ntc = Native_country.Thailand;
		else if (info[13].equals("Yugoslavia")) ali.ntc = Native_country.Yugoslavia;
		else if (info[13].equals("El-Salvador")) ali.ntc = Native_country.El_Salvador;
		else if (info[13].equals("Trinadad&Tobago")) ali.ntc = Native_country.Trinadad_Tobago;
		else if (info[13].equals("Peru")) ali.ntc = Native_country.Peru;
		else if (info[13].equals("Hong")) ali.ntc = Native_country.Hong;
		else if (info[13].equals("Holand-Netherlands")) ali.ntc = Native_country.Holand_Netherlands;
		else {
			System.out.println("Error at InfoReader > parseInfo > native-country: " + info[13]);
			return false;
		}
		
		if (info[14].equals("?")) ali.gt50k = null;
		else if (info[14].contains(">50K")) ali.gt50k = true;
		else if (info[14].contains("<=50K")) ali.gt50k = false;
		else {
			System.out.println("Error at InfoReader > parseInfo > gt50k: " + info[14]);
			return false;
		}
		
		return true;
	}
}







