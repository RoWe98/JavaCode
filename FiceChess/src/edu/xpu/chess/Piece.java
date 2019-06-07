package edu.xpu.chess;

class  Piece implements Goconfig{

	public Piece(int color){
		this.color=0;
	}
	public int color=0;   //棋子颜色
	//权值重置
	public static void weightReset() {
		for(int i=0;i<weightarr.length;i++) {
			for(int j=0;j<weightarr[i].length;j++) {
				weightarr[i][j]=0;
			}
		}
	}
	public static Chess weight() {
		int maxWeight=0;int x=0,y=0;
		for(int i=0;i<go.length;i++) {
			for(int j=0;j<go[i].length;j++) {
				if(go[i][j].color==0) {
					String key1="0";String key2="0";
					int ux=i,uy=j,dx=i,dy=j;
					//左斜方向判断
					int first1=0,first2=0;
					while((ux-1)>=0&&(uy-1)>=0) {
						int first=go[i-1][j-1].color;
						first1=first;
						if(first==0) break;
						else {
							--ux;--uy;
							if(go[ux][uy].color==first) {
								key1+=go[ux][uy].color;
								System.out.println(ux);
								System.out.println(uy);
							}
							else {
								key1+=go[ux][uy].color;
								System.out.println(ux);
								System.out.println(uy);
								break;
							}
						}
					}
					while((dx+1)<=14&&(dy+1)<=14) {
						int first=go[i+1][j+1].color;
						first2=first;
						if(first==0) break;
						else {
							++dx;++dy;
							if(go[dx][dy].color==first) {
								key2+=go[dx][dy].color;
								System.out.println(dx);
								System.out.println(dy);
							}
							else {
								key2+=go[dx][dy].color;
								System.out.println(dx);
								System.out.println(dy);
								break;
							}
						}
					}
					Integer res1=map.get(key1);
					Integer res2=map.get(key2);
					int value1=0,value2=0;
					if(res1!=null) value1 = res1.intValue();
					if(res2!=null) value2 = res2.intValue();
					int value=value1+value2;
					if((first1==first2)&&first1!=0) value*=2;
					//在对应空位累加权值

					key1="0";key2="0";
					ux=i;uy=j;dx=i;dy=j;
					//竖直方向判断
					first1=0;first2=0;
					while((uy-1)>=0) {
						int first=go[i][j-1].color;
						first1=first;
						if(first==0) break;
						else {
							--uy;
							if(go[ux][uy].color==first) {
								key1+=go[ux][uy].color;
							}
							else {
								key1+=go[ux][uy].color;
								break;
							}
						}
					}
					while((dy+1)<=14) {
						int first=go[i][j+1].color;
						first2=first;
						if(first==0) break;
						else {
							++dy;
							if(go[dx][dy].color==first) {
								key2+=go[dx][dy].color;
							}
							else {
								key2+=go[dx][dy].color;
								break;
							}
						}
					}
					res1=map.get(key1);
					res2=map.get(key2);
					value1=0;value2=0;
					if(res1!=null) value1 = res1.intValue();
					if(res2!=null) value2 = res2.intValue();
					value+=value1+value2;
					if((first1==first2)&&first1!=0) value*=2;
//					value+=value;//在对应空位累加权值

					//水平方向
					key1="0";key2="0";
					ux=i;uy=j;dx=i;dy=j;
					first1=0;first2=0;
					while((ux-1)>=0) {
						int first=go[i-1][j].color;
						first1=first;
						if(first==0) break;
						else {
							--ux;
							if(go[ux][uy].color==first) {
								key1+=go[ux][uy].color;
							}
							else {
								key1+=go[ux][uy].color;
								break;
							}
						}
					}
					while((dx+1)<=14) {
						int first=go[i+1][j].color;
						first2=first;
						if(first==0) break;
						else {
							++dx;
							if(go[dx][dy].color==first) {
								key2+=go[dx][dy].color;
							}
							else {
								key2+=go[dx][dy].color;
								break;
							}
						}
					}
					res1=map.get(key1);
					res2=map.get(key2);
					value1=0;value2=0;
					if(res1!=null) value1 = res1.intValue();
					if(res2!=null) value2 = res2.intValue();
					value+=value1+value2;
					if((first1==first2)&&first1!=0) value*=2;
//					value+=value;//在对应空位累加权值

					//右斜方向
					key1="0";key2="0";
					ux=i;uy=j;dx=i;dy=j;
					//左斜方向判断
					first1=0;first2=0;
					while((ux+1)<=14&&(uy-1)>=0) {
						int first=go[i+1][j-1].color;
						first1=first;
						if(first==0) break;
						else {
							++ux;--uy;
							if(go[ux][uy].color==first) {
								key1+=go[ux][uy].color;
							}
							else {
								key1+=go[ux][uy].color;
								break;
							}
						}
					}
					while((dx-1)>=0&&(dy+1)<=14) {
						int first=go[i-1][j+1].color;
						first2=first;
						if(first==0) break;
						else {
							--dx;++dy;
							if(go[dx][dy].color==first) {
								key2+=go[dx][dy].color;
							}
							else {
								key2+=go[dx][dy].color;
								break;
							}
						}
					}
					res1=map.get(key1);
					res2=map.get(key2);
					value1=0;value2=0;
					if(res1!=null) value1 = res1.intValue();
					if(res2!=null) value2 = res2.intValue();
					value+=value1+value2;
					if((first1==first2)&&first1!=0) value*=2;
					weightarr[i][j]+=value;//在对应空位累加权值
					if(weightarr[i][j]>=maxWeight) {
						maxWeight=weightarr[i][j];
						x=i;y=j;
					}
				}
			}
		}
		return new Chess(x,y);
	}
	public int judge(int x,int y) {   //每下一枚子，对四个方向上的连子个数进行判断；
		for(int i=0;i<4;i++) {
			int ux=x,uy=y,dx=x,dy=y;
			int count=1;
			/*
			 * 1：先看它是否有相邻的棋子，如果有
			 * 2：看颜色是否相同，如果相同，计数器+1；
			 * 3: 如果达到》=5，则说明游戏结束；
			 */
			if(i==0) {
				while((ux-1)>=0&&(uy-1)>=0) {
					if(go[--ux][--uy].color==this.color) count++;
					else break;
				}
				while((dx+1)<=14&&(dy+1)<=14) {
					if(go[++dx][++dy].color==this.color) count++;
					else break;
				}
				if(count>=5) {
					System.out.print(this.color+"胜利");
					return this.color;
				}
			}
			else if(i==1) {
				while((uy-1)>=0) {
					if(go[ux][--uy].color==this.color) count++;
					else break;
				}
				while((dy+1)<=14) {
					if(go[dx][++dy].color==this.color) count++;
					else break;
				}
				if(count>=5) {
					System.out.print(this.color+"胜利");
					return this.color;
				}
			}
			else if(i==2) {
				while((ux+1)<=14&&(uy-1)>=0) {
					if(go[++ux][--uy].color==this.color) count++;
					else break;
				}
				while((dx-1)>=0&&(dy+1)<=14) {
					if(go[--dx][++dy].color==this.color) count++;
					else break;
				}
				if(count>=5) {
					System.out.print(this.color+"胜利");
					return this.color;
				}
			}
			else if(i==3) {
				while((dx-1)>=0) {
					if(go[--dx][dy].color==this.color) count++;
					else break;
				}
				while((ux+1)<=14) {
					if(go[++ux][uy].color==this.color) count++;
					else break;
				}
				if(count>=5) {
					System.out.print(this.color+"胜利");
					return this.color;
				}
			}
		}
		return 0;
	}

}
