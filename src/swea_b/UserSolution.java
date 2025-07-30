package swea_b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class UserSolution {
	private int N;
	private ArrayList<Integer> brandOfHotels;
	private ArrayList<ArrayList<Edge>> edgeList;
	
	static class Edge implements Comparable<Edge> {
		int dst;
		int dist;

		Edge(int dst, int dist) {
			this.dst = dst;
			this.dist = dist;
		}
	
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	void init(int N, int mBrands[]) {
		this.N = N;
		brandOfHotels = new ArrayList<>();
		edgeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			brandOfHotels.add(mBrands[i]);
			edgeList.add(new ArrayList<>());
		}
    }
    
    void connect(int mHotelA, int mHotelB, int mDistance) {
    	edgeList.get(mHotelA).add(new Edge(mHotelB, mDistance));
    	edgeList.get(mHotelB).add(new Edge(mHotelA, mDistance));
    }
    
    int merge(int mHotelA, int mHotelB) {
    	int cnt = 0;
    	
    	int brandA = brandOfHotels.get(mHotelA);
    	int brandB = brandOfHotels.get(mHotelB);
    	
    	for(int i = 0; i < brandOfHotels.size(); i++) {
    		int currentBrand = brandOfHotels.get(i); 
    		if (currentBrand == brandA) {
    			cnt++;
    		} else if (currentBrand == brandB) {
    			brandOfHotels.set(i, brandA);
    			cnt++;
    		}
    	}
        return cnt;
    }
    
    public int move(int start, int brandA, int brandB) {
        int maxD = 10 * N;
        @SuppressWarnings("unchecked")
        List<Integer>[] bucket = new ArrayList[maxD+1];
        for (int i = 0; i <= maxD; i++) bucket[i] = new ArrayList<>();

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        bucket[0].add(start);

        // 두 브랜드 각각의 최솟값. (동일 브랜드면 bestA, bestB 가 1st, 2nd)
        long bestA = Long.MAX_VALUE, bestB = Long.MAX_VALUE;

        for (int d = 0; d <= maxD; d++) {
            for (int u : bucket[d]) {
                if (dist[u] != d || u == start) continue;

                int b = brandOfHotels.get(u);
                // 브랜드 A 후보
                if (b == brandA) {
                    if (brandA == brandB) {
                        // 동일 브랜드 → 두 최소값 관리
                        if (d < bestA) {
                            bestB = bestA;
                            bestA = d;
                        } else if (d < bestB) {
                            bestB = d;
                        }
                    } else {
                        // 서로 다른 브랜드
                        if (d < bestA) bestA = d;
                    }
                }
                // 브랜드 B 후보 (단, 서로 다른 브랜드일 때만)
                if (brandA != brandB && b == brandB) {
                    if (d < bestB) bestB = d;
                }

                // 종료 조건
                if ((brandA != brandB && bestA < Long.MAX_VALUE && bestB < Long.MAX_VALUE)
                 || (brandA == brandB && bestB < Long.MAX_VALUE)) {
                    return (int)(bestA + bestB);
                }

                // 인접 간선 완화
                for (Edge e : edgeList.get(u)) {
                    long nd = d + e.dist;
                    if (nd < dist[e.dst]) {
                        dist[e.dst] = nd;
                        bucket[(int)nd].add(e.dst);
                    }
                }
            }
        }
        return -1; // 항상 해답이 있다는 가정
    }
}