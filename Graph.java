import java.util.LinkedList;

class Graph{

      static void DFS(int[][] adj, int u, boolean[] visited){
            if(visited[u]){
                  return;
            }

            System.out.println(u);
            visited[u] = true;

            for(int v : adj[u]){
                  if(visited[v]) continue;
                  DFS(adj, v, visited);
            }

            return;
      }

      static void BFS(int[][] adj, boolean[] visited){
            LinkedList<Integer> q = new LinkedList<Integer>();

            q.offer(0);

            while(!q.isEmpty()){
                  int u = q.poll();

                  if(visited[u]) continue;

                  System.out.println(u);
                  visited[u] = true;

                  for(int v : adj[u]){
                        if(visited[v]) continue;
                        q.offer(v);
                  }
            }

            return;
      }

      public static void main(String[] args){
            int[][] adj = {{3},{3},{1},{2,4},{}};
            boolean[] visited = new boolean[adj.length];

            // DFS(adj, 0,visited);
            BFS(adj, visited);
      }
}