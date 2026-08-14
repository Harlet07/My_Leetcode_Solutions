class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans=numBottles,emp=numBottles;
        while(emp>=numExchange){
            int a=emp/numExchange;
            ans+=a;
            emp-=a*numExchange-a;
        }
        return ans;
    }
}
