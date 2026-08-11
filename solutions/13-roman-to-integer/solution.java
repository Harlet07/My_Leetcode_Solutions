class Solution {
    public int romanToInt(String s) {
        int i=s.length()-1;
        int res=0;
        while(i>=0){
            if(s.charAt(i)=='I'){
                if(i+1!=s.length() && s.charAt(i+1)=='V') res--;
                else if(i+1!=s.length() && s.charAt(i+1)=='X') res--;
                else res++;
            }
            if(s.charAt(i)=='V') res+=5;

            if(s.charAt(i)=='X'){
                if(i+1!=s.length() && s.charAt(i+1)=='L') res-=10;
                else if(i+1!=s.length() && s.charAt(i+1)=='C') res-=10;
                else res+=10;
            }
            if(s.charAt(i)=='L') res+=50;


            if(s.charAt(i)=='C'){
                if(i+1!=s.length() && s.charAt(i+1)=='D') res-=100;
                else if(i+1!=s.length() && s.charAt(i+1)=='M') res-=100;
                else res+=100;
            }
            if(s.charAt(i)=='D') res+=500;
            
            if(s.charAt(i)=='M') res+=1000;

            i--;
        }
        return res;
    }
}
