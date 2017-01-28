public class Board
{
public int m,n;
public int[][]nums;
public Board(int rowcols)
{
this(rowcols,rowcols);
}
public Board(int rows,int cols)
{
m=rows;
n=cols;
nums=new int[m][n];
refresh();
}
public int[]find(int num)
{
if(num<1||num>m*n)
{
System.out.println("\nCould not find "+num);
return null;
}
int i=0,j;
for(;i<m;i++)
for(j=0;j<n;j++)
if(nums[i][j]==num)
return new int[]{i,j};
return null;
}
public boolean isSolved()
{
for(int i=0;i<m;i++)
for(int j=0;j<n;j++)
if(nums[i][j]!=i*n+j+1)
return false;
return true;
}
public void mix(int times)
{
if(m>1||n>1)
{
for(int i=0;i<times;i++)
{
int ri=(int)Math.floor(Math.random()*m),rj=(int)Math.floor(Math.random()*n);
int dir=(int)Math.ceil(Math.random()*3);
if(ri==0&&dir==Direction.UP)
dir=Direction.DOWN;
else if(ri==m-1&&dir==Direction.DOWN)
dir=Direction.UP;
if(rj==0&&dir==Direction.LEFT)
dir=Direction.RIGHT;
else if(rj==n-1&&dir==Direction.RIGHT)
dir=Direction.LEFT;
swap(ri,rj,dir);
}
if(isSolved())
mix(times);
}
}
public void refresh()
{
for(int i=0;i<m;i++)
for(int j=0;j<n;j++)
nums[i][j]=i*n+j+1;
}
public void swap(int num,int dir)
{
int[]cords=find(num);
swap(cords[0],cords[1],dir);
}
public void swap(int i,int j,int dir)
{
int i2=i,j2=j;
switch(dir)
{
case Direction.UP:
i2=i-1;
break;
case Direction.DOWN:
i2=i+1;
break;
case Direction.LEFT:
j2=j-1;
break;
case Direction.RIGHT:
j2=j+1;
}
if(i2<0||i2>=m||j2<0||j2>=n)
{
System.out.println("\nCannot swap");
return;
}
int tmp=nums[i][j];
nums[i][j]=nums[i2][j2];
nums[i2][j2]=tmp;
}
public String toString()
{
return toString("");
}
public String toString(String indent)
{
int p=(""+m*n).length();
String ret="";
for(int i=0;i<m;i++)
{
ret+=indent;
for(int j=0;j<n;j++)
{
String nmS=""+nums[i][j];
int s=(p-nmS.length())/2;
for(int k=0;k<s;k++)
nmS=" "+nmS+" ";
if((p-nmS.length())%2!=0)
nmS=" "+nmS;
ret+=" "+nmS+" ";
}
ret+="\n";
}
return ret;
}
public static final class Direction
{
public static final int UP=1,DOWN=2,LEFT=3,RIGHT=4;
}
}
