class Solution {
    public static int[] mergeSort(int[] a,int[] b){
        int al=a.length,bl=b.length;
        int n=al+bl;
        int[] arr=new int[n];
        int i=0,j=0,k=0;
        while(i<al && j<bl){
            if(a[i]>b[j])
                arr[k]=b[j++];
            else arr[k]=a[i++];
            System.out.println(arr[k++]);
        }

        while(i<al){
            arr[k]=a[i++];
            System.out.println(arr[k++]);
        }

        while(j<bl){
            arr[k]=b[j++];
            System.out.println(arr[k++]);
        }

        return arr;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double med=0;
        
        int[] arr=mergeSort(nums1,nums2);
        int n=arr.length;
        if(n%2==0) med=(arr[n/2]+arr[n/2-1])/2.0;

        else med=arr[n/2];

        return med;
    }
}
