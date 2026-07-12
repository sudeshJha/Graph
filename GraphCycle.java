import java.util.ArrayList;
import java.util.LinkedList;

class Pair{
    int child;
    int parent;

    Pair(int child, int parent){
        this.child = child;
        this.parent = parent;
    }
}

class GraphCycle{

    // ----------------------------------------------------------------
    // ------------------------------------- DIRECTED GRAPH USING DFS
    private boolean solveDirected(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] inRec, int u){
        visited[u] = true;
        inRec[u] = true;
        
        for(int v : adj.get(u)){
            if(visited[v] && inRec[v]) return true;
            
            if(!visited[v]){
                if(solveDirected(adj, visited, inRec, v)) return true;
            } 
        }
        
        inRec[u] = false;
        
        return false;
    }

    private boolean solveDirected(ArrayList<ArrayList<Integer>> adj, int[][] edges ){
        int[] inDegree = new int[adj.size()];
        for(int[] edge : edges){
            inDegree[edge[1]]++;
        }

        LinkedList<Integer> q = new LinkedList<Integer>();

        for(int i=0;i<inDegree.length;i++){
            if(inDegree[i] == 0){
                q.offer(i);
            }
        }
        int nodeCount = 0;
        while(!q.isEmpty()){
            int u = q.poll();
            nodeCount++;

            for(int v : adj.get(u)){
                inDegree[v]--;

                if(inDegree[v] == 0){
                    q.offer(v);
                }
            }
        }

        if(nodeCount == adj.size()) return false;

        return true;
    }
    
    
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

    // ----------------------------------------------------------------
    // ------------------------------------- UNDIRECTED GRAPH USING BFS
    private boolean solveUndirected(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int el){
        
        LinkedList<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(el, -1));
        
        while(!q.isEmpty()){
        
            int u = q.peek().child;
            int parent = q.poll().parent;
            visited[u] = true;
            
            for(int v : adj.get(u)){
                if(v == parent){
                    continue;
                }
                
                if(visited[v]){
                    return true;
                }
                
                q.offer(new Pair(v, u));
            }
        }
        
        return false;
        
    }

    // ----------------------------------------------------------------------
    // ------------------------------------- DIRECTED GRAPH CYCLE DETECTION
    boolean directedGraphCycleDetection(int V, int[][] edges) {
        
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
        }

        // ------------------------------
        return solveDirected(adj, edges);
        
        /*
        // creating visited array to track the vertices
        boolean[] visited = new boolean[V];
        boolean[] inRec = new boolean[V];
        
        for(int u=0;u<V;u++){
            if(!visited[u] && solveDirected(adj, visited, inRec, u)){
                return true;
            }
        }
        
        return false;
        */
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
            if(!visited[u] && solveUndirected(adj, visited, u)){
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args){
        GraphCycle gc = new GraphCycle();

        int[][] edges = {{0,1},{1,2},{2,3},{3,0}};
        int V = 4;

        // System.out.println(gc.undirectedGraphCycleDetection(V, edges));
        System.out.println(gc.directedGraphCycleDetection(V, edges));
    }
}