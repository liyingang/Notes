package com.li.home.work.tree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static String[][] str;
    
    public static void main(String[] args) {
        str=Mock.read();
        Node head=new Node();
        head.setName("head");
        find(0, str.length, head);
        head.look(0);
    }
    
    
    public static void find(int begin,int end,Node father){
        if(begin>end||begin>=str.length){
            return ;
        }else {
            List<Integer> numList=getNodeNum(begin, end);
            for (int i = 0; i < numList.size(); i++) {
                Node child=prepareNode(numList.get(i));  
                father.add(child);
                if(i==numList.size()-1){
                    find(numList.get(i)+1, end, child); 
                }else{
                    find(numList.get(i)+1, numList.get(i+1), child);                    
                }
            }
        }
    }
    
    
    public static Node prepareNode(int begin){
        
        Node node=new Node();
        node.setName(begin+" node");
        node.put(NodeContansts.LEVEL_NUMBER, Integer.parseInt(str[begin][0]));
        node.put(NodeContansts.DATA_NAME, str[begin][1]);
        int length=str[begin].length;
        int i=2;
        while(length>i){
            i=node.choose(str, i, begin);
        }
        return node;
    }
    
    
    public static List<Integer> getNodeNum(int begin,int end){
        if(begin>end){
            return null;
        }
        List<Integer> list=new ArrayList<Integer>();
        String num=str[begin][0];
        for (int i = begin; i < end; i++) {
            if(str[i][0].equals(num)){
                list.add(i);
            }
        }
        return list;
    }
}
