class Solution {

    public static void insort(int[] a){
        for(int i=1;i<a.length;i++){
            int j=i-1,k=a[i];
            while(j>=0 && a[j]>k) a[j+1]=a[j--];
            a[j+1]=k;
        }
    }

    public int arrayPairSum(int[] nums) {
        int n=nums.length;
        insort(nums);
        int res=0;
        for(int i=0;i<n;i+=2) res+=nums[i];
        return res;
    }
}
