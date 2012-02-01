//Time O(MN) & Space O(MN)

#include <cstdio>
#include <cstring>
const int N = 201;
int d[N][N]; // '0', '1'
int ht[N][N], lf[N][N], rt[N][N]; // 
int m, n;
inline void solve() {
	int res = 0;
	int i, j;
	for(i=1; i<=n; i++) { // init;
		ht[0][i] = 0; lf[0][i] = 1; rt[0][i] = n;
	}
	for(i=1; i<=m; i++) {
		int k = 1, tmp;
		//from left to right, find the height(ht[][]) and the further left cell for the rectangular (lf[][])
		for(j=1; j<=n; j++) {
			if(d[i][j]) {
				ht[i][j] = ht[i-1][j] + 1;
				if(k>lf[i-1][j]) 
					lf[i][j] = k;
				else 
					lf[i][j] = lf[i-1][j];
			}
			else {
				ht[i][j] = 0;
				lf[i][j] = 1; 
				k = j + 1;
			}
		}

		//from right to left, find the further right cell for the rectangular (rt[][])
		k = n;
		for(j=n; j>0; j--) {
			if(d[i][j]) {
				if(k<rt[i-1][j]) 
					rt[i][j] = k;
				else 
					rt[i][j] = rt[i-1][j];
				//calculate maximum rectangular area so far
				if((tmp=ht[i][j]*(rt[i][j]-lf[i][j]+1))>res) 
					res = tmp;
			}
			else {
				rt[i][j] = n;
				k = j - 1;
			}
		}
	}
	printf("%d\n", res);
}
int main() {
	freopen("in.txt", "r", stdin);
	int i, j;
	scanf("%d%d", &m, &n);
	for(i=1; i<=m; i++) {
		for(j=1; j<=n; j++) {
			scanf("%d", &d[i][j]);
		}
	}
	solve();
	return 0;
}