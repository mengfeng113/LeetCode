/* 365. Water and Jug Problem
You are given two jugs with capacities x and y litres. There is an infinite amount
 of water supply available. You need to determine whether it is possible to measure 
 exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within 
one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first 
jug itself is empty.
*/
public class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(z == 0) return true;
        if(z > x + y) return false;
        int gcd = GCD(x,y);
        if(gcd == 0) return false;
        else return z % gcd == 0;
    }
    
    public int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }
}
