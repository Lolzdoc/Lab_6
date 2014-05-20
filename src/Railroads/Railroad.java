package Railroads;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Railroad {
    private final int source = 0;
    private final int target = 54;
    private Integer[][] capacityMap;
    private Integer[][] flowMap;
    private Integer[][] residualNetworkMap;
    private String[] nodes;


    public static void main(String[] args){
        ArrayList<Integer> found = new ArrayList<Integer>();
        Railroad road = new Railroad();
        Parser pars = new Parser();
        road.nodes = pars.getNodeArray();
        road.capacityMap = pars.getRailMap();
        road.flowMap = pars.getCurrentFlow();
        road.residualNetworkMap = pars.getResidualNetworkMap();
        road.search();

    }


    private void debug(Object s){
        System.out.println("Debug : "+s);
    }

    private void search(){
        HashMap<Integer,Object> found = new HashMap<Integer, Object>();
        HashMap<Integer,Integer> paths = new HashMap<Integer, Integer>();
        Integer[][] neighbour = new Integer[55][55];
        for(int i =0 ; i<55 ; i++){
            ArrayList a = getAdjacent(i);
            neighbour[i]=(Integer[])a.toArray();
            Iterator itr = a.iterator();
            while (itr.hasNext()){

            }
        }















        /*
        Stack<Integer> que = new Stack<Integer>();
        int index =0;
        que.add(que.size(),0);
        paths.put(0,index);
        found.put(0,"");
        index++;
        while(!que.isEmpty()){

            int t = que.pop();
                if(t == target){
                break;
                }
            ArrayList<Integer> adjacent = getAdjacent(t);
            for(int i : adjacent){
                if(!found.containsKey(i)){
                    found.put(i,"");
                    paths.put(i,paths.get(t));
                    que.add(que.size(),i);
                }
            }


        }
        */

    }
    private ArrayList<Integer> getAdjacent(int n){
        ArrayList<Integer> adjacent = new ArrayList<Integer>();
        for(int i = 0;i<55;i++){
            if(capacityMap[n][i]!=0 && i!=n){
                adjacent.add(capacityMap[n][i]);
                debug(capacityMap[n][i]);
            }

        }
        return adjacent;
    }

}
