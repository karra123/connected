package com.connected;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Srinivas Reddy Karra
 * This is the main class for reading input, building graph, and finding connectivity between two cities. 
 */
public class Connected {
	public static Logger logger = LoggerFactory.getLogger(Connected.class);
	
	private static int ARGS_COUNT = 3;	

	private static String fileName;
	private static String cityName1;
	private static String cityName2;
	
	private String mFileName;
	private Map<String, CityNode> mapOfCities;

	/**
	 * Program entry point
	 * @param args
	 */
	public static void main(String[] args) {

		if (checkArgs(args)){
			Connected connected = new Connected(fileName);
			connected.isConnected(cityName1, cityName2);
		}
		else {
			printUsage();
		}
		
	}

	/**
	 * Method for checking the arguments
	 * @param args
	 * @return
	 */
    private static boolean checkArgs(String [] args){
    	logger.debug("checkArgs()");
    	boolean retVal = false ;
    	if ((args!=null) && (args.length==ARGS_COUNT )) retVal = true;
    	if (retVal) {
    		fileName = args[0];
    		cityName1 = args[1];
    		cityName2 = args[2];
    		logger.debug("fileName = " + fileName + " cityName1 = " + cityName1 + " cityName2 = " + cityName2);
    	}
    	logger.debug("checkArgs() - returns " + retVal);
    	return retVal;
    }
	
    /**
     * Method for printing program usage
     */
    private static void printUsage(){
    	System.out.println("Usage : java -jar connected-1.0.jar Input_file_name City_name_1 City_name_2");
    }
    
    /**
     * Constructor
     * @param tFileName
     */
    public Connected(String tFileName ){
    	this.mFileName = tFileName;
    	this.mapOfCities = new HashMap<>();
    	processFile();
    }
    
    /**
     * Method for reading the file and creating graph
     */
	private void processFile(){
		logger.debug("processFile()");
		try (BufferedReader reader = new BufferedReader(new FileReader(mFileName))) {	
			String line;
			while((line = reader.readLine()) != null){
				String [] strArray = line.split(",");
				String city1 = strArray[0].trim();
				String city2 = strArray[1].trim();
				if (!mapOfCities.containsKey(city1)) {
					mapOfCities.put(city1, new CityNode(city1));
				}
				if (!mapOfCities.containsKey(city2)) {
					mapOfCities.put(city2, new CityNode(city2));
				}
				CityNode cityNode1 = mapOfCities.get(city1);
				CityNode cityNode2 = mapOfCities.get(city2);
				cityNode1.addConnection(cityNode2);
				cityNode2.addConnection(cityNode1);
			}
		}
		catch (Exception e){
			logger.error(e.getClass() + ": " +  e.getMessage(), e);
		}
	}
	
	/**
	 * Method for checking if two cities are connected using breadth-first
	 */
	public void isConnected(String mCityName1, String mCityName2){
		logger.debug("isConnected()");
		boolean retVal = false;
		if (mapOfCities.size()<=0) {
			retVal = false;
		}
		else if (!mapOfCities.containsKey(mCityName1)) {
			retVal = false;
		}
		else if (!mapOfCities.containsKey(mCityName2)) {
			retVal = false;
		}
		
		if (mapOfCities.containsKey(mCityName1) && mapOfCities.containsKey(mCityName2)){
			logger.debug("map contains both cities");
			CityNode start = mapOfCities.get(mCityName1);
			CityNode end = mapOfCities.get(mCityName2);
			Set<CityNode> traversedCities = new HashSet<CityNode>();
			
			DistinctQueue<CityNode> myQ = new DistinctQueue<CityNode>();
			myQ.add(start);
			while (!myQ.isEmpty()){
				CityNode currentNode = myQ.poll();
				traversedCities.add(currentNode);
				logger.debug("current Node = " + currentNode.getMyName());
				if (currentNode.getMyConnections()!=null && currentNode.getMyConnections().size()>0){
					if (currentNode.getMyConnections().contains(end)){
						retVal = true;
						break;
					}
					else {
						Set<CityNode> myConnections = currentNode.getMyConnections();
						myConnections.removeAll(traversedCities);
						myQ.addAll(myConnections);
					}
				}
			}
		}		
		if (!retVal){
			System.out.println("no");
		}
		else {
			System.out.println("yes");
		}
	}
   
}
