class Solution {
    public int reverse(int x) {
        long ans=x%10;
        int q=x/10;
        int r;
        while(q!=0){
            r=q%10;
            q/=10;
            ans=ans*10 + r;
        }
        if(ans > 2147483647 || ans < -2147483648) return 0;
        return (int)ans;
    }
}
