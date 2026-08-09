class Solution {
    // public static int[] freq(String s){
    //     int[] fre=/**/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    //     for(int i=0;i<s.length();i++){
    //         fre[s.charAt(i)-'a']++;
    //     }
    //     return fre;
    // }
    public int maxNumberOfBalloons(String text) {
        int ans=0;
        int[] fre=new int[26];

        for(int i=0;i<text.length();i++){
            fre[text.charAt(i)-'a']++;
        }

        // b -> 1
        // a -> 0
        // l -> 10
        // o -> 13
        // n -> 12

        while(fre[0]>0 && fre[1]>0 && fre[11]>0 && fre[14]>0 && fre[13]>0){
            fre[0]--;
            fre[1]--;
            fre[11]-=2;
            fre[14]-=2;
            fre[13]--;
            ans++;
        }
        if(fre[11]<0 || fre[14]<0) ans--;
        return ans;
    }
}
