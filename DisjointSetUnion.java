class DisjointSetUnion{

      static int find(int i, int[] parents){
            if(i == parents[i]){
                  return i;
            }

            return find(parents[i], parents);
      }

      static int findByPathCompression(int i, int[] parents){
            if(i == parents[i]){
                  return i;
            }

            return parents[i] = findByPathCompression(parents[i], parents);
      }

      static void union(int a, int b, int[] parents){

            int parent_a = find(a, parents);
            int parent_b = find(b, parents);

            if(parent_a != parent_b){
                  parents[parent_a] = parent_b;
            }
      }

      static void unionByRank(int a, int b, int[] parents, int[] rank){
            int parent_a = findByPathCompression(a, parents);
            int parent_b = findByPathCompression(b, parents);

            // both are in same set no need for union
            if(parent_a == parent_b){
                  return;
            }
            else{
                  if(rank[parent_a] == rank[parent_b]){
                        rank[parent_a]++;
                        parent[parent_b] = parent_a;

                  }
                  else if(rank[parent_a] > rank[parent_b]){
                        parents[parent_b] = parent_a;
                  }
                  else{
                        parents[parent_a] = parent_b;
                  }
            }

            return;
      }

      
}