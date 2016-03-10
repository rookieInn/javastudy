package com.gxy.graph;

/**
 * 为了显示一个图，需要知道每个顶点的位置以及名字。
 * 为了确保图可以显示，定义了一个名为Displayable的接口，并让顶点成为Display的实例。
 * 
 * @author gxy
 * @date 2016年2月17日下午2:02:22
 */
public interface Displayable {
	
	public int getX(); //Get x-coordinate of the vertex
	public int getY(); //Get y-coordinate of the vertex
	public String getName(); //Get display name of the vertex
	
}
