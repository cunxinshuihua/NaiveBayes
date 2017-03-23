
public class MainDriver {

	public MainDriver() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	InfoReader ir = new InfoReader("data/adult.train");
		System.out.println("Program start... ");
			while(ir.getNextInfo().tag == true) ;
		System.out.println("Program end... ");*/
		
		// quick naive bayes
		System.out.println("Program start ... ");
		QuickNaiveBayes.quickInfoReader("data/adult.train");
		QuickNaiveBayes.quickPosCal();
		QuickNaiveBayes.quickNaiveBayes("data/adult.test");
		System.out.println("Program end ... ");
	}

}
