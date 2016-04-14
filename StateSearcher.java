import java.util.*;
public class StateSearcher{

//Likely subject to change

protected State startingState;

public LinkedList<State> open;
public LinkedList<State> closed;
public String targetString = "Slepice";
public int lookBackDistance = 3;

public StateSearcher(State theState){
startingState = theState;
theState.parent = theState;
open = new LinkedList<State>();
closed = new LinkedList<State>();
}

public State find(String str){
	int stateNum = 0;
	open.clear();
	closed.clear();
	startingState.children.clear();
	startingState.hfVal = hf(startingState);
	open.add(startingState);
	ArrayList<State> temp = new ArrayList<State>();
	targetString = str;

	if(!startingState.stateEx.varContains(targetString)){
		return startingState;
		}
	
	while(open.size()>0){
		State currState = findBest();
		
		closed.add(currState);
		open.remove(currState);
		stateNum ++;
		int gen =0;
		State genState = currState;
		while(true){
			if(genState==startingState){
				break;
				}
			gen ++;
			if(gen==40){
				return currState;
				}
			genState = genState.parent;
			}

		System.out.println("=====================================STATE " + stateNum + " , generation " + gen) ;
		System.out.println("CurrState is " + currState.stateEx.report());
		System.out.println("It's hf is " + hf(currState));
		if(!currState.stateEx.varContains(targetString)){ //TODO REMOVE
			System.out.println("Variable lost. It's parent is : " + currState.parent.stateEx.report());
			System.out.println("It's parent's operator is : " + currState.parent.stateOp);
			System.out.println("It's operator is : " + currState.stateOp);
			System.out.println("Initiating debug crunch on father, using the cruncher");
			System.out.println(currState.stateOp.execute(currState.parent.stateEx));
			return currState;
			}
		if(hf(currState)==1){
			return currState;
			}

		currState.propagate();
		System.out.println("It just propagated.");
		ArrayList<State> newChildren = currState.children;
		for(State s : newChildren){
			if(isNew(s)){
				s.hfVal = hf(s);
				if(s.hfVal==0){
					return s;
					}
				open.add(s);
				}
			}
		
		String deb = "New children : <";
		for(State s : newChildren){
			deb+= s.stateEx.report() + " ; ";
			}
		deb+= ">";
		System.out.println(deb);
		}
	System.out.println("No more opens.");
	return startingState;
	}

public boolean equalStates(State x, State y){
	String a = x.stateEx.report();
	String b = y.stateEx.report();
	if(a.equals(b)){
		return true;
		}
	return false;
	}

public boolean isNew(State x){
	for(State s : closed){
		if(equalStates(x,s)){
			return false;
			}	
		}
	for(State s : open){
		if(equalStates(x,s)){
			return false;
			}
	}
	return true;
	}

public int hf(State argState){
	int d = argState.stateEx.varDepth(targetString);
	d --;
	d = d*1000;
	int complexity = argState.stateEx.subExTotal();
	if(complexity>999){
		complexity = 999;
		}
	d += complexity;
	return d;
	}

public State findBest(){
	State hen = open.get(0);
	for(State x : open){
		if (x.hfVal < hen.hfVal){
			hen = x;
		}
	}		
	return hen;
	}

public boolean didImprove(State theState, int lookBack){
	if(theState == startingState){
		return true;
		}
	int i;
	State theParent = theState.parent;
	for(i = (lookBack - 1); i>0; i--){
			theParent = theParent.parent;
		}
	System.out.println("checking if it improved against it's grand-something parent " + theParent.stateEx.report());
	
	if(theParent == startingState){
		return true; //Need this to get past the start
		}

	if(hf(theParent)>hf(theState)){
		return true;
		}
	return false;

	}

}