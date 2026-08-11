class Solution {
    public int titleToNumber(String columnTitle) {
        int res=0,n=columnTitle.length();
        int i=n-1;
        int[] po26={1,26,676,17576,456976,11881376,308915776};
        while(i>=0){
            res+=(columnTitle.charAt(i)-'A'+1)*po26[n-i-1];
            i--;
        }
        return res;
    }
}
