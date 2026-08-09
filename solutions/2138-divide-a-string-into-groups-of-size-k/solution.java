class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n=s.length(),arl,j=0;
        String a="";
        if(n%k==0) arl=n/k;
        else arl=n/k+1;
        String[] res=new String[arl];
        for(int i=0;i<arl*k;i++){
            if(i<n) a+=s.charAt(i);
            else a+=fill;
            if(a.length()==k){
                res[j++]=a;
                a="";
            }
        }
        return res;
    }
}
