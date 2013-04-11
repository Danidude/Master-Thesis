import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class dataPresenter {
	HashMap<Float, Float> suriversDjixstra;
	HashMap<Float, Float> deadDjixstra;
	HashMap<Float, Float> stillRunningDjixstra;
	
	HashMap<Float, Float> suriversACO;
	HashMap<Float, Float> deadACO;
	HashMap<Float, Float> stillRunningACO;
	
	
	public dataPresenter()
	{
		
	}
	
	public void readACOFile() throws IOException
	{
		suriversACO = new HashMap<Float, Float>();
		deadACO = new HashMap<Float, Float>();
		stillRunningACO = new HashMap<Float, Float>();
		
		BufferedReader br = new BufferedReader(new FileReader("ACO\\ACO data test one.txt"));
		String line;
		br.readLine();
		br.readLine();
		while ((line = br.readLine()) != null) {
		
			String nextString = line.replace("	", "");
			
			String[] numbers = nextString.split(",");
			
			suriversACO.put(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[4]));
			deadACO.put(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[3]));
			stillRunningACO.put(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[2]));
			
			
			//List<String> list = ;
			
			
		}
		System.out.println(suriversACO);
		br.close();
	}
	
	public void readDjixstraFile() throws IOException
	{
		suriversDjixstra = new HashMap<Float, Float>();
		deadDjixstra = new HashMap<Float, Float>();
		stillRunningDjixstra = new HashMap<Float, Float>();
		
		BufferedReader br = new BufferedReader(new FileReader("Djixstra\\Dixstra data test one.txt"));
		String line;
		br.readLine();
		br.readLine();
		while ((line = br.readLine()) != null) {
			
			String nextString = line.replace("	", "");
			
			String[] numbers = nextString.split(",");
			
			suriversDjixstra.put(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[4]));
			deadDjixstra.put(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[3]));
			stillRunningDjixstra.put(Float.parseFloat(numbers[0]), Float.parseFloat(numbers[2]));
		}
		System.out.println();
		br.close();
	}
	
	public void crateGraph() throws IOException
	{
		XYSeries survivedACO = new XYSeries("ACO");
		XYSeries survivedDjix = new XYSeries("Djixstra");
		//XYSeries bruteForce = new XYSeries("Brute force");
		
		readACOFile();
		readDjixstraFile();
		
		addNumbersToSeries(survivedACO, suriversACO);
		addNumbersToSeries(survivedDjix, suriversDjixstra);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		dataset.addSeries(survivedACO);
		dataset.addSeries(survivedDjix);
		//dataset.addSeries(antSystem);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Survivors", "Ants", "Survivors", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
		ChartFrame frame = new ChartFrame("Data", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	private XYSeries addNumbersToSeries(XYSeries theSelectedOne, HashMap<Float, Float> theNumbers)
	{
		for(int i = 1; i<theNumbers.size(); i++)
		{
			theSelectedOne.add(i, theNumbers.get(i));
		}
		return theSelectedOne;
	}

}
