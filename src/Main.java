import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<int[]> sharkList = new ArrayList<>();
		ArrayList<int[]> spaceList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if(st.nextToken().equals("1")) {
					sharkList.add(new int[] {i, j});
				} else {
					spaceList.add(new int[] {i, j});
				}
			}
		}
		
		int ans = 0;
		for (int[] space : spaceList) {
			int minLength = 50;
			for (int[] shark : sharkList) {
				minLength = Math.min(
						minLength, 
						Math.max(
								Math.abs(space[0] - shark[0]),
								Math.abs(space[1] - shark[1])
						)
				);
			}
			ans = Math.max(ans, minLength);
		}
		System.out.println(ans);
		
		
	}

}
