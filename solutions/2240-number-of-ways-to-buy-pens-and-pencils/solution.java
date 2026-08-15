class Solution {
    public long waysToBuyPensPencils(int total, int pen, int cil) {
        // int money;
        if(pen>total && cil>total) return 1;
        // if((pen>total && cil==total)) return 2;
        // if(pen==total && cil==total) return 4;
        long res;
        if(pen>total && cil<total) res=total/cil+1;
        else res=total/pen+1;
        for(int i=0;i<total/pen;i++){
            res+=(total-pen*i)/cil;
        }
        if(total>pen) res+=(total%pen)/cil;
        return res;
    }
}
