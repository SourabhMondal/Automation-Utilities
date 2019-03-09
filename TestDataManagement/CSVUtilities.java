import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class CSVUtilities {

	
	// method name: getCSVDataAsMAP
	// purpose: use this method to get csv file data into Map [key, vakue] format
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: csv file path with csv file name
	// Return type: Map<Key, Value>
	
	
	public Map<String, String> getCSVDataAsMAP(String strCSVFilePath){
		
		File file = new File(strCSVFilePath);
		ArrayList<String> oData = new ArrayList<String>();
		Scanner scan = null;
		Map<String, String> oMap = new HashMap<String, String>();
		try {
			scan = new Scanner(file);
		} catch (Exception e) {
			System.out.println("File not found in derectory - "+strCSVFilePath);
		}
		String line = null;
		while(scan.hasNextLine()){
			line = scan.nextLine();
			if(line!=null){
				oData.add(line);
			}
		}
		String oHeader = oData.get(0);
		ArrayList<String> values = new ArrayList<String>();
		for(int i=1; i<oData.size();i++){
			values.add(oData.get(i));
		}
		
		String[] headerArray = oHeader.split(Pattern.quote(","));
		String[] arrayValue = values.get(0).split(Pattern.quote(","));
		for(int j=0;j<headerArray.length; j++){
			oMap.put(headerArray[j], arrayValue[j]);
		}
		
		return oMap;
	}
}
