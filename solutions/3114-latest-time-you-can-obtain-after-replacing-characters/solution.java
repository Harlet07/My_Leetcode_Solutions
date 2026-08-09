class Solution {
    public String findLatestTime(String s) {
        char a=s.charAt(0),b=s.charAt(1),c=s.charAt(3),d=s.charAt(4);
        if(a=='?'){
            if(b=='?'){
                a='1';
                b='1';
            }
            else if(b>'1') a='0';
            else a='1';
        }
        else if(b=='?'){
            if(a=='1') b='1';
            else b='9';
        }

        if(c=='?') c='5';
        if(d=='?') d='9';

        String str= "" + a + b + ':' + c + d;
        return str;
    }
}
