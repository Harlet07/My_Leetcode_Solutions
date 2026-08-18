class Solution {
    public static int digitSPS(int n){
        int s=0,p=1;
        int q=n,r;
        while(q>0){
            r=q%10;
            q/=10;
            s+=r;
            p*=r;
        }
        return s+p;
    }

    public boolean checkDivisibility(int n) {
        if(n%digitSPS(n)==0) return true;
        return false;
    }
}
