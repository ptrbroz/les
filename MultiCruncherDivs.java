import java.util.*;

public class MultiCruncherDivs extends Cruncher{
	
public int crunch(Ex targetEx){

workList = targetEx.getSubExList();

Ex firstDiv = null;
ListIterator<Ex> iter = workList.listIterator();
Ex e;

while(iter.hasNext()){
	e = iter.next();
	if(e instanceof DivEx){
		if(firstDiv == null){
			firstDiv = e;
			}
		else{
			firstDiv.multi(e.getSubEx(0));
			firstDiv.div(e.getSubEx(1));
			}
		iter.remove();
		}
	}

	

if(firstDiv == null){
	return 0;
	}

targetEx.wipe();
targetEx.multi(workList);
targetEx.multi(firstDiv);
return 1;

}
}