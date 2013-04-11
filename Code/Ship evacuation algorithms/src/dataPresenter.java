import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class dataPresenter {
	HashMap<Double, Double> suriversDjixstra;
	HashMap<Double, Double> deadDjixstra;
	HashMap<Double, Double> stillRunningDjixstra;
	
	HashMap<Double, Double> suriversACO;
	HashMap<Double, Double> deadACO;
	HashMap<Double, Double> stillRunningACO;
	
	
	public dataPresenter()
	{
		
	}
	
	public void readACOFile() throws IOException
	{
		suriversACO = new HashMap<Double, Double>();
		deadACO = new HashMap<Double, Double>();
		stillRunningACO = new HashMap<Double, Double>();
		
		BufferedReader br = new BufferedReader(new FileReader("ACO\\ACO data test one.txt"));
		String line;
		br.readLine();
		br.readLine();
		while ((line = br.readLine()) != null) {
		
			String nextString = line.replace("	", "");
			
			
			String[] numbers = nextString.split(",");
			
			
			suriversACO.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[4]));
			deadACO.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[3]));
			stillRunningACO.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[2]));
			
			
			//List<String> list = ;
			
			
		}
		System.out.println(suriversACO);
		br.close();
	}
	
	public void readDjixstraFile() throws IOException
	{
		suriversDjixstra = new HashMap<Double, Double>();
		deadDjixstra = new HashMap<Double, Double>();
		stillRunningDjixstra = new HashMap<Double, Double>();
		
		BufferedReader br = new BufferedReader(new FileReader("Djixstra\\Dixstra data test one.txt"));
		String line;
		br.readLine();
		br.readLine();
		while ((line = br.readLine()) != null) {
			
			String nextString = line.replace("	", "");
			
			String[] numbers = nextString.split(",");
			
			
			suriversDjixstra.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[4]));
			deadDjixstra.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[3]));
			stillRunningDjixstra.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[2]));
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
		
		JFreeChart chart = ChartFactory.createXYLineChart("Survivors", "Time step", "Survivors", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
		ChartFrame frame = new ChartFrame("Data", chart);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	private XYSeries addNumbersToSeries(XYSeries theSelectedOne, HashMap<Double, Double> theNumbers)
	{
		for(double i = 0; i<theNumbers.size(); i++)
		{
			Double f = theNumbers.get(i);
			theSelectedOne.add(i, f);
		}
		return theSelectedOne;
	}
	
	public void test()
	{
		XYSeries series = new XYSeries("Average Weight");
		  series.add(20.0, 20.0);
		  series.add(40.0, 25.0);
		  series.add(55.0, 50.0);
		  series.add(70.0, 65.0);
		  XYDataset xyDataset = new XYSeriesCollection(series);
		  JFreeChart chart = ChartFactory.createXYLineChart
		  ("XYLine Chart using JFreeChart", "Age", "Weight",
		 xyDataset, PlotOrientation.VERTICAL, true, true, false);
		  ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
		  frame1.setVisible(true);
		  frame1.setSize(300,300);
	}

}
