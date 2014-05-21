package Railroads;


import java.util.*;

public class Railroad {
    private final int source = 0;
    private final int target = 54;
    private Integer[][] capacityMap;
    private Integer[][] flowMap;
    private Integer[][] residualNetworkMap;
    private Integer capacity;
    private String[] nodes;

    public static void main(String[] args) {
        ArrayList<Integer> found = new ArrayList<Integer>();
        Railroad road = new Railroad();
        Parser pars = new Parser();
        road.nodes = pars.getNodeArray();
        road.capacityMap = pars.getRailMap();
        road.flowMap = pars.getCurrentFlow();
        road.residualNetworkMap = pars.getResidualNetworkMap();
        road.search();

    }

    private void debug(Object s) {
        System.out.println("Debug : " + s);
    }

    private void search() {
        int maxFlow = 0;
        HashMap<Integer, Integer> path;
        capacity = 0;
        while (true) {
            path = bfs();
            if (capacity == null) {
                break;
            }
            maxFlow += capacity;
            int v = target;
            while (v != source) {
                int u = path.get(v);
                flowMap[u][v] += capacity;
                flowMap[v][u] -= capacity;
                residualNetworkMap[u][v] = capacityMap[u][v] - flowMap[u][v];
                residualNetworkMap[v][u] = capacityMap[v][u] - flowMap[v][u];
                debug("resid: " + residualNetworkMap[u][v] + " " + residualNetworkMap[v][u]);
                v = u;
            }
        }
        debug("MaxFlow: " + maxFlow);
    }

    private HashMap<Integer, Integer> bfs() {
        Integer[] pathCapacity;
        HashMap<Integer, Object> found = new HashMap<Integer, Object>();
        HashMap<Integer, Integer> paths = new HashMap<Integer, Integer>();
        Stack<Integer> que = new Stack<Integer>();
        pathCapacity = new Integer[55];
        Iterator<Integer>[] neighbourhood = new Iterator[55];
        for (int i = 0; i < 55; i++) {
            ArrayList a = getAdjacent(i);
            neighbourhood[i] = a.iterator();
        }
        pathCapacity[source] = -1;
        found.put(source, "");
        que.add(source);
        paths.put(source, Integer.MAX_VALUE);


        while (!que.isEmpty()) {
            int node = que.pop();
            while (neighbourhood[node].hasNext()) {
                int nextTarget = neighbourhood[node].next();
                //debug("Next Target:" +nextTarget);
                if (residualNetworkMap[node][nextTarget] > 0 && !found.containsKey(nextTarget)) {
                    paths.put(nextTarget, node);
                    found.put(nextTarget, "");
                    if (capacityMap[node][nextTarget] != -1) {
                        pathCapacity[nextTarget] = Math.min(pathCapacity[node], residualNetworkMap[node][nextTarget]);
                    } else {
                        pathCapacity[nextTarget] = pathCapacity[node];
                    }
                    capacity = pathCapacity[nextTarget];
                    debug(capacity);
                    if (nextTarget != target) {
                        que.add(que.size(), nextTarget);
                    } else {
                        return paths;

                    }
                }
            }
        }
        capacity = null;
        return paths;
    }


    private ArrayList<Integer> getAdjacent(int n) {
        ArrayList<Integer> adjacent = new ArrayList<Integer>();
        for (int i = 0; i < 55; i++) {
            if (capacityMap[n][i] != -2 && i != n) {
                adjacent.add(i);
                //+ debug(i);
            }

        }
        return adjacent;
    }

}
