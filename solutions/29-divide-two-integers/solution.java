class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor==1) return dividend;
        if(divisor==-1){
            if(dividend==-2147483648) return 2147483647;
            return -dividend;
        }

        boolean pos=true;
        if((dividend<0 && divisor>0) || (dividend>0 && divisor<0)) pos=false;
        long q=Math.abs((long)dividend),d=Math.abs((long)divisor);
        
        int res=0;
        while(q>=d){
            q-=d;
            res++;
        }
        return (pos)? res:-res;
    }
}
