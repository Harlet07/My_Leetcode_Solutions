class Solution {
    public int maxArea(int[] height) {
        if(height.length<3) return Math.min(height[0],height[1]);
        int l=0,r=height.length-1;
        int maxv=0;
        while(l<r){
            int h=Math.min(height[l],height[r]);
            maxv=Math.max(maxv,h*(r-l));
            if(height[l]>height[r]) r--;
            else l++;
        }
        return maxv;

    }
}
