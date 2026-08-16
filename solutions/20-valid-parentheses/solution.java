class Solution {
    public boolean isValid(String s) {
        Stack<Character> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='(' || c=='[' || c=='{') st.push(c);
            else if(st.empty()) return false;
            else if((st.peek()=='(' && c==')') || (st.peek()=='[' && c==']') || (st.peek()=='{' && c=='}')) st.pop();
            else return false;
        }
        if(st.empty())
            return true;
        else return false;
    }
}
