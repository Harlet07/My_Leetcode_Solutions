class Solution {
    public int[] plusOne(int[] digits) {
        int n=digits.length;
        digits[n-1]++;
        if(digits[n-1]!=10) return digits;
        if(n>1){
            int i=n-1;
            while(digits[i]==10 && i>=1){
                digits[i]=0;
                digits[i-1]++;
                i--;
            }
        }
        if(digits[0]==10){
            int[] ans=new int[n+1];
            for(int i=n-1;i>=0;i--){
                ans[i+1]=digits[i];
            }
            ans[0]=1;
            ans[1]=0;    
            return ans;
        }
        else return digits;
    }
}
