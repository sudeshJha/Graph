import java.util.ArrayList;

class BipartiteGraph {
      
      static boolean DFS(ArrayList<ArrayList<Integer>> adj, int[] color, int u, int currentColor){
            
            color[u] = currentColor;
            
            for(int v : adj.get(u)){
                  if(color[u] == color[v]) return false;
                  
                  if(color[v] == -1 && !DFS(adj, color, v, 1-currentColor)){
                  return false;
                  }
            }
            
            return true;
      }
      
      static boolean isBipartiteDFS(int V, int[][] edges) {
            
            ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
            
            for(int i=0;i<V;i++){
                  adj.add(new ArrayList<Integer>());
            }
            
            for(int[] edge : edges){
                  int u = edge[0];
                  int v = edge[1];
                  
                  adj.get(u).add(v);
                  adj.get(v).add(u);
            }
            
            int[] color = new int[V];
            
            for(int u=0;u<V;u++){
                  color[u] = -1;
            }
            
            for(int u=0;u<V;u++){
                  if(color[u] == -1 && !DFS(adj,color,u,0)){
                  return false;
                  }
            }
            
            return true;
      }

      public static void main(String[] args){
            int V = 4;
            int[][] edges = {{0, 3}, {1, 2}, {3, 2}, {0, 2}};
            // int[][] edges = {{0,1},{0,2}};

            System.out.println(isBipartiteDFS(V, edges));
      }
}