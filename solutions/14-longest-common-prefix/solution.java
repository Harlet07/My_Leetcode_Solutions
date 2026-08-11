class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==1) return strs[0];
        String pr="";
        for(int i=0;i<strs[0].length();i++){
            int j=1;
            while(j<strs.length){
                if(strs[j].length()<=i || strs[0].charAt(i)!=strs[j].charAt(i))
                    return pr;
                j++;
            }
            pr+=strs[0].charAt(i);
        }
        return pr;
    }
}
