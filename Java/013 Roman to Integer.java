/* 13	Roman to Integer
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int res = 0, prev = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            int cur = map.get(s.charAt(i));
            if (prev > cur) res -= cur;
            else res += cur;
            prev = cur;
        }
        
        return res;
    }
}