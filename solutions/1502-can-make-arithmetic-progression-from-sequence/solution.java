class Solution {
    public static void insort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int j=i-1,k=arr[i];
            while(j>=0 && k<arr[j]) arr[j+1]=arr[j--];
            arr[j+1]=k;
        }
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        insort(arr);

        int n=arr.length,d=arr[1]-arr[0];
        
        for(int i=2;i<n;i++){
            if(arr[i]-arr[i-1]!=d) return false;
        }

        return true;
    }
}
