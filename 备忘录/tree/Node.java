package com.li.home.work.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * AST Node
 * 
 * @author liyg
 *
 */
public class Node {
    
    /** name */
    private String name;
    
    /** properties */
    private Map<String,Object> properties;
    
    /** children */
    private List<Node> childrenList;
    
    /**
     * inital Children List
     */
    private void initChildren(){
        if(this.childrenList==null){
            this.childrenList=new ArrayList<>();
        }
    }
    /**
     * set properties into map
     * @param args1 key
     * @param args2 value
     */
    public void put(String args1,Object args2){
        if(this.properties==null){
            this.properties=new LinkedHashMap<>();
        }
        this.properties.put(args1, args2);
    }
    /**
     * get properties from map
     * @param args key
     * @return value
     */
    public Object getProPerties(String args){
        if(this.properties!=null){
            return this.properties.get(args);
        }else{
            System.err.println("propertis is null");
            return null;
        }
    }
    /**
     * add a new child node
     * @param node child node
     */
    public void add(Node node){
        initChildren();
        this.childrenList.add(node);
    }
    
    /**
     * get child by index
     * @param index [0,size())
     * @return the index`s child node
     */
    public Node getChildByIndex(Integer index){
        if(NodeContansts.ZERO>index||index>getChidrenSize()){
            System.err.println("index out of bound");
            return null;
        }
        return this.childrenList.get(index);
    }
    
    /**
     * get children size
     * @return the num of the child node
     */
    public Integer getChidrenSize(){
        initChildren();
        return this.childrenList.size();
    }
    
    
    /**
     * form the grammar switch machine
     * 
     * @param strings the file(processed)
     * @param begin the begin level
     * @param i  the i of this level
     * @return control the init of the level secentance
     */
    public int choose(String[][] strings,int i,int begin){
        switch(strings[begin][i]){
        case NodeContansts.PIC:
            this.put(NodeContansts.PICTURE,strings[begin][i+1]);
            return i+2;
        case NodeContansts.COMP:
            this.put(NodeContansts.USAGE,NodeContansts.COMP);
            return i+1;
        }
        return i+1;
    }
    
    /**
     * ergodic the AST from this node
     * @param lev control the output level
     */
    public void look(int lev){
        if(!isBlankProperties()){
            Iterator<Entry<String, Object>> entries = this.getProperties().entrySet().iterator();
            while(entries.hasNext()){
                Entry<String, Object> entry = entries.next();
                String key = entry.getKey();
                for (int j = 0; j < lev*4; j++) {
                    System.out.print("-");
                }
                System.out.println(key+":"+entry.getValue());
            }
        }
        if(isBlankChild()){
            return;
        }else{
            for (int i = 0; i < this.getChidrenSize(); i++) {
                for (int j = 0; j < lev*4; j++) {
                    System.out.print("-");
                }
                System.out.println(this.name+"`s "+"child "+(i+1)+"/"+this.getChidrenSize());
                this.getChildrenList().get(i).look(lev+1);
            }
        }
        
    }
    
    public boolean isBlankChild(){
        if(this.getChildrenList()==null||this.getChidrenSize()==0){
            return true;
        }
        return false;
    }
    
    public boolean isBlankProperties(){
        if(this.getProperties()==null||this.getProperties().size()==0){
            return true;
        }
        return false;
    }
    
    /**
     * propertiesを戻す。
     * <br>
     * @return  properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }
    /**
     * propertiesを設定する。
     * <br>
     * @param properties Map<String,Object>
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    /**
     * childrenListを戻す。
     * <br>
     * @return  childrenList
     */
    public List<Node> getChildrenList() {
        return childrenList;
    }
    /**
     * childrenListを設定する。
     * <br>
     * @param childrenList List<Node>
     */
    public void setChildrenList(List<Node> childrenList) {
        this.childrenList = childrenList;
    }

    /**
     * nameを戻す。
     * <br>
     * @return  name
     */
    public String getName() {
        return name;
    }

    /**
     * nameを設定する。
     * <br>
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}



