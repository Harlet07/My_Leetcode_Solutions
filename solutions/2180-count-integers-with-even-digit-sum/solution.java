class Solution {
    public static int digitSum(int n){
        int q=n,r;
        int s=0;
        while(q>0){
            r=q%10;
            q/=10;
            s+=r;
        }
        return s;
    }

    public int countEven(int num) {
        if(num<10) return num/2;
        int se=0;
        for(int i=num;i>0;i--){
            if(i<10){
                se+=i/2;
                break;
            }
            if(digitSum(i)%2==0) se++;
        }
        return se;
    }
}
