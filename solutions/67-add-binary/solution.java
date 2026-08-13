class Solution {
    public String addBinary(String a, String b) {
        if(a=="0")
            return b;

        if(b=="0")
            return a;
        String res="";
        int al=a.length(),bl=b.length();
        boolean carry=false;

        int i=al-1,j=bl-1;
        while(i>=0 && j>=0){
            if(a.charAt(i)=='0')
                if(b.charAt(j)=='0')
                    if(carry){
                        res='1'+res;
                        carry=false;
                    }
                    else res='0'+res;
                
                else{
                    if(carry) res='0'+res;
                    else res='1'+res;
                }
            
            else{
                if(b.charAt(j)=='1')
                    if(carry) res='1'+res;
                    else{
                        res='0'+res;
                        carry=true;
                    }
                
                else
                    if(carry) res='0'+res;
                    else res='1'+res;
            }
            i--;
            j--;
        }
        while(i>=0){
            if(carry)
                if(a.charAt(i)=='0'){
                    res='1'+res;
                    carry=false;
                }
                else res='0'+res;
            else{
                if(a.charAt(i)=='0') res='0'+res;
                else res='1'+res;
            }
            i--;
        }

        while(j>=0){
            if(carry)
                if(b.charAt(j)=='0'){
                    res='1'+res;
                    carry=false;
                }
                else res='0'+res;
            else{
                if(b.charAt(j)=='0') res='0'+res;
                else res='1'+res;
            }
            j--;
        }
        if(carry) return '1'+res;
        return res;
    }
}
