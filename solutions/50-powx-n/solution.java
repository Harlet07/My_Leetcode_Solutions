class Solution {
    public double myPow(double x, int n) {
        if(x==0 || x==1) return x;
        if(n==0) return 1;
        if(x==-1){
            if(n%2==0) return 1;
            else return -1;
        }
        if(n<-15) return 0;

        double r;
        if(n>0){
            r=x;
            for(int i=1;i<n;i++)
                r*=x;   
        }
        
        else{
            r=1;

            for(int i=-1;i<(-(n+1));i++){
                r/=x;
            }
        }
        return r;
    }
}
