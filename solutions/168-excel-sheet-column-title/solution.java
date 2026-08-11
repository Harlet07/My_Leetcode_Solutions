class Solution {
    public String convertToTitle(int columnNumber) {
        int q=columnNumber,r;
        String res="";
        while(q>0){
            r=q%26;
            q/=26;
            if(r==0){
                res='Z'+res;
                q--;
            }
            else res=(char)('A'+r-1)+res;
        }
        return res;
    }
}
