class Solution {
    public String intToRoman(int num) {
        String[] units={"I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] tens={"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] hundreds={"C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] thou={"M","MM","MMM"};

        String s="";

        int q=num,r,pos=0;
        while(q>0){
            r=q%10;
            q/=10;
            if(r==0){
                pos++;
                continue;
            }
            if(pos==0){
                s=units[r-1]+s;
            }
            if(pos==1){
                s=tens[r-1]+s;
            }
            if(pos==2){
                s=hundreds[r-1]+s;
            }
            if(pos==3){
                s=thou[r-1]+s;
            }
            pos++;
        }
        return s;
    }
}
