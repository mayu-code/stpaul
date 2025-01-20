package com.college.stpaul.Helper;



public class IncrementSession {
    
    public static String incrementSession(String session) {
        String[] years = session.split("-");
        
        int startYear = Integer.parseInt(years[0]) + 1;
        int endYear = Integer.parseInt(years[1]) + 1;
        
        return startYear + "-" + endYear;
    }

    public static String decrementSession(String session) {
        String[] years = session.split("-");
        
        int startYear = Integer.parseInt(years[0]) - 1;
        int endYear = Integer.parseInt(years[1]) - 1;
        
        return startYear + "-" + endYear;
    }
}
