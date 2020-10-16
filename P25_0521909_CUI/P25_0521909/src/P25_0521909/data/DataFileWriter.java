package P25_0521909.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * DataFileWriter writes data contained in a database to a .txt file.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class DataFileWriter {
    public Database database;       // The database that contains the data that will end up going into the .txt file.
    private final String filePath;  // The full directory path to the data file.

    /**
     * To create a DataFileWriter instance, the user must pass
     * a path to the .txt file, and the database schema.
     * 
     * @param filePath
     * @param dataSchema
     */    
    public DataFileWriter(String filePath, ArrayList<String> dataSchema){
        this.filePath = filePath;

        database = new Database();
        database.dataSchema = dataSchema;
    }
    
    /**
     * Writes the database's data to the .txt file.
     * 
     */
    public void writeFileData(){
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
            
            // Prints the database schema to the file.
            pw.print("key, ");  
            for (int i = 0; i < database.dataSchema.size(); i++){
                pw.print(database.dataSchema.get(i));
                if(i < database.dataSchema.size() - 1){
                    pw.print(", ");
                }
            }
            
            pw.println();
            
            // Prints the database data to the file.
            for(int i = 0; i < database.getSize(); i++){
                // Gets each row in the database first...
                Entry<String, DataRow> temp = database.getEntrySet(i);
                pw.print(temp.getKey());
                pw.print(", ");
                
                // ...then prints each element in the row to the .txt file.
                DataRow dataRow = temp.getValue();
                for(int j = 0; j < database.dataSchema.size(); j++){
                    pw.print(dataRow.getDatum(j));
                    if(j < database.dataSchema.size() - 1){
                        pw.print(", ");
                    }                    
                }
                
                if(i < database.getSize() - 1){
                    pw.println();
                }
            }
            
            pw.close();
        }
        catch(FileNotFoundException e){}        
    }    
}
