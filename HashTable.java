package Hash;

import java.util.*;

public class HashTable {
    //Attributes
    LinkedList[] thearray;
    int maximum;
    int arraysize;
    int tableSize;
    int itemsinarray = 0;
    
    //Airport codes which are in the airport codes file
    //Alphabetical
    static String[][] airport = {                  
                                 {"XAZ"},{"XBR"},{"XCM"},{"XDM"},{"XFD"},{"XLV"},{"XLZ"},{"YBA"},{"YBC"},{"YBR"},{"YCA"},{"YCC"},{"YCD"},{"YCG"},{"YCM"},{"YDF"},
                                 {"YDN"},{"YDQ"},{"YEG"},{"YEL"},{"YEV"},{"YEY"},{"YFB"},{"YFC"},{"YFS"},{"YGB"},{"YGK"},{"YGW"},{"YGX"},{"YHB"},{"YHI"},
                                 {"YHM"},{"YHN"},{"YHZ"},{"YJA"},{"YJT"},{"YKA"},{"YYU"},{"YYY"},{"YYZ"},{"YZF"},{"YZT"},{"YZV"},{"ZGI"},{"ZHO"}
                                };
    
    //Airport codes which are not in the airport codes file
    //Not alphabetical
    static String[][] airport2 = {{"YTS"},{"YTT"},{"YAZ"},{"YKD"},{"YZZ"},{"YTI"},{"YRQ"},{"YUB"},{"ZFN"},{"YTK"},{"TUX"},{"TNS"},{"YUD"},{"YBE"},{"YVO"}
                                  ,{"ZBD"},{"CXH"},{"YVR"},{"YVG"},{"YVE"},{"YWH"},{"YKQ"},{"YKF"},{"YQH"},{"YXZ"},{"YWP"},{"YNC"},{"YXN"},{"YLE"},{"YWS"},{"YWR"}
                                  ,{"YZU"},{"YXY"},{"YVV"},{"YWM"},{"YWL"},{"YQG"},{"YWN"},{"YWG"},{"YAV"},{"ZWL"},{"YWY"},{"WNN"},{"YQI"},{"ZST"}
    
    
                                 };
        
    
    
    public HashTable(int tablesize, double loadfac) { //Constructor
        if (tablesize <= 0 || loadfac <= 0.0) {
            throw new IllegalArgumentException();
        }
        arraysize = tablesize;
        thearray = new LinkedList[tablesize]; //Creates an array
        for (int i = 0; i < tablesize; i++) {
            thearray[i] = new LinkedList(); //For each element in the array, it creates a linkedlist
        }

        tableSize = tablesize;
        maximum = (int) (tablesize * loadfac); //The threshold for the arraysize
        
        arraysize = maximum;
        addTheArray(airport);
        

    }
    public int HashFunction1(String stringtohash) { 

        int hashindex = 0;
        
        //Splits the string
        String First = stringtohash.substring(0, 1);
        String Second = stringtohash.substring(1, 2);
        String Third = stringtohash.substring(2);

        //Each split letters are then converted to an integer
        int num = First.charAt(0) - 65;
        int num1 = Second.charAt(0) - 65;
        int num2 = Third.charAt(0) - 65;
        
        //Calculates the hash value
        int hashvalue = num * 26 * 26 + num1 * 26 + num2;
        
        //Mods the hashvalue to a hashindex
        hashindex = hashvalue % tableSize;
        
      
        //Since we can only access first 45 locations, we mod again
        if(hashindex>arraysize){
            
            hashindex = hashindex%arraysize;
 
            return hashindex;
            
        }
        else{

        return hashindex;
        }

    }

    public int HashFunction2(String stringtohash) {
        //Same concept as the hashfunction1

        int hashindex = 0;

        String First = stringtohash.substring(0, 1);
        String Second = stringtohash.substring(1, 2);
        String Third = stringtohash.substring(2);


        int num = First.charAt(0) - 65;
        int num1 = Second.charAt(0) - 65;
        int num2 = Third.charAt(0) - 65;
        
        //Different calculation then hashfunction1, reverses the use of the designator's characters
        int hashvalue = num2 * tableSize * tableSize + num1 * tableSize + num;

        hashindex = hashvalue % tableSize;
        
        if(hashindex>arraysize){        
            hashindex = hashindex%arraysize;
   
            return hashindex;
            
        }
       
        else{

            return hashindex;
        }

    }

