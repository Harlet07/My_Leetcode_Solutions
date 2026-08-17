class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.equals(needle)) return 0;
        int i=0;
        int hl=haystack.length(),nl=needle.length();
        while(i<hl-nl+1){
            if(haystack.charAt(i)==needle.charAt(0))
                if(needle.equals(haystack.substring(i,i+nl))) return i;
            i++;
        }
        return -1;
    }
}
