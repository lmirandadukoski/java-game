package P25_0521909.data;

import java.io.*;

/**
 * DataFileReader reads data in .txt files and parses them into
 * a database.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class DataFileReader {
    public Database database;       // The database that will end up containing all of the file's data.    
    private final String filePath;  // The full directory path to the data file.
    
    /**
     * To create a DataFileReader instance, the user must pass
     * a path to the data file.
     * 
     * @param filePath
     */
    public DataFileReader(String filePath){
        this.filePath = filePath;
        database = new Database();
    }
    
    /**
     * Reads each row in the data file.
     * 
     */
    public void readFileData(){
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader inStream = new BufferedReader(fr);
            
            String line;            
            while((line = inStream.readLine()) != null){
                parseRow(line); //Each line will be processed in separate functions.
            }
            
            inStream.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("The " + filePath + "file was not found.");
        }
        catch (IOException e){
            System.out.println("There were issues reading from " + filePath);
        }        
    }

    /**
     * To create a DataFileReader instance, the user must pass
     * a path to the data file.
     * 
     * @param rowRawData
     */    
    private void parseRow(String rowRawData){
        if(rowRawData.contains("key")){ // The line in the data file containing the word "key" will be come the database schema.
            addSchemaRow(rowRawData);   // The database schema is store separately in the database, so it's parsed by a different function to data rows.
        }
        else{
            String rawKey = rowRawData.substring(0, rowRawData.indexOf(",")).trim();    // The key is the first portion of each line in the data file.
            String rowData = rowRawData.substring(rowRawData.indexOf(",") + 1).trim(); // The rest of the line is data values.
        
            try{
                int intKey = Integer.parseInt(rawKey);  // Testing to see if the data line's key is an Integer so that it can be stored as that type.
                database.addDataRow(intKey, rowData);
            }
            catch(NumberFormatException e){
                database.addDataRow(rawKey, rowData);  
            }              
        }
    }

    /**
     * Adds the data schema line to the database.
     * 
     * @param rowRawData
     */      
    private void addSchemaRow(String rowRawData){
        String [] keys = rowRawData.split(",");
        for(int i = 1; i < keys.length; i++){
            database.dataSchema.add(keys[i].trim());
        }
    }  
}
