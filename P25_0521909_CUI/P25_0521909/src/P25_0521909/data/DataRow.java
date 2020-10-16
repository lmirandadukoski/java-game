package P25_0521909.data;

import P25_0521909.interfaces.IDebugable;

/**
 * DataRow represents a single line of data in a database.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class DataRow implements IDebugable{
    private Object[] rowData;
    
    /**
     * To create a DataRow instance, the user must pass
     * a line of raw data (i.e., without having removed
     * delimiters first).
     * 
     * @param rawData
     */
    public DataRow(String rawData){        
        int regexCount = countRegex(rawData, ',');
        rowData = new Object[regexCount];
        
        if(regexCount > 0){
            rowData = parseRowData(rawData, ",");
        }
        else{
            rowData[0] = rawData.trim();
        }
    }
    
    @Override
    public void printInfo(){
        for(int i = 0; i < rowData.length; i++){
            System.out.print(rowData[i] + " ");
        }
        System.out.println("");
    } 

    /**
     * Returns the number of delimiters in the raw data line.
     * 
     * @param rawData
     * @param regex
     */    
    private int countRegex(String rawData, char regex){
        int count = 0;

        for(int i=0; i < rawData.length(); i++)
        {    if(rawData.charAt(i) == regex)
                count += 1;
        }

        return count;
    }

    /**
     * Parses each element in the raw data line into an array.
     * 
     * @param rawData
     * @param regex
     */    
    private Object[] parseRowData(String rawData, String regex){
        String[] temp = rawData.split(regex);
        Object[] cleanData = new Object[temp.length];
        
        for (int i = 0; i < temp.length; i++){
            Object value = temp[i].trim();
            cleanData[i] = value;
        }

        return cleanData;
    }

    /**
     * Returns the data element at the specified position.
     * 
     * @param index
     */      
    public Object getDatum(int index){
        return rowData[index];
    }
}
