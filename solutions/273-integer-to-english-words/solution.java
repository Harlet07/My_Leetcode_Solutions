class Solution {
    public String numberToWords(int num) {
        if(num==0)
            return "Zero";
        String[] units={
            "One","Two","Three","Four",
            "Five","Six","Seven","Eight","Nine"
            };
        String[] twenties={
            "Eleven","Twelve","Thirteen","Fourteen",
            "Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"
            };
        String[] tens={
            "Ten","Twenty","Thirty","Forty",
            "Fifty","Sixty","Seventy","Eighty","Ninety"
            };

        String s="";
        int q=num,r=10,pos=0,pr;
        boolean nr=false,mil=false,thou=false,bil=false;
        while(q!=0){
            pr=r;
            r=q%10;
            q/=10;
            if(q%10==1) nr=true;
            else nr=false;
            if(r!=0){
                if(r!=0 && pos>=3 && pos<6 && !thou){
                    s="Thousand "+s;
                    thou=true;
                }
                if(r!=0 && pos>=6 && pos<9 && !mil){
                    s="Million "+s;
                    mil=true;
                }
                if(r!=0 && (pos-2)%3==0) s="Hundred "+s;
                if(r!=0 && pos>=9 && !bil){
                    s="Billion "+s;
                    mil=true;
                }
                
                if(pos%3==0){
                    if(r==0){
                        pos++;
                        continue;
                    }
                    if(nr) s=twenties[r-1]+" "+s;
                    else s=units[r-1]+" "+s;
                }
                else if(pos%3==1){
                    if(r==1 && pr==0) s="Ten "+s;
                    else if(r==1){
                        pos++;
                        continue;
                    }
                    else s=tens[r-1]+" "+s;
                }
                else if(pos%3==2){
                    if(r==0){
                        pos++;
                        continue;
                    }
                    s=units[r-1]+" "+s;
                }

            }
            pos++;
        }
        s = s.substring(0, s.length() - 1);

        return s;
    }
}
