import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

class FindShortestPath{

      static ArrayList<Integer> findShortestPath(int V, int[][] edges, int src, int dest){
            ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                  (a,b)->Integer.compare(a[1],b[1])
            );
            int[] parent = new int[V];
            int[] dist = new int[V];
            ArrayList<Integer> path = new ArrayList<>();

            // Initialising the arrays
            for(int i=0;i<V;i++){
                  adj.add(new ArrayList<int[]>());
                  parent[i] = i;
                  dist[i] = Integer.MAX_VALUE;
            }

            // creating adjacency list
            for(int[] edge : edges){
                  int u = edge[0];
                  int v = edge[1];
                  int d = edge[2];

                  adj.get(u).add(new int[]{v, d});
                  adj.get(v).add(new int[]{u, d});
            }

            // insert source node in priority queue
            // {node, distance_from_source}
            pq.offer(new int[]{src, 0});
            dist[src] = 0;

            while(!pq.isEmpty()){
                  int[] pair = pq.poll();
                  int u = pair[0];
                  int d = pair[1];

                  for(int[] p : adj.get(u)){
                        int v = p[0];
                        int wt = p[1];

                        // if found new small distance
                        // update -> distance, pq, parents
                        if(d+wt < dist[v]){
                              dist[v] = d+wt;
                              parent[v] = u;
                              pq.offer(new int[]{v, d+wt});
                        }
                  }
            }

            while(dest != parent[dest]){
                  path.add(dest);
                  dest = parent[dest];
            }
            path.add(dest);
            Collections.reverse(path);
            
            return path;
      }

      public static void main(String[] args){
            int[][] edges =  new int[][]{{0,1,6},{1,2,3},{0,3,14},{1,3,7},{2,3,5}};
            int V = 4;
            int src = 0;
            int dest = 3;

            ArrayList<Integer> path = findShortestPath(V, edges, src, dest);

            for(int p : path){
                  System.out.print(p + " ");
            }
      }
}