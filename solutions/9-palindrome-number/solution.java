class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        String xs=x+"";
        
        int l=0,r=xs.length()-1;
        while(l<r){
            if(xs.charAt(r)!=xs.charAt(l)) return false;
            l++;r--;
        }
        return true;

    }
}
