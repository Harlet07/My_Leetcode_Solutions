class Solution {
    public static int sumOfDigitSqr(int n){
        int q=n,r,sum=0;
        while(q>0){
            r=q%10;
            q/=10;
            sum+=r*r;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        // if(n==1 || n==10 || n==13 || n==19 || n==23 || n==28 || n==31 || n==32 || n==44 || n==49) return true;
        // int s=sumOfDigitSqr(n);

        // while(true){
        //     if(s<51 && !(s==1 || s==10 || s==13 || s==19 || s==23 || s==28 || s==31 || s==32 || s==44 || s==49)) return false;
        //     else if(s<51) return true;
        //     s=sumOfDigitSqr(s);
        // }
        // return false;

        int[] h=new int[1000];
        int s=n;
        while(true){
            s=sumOfDigitSqr(s);
            if(s==1) return true;
            h[s]++;
            if(h[s]>1) return false;
        }
    }
}
