import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuickNaiveBayes {
	// public static Map<String, Integer> accNum = new HashMap<String, Integer>();
	// public static Map<String, Double> posCal = new HashMap<String, Double>();
	public static ArrayList<Map<String, Integer>> accNum = new ArrayList<Map<String, Integer>>(15);
	public static ArrayList<Map<String, Double>> posCal = new ArrayList<Map<String, Double>>(15);
	public static String GT = ">50K.";
	public static String LE = "<=50K.";
	public static Integer[] cateNum = 
	// new Integer[15];
	{
		12, 8, 100, 16, 10, 7, 14, 6, 5, 2, 100, 50, 100, 30000
	};
	
	public static void quickInit() {
		for (int i = 0; i < 15; ++i) {
			Map<String, Integer> e = new HashMap<String, Integer>();
			accNum.add(e);
		}
		for (int i = 0; i < 15; ++i) {
			Map<String, Double> e = new HashMap<String, Double>();
			posCal.add(e);
		}
	}

	public static void quickInfoReader(String fileName) {
		quickInit();
		String line = "";
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			double sampleRate = 1;
			while ((line = br.readLine()) != null) {
				double random = Math.random();
				if (random <= sampleRate) 
					quickParseInfo(line);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fr != null) fr.close();
				if (br != null) br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/*for (int i = 0; i < 15; ++i) {
			System.out.println(accNum.get(i).size());
		}*/
	}
	
	public static void quickPosCal() {
		/*for (int i = 0; i < 14; ++i) {
			cateNum[i] = accNum.get(i).size();
		}*/
		
		Double bias = 0.4;
		for (int idx = 0; idx < 14; ++idx) {
			for (Map.Entry<String, Double> entry : posCal.get(idx).entrySet()) {
				String key = entry.getKey();
				if (key.contains("::")) {
					String pos = key.split("::")[1];
					Double n_xi_y = accNum.get(idx).get(key).doubleValue() + bias;
					// Double n_xi_y = accNum.get(idx).get(key).doubleValue();
					Double n_y = accNum.get(14).get(pos).doubleValue() + cateNum[idx] * bias;
					// Double n_y = accNum.get(idx).get(key).doubleValue();
					entry.setValue(n_xi_y / n_y);
				}
			}	
		}
		
		int total = accNum.get(14).get(GT) + accNum.get(14).get(LE);
		posCal.get(14).put(GT, accNum.get(14).get(GT) * 1.0 / total);
		posCal.get(14).put(LE, accNum.get(14).get(LE) * 1.0 / total);
	}
	
	public static void quickNaiveBayes(String fileName) {
		String line = "";
		FileReader fr = null;
		BufferedReader br = null;
		int correct = 0;
		int total = 0;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			// System.out.println(posCal.get(GT));
			int cc = 0;
			while ((line = br.readLine()) != null) {
				String[] info = line.split(",");
				
				if (info.length != 15) {
					System.out.println("Error at parse information with quicknaiveBayes...");
					continue;
				}
				
				Double ygt = Math.log(posCal.get(14).get(GT)); Double yle = Math.log(posCal.get(14).get(LE));
				boolean flag = false;
				for (int idx = 0; idx < 14; ++idx) {
					String text = info[idx];
					// if (text.equals("?")) continue;
					
					if (idx == 0 || idx == 2 || idx == 4 || idx == 10 || idx == 11 || idx == 12) {
						text = decreNum(idx, text);
					}
					
					if (posCal.get(idx).containsKey(text + "::" + GT)) {
						ygt += Math.log(posCal.get(idx).get(text + "::" + GT));
					}
					else {
						// System.out.println("Empty entry : " + text);
						ygt += Math.log(1.0 / cateNum[idx]);
						flag = true;
					}
					if (posCal.get(idx).containsKey(text + "::" + LE)) {
						yle += Math.log(posCal.get(idx).get(text + "::" + LE));
					}
					else {
						// System.out.println("Empty entry : " + text);
						yle += Math.log(1.0 / cateNum[idx]);
						flag = true;
					}
				}
				
				/*Double cpare = ygt / yle;
				if (cpare > 0.99 && cpare < 1.01) {
					System.out.println("Probability : " + ygt + " : " + yle + " > " + info[14]);
				}*/
				
				if ((ygt <= yle && info[14].equals(LE)) || (ygt >= yle && info[14].equals(GT))) {
					correct ++;
				}
				total ++; 
				if (flag) cc ++;
			}
			System.out.println("Empty Entry : " + cc);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fr != null) fr.close();
				if (br != null) br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Correct : " + correct + "\n" + "Total : " + total);
		System.out.println("Accuracy : " + correct * 1.0 / total);
	}

	public static void quickParseInfo(String content) {
		String[] info = content.split(",");
		
		if (info.length != 15) {
			System.out.println("Error at parse line format... ");
		}
		
		int cc = 1;
		if (accNum.get(14).containsKey(info[14])) {
			cc = accNum.get(14).get(info[14]) + 1;
		}
		else {
			posCal.get(14).put(info[14], 0.0);
			// System.out.println(info[14]);
		}
		accNum.get(14).put(info[14], cc);
		
		for (int idx = 0; idx < 14; ++idx) {
			String text = info[idx];
			// if (text.equals("?")) continue;
			
			if (idx == 0 || idx == 2 || idx == 4 || idx == 10 || idx == 11 || idx == 12) {
				text = decreNum(idx, text);
			} 
			
			cc = 1;
			if (accNum.get(idx).containsKey(text + "::" + info[14])) {
				cc = accNum.get(idx).get(text + "::" + info[14]) + 1;
			}
			else {
				posCal.get(idx).put(text + "::" + info[14], 0.0);
				// System.out.println(text + "::" + info[14]);
			}
			accNum.get(idx).put(text + "::" + info[14], cc);
		}
	}
	
	public static String decreNum(int idx, String text) {
		if (text.equals("?")) return text;
		
		Integer num = Integer.parseInt(text);
		
		switch (idx) {
		case 0: 
			num = num / 6; 
			break;
		case 2:
			num = num / 99000; 
			break;
		case 4:
			num = num / 5;
			break;
		case 10:
			num = num / 4000;
			break;
		case 11:
			num = num / 220; 
			break;
		case 12:
			// System.out.println(num);
			num = num / 9;
			break;
		default: break;
		}
		
		return num.toString();
	}

}
