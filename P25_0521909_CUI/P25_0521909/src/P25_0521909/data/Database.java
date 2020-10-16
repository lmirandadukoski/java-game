package P25_0521909.data;

import java.util.*;
import java.util.Map.Entry;

/**
 * DataRow represents collection of data that was originally contained
 * in the application or a .txt file.
 * The database's key is a generic, so as to contain keys of all types.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class Database <E>{
    private HashMap<E, DataRow> data;   // The collection containing the database's data
    ArrayList<String> dataSchema;       // The datbase's schema.
    
    public Database(){
        data = new HashMap<>();
        dataSchema = new ArrayList<>();
    }
    
    /**
     * Adds a new row of data to the database.
     * 
     * @param key
     * @param rowData
     */
    public void addDataRow(E key, String rowData){
        data.put(key, new DataRow(rowData));
    }
    
    /**
     * Returns the database's schema.
     * 
     * @return
     */
    public ArrayList<String> getDataSchema(){
        return dataSchema;
    }

    /**
     * Returns a row of data matching the key's value.
     * 
     * @param keyValue
     * @return
     */
    public DataRow getDataRow(E keyValue){
        return data.get(keyValue);
    }
    
    /**
     * Returns a random row of data.
     * 
     * @return
     */
    public DataRow getRandomDataRow(){
        int index = (int) (Math.random() * data.size());
        int i = 0;
        DataRow temp = null;
        
        for(Map.Entry<E, DataRow> entry: data.entrySet()){
            if(i == index){
                temp = entry.getValue();
                break;
            }
            i++;
        }

        return temp;
    }
    
    /**
     * Returns the entire entry set at a specified index location
     * in the database.
     * 
     * @param index
     * @return
     */
    public Entry<E, DataRow> getEntrySet(int index){
        Entry<E, DataRow> temp = null;
        int i = 0;

        for(Map.Entry<E,DataRow> entry: data.entrySet()){
            if(i == index){
                temp = entry;
                break;
            }
            i += 1;
        }        
        return temp;
    }
    
    /**
     * Returns the size of the database (i.e., the number
     * of data rows).
     * 
     * @return
     */
    public int getSize(){
        return data.size();
    }
}
