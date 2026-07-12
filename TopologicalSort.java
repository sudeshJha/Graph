import java.util.ArrayList;
import java.util.LinkedList;

class TopologicalSort{

      static void topologicalSortDFS(ArrayList<ArrayList<Integer>> adj,boolean[] visited,LinkedList<Integer> st,int u){
            
            visited[u] = true;
            
            for(int v : adj.get(u)){
                  if(visited[v]) continue;
                  
                  topologicalSortDFS(adj, visited, st, v);
            }
            
            st.push(u);
            
            return;
      }
    
      public static void main(String[] args) {
            
            int[][] edges = {{1, 3}, {2, 3}, {4, 1}, {4, 0}, {5, 0}, {5, 2}};
            int V = 6;

            ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
            
            for(int i=0;i<V;i++){
                  adj.add(new ArrayList<Integer>());
            }
            
            for(int[] edge : edges){
                  int u = edge[0];
                  int v = edge[1];
                  
                  adj.get(u).add(v);
            }
            
            LinkedList<Integer> st = new LinkedList<Integer>();
            boolean[] visited = new boolean[V];
            
            for(int u=0;u<V;u++){
                  if(!visited[u]){
                        topologicalSortDFS(adj, visited, st, u);
                  }
            }
            
            while(!st.isEmpty()){
                  System.out.print(st.pop() + " ");
            }
      }
}