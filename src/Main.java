import java.util.*;
import java.util.regex.*;
public class Main
{
	public static int moves;
	public static Board brd;
	public static Scanner in=new Scanner(System.in);
public static void main(String[] args)
{
System.out.println("ARRANGE - a number game");
play();
}
public static void play()
{
moves=0;
System.out.println("Hardness? <hardness|board size> (E for exit)");
String h=in.nextLine().toLowerCase();
if(h.matches("e(xit)?"))
System.exit(0);
int m=2,n=2;
int mxlvl=8;
if(h.contains("hard"))
{
m=n=9;
mxlvl=128;
}
else if(h.contains("moderate")||h.contains("medium"))
{
m=n=6;
mxlvl=64;
}
else if(h.contains("easy"))
{
m=n=3;
mxlvl=16;
}
else
{
List<String>ds=new ArrayList<String>();
//following 3 line from a web search
Matcher mt=Pattern.compile("((\\d+))").matcher(h);
while(mt.find())
ds.add(mt.group());
if(ds.size()==1)
m=n=Integer.parseInt(ds.get(0));
else if(ds.size()>1)
{
m=Integer.parseInt(ds.get(0));
n=Integer.parseInt(ds.get(1));
}
mxlvl=m*n*2;
}
brd=new Board(m,n);
brd.mix(mxlvl);
print();
handleMove();
}
private static void handleMove()
{
String mv=in.nextLine().toLowerCase();
if(mv.matches("e(xit)?"))
System.exit(0);
try
{
Matcher mt=Pattern.compile("(\\d+)").matcher(mv);
mt.find();
int toswp=Integer.parseInt(mt.group());
if(mv.contains("u"))
brd.swap(toswp,Board.Direction.UP);
else if(mv.contains("r"))
brd.swap(toswp,Board.Direction.RIGHT);
else if(mv.contains("d"))
brd.swap(toswp,Board.Direction.DOWN);
else if(mv.contains("l"))
brd.swap(toswp,Board.Direction.LEFT);
else
throw new Exception();
moves++;
print();
if(brd.isSolved())
{
System.out.println("\nCongrats! You solved it in "+moves+" moves. You won't get a reward though.");
System.out.println("Play again? <Y|N> (E for exit)");
String chs=in.nextLine().toLowerCase();
if(chs.matches("e(xit)?"))
System.exit(0);
if(chs.contains("y"))
play();
else
System.out.println("Done.");
}
else
handleMove();
}
catch(Exception e)
{
System.out.println("\nWrong move. Try again.");
handleMove();
}
}
public static void print()
{
for(int i=0;i<2500;i++)
System.out.println();
System.out.print("ARRANGE -a number game\n\n");
System.out.println("Type: "+brd.m+"×"+brd.n);
System.out.println("Moves: "+moves+"\n");
System.out.println(brd.toString(" "));
System.out.println("Move? <U|R|D|L> (E for exit)");
}
}