    public void addTheArray(String[][] airportcodes) { //Adds the array

        for (int i = 0; i < airportcodes.length; i++) {

            String code = airportcodes[i][0];

            airport designator = new airport(code);

            insert(designator);

        }

    }

    public void insert(airport newWord) {

        String stringtohash = newWord.designator;
        
        int hashKey = HashFunction2(stringtohash); //Inserts using function 1 or 2
        
        thearray[hashKey].add(newWord);
    }
    
    public void find (String wordtofind){
         int comparison = 1; //Successful
         int comparison2 = 0; //Unsuccessful
         int found = 0; //If found/not found
         

         int hashKey = HashFunction2(wordtofind); //Uses function 1 or 2
 
         for(int i=0; i<thearray[hashKey].size();i++){
             comparison++;
             if(wordtofind.equalsIgnoreCase(thearray[hashKey].get(i).toString())){//If found
                 System.out.println("HashKey:" + hashKey);
                 System.out.println("Code:" + wordtofind);
                 System.out.println("IterationNum:" + i);
                 System.out.println("Comparisons:" + comparison);
                 System.out.println();
                 found++;
             }   
         }
            
         if(found==0){//If nothing was found
            for(int i=0; i<arraysize;i++){
                if(thearray[i].head==null){
                    comparison2++;
                }
                if(thearray[i].head!=null){
                    for(int k=0; k<thearray[i].size();k++){
                        if(!wordtofind.equalsIgnoreCase(thearray[i].get(k).toString())){
                            comparison2++;
                        }
                    }
                }
            }
            System.out.println("Nothing found");
            System.out.println("Comparisons:" + comparison2);
         }
               
    }

    public void displayTheArray() {//Displays array

        for (int i = 0; i < tableSize; i++) {

            System.out.println("Index" + " " + i);

            thearray[i].printList(thearray[i].head);
        }
    }


