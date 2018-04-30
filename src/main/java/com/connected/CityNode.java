package com.connected;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for encapsulating city node and its connections
 * @author Srinivas Reddy Karra
 *
 */
public class CityNode {
	public static Logger logger = LoggerFactory.getLogger(CityNode.class);
	
	private String myName;
	private Set<CityNode> myConnections;
	
	/**
	 * Constructor
	 * @param cityName
	 */
	public CityNode(String cityName){
		this.myName = cityName;
		myConnections = new HashSet<CityNode>();
	}

	/**
	 * Method for hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myName == null) ? 0 : myName.hashCode());
		return result;
	}

	/**
	 * Method for equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityNode other = (CityNode) obj;
		if (myName == null) {
			if (other.myName != null)
				return false;
		} else if (!myName.equalsIgnoreCase(other.myName))
			return false;
		return true;
	}
	
	/**
	 * Method for adding a node
	 * @param node
	 */
	public void addConnection(CityNode node){
		myConnections.add(node);
	}

	@Override
	public String toString() {
		return "CityNode [myName=" + myName + ", myConnections=" + myConnections + "]";
	}
	
	public String getMyName(){
		return this.myName;
	}
	
	public Set<CityNode> getMyConnections(){
		return this.myConnections;
	}
}
