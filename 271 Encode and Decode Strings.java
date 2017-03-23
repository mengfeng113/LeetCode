public class Codec {
    // how do we seperate strings? - seperators
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str.length()).append('/').append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        
        int start = 0;
        while(start < s.length()){
            int slashIndex = s.indexOf('/', start);
            int size = Integer.parseInt(s.substring(start, slashIndex));
            res.add(s.substring(slashIndex + 1, slashIndex + 1 + size));
            start = slashIndex + 1 + size;
        }
        
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));