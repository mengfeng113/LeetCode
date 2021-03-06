/* 91	Decode Ways
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

public class Solution {
    public int numDecodings(String s) {
        // idea : 
        int n = s.length();
        if (n == 0) return 0;
        
        int[] m = new int[n+1];
        m[n]  = 1;
        m[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        
        for (int i = n - 2; i >= 0; i--){
            if (s.charAt(i) == '0') { continue; }
            else {
                m[i] = (Integer.parseInt(s.substring(i,i+2)) <= 26) ? m[i+1] + m[i+2] : m[i+1];
            }
        }
        return m[0];
    }
}