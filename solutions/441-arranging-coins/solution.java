class Solution {
    public int arrangeCoins(int n){int r=0;while(n>0)n-=++r;if(n<0)return r-1;return r;}
}