    public static void main(String[] args) {
        Random r = new Random(); //Randomizer
        /*
        //Randomly selected 5 different airport codes which are not in the airport codes file
        //Ran the program until 5 different codes were seen
        String miss = airport2[r.nextInt(airport2.length)][0];
        String miss1 = airport2[r.nextInt(airport2.length)][0];
        String miss2 = airport2[r.nextInt(airport2.length)][0];
        String miss3 = airport2[r.nextInt(airport2.length)][0];
        String miss4 = airport2[r.nextInt(airport2.length)][0];
        
        //Randomly selected 5 different airport codes which are from the airport codes file
        //Ran the program until 5 different codes were seen
        String select = airport[r.nextInt(airport.length)][0];
        String select1 = airport[r.nextInt(airport.length)][0];
        String select2 = airport[r.nextInt(airport.length)][0];
        String select3 = airport[r.nextInt(airport.length)][0];
        String select4 = airport[r.nextInt(airport.length)][0];
        
        //Hashtable for each ladfactor and associated table size is generated
        HashTable show = new HashTable(449, 0.1);
        HashTable show1 = new HashTable(53, 0.85);
        HashTable show2 = new HashTable(61, 0.74);
        HashTable show3 = new HashTable(89, 0.5);
        HashTable show4 = new HashTable(181, 0.25);
        HashTable show5 = new HashTable(47, 0.96);
        
        //Prints and finds for successful comparisons and unsuccessful comparisons for each load factor and their associated table size
        System.out.println("Avg. # of Comparisons/successful&unsuccessful look up");
        System.out.println();
        System.out.println("Load Factor 10%, Associated table size: 449");
        System.out.println("Successful:");
        System.out.println();
        show.find(select);
        show.find(select1);  
        show.find(select2);  
        show.find(select3);  
        show.find(select4);
        System.out.println();
        System.out.println("Unsuccessful:");
        show.find(miss);
        show.find(miss1);
        show.find(miss2);
        show.find(miss3);
        show.find(miss4);
        System.out.println();
        
        System.out.println();
        System.out.println("Load Factor 25%, Associated table size: 181");
        System.out.println("Successful:");
        System.out.println();
        show1.find(select);
        show1.find(select1);
        show1.find(select2);
        show1.find(select3);
        show1.find(select4);
        System.out.println();
        System.out.println("Unsuccessful:");
        show1.find(miss);
        show1.find(miss1);
        show1.find(miss2);
        show1.find(miss3);
        show1.find(miss4);
        System.out.println();
        
        System.out.println();
        System.out.println("Load Factor 50%, Associated table size: 89");
        System.out.println("Successful:");
        System.out.println();
        show2.find(select);
        show2.find(select1);
        show2.find(select2);
        show2.find(select3);
        show2.find(select4);
        System.out.println();
        System.out.println("Unsuccessful:");
        show2.find(miss);
        show2.find(miss1);
        show2.find(miss2);
        show2.find(miss3);
        show2.find(miss4);
        System.out.println();
        
        System.out.println();
        System.out.println("Load Factor 74%, Associated table size: 61");
        System.out.println("Successful:");
        System.out.println();
        show3.find(select);
        show3.find(select1);
        show3.find(select2);
        show3.find(select3);
        show3.find(select4);
        System.out.println();
        System.out.println("Unsuccessful:");
        show3.find(miss);
        show3.find(miss1);
        show3.find(miss2);
        show3.find(miss3);
        show3.find(miss4);
        System.out.println();
        
        System.out.println();
        System.out.println("Load Factor 85%, Associated table size: 53");
        System.out.println("Successful:");
        System.out.println();
        show4.find(select);
        show4.find(select1);
        show4.find(select2);
        show4.find(select3);
        show4.find(select4);
        System.out.println();
        System.out.println("Unsuccessful:");
        show4.find(miss);
        show4.find(miss1);
        show4.find(miss2);
        show4.find(miss3);
        show4.find(miss4);
        System.out.println();
        
        System.out.println();
        System.out.println("Load Factor 96%, Associated table size: 47");
        System.out.println("Successful:");
        System.out.println();
        show5.find(select);
        show5.find(select1);
        show5.find(select2);
        show5.find(select3);
        show5.find(select4);
        System.out.println();
        System.out.println("Unsuccessful:");
        show5.find(miss);
        show5.find(miss1);
        show5.find(miss2);
        show5.find(miss3);
        show5.find(miss4);
        */
        
        //The program ran 10 times for each function
        //The statistics have been generated for the following functions
        System.out.println();
        System.out.println("Function 1:");
        System.out.println("Load Factor" + "            " + "10%" + "       " + "25%" + "       " + "50%" + "       " + "74%" + "       " + "85%"   + "       " + "96%");
        System.out.println("Associated table size" + "  " + "449" + "       " + "181" + "       " + "89 " + "       " + "61 " + "       " + "53 "   + "       " + "47 ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("avg.# of comparisons/\nsuccessful look-up" +  "     " + "11.5" +  "      " + "11.9" + "      "+ "12.2" + "      "+ "11.6" +"      " +  "12.7" + "      "+ "14.4");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("avg.# of comparisons/\nunsuccessful look-up" +  "    " + "58" +  "        " + "59" + "        "+ "62" + "        "+ "58" +"        " +  "62" + "        "+ "63");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Function 2:");
        System.out.println("Load Factor" + "            " + "10%" + "       " + "25%" + "       " + "50%" + "       " + "74%" + "       " + "85%"   + "       " + "96%");
        System.out.println("Associated table size" + "  " + "449" + "       " + "181" + "       " + "89 " + "       " + "61 " + "       " + "53 "   + "       " + "47 ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("avg.# of comparisons/\nsuccessful look-up" +  "     " + "89.8" +  "      " + "89.8" + "      "+ "89.8" + "      "+ "89.8" +"      " +  "89.8" + "      "+ "89.8");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("avg.# of comparisons/\nunsuccessful look-up" +  "    " + "86" +  "        " + "87" + "        "+ "87" + "        "+ "86" +"        " +  "87" + "        "+ "87");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        
        
        
        
        

    }
}
