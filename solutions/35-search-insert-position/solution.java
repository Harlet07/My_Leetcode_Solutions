class Solution {
    public int searchInsert(int[] arr, int t) {
        int l=0,r=arr.length-1,m=(r)/2;
        // int i=m;
        while(l<=r){
            if(arr[m]==t) return m;
            if(t<arr[m]) r=m-1;
            else l=m+1;
            m=(l+r)/2;
        }
        return l;
    }
}
