package com.pd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFAutomation {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, Exception {
		String pdfPath = "C:\\Users\\soura\\workspace\\PDFAutomation\\src\\demo.pdf";
		boolean value = isPDFFile(pdfPath);
		System.out.println(value);
		
		boolean isTextPresent = verifyPDFContent(pdfPath, "SOURABHKUMAR");
		System.out.println(isTextPresent);
		
	}

	// method name: isPDFFile
	// purpose: use this method to verify whether file is pdf file or not
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: String pdf file path
	// Return type: boolean
	
	public static boolean isPDFFile(String oFilePath){
	    
		File file = new File(oFilePath);
		
		Scanner input = null;
		try {
			input = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    while (input.hasNextLine()) {
	        final String checkline = input.nextLine();
	        if(checkline.contains("%PDF-")) { 
	            // a match!
	            return true;
	        }  
	    }
	    return false;
	}
	
	// method name: verifyPDFContent
	// purpose: use this method to verify text content inside pdf document
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: String pdf file path & text(String) to validate
	// Return type: boolean
	
	public static boolean verifyPDFContent(String pdfPath, String text) throws Exception, IOException{
		File file = new File(pdfPath);
		PDDocument document = PDDocument.load(file);
		
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String pdftext = pdfTextStripper.getText(document);
		
		if(pdftext.contains(text)){
			System.out.println(text + " = is present in pdf ");
			document.close();
			return true;
		}else{
			System.out.println(   text + " = is not present in pdf ");
			document.close();
			return false;
		}
	}
}
