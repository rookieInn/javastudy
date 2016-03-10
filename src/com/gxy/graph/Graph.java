package com.gxy.graph;

import java.util.List;

/**
 * 图
 * 
 * @author gxy
 * @date 2016年2月15日下午2:51:26
 * @param <V>
 */
public interface Graph<V> {
	
	/**
	 * 返回图中定点的个数
	 */
	public int getSize(); 
	
	/**
	 * 返回图中的顶点
	 */
	public List<V> getVertices();
	
	/**
	 * 返回指定顶点下标的顶点对象
	 * @return
	 */
	public V getVertex(int index);
	
	/**
	 * 返回指定顶点的下标
	 */
	public int getIndex(V v);
	
	/**
	 * 返回指定下标的顶点的邻居
	 */
	public List<Integer> getNeighbors(int index);
	
	/**
	 * 返回指定顶点下标的度
	 */
	public int getDegree(int v);
	
	/**
	 * 返回邻接矩阵
	 */
	public int[][] getAdjacencyMatrix();
	
	/**
	 * 打印邻接矩阵
	 */
	public void printAdjacencyMatrix();
	
	/**
	 * 打印边
	 */
	public void printEdges();
	
	/**
	 * 获取一个深度优先搜索树
	 */
	public AbstractGraph<V>.Tree dfs(int v);
	
	/**
	 * 获取一个广度优先搜索树
	 */
	public AbstractGraph<V>.Tree bfs(int v);
	
}
