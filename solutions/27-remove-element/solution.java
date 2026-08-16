class Solution {
    public int removeElement(int[] nums, int val) {
        int n=nums.length;
        if(val>50 || n==0) return n;
        if(n==1 && nums[0]==val) return 0;
        int i=0,end=n;
        while(i<end){
            if(nums[i]==val){
                nums[i]+=nums[end-1];
                nums[end-1]=nums[i]-nums[end-1];
                nums[i]-=nums[end-1];
                end--;
            }
            else i++;
        }
        return end;
    }
}
