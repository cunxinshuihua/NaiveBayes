
public class AdultInfo{
	boolean tag;
	Integer age;
	WorkClass wrc;
	Integer fnlwgt;
	Education edu;
	Integer edu_num;
	Marital_status mar;
	Occupation ocp;
	Relationship rls;
	Race race;
	Sex sex;
	Integer capgain;	// capital-gain
	Integer caploss;	// capital-loss
	Integer hpw;		// hours-per-week
	Native_country ntc;
	Boolean gt50k;
	
	public void clear() {
		tag = false;
		age = null; wrc = null; fnlwgt = null; edu = null;
		edu_num = null; mar = null; ocp = null; rls= null;
		race = null; sex = null; capgain = null; caploss = null;
		hpw = null; ntc = null; gt50k = null;
	}
}

/**
 * workclass: Private, Self-emp-not-inc, Self-emp-inc, Federal-gov, 
 * Local-gov, State-gov, Without-pay, Never-worked.
 */
enum WorkClass {
	Private, Self_emp_not_inc, Self_emp_inc, Federal_gov, Local_gov, 
	State_gov, Without_pay, Never_worked
}

/**
 * education: Bachelors, Some-college, 11th, HS-grad, Prof-school, 
 * Assoc-acdm, Assoc-voc, 9th, 7th-8th, 12th, Masters, 1st-4th, 10th, 
 * Doctorate, 5th-6th, Preschool.
 */
enum Education {
	Bachelors, Some_college, A11th, HS_grad, Prof_school, Assoc_acdm, 
	Assoc_voc, A9th, A7th_8th, A12th, Masters, A1st_4th, A10th, Doctorate, 
	A5th_6th, Preschool
}

/**
 * marital-status: Married-civ-spouse, Divorced, Never-married, Separated, 
 * Widowed, Married-spouse-absent, Married-AF-spouse.
 */
enum Marital_status {
	Married_civ_spouse, Divorced, Never_married, Separated, Widowed, 
	Married_spouse_absent, Married_AF_spouse
}

/**
 * occupation: Tech-support, Craft-repair, Other-service, Sales, 
 * Exec-managerial, Prof-specialty, Handlers-cleaners, Machine-op-inspct, 
 * Adm-clerical, Farming-fishing, Transport-moving, Priv-house-serv, 
 * Protective-serv, Armed-Forces.
 */
enum Occupation {
	Tech_support, Craft_repair, Other_service, Sales, Exec_managerial, 
	Prof_specialty, Handlers_cleaners, Machine_op_inspct, Adm_clerical, 
	Farming_fishing, Transport_moving, Priv_house_serv, Protective_serv, 
	Armed_Forces
}

/**
 * relationship: Wife, Own-child, Husband, Not-in-family, Other-relative, Unmarried.
 */
enum Relationship {
	Wife, Own_child, Husband, Not_in_family, Other_relative, Unmarried
}

/**
 * race: White, Asian-Pac-Islander, Amer-Indian-Eskimo, Other, Black.
 */
enum Race {
	White, Asian_Pac_Islander, Amer_Indian_Eskimo, Other, Black
}

/**
 * sex: Female, Male.
 */
enum Sex {
	Female, Male
}

/**
 * native-country: United-States, Cambodia, England, Puerto-Rico, Canada, Germany, 
 * Outlying-US(Guam-USVI-etc), India, Japan, Greece, South, China, Cuba, Iran, 
 * Honduras, Philippines, Italy, Poland, Jamaica, Vietnam, Mexico, Portugal, 
 * Ireland, France, Dominican-Republic, Laos, Ecuador, Taiwan, Haiti, Columbia, 
 * Hungary, Guatemala, Nicaragua, Scotland, Thailand, Yugoslavia, El-Salvador, 
 * Trinadad&Tobago, Peru, Hong, Holand-Netherlands.
 */
enum Native_country {
	United_States, Cambodia, England, Puerto_Rico, Canada, Germany, 
	Outlying_US, Guam_USVI_etc, India, Japan, Greece, South, China, 
	Cuba, Iran, Honduras, Philippines, Italy, Poland, Jamaica, Vietnam,
	Mexico, Portugal, Ireland, France, Dominican_Republic, Laos, Ecuador, 
	Taiwan, Haiti, Columbia, Hungary, Guatemala, Nicaragua, Scotland, 
	Thailand, Yugoslavia, El_Salvador, Trinadad_Tobago, Peru, Hong, Holand_Netherlands
}











