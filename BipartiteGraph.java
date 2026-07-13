import java.util.ArrayList;
import java.util.LinkedList;

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

      static boolean BFS(ArrayList<ArrayList<Integer>> adj, int[] color, int el, int currentColor){
        // -1 = not visited 
        // 0 = white color (visited)
        // 1 = black color (visited)

        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(el);
        color[el] = currentColor;

        while(!q.isEmpty()){
            int u = q.poll();

            for(int v : adj.get(u)){
                // adjacent node have same color
                if(color[u] == color[v]) return false;

                // already visited
                if(color[v] != -1) continue;

                q.offer(v);
                color[v] = 1 - color[u];
            }
        }

        return true;        
    }
      
      static boolean isBipartite(int V, int[][] edges) {
            
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
                  if(color[u] == -1 && !BFS(adj,color,u,0)){
                        return false;
                  }
            }
            
            return true;
      }

      public static void main(String[] args){
            int V = 4;
            int[][] edges = {{0, 3}, {1, 2}, {3, 2}, {0, 2}};
            // int[][] edges = {{0,1},{0,2}};

            System.out.println(isBipartite(V, edges));
      }
}