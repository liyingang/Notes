package com.li.home.work.tree;


public class Mock {
    private static String data="01 WS_WORK_FIELD1\n"+
                        "05 WS-FUND_ID-LEN1 PIC S9(04) COMP\n"+
                        "05 WS-FUND_ID-DATA1 PIC X(04) \n"+
                        "07 WS-FUND_ID-DATA1 PIC BT(05) \n"+
                        "01 WS_WORK_FIELD2\n"+
                        "05 WS-FUND_ID-LEN2 PIC S9(04) COMP\n"+
                        "05 WS-FUND_ID-DATA2 PIC X(04)";
    
    
    
    private static String[] readLine(){
        return data.split("\n");
    }
    
    public static String[][] read(){
        String[] s=readLine();
        String[][] strings=new String[s.length][];
        for (int i = 0; i < strings.length; i++) {
            strings[i]=s[i].trim().split("\\s+");
        }
        return strings;
    }
    
   
}
