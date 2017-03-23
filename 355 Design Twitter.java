/* 355. Design Twitter
Design a simplified version of Twitter where users can post tweets, follow/unfollow 
another user and is able to see the 10 most recent tweets in the user's news feed. 
Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 
Each item in the news feed must be posted by users who the user followed or by the user herself. 
Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
*/
public class Twitter {
    private static int timeStamp=0;
    
    // easy to find if user exist
    private Map<Integer, User> userMap;
    
    // Tweet link to next Tweet so that we can save a lot of time
    // when we execute getNewsFeed(userId)
    private class Tweet implements Comparable<Tweet>{
        public int id;
        public int time;
        public Tweet next;
        
        public Tweet(int id){
            this.id = id;
            time = timeStamp++;
            next = null;
        }
        
        public int compareTo(Tweet toComp){
            return toComp.time - this.time;
        }
    }
    
    
    // OO design so User can follow, unfollow and post itself
    public class User{
        public int id;
        public Set<Integer> followee;
        public Tweet tweetHead;
        
        public User(int id){
            this.id = id;
            followee = new HashSet<>();
            follow(id); // first follow itself
            tweetHead = null;
        }
        
        public void follow(int id){
            followee.add(id);
        }
        
        public void unfollow(int id){
            followee.remove(id);
        }
        
        
        // everytime user post a new tweet, add it to the head of tweet list.
        public void post(int id){
            Tweet t = new Tweet(id);
            t.next = tweetHead;
            tweetHead = t;
        }
    }
    
    
    

    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)){
            User u = new User(userId);
            userMap.put(userId, u);
        }
        userMap.get(userId).post(tweetId);
            
    }
    

    
    // Best part of this.
    // first get all tweets lists from one user including itself and all people it followee.
    // Second add all heads into a max heap. Every time we poll a tweet with 
    // largest time stamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this 
    // heap before we get the 10 most recent tweet.
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();

        if(!userMap.containsKey(userId)) { return res; }
        
        Set<Integer> users = userMap.get(userId).followee;
        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>();
        //if(userMap.get(userId).tweetHead != null) { q.add(userMap.get(userId).tweetHead); }
        
        for(int user: users){
            Tweet t = userMap.get(user).tweetHead;
            // very imporant! If we add null to the head we are screwed.
            if(t!=null){
                q.add(t);
            }
        }
        int n = 10;
        while(!q.isEmpty() && n > 0){
          Tweet t = q.poll();
          res.add(t.id);
          n--;
          if(t.next != null)
            q.add(t.next);
        }
        
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)){
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if(!userMap.containsKey(followeeId)){
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId == followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */