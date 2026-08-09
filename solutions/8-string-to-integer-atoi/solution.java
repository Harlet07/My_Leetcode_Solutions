class Solution {
    public int myAtoi(String s) {
        int j=0;
        while(j<s.length() && s.charAt(j)==' ') j++;

        s=s.substring(j,s.length());

        if(s.length()==0 || (s.charAt(0)!='+' && s.charAt(0)!='-' && (s.charAt(0)<'0' || s.charAt(0)>'9'))) return 0;

        long ans=0;
        boolean negative=false;
        int i=0;


        if(s.charAt(0)=='-'){
            negative=true;
            i++;
        }
        else if(s.charAt(0)=='+') i++;

        while(i<s.length()){
            if(ans>2147483648L){
                ans=2147483648L;
                break;
            }
            if(s.charAt(i)<'0' || s.charAt(i)>'9') break;
            ans*=10;
            ans+=s.charAt(i)-'0';
            i++;
        }
        ans*=(negative)? -1 : 1;
        if(ans<-2147483648) ans=-2147483648;
        if(ans>2147483647) ans=2147483647;
        return (int)ans;
    }
}
