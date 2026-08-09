class Solution {
    public String getHint(String secret, String guess) {
        int bull=0,n=secret.length();
        int cow=0;
        int[] sfq={0,0,0,0,0,0,0,0,0,0};
        int[] gfq={0,0,0,0,0,0,0,0,0,0};

        for(int i=0;i<n;i++){
            if(secret.charAt(i)==guess.charAt(i)) bull++;
            sfq[secret.charAt(i)-'0']++;
            gfq[guess.charAt(i)-'0']++;
        }

        for(int i=0;i<10;i++){
            cow+=Math.min(sfq[i],gfq[i]);
        }
        cow-=bull;
        return ""+bull+"A"+cow+"B";
    }
}
