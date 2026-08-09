class Solution {
    public boolean search(int[] nums, int target) {
        int n=nums.length;
        if(n==1){
            if(nums[0]==target) return true;
            else return false;
        }
        if(nums[0]==target) return true;
        else if(nums[0]<target){
            int i=1;
            while(i<n){
                if(nums[i]==target) return true;
                if(nums[i]<nums[i-1]) break;
                i++;
            }
        }
        else{
            int i=n-1;
            while(i>0){
                if(nums[i]==target) return true;
                if(nums[i]<nums[i-1]) break;
                i--;
            }
        }
        return false;
    }
}
