package edu.xpu.chess;

import java.util.HashMap;

public interface Goconfig {
	public static final int row=15;
	public static final int column=15;
	public static final int square_size=35;
	public static final int radius=12;
	public static final int X=30,Y=30;
	public static Piece go[][]=new Piece[15][15];
	public static int weightarr[][]=new int[15][15];
	public static HashMap<String,Integer> map = new HashMap<String,Integer>();
	
}
