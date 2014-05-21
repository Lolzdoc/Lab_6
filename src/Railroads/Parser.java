package Railroads;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Parser {
    private int numberOfNodes;
    private String[] nodes;
    private int NumberOfArcs = -1;
    private final String fileName = "rail.txt";
    Integer[][] capacityMap;
    Integer[][] flowMap;
    Integer[][] residualNetworkMap;




    public Parser() {
        String line = "";
        int index = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/inputFiles/" + fileName)));
            line = reader.readLine();

            while (line != null) {
                    if (index == 0) {
                        numberOfNodes = Integer.parseInt(line.trim());
                        debug(" NumberOfNods: " + numberOfNodes);
                        nodes = new String[numberOfNodes];
                        capacityMap = new Integer[numberOfNodes][numberOfNodes];
                        flowMap = new Integer[numberOfNodes][numberOfNodes];
                        residualNetworkMap = new Integer[numberOfNodes][numberOfNodes];

                        for(int i = 0; i< numberOfNodes;i++){
                            for(int j =0 ; j< numberOfNodes;j++){
                                capacityMap[i][j]=-2;
                                flowMap[i][j]=0;
                                residualNetworkMap[i][j]=0;

                           }
                        }
                    } else if (index <= numberOfNodes) {
                        nodes[index - 1] = line.trim();
                        debug("index : " + (index - 1) + " : " + line);
                    } else if (index == numberOfNodes +1 ) {
                        NumberOfArcs = Integer.parseInt(line.trim());
                        debug("NumberOfArcs:" + NumberOfArcs);

                    } else if (NumberOfArcs != -1) {
                        String[] segments = line.split(" ");
                        int node_1 = Integer.parseInt(segments[0].trim());
                        int node_2 = Integer.parseInt(segments[1].trim());
                        int cap = Integer.parseInt(segments[2].trim());
                        if(cap == -1){
                            cap = Integer.MAX_VALUE;
                        }
                        capacityMap[node_1][node_2] = cap;
                        capacityMap[node_2][node_1] = cap;
                        debug(node_1 + " " + node_2 + " " + capacityMap[node_1][node_2]);
                        flowMap[node_1][node_2] = 0;
                        flowMap[node_2][node_1] = 0;
                        residualNetworkMap[node_1][node_2] =cap;
                        residualNetworkMap[node_2][node_1] =cap;


                    }
                    index++;

                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public Integer[][] getCurrentFlow(){
        return flowMap;
    }

    public String[] getNodeArray() {
        return nodes;
    }

    public Integer[][] getRailMap() {
        return capacityMap;
    }
    public Integer[][] getResidualNetworkMap(){
        return residualNetworkMap;
    }


    private void debug(Object s){
       // System.out.println("Debug : "+s);
    }
}














