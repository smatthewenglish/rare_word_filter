import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class rare_word_filter 
{
  Set<Character> keys = new HashSet<>();
  PrintWriter osw; 
  void checkAndDump( List<String> lines ) throws Exception 
  {
    if( lines.size() >= 1 &&
        keys.contains( lines.get(0).charAt(0) ) )
    {
      for( String s: lines )
      {
        osw.println( s );
      }
      osw.println();
    }
    lines.clear();
  }

  void filter( String inpath, String outpath ) throws Exception 
  {
    BufferedReader lr = new BufferedReader( new FileReader( inpath ) );
    osw = new PrintWriter( new FileOutputStream( outpath ) );
    String line;
    List<String> lines = new ArrayList<>();
    while( (line = lr.readLine()) != null )
    {
      if( line.length() > 0 )
      {
        lines.add( line );
      } 
      else 
      {
        checkAndDump( lines );
      }
    }
    checkAndDump( lines );
    osw.close();
    lr.close();
  }

  void fillSet( String path ) throws Exception 
  {
    BufferedReader br = new BufferedReader( new FileReader( path ) );
    String line;
    while( (line = br.readLine()) != null )
    {
      if( line.length() > 0 )
      {
        keys.add( line.charAt(0) );
      }
    }
    br.close();
  }    

  public static void main( String[] args ) throws Exception 
  {
    //path
    String path = "/home/matthias/Workbench/SUTD/1_February/rare_word_filter/";

    //inputs
    String raw_character_component_doc = "character_component_document_including_rare.txt";
    String common_character_filter_file = "commonly_used_characters_extracted.txt";
    
    //output
    String output_file = "refined_table.txt";


    rare_word_filter f = new rare_word_filter();

    f.fillSet( path + common_character_filter_file );

    f.filter( path + raw_character_component_doc , path + output_file );
  }
}