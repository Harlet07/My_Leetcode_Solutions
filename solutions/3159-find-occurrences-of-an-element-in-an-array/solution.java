class Solution {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int n=nums.length,j=0,ql=queries.length;

        int[] ocr=new int[n],res=new int[ql];

        // for(int i=0;i<n;i++) ocr[i]=-1;

        for(int i=0;i<n;i++){
            if(nums[i]==x){
                ocr[j++]=i;
            }
        }

        for(int i=0;i<ql;i++){
            if(queries[i]>j) res[i]=-1;
            else res[i]=ocr[queries[i]-1];
        }

        return res;
    }
}
