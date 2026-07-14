class DisjointSetUnion{

      static int find(int i, int[] parents){
            if(i == parents[i]){
                  return i;
            }

            return find(parents[i], parents);
      }

      static void union(int a, int b, int[] parents){

            int parent_a = find(a, parents);
            int parent_b = find(b, parents);

            if(parent_a != parent_b){
                  parents[parent_a] = parent_b;
            }
      }
}