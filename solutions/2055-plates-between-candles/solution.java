class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n=s.length(),last=-1;
        int[] pref=new int[n];
        int[][] nearest=new int[n][2];


        int plates=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='*') plates++;
            pref[i]=plates;
        }

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='|') last=i;
            nearest[i][0]=last;
        }

        last=n;
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i)=='|') last=i;
            nearest[i][1]=last;
        }

        int[] ans=new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int L=queries[i][0];
            int R=queries[i][1];

            int first=nearest[L][1];
            int lastl=nearest[R][0];

            if (first != n && lastl != -1 && first < lastl) {
                ans[i]=pref[lastl] - pref[first];
            } else {
                ans[i]=0;
            }
        }

        return ans;
    }
}
