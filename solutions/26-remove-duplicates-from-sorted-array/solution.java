// class Solution {
//     public void swaP(int a,int b){
//         a=a+b;
//         b=a-b;
//         a=a-b;
//     }

//     public int removeDuplicates(int[] nums) {
//         int n=nums.length,exLen=1,i=0,j=1;
//         if(n==1) return exLen;

//         while(j<n){
//             if(nums[i]!=nums[j]){
//                 i++;
//                 nums[i]=nums[j];
//                 j++;
//             }
//             else j++;
//         }

//         return ++i;
//     }
// }

class Solution {

    public int removeDuplicates(int[] nums) 
    {
        int write=1;
        int n=nums.length;

        for(int i=0;i<n-1;i++)
        {
            if(nums[i]==nums[i+1])
            {
                continue;
            }
            nums[write]=nums[i+1];
            write++;
        }
        return write;
    }
}
