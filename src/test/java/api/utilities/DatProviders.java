package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DatProviders {

	//DataProvider1
	@DataProvider(name ="Data")
	public String [][] getData()throws IOException
	{
		String path = ".\\testData\\RestAPI.xlsx";

		//String path = System.getProperty("user.dire")+"//testData//RestAPI.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcolms = xlutil.getCellCount("Sheet1",1);
		
		String apidata[][]=new String [totalrows][totalcolms];
		
		for(int i =1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcolms;j++)
			{
				apidata[i-1][j] = xlutil.getCellData("Sheet1", i,j);
			}	
		}
		
		return apidata;//return two dimensional array
	}
	
	@DataProvider(name ="UserNames")
	public String []getUsernames()throws IOException
	{
		//String path = ".//testData//RestAPI.xlsx";

		String path = System.getProperty("user.dire")+"//testData//RestAPI.xlsx";
		
		ExcelUtility xl = new ExcelUtility(path);
		
		int totalrows = xl.getRowCount("Sheet1");
		//int totalcolms = xlutil.getCellCount("Sheet1",1);
		
		String apidata[]=new String [totalrows];
		
		for(int i =1;i<=totalrows;i++)
		{
			apidata[i-1]	=xl.getCellData("Sheet1", i,1);
		}
		
		return apidata;//return two dimensional array
	}
	
	
	
	
	
}
