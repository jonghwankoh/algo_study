package swea_b.OTT;

import java.util.*;

class UserSolution
{
    static class Movie implements Comparable<Movie> {
        final int mId, mGenre, createdAt;
        int mTotal;
        List<Integer> watchers;
        
        Movie(int mId, int mGenre, int mTotal, int createdAt) {
            this.mId = mId;
            this.mGenre = mGenre;
            this.mTotal = mTotal;
            this.createdAt = createdAt;
            watchers = new ArrayList<>();
        }

        void addWatcher(int uID) {
            watchers.add(uID);
        }

        @Override
        public int compareTo(Movie o) {
            if (this.mTotal == o.mTotal) {
                return -Integer.compare(this.createdAt, o.createdAt);
            }
            return -Integer.compare(this.mTotal, o.mTotal);
        }
    }

    static class WatchInfo implements Comparable<WatchInfo> {
        final int mId, mRating, mGenre, createdAt;

        WatchInfo(int mId, int mRating, int mGenre, int createdAt) {
            this.mId = mId;
            this.mRating = mRating;
            this.mGenre = mGenre;
            this.createdAt = createdAt;
        }

        @Override
        public int compareTo(WatchInfo o) {
            return -Integer.compare(this.createdAt, o.createdAt);
        }
    }

    static int mCreatedCnt;
    static List<Movie> movieList;
    static Map<Integer, Movie> movieMap;

    static int wCreatedCnt;
    static TreeSet<WatchInfo>[] watchListByUser; 
    static HashSet<Integer>[] watchedMIdByUser;

    void init(int N)
    {
        // ID가 1~N인 사용자가 존재
        // uId <= 1000
        // mId <= 10억
        // 장르 1~5
        mCreatedCnt = 0;
        movieList = new ArrayList<>();
        movieMap = new HashMap<>();
        
        wCreatedCnt = 0;
        watchListByUser = new TreeSet[N + 1];
        watchedMIdByUser = new HashSet[N + 1];
        for (int i = 0; i <= N; i++) {
            watchListByUser[i] = new TreeSet<>();
            watchedMIdByUser[i] = new HashSet<>();
        }
    }
    
    int add(int mID, int mGenre, int mTotal)
    {
        // 호출 10,000 이하
        // 성공시 1, 실패시 0
        // 같은 ID가 있으면 실패
        // 삭제된 영화의 ID로 다시 등록은 주어지지 않음
        // 이 함수가 나중에 호출된게 최신에 등록된 영화
        if (movieMap.get(mID) != null) return 0;

        Movie m = new Movie(mID, mGenre, mTotal, mCreatedCnt++);
        addToMovieList(m);
        movieMap.put(m.mId, m);
        return 1;
    }
    
    int erase(int mID)
    {
        // 호출 1,000 이하
        // 없거나 이미 삭제한 경우 0
        Movie m = movieMap.get(mID);
        if (m == null) return 0;

        for (int watcher : m.watchers) {
            removeWatchInfo(watcher, mID);
        }
        removeFromMovieList(m);
        movieMap.remove(mID);

        return 1;
    }

    int watch(int uID, int mID, int mRating)
    {
        // 호출 30,000 이하
        // 영화의 총점이 mRating만큼 증가
        // 이미 시청했거나 영화가 없는 경우 0
        // 이 함수가 나중에 호출된게 최근에 시청한 영화
        Movie m = movieMap.get(mID);
        
        if (m == null || isWatched(uID, mID)) return 0;

        removeFromMovieList(m);
        m.mTotal += mRating;
        watchListByUser[uID].add(new WatchInfo(mID, mRating, m.mGenre, wCreatedCnt++));
        watchedMIdByUser[uID].add(mID);
        m.addWatcher(uID);
        addToMovieList(m);
        return 1;
    }
    
    Solution.RESULT suggest(int uID)
    {
        // 호출 5,000 이하
        // 인덱스 0부터 채워서 순위대로 추천
        // 1. 최근 5개 영허 추출
        // 1-1. 없으면 모든 영화 선택
        // 2. 가장 평점이 높고 최근에 본 영화의 장르 모두 선택
        // 3. 그 장르 영화 중에서 가장 총점이 높은 순서로, 같으면 최신 영화를 우선으로 5개 추천
        Solution.RESULT res = new Solution.RESULT();
        
        // 1. 최근 5개 영화 추출
        TreeSet<WatchInfo> watchSet = (TreeSet<WatchInfo>) watchListByUser[uID].clone();
        List<WatchInfo> tmpList = new ArrayList<>();
        while (!watchSet.isEmpty()) {
            tmpList.add(watchSet.pollFirst());
            if (tmpList.size() == 5) break;
        }
        List<Movie> candidates;
        
        if (tmpList.isEmpty()) {
            // 1-1. 없으면 모든 영화 선택
            candidates = new ArrayList<>();
            for (Movie m : movieList) {
                candidates.add(m);
            }
        } else {
            // 2. 가장 평점이 높고 최근에 본 영화의 장르 모두 선택
            Collections.sort(tmpList, (w1, w2) -> {
                    if (w1.mRating == w2.mRating) {
                        return -Integer.compare(w1.createdAt, w2.createdAt);
                    }
                    return -Integer.compare(w1.mRating, w2.mRating);
                });
            int bestGenre = tmpList.get(0).mGenre;
            
            candidates = new ArrayList<>();
            for (Movie m : movieList) {
                if (m.mGenre == bestGenre && !isWatched(uID, m.mId)) {
                    candidates.add(m);
                }
            }
        }

        // 후보 중에서 총점이 높고 가능한 최신 영화 5개 선정 -> 최적화 필요
        Collections.sort(candidates, (m1, m2) -> {
                if (m1.mTotal == m2.mTotal) {
                    return -Integer.compare(m1.createdAt, m2.createdAt);
                }
                return -Integer.compare(m1.mTotal, m2.mTotal);
            });
        
        res.cnt = 0;
        while (res.cnt < candidates.size()){
            res.IDs[res.cnt] = candidates.get(res.cnt).mId;
            res.cnt++;
            if (res.cnt == 5) break;
        }

        return res;
    }

    // util code
    void addToMovieList(Movie target) {
        // add at upper bound
        int left = 0, right = movieList.size();
        while (left < right) {
            int mid = (left + right) / 2;
            Movie m = movieList.get(mid);
            int cmp = target.compareTo(m);
            if (cmp >= 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        movieList.add(left, target);
    }

    void removeFromMovieList(Movie target) {
        int left = 0, right = movieList.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            Movie m = movieList.get(mid);
            int cmp = target.compareTo(m);
            if (cmp > 0) {
                left = mid + 1;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                movieList.remove(mid);
                return;
            }
        }
    }

    void removeWatchInfo(int watcher, int mID) {
        watchListByUser[watcher].removeIf(info -> info.mId == mID);
        watchedMIdByUser[watcher].remove(mID);
    }

    boolean isWatched(int uID, int mID) {
        return watchedMIdByUser[uID].contains(mID);
    }
}