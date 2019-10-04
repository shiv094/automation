package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Platform;

public class ReadProperty {
	public static String loadProperty(String key,String prop)
	{
		Properties property=new Properties();
		FileInputStream fis=null;
		Platform current=Platform.getCurrent();
		if(current.WINDOWS!=null||current.VISTA!=null||current.XP!=null||current.MAC!=null)
		{
			String filepath="src//test//resources//configFiles//"+prop+".properties";
			try{
				fis=new FileInputStream(filepath);
				property.load(fis);
				fis.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}else{
			System.out.println("please check it again");
		}
		return property.getProperty(key);
	}

}
