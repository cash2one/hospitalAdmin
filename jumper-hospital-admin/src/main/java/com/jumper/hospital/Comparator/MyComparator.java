package com.jumper.hospital.Comparator;

import java.util.Comparator;

public class MyComparator implements Comparator<String>
{

    public int compare(String o1, String o2)
    {
        
        String[] strArray1 = o1.split(";");
        String[] strArray2 = o2.split(";");
        
        int result = Integer.parseInt(strArray1[0]) - Integer.parseInt(strArray2[0]);
        
        if( result == 0){
            return Integer.parseInt(strArray1[1]) - Integer.parseInt(strArray2[1]);
        } else {
            return result;
        }
    }

}
