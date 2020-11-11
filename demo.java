import java.util.*;
import java.lang.*;
import java.io.*;

class Main{
    
    static int adj[][], vis[], n;
    static ArrayList<Integer> g[], bfss, dfss;
    
    static void dfs(int v){
        vis[v]=1;
        dfss.add(v);
        for(int i=0; i<g[v].size(); i++){
            int to = g[v].get(i);
            if(vis[to]==0)
                dfs(to);
        }
    }
    
    static void bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        vis[v] = 1;
        while(q.size()>0){
            int u = q.remove();
            bfss.add(u);
            for(int i=0; i<g[u].size(); i++){
                int to = g[u].get(i);
                if(vis[to]==0){
                    q.add(to);
                    vis[to]=1;
                }
            }
        }
    }
    
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		adj = new int[n][n];
		g = new ArrayList[n];
		
		for(int i=0; i<n; i++){
		    for(int j=0; j<n; j++){
		        adj[i][j] = sc.nextInt();
		    }
		}
		
		ArrayList<Edge> v = new ArrayList(), v1 = new ArrayList();
		for(int i=0; i<n; i++){
		    for(int j=i+1; j<n; j++){
		        if(adj[i][j]==1)
		            v.add(new Edge(i, j));
		    }
		}
		
		int m = v.size();
		
		for(int msk=0; msk<(1<<m); msk++){
		    
		    ArrayList<Edge> v2 = new ArrayList();
		    bfss = new ArrayList();
		    dfss = new ArrayList();
		    
		    for(int i=0; i<n; i++)
		        g[i] = new ArrayList();
		       
		    for(int i=0; i<m; i++){
		        Edge e = v.get(i);
		        if((msk>>i)%2 == 1){
		            g[e.u].add(e.v);
		            g[e.v].add(e.u);
		        }else{
		            v2.add(e);
		        }
		    }
		    
		    for(int i=0; i<n; i++)
		        Collections.sort(g[i]);
		    
		    vis = new int[n];
		    for(int i=0; i<n; i++){
		        if(vis[i]==0)
		            dfs(i);
		    }
		    vis = new int[n];
		    for(int i=0; i<n; i++){
		        if(vis[i]==0)
		            bfs(i);
		    }
		    
		    if(bfss.equals(dfss)){
		        if(msk==0 || v1.size()>v2.size()){
		            v1 = v2;
		        }
		    }
		}
		
		System.out.print("BFS: ");
        for(int i=0; i<n; i++)
            System.out.print(bfss.get(i)+" ");
        System.out.println();
        
        System.out.print("DFS: ");
        for(int i=0; i<n; i++)
            System.out.print(dfss.get(i)+" ");
        System.out.println();
		
		System.out.println(v1.size());
		for(int i=0; i<v1.size(); i++){
		    Edge e = v1.get(i);
		    System.out.println(e.u+" "+e.v);
		}
		
	}
}

class Edge{
    int u, v;
    Edge(int u, int v){
        this.u = u;
        this.v = v;
    }
}

