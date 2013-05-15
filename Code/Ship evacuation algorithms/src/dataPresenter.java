import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import javax.imageio.ImageIO;

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
	
	int howManyRepetitions;
	String fileName;
	int maxTurns;
	String imageFileName;
	
	
	public dataPresenter(int howManyRepetisions, String fileName, int maxTurns, String imageFileName)
	{
		this.howManyRepetitions = howManyRepetisions;
		this.fileName = fileName;
		this.maxTurns = maxTurns;
		this.imageFileName = imageFileName;
	}
	
	public void readACOFile() throws IOException
	{
		suriversACO = new HashMap<Double, Double>();
		deadACO = new HashMap<Double, Double>();
		stillRunningACO = new HashMap<Double, Double>();
		
		BufferedReader br = new BufferedReader(new FileReader("ACO\\ACO "+fileName+".txt"));
		String line;
		br.readLine();
		br.readLine();
		String[] prevNumbers = null;
		while ((line = br.readLine()) != null) {
		
			String nextString = line.replace("	", "");
			
			
			String[] numbers = nextString.split(",");
			
			if(prevNumbers != null && Double.parseDouble(numbers[0]) == 0 && Double.parseDouble(prevNumbers[0]) < (double)maxTurns)
			{
				double a;
				for(a = Double.parseDouble(prevNumbers[0])+1; a <= (double)maxTurns; a++)
				{
					prevNumbers[0] = Double.toString(a);
					
					addACOLists(prevNumbers);
				}
			}
			
			
			prevNumbers = numbers;
			
			addACOLists(numbers);
			
		}
		
		
		for(double i = 0; i<suriversACO.size(); i++)
		{
			suriversACO.put(i, suriversACO.get(i)/howManyRepetitions);
			deadACO.put(i, deadACO.get(i)/howManyRepetitions);
			stillRunningACO.put(i, stillRunningACO.get(i)/howManyRepetitions);
		}
		//System.out.println(suriversACO);
		br.close();
	}
	
	public void readDjixstraFile() throws IOException
	{
		suriversDjixstra = new HashMap<Double, Double>();
		deadDjixstra = new HashMap<Double, Double>();
		stillRunningDjixstra = new HashMap<Double, Double>();
		
		BufferedReader br = new BufferedReader(new FileReader("Djixstra\\Dixstra "+fileName+".txt"));
		String line;
		br.readLine();
		br.readLine();
		String[] prevNumbers = null;
		while ((line = br.readLine()) != null) {
			
			
			
			String nextString = line.replace("	", "");
			
			String[] numbers = nextString.split(",");
			
			
			if(prevNumbers != null && Double.parseDouble(numbers[0]) == 0 && Double.parseDouble(prevNumbers[0]) < (double)maxTurns)
			{
				double a;
				for(a = Double.parseDouble(prevNumbers[0])+1; a <= (double)maxTurns; a++)
				{
					prevNumbers[0] = Double.toString(a);
					
					addDjixstraLists(prevNumbers);
				}
			}
			
			
			prevNumbers = numbers;
			
			addDjixstraLists(numbers);
			
			
			
		}
		
		for(double i = 0; i<suriversDjixstra.size(); i++)
		{
			suriversDjixstra.put(i, suriversDjixstra.get(i)/howManyRepetitions);
			deadDjixstra.put(i, deadDjixstra.get(i)/howManyRepetitions);
			stillRunningDjixstra.put(i, stillRunningDjixstra.get(i)/howManyRepetitions);
		}
		
		
		br.close();
	}
	
	public void crateGraph() throws IOException
	{
		XYSeries survivedACO = new XYSeries("ACO survivers");
		XYSeries survivedDjix = new XYSeries("Dijkstra survivers");
		XYSeries deadACO = new XYSeries("ACO passangers that had died");
		XYSeries deaddDjix = new XYSeries("Dijkstra passangers that had died");
		//XYSeries bruteForce = new XYSeries("Brute force");
		
		readACOFile();
		readDjixstraFile();
		
		addNumbersToSeries(survivedACO, suriversACO);
		addNumbersToSeries(survivedDjix, suriversDjixstra);
		addNumbersToSeries(deadACO, this.deadACO);
		addNumbersToSeries(deaddDjix, deadDjixstra);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		
		
		dataset.addSeries(survivedACO);
		dataset.addSeries(survivedDjix);
		dataset.addSeries(deadACO);
		dataset.addSeries(deaddDjix);
		//dataset.addSeries(antSystem);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Survivors", "Time step", "Survivors", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
		ChartFrame frame = new ChartFrame("Data", chart);
		frame.pack();
		frame.setVisible(true);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
		Date date = new Date();
		
		String s = dateFormat.format(date);
		
		String[] sl = s.split("/");
		
		
		
		BufferedImage objBufferedImage=chart.createBufferedImage(1000,600);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		        try {
		            ImageIO.write(objBufferedImage, "png", bas);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		byte[] byteArray=bas.toByteArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage image = ImageIO.read(in);
		File outputfile = new File("Data images\\"+imageFileName+" "+sl[0]+"-"+sl[1]+"-"+sl[2]+"-"+sl[3]+"-"+sl[4]+".png");
		ImageIO.write(image, "png", outputfile);
		
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
	
	private void addDjixstraLists(String[] numbers)
	{
		//Survivrs
		if(suriversDjixstra.containsKey(Double.parseDouble(numbers[0])))
		{
			suriversDjixstra.put(Double.parseDouble(numbers[0]), suriversDjixstra.get(Double.parseDouble(numbers[0]))+Double.parseDouble(numbers[4])); 
		}
		else
		{
			suriversDjixstra.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[4]));
		}
		
		//Dead
		if(deadDjixstra.containsKey(Double.parseDouble(numbers[0])))
		{
			deadDjixstra.put(Double.parseDouble(numbers[0]), deadDjixstra.get(Double.parseDouble(numbers[0]))+Double.parseDouble(numbers[3])); 
		}
		else
		{
			deadDjixstra.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[3]));
		}
		
		//Stillrunning
		if(stillRunningDjixstra.containsKey(Double.parseDouble(numbers[0])))
		{
			stillRunningDjixstra.put(Double.parseDouble(numbers[0]), stillRunningDjixstra.get(Double.parseDouble(numbers[0]))+Double.parseDouble(numbers[2])); 
		}
		else
		{
			stillRunningDjixstra.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[2]));
		}
	}
	
	private void addACOLists(String[] numbers)
	{
		//Survivrs
		if(suriversACO.containsKey(Double.parseDouble(numbers[0])))
		{
			suriversACO.put(Double.parseDouble(numbers[0]), suriversACO.get(Double.parseDouble(numbers[0]))+Double.parseDouble(numbers[4])); 
		}
		else
		{
			suriversACO.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[4]));
		}
		
		//Dead
		if(deadACO.containsKey(Double.parseDouble(numbers[0])))
		{
			deadACO.put(Double.parseDouble(numbers[0]), deadACO.get(Double.parseDouble(numbers[0]))+Double.parseDouble(numbers[3])); 
		}
		else
		{
			deadACO.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[3]));
		}
		
		//Stillrunning
		if(stillRunningACO.containsKey(Double.parseDouble(numbers[0])))
		{
			stillRunningACO.put(Double.parseDouble(numbers[0]), stillRunningACO.get(Double.parseDouble(numbers[0]))+Double.parseDouble(numbers[2])); 
		}
		else
		{
			stillRunningACO.put(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[2]));
		}
	}


}
