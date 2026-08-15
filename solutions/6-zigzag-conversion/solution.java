class Solution {
    public String convert(String s, int n) {
        if(n==1) return s;
        String res="";
        for(int i=0;i<n;i++){
            int j=i,a=2+i*2;
            int b=2*n-a+2;
            boolean se=false;
            while(j<s.length()){
                res+=s.charAt(j);
                if(i==0 || i==n-1) j+=2*n-2;
                else{
                    j+=2*n-((se)?b:a);
                    se=!se;
                }
            }
        }
        return res;
    }
}

// class Solution {
// public String convert(String s, int n) {
//         if(s.length()<2 || n<2) return s;
//         String res="";
//         int si=s.length();
//         if(n==2){
//             for(int i=0;i<si;i+=2){
//                 res+=s.charAt(i);
//             }
//             for(int i=1;i<si;i+=2){
//                 res+=s.charAt(i);
//             }
//             return res;
//         }
//         int a=(n-1)*2;
//         for(int i=0;i<n;i++){
//             int j=i;
//             int c=0;
//             if(i==0 || i==n-1){
//                 while(j<si){
//                     res+=s.charAt(j);
//                     j+=a;
//                 }
//             }
//             else{
//                 while(j<si){
//                     res+=s.charAt(j);
//                     if(c%2==0) j+=(a-i*2);
//                     else j+=(i*2);
//                     c++;
//                 }
//             }
//         }
//         return res;
//     }
// };
