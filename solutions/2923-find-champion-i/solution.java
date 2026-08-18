class Solution {
    public int findChampion(int[][] grid) {
        int n=grid.length;
        int a;
        for(int i=0;i<n;i++){
            a=0;
            for(int j=0;j<n;j++) a+=grid[i][j];
            if(a==n-1) return i;
        }
        return 0;
    }
}
