import java.util.ArrayList;

class GraphCycle{
    // ----------------------------------------------------------------
    // ------------------------------------- UNDIRECTED GRAPH USING DFS
    private boolean solveUndirected(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int u, int parent){
        
        visited[u] = true;
        
        for(int v : adj.get(u)){
            
            // if v is not parent and u is already visited then cycle detected 
            if(v != parent && visited[v]){
                return true;
            }
            
            // if v is either parent or visited then continue
            if(v == parent || visited[v]){
                continue;
            }
            
            if(solveUndirected(adj, visited, v, u)) return true;
        }
        
        return false;
    }
    
    // ----------------------------------------------------------------------
    // ------------------------------------- UNDIRECTED GRAPH CYCLE DETECTION
    boolean undirectedGraphCycleDetection(int V, int[][] edges) {
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        
        // initialise the array in the 2-d array
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<Integer>());
        }
        
        // creating a graph matrix from edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // creating visited array to track the vertices
        boolean[] visited = new boolean[V];
        
        for(int u=0;u<V;u++){
            if(!visited[u] && solveUndirected(adj, visited, u, -1)){
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args){
        GraphCycle gc = new GraphCycle();

        int[][] edges = {{1,2},{2,3},{1,3}};
        int V = 4;

        System.out.println(gc.undirectedGraphCycleDetection(V, edges));
    }
}