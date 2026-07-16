import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

class DijkstraAlgorithm{

      static int[] algo_1(int V, int[][] edges, int src){

            ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
            int[] ans = new int[V];
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                  (a,b) -> Integer.compare(a[1], b[1])
            );
            // pq[0] = node
            // pq[1] = distance;

            for(int i=0;i<V;i++){
                  adj.add(new ArrayList<int[]>());
                  ans[i] = Integer.MAX_VALUE;
            }

            for(int[] edge : edges){
                  int u = edge[0];
                  int v = edge[1];
                  int d = edge[2];

                  adj.get(u).add(new int[]{v, d});
                  adj.get(v).add(new int[]{u, d});
            }

            pq.offer(new int[]{src, 0});
            ans[src] = 0;

            while(!pq.isEmpty()){
                  int[] pair = pq.poll();
                  int u = pair[0];
                  int d = pair[1];

                  for(int[] p : adj.get(u)){
                        int v = p[0];
                        int wt = p[1];

                        if(d+wt < ans[v]){
                              ans[v] = d+wt;
                              pq.offer(new int[]{v, d+wt});
                        }
                  }
            }

            return ans;
      }

      static int[] algo_2(int V, int[][] edges, int src){
            ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
            TreeSet<int[]> set = new TreeSet<int[]>(
                  (a,b)->a[1] != b[1] ?Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0])
            );
            int[] ans = new int[V];

            for(int i=0;i<V;i++){
                  ans[i] = Integer.MAX_VALUE;
                  adj.add(new ArrayList<int[]>());
            }

            for(int[] edge : edges){
                  int u = edge[0];
                  int v = edge[1];
                  int d = edge[2];

                  adj.get(u).add(new int[]{v, d});
                  adj.get(v).add(new int[]{v,d});
            }

            set.add(new int[]{src, 0});
            ans[src] = 0;

            while(!set.isEmpty()){
                  int[] pair = set.pollFirst();
                  int u = pair[0];
                  int d = pair[1];

                  for(int[] p : adj.get(u)){
                        int v = p[0];
                        int wt = p[1];

                        if(d+wt < ans[v]){
                              ans[v] = d+wt;
                              set.add(new int[]{v, d+wt});
                        }
                  }
            }

            return ans;
      }

      public static void main(String[] args){
            // u - v - distance
            int[][] edges =  new int[][]{{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, 10}};
            int V = 5;
            int src = 0;

            // using priority queue
            int[] arr = algo_1(V, edges, src);
            for(int i : arr){
                  System.out.print(i + " ");
            }
            System.out.println();

            // using set
            arr = algo_2(V, edges, src);
            for(int i : arr){
                  System.out.print(i + " ");
            }

      }
}