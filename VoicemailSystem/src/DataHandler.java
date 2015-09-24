import java.io.*;
import java.util.*;


public class DataHandler {
	public void saveMes(String fileName, ArrayList<String> mesList){
		FileWriter fileWriter;  
	    BufferedWriter bufferedWriter; 
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter); 
			//Once writing objects are instantiated, the existing content of the file would be wiped out...
            Iterator<String> it = mesList.iterator();
            while ( it.hasNext() ) {
            	String mes = it.next();
            	bufferedWriter.write( mes );
            	bufferedWriter.write(System.getProperty ( "line.separator" ));
    		}
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
	}
	
	public void getMes(String fileName, ArrayList<String> mesList){
		String mes = null;
        try {
            FileReader fileReader = new FileReader(fileName);            
            BufferedReader bufferedReader = new BufferedReader(fileReader);//Wrap FileReader in BufferedReader.

            while((mes = bufferedReader.readLine()) != null) {
             	mesList.add(mes);
            } 
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Error reading file"); 
            ex.printStackTrace();                  
        }
	}
	
	@SuppressWarnings("resource")
	public boolean numberFound(String fileName, String number){
		boolean found = false;
		String num = "";
		File file = new File(fileName);
		try {
            Scanner sc;
            sc = new Scanner(file);
            sc.useDelimiter("[1-9]d{9}");
            while(sc.hasNext()){
            	num = sc.next();
            	if(number.equals(num)){
            		found = true;
            		break;
            	}
            }
		} catch(IOException e) {
            System.out.println("Unable to read from file.");
		}
		return found;
	}
	
	public void saveData(String fileName, String data){
		FileWriter fileWriter;  
	    BufferedWriter bufferedWriter; 
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter); 
			//Once writing objects are instantiated, the existing content of the file would be wiped out...
            bufferedWriter.write( data );
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
	}
	
	public String getData(String fileName, String data){
        try {
            FileReader fileReader = new FileReader(fileName);            
            BufferedReader bufferedReader = new BufferedReader(fileReader);//Wrap FileReader in BufferedReader.

            data = bufferedReader.readLine(); 
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Error reading file"); 
            ex.printStackTrace();                  
        }
        return data;
	}

}
