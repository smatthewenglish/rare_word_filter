import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extract_ideograph
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		
		String text; 
		BufferedReader br = new BufferedReader(new FileReader("/home/matthias/Workbench/SUTD/1_February/rare_word_filter/commonly_used_characters.csv"));

		while ((text = br.readLine()) != null) 
		{
			
			//the main character
			Pattern pat_0 = Pattern.compile( "“(.*?)”字的基本信息" );
			Matcher mat_0 = pat_0.matcher( text );
			if( mat_0.find() )
			{
			    System.out.println( "\n" + mat_0.group(1) );
			    
			}
			
		}
		
	}

}