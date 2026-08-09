class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=m,j=0;i<m+n;i++) nums1[i]=nums2[j++];
        for(int i=0;i<m+n;i++){
            int j=i-1;
            int k=nums1[i];
            while(j>=0 && nums1[j]>k){
                nums1[j+1]=nums1[j--];
            }
            nums1[j+1]=k;
        }
    }
}
