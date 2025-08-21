package swea_b.OTT;

import java.util.*;

class UserSolution
{
    static class Movie implements Comparable<Movie> {
        final int id, genre, createdAt;
        int totalRating;
        List<Integer> watchers;
        
        Movie(int id, int genre, int totalRating, int createdAt) {
            this.id = id;
            this.genre = genre;
            this.totalRating = totalRating;
            this.createdAt = createdAt;
            watchers = new ArrayList<>();
        }

        void addWatcher(int uID) {
            watchers.add(uID);
        }

        @Override
        public int compareTo(Movie o) {
            if (this.totalRating == o.totalRating) {
                return -Integer.compare(this.createdAt, o.createdAt);
            }
            return -Integer.compare(this.totalRating, o.totalRating);
        }
    }

    static class WatchInfo implements Comparable<WatchInfo> {
        final int mID, rating, genre, createdAt;

        WatchInfo(int mId, int mRating, int mGenre, int createdAt) {
            this.mID = mId;
            this.rating = mRating;
            this.genre = mGenre;
            this.createdAt = createdAt;
        }

        @Override
        public int compareTo(WatchInfo o) {
            return -Integer.compare(this.createdAt, o.createdAt);
        }
    }

    static int mCreatedCnt;
    static TreeSet<Movie> movieSet;
    static Map<Integer, Movie> movieMap;

    static int wCreatedCnt;
    static TreeSet<WatchInfo>[] watchSetByUser; 
    static Map<Integer, WatchInfo>[] watchMapByUser;

    void init(int N)
    {
        // N = 100
        // movie
        mCreatedCnt = 0;
        movieSet = new TreeSet<>();
        movieMap = new HashMap<>();
        
        // watch list
        wCreatedCnt = 0;
        watchSetByUser = new TreeSet[N + 1];
        watchMapByUser = new HashMap[N + 1];
        for (int i = 0; i <= N; i++) {
            watchSetByUser[i] = new TreeSet<>();
            watchMapByUser[i] = new HashMap<>();
        }
    }
    
    int add(int mID, int mGenre, int mTotal)
    {
        // 호출 10,000
        if (movieMap.get(mID) != null) return 0;

        Movie m = new Movie(mID, mGenre, mTotal, mCreatedCnt);
        movieSet.add(m);
        movieMap.put(m.id, m);
        mCreatedCnt++;
        return 1;
    }
    
    int erase(int mID)
    {
        // 호출 1,000
        Movie m = movieMap.get(mID);
        if (m == null) return 0;

        for (int watcher : m.watchers) { // O(W)
            watchSetByUser[watcher].remove(watchMapByUser[watcher].get(mID));
            watchMapByUser[watcher].remove(mID);
        }
        movieSet.remove(m);
        movieMap.remove(mID);

        return 1;
    }

    int watch(int uID, int mID, int mRating)
    {
        // 호출 30,000
        Movie m = movieMap.get(mID);
        
        if (m == null || isWatched(uID, mID)) return 0;

        movieSet.remove(m);
        m.totalRating += mRating;
        WatchInfo w = new WatchInfo(mID, mRating, m.genre, wCreatedCnt++);
        watchSetByUser[uID].add(w);
        watchMapByUser[uID].put(mID, w);
        m.addWatcher(uID);
        movieSet.add(m);
        return 1;
    }
    
    Solution.RESULT suggest(int uID)
    {
        // 호출 5,000
        Solution.RESULT res = new Solution.RESULT();
        
        // 1. 최근 5개 영화 추출
        TreeSet<WatchInfo> watchSet = (TreeSet<WatchInfo>) watchSetByUser[uID].clone();
        List<WatchInfo> tmpList = new ArrayList<>();
        while (!watchSet.isEmpty()) {
            tmpList.add(watchSet.pollFirst());
            if (tmpList.size() == 5) break;
        }
        List<Movie> candidates;
        
        if (tmpList.isEmpty()) {
            // 2-a. 없으면 모든 영화 선택
            int cnt = 0;
            candidates = new ArrayList<>();
            for (Movie m : movieSet) {
                candidates.add(m);
                if (++cnt >= 5) break;
            }
        } else {
            // 2-b. 5개중 가장 평점이 높고 최근에 본 영화의 장르 모두 선택
            Collections.sort(tmpList, (w1, w2) -> {
                    if (w1.rating == w2.rating) {
                        return -Integer.compare(w1.createdAt, w2.createdAt);
                    }
                    return -Integer.compare(w1.rating, w2.rating);
                });
            int bestGenre = tmpList.get(0).genre;
            
            candidates = new ArrayList<>();
            int cnt = 0;
            for (Movie m : movieSet) {
                if (m.genre == bestGenre && !isWatched(uID, m.id)) {
                    candidates.add(m);
                    if (++cnt >= 5) break;
                }
            }
        }
        
        res.cnt = 0;
        while (res.cnt < candidates.size()){
            res.IDs[res.cnt] = candidates.get(res.cnt).id;
            res.cnt++;
        }

        return res;
    }

    // util code
    boolean isWatched(int uID, int mID) {
        return watchMapByUser[uID].containsKey(mID);
    }
}