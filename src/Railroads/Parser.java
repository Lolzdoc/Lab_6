package Railroads;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Parser {
    private int numberOfNods;
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
                        numberOfNods = Integer.parseInt(line.trim());
                        Debug(" NumberOfNods: " + numberOfNods);
                        nodes = new String[numberOfNods];
                        capacityMap = new Integer[numberOfNods][numberOfNods];
                        flowMap = new Integer[numberOfNods][numberOfNods];
                        residualNetworkMap = new Integer[numberOfNods][numberOfNods];
                        for(int i = 0; i<numberOfNods;i++){
                            for(int j =0 ; j< numberOfNods;j++){
                                capacityMap[i][j]=0;
                                flowMap[i][j]=0;
                                residualNetworkMap[i][j]=0;

                           }
                        }

                    } else if (index <= numberOfNods) {
                        nodes[index - 1] = line.trim();
                        Debug("index : " + (index - 1) + " : " + line);
                    } else if (index == numberOfNods+1 ) {
                        NumberOfArcs = Integer.parseInt(line.trim());
                        Debug("NumberOfArcs:" + NumberOfArcs);

                    } else if (NumberOfArcs != -1) {
                        String[] segments = line.split(" ");
                        int node_1 = Integer.parseInt(segments[0].trim());
                        int node_2 = Integer.parseInt(segments[1].trim());
                        capacityMap[node_1][node_2] = Integer.parseInt(segments[2].trim());
                        Debug(node_1+" "+node_2+" "+capacityMap[node_1][node_2]);
                        flowMap[node_1][node_2] = 0;
                        residualNetworkMap[node_1][node_2] =Integer.parseInt(segments[2].trim());


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


    private void Debug(Object s){
        System.out.println("Debug : "+s);
    }
}














