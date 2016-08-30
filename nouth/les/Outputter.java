package nouth.les;
import java.util.*;

public class Outputter{ 
	TexParser t = new TexParser();

public String getOutput(String in, String var){
	Ex e = t.parse(in);
	State startState = new State(e);
	StateSearcher searchie = new StateSearcher(startState);
	State foundState = searchie.find(var);
	
	System.out.println("outputter foundState prior to searcher : " + foundState.stateEx.reportForTex());
	ComputeSearcher cs = new ComputeSearcher();
	foundState = cs.search(foundState);

	String res = "";	

	State papa = foundState;
	System.out.println("outputer found " + foundState.stateEx.report());
	LinkedList<String> as = new LinkedList<String>();

	String ugly = foundState.stateEx.report();
/*	Simplifier wakko = new Simplifier();
	Ex simple = wakko.simplify(foundState.stateEx);
	if(!simple.report().equals(ugly)){
		as.add(simple.reportForTex());
		}
*/
	while(true){
		
		as.add((papa.stateEx.reportForTex())); 
		papa = papa.parent;
		if(papa == startState){
		as.add((papa.stateEx.reportForTex()));
			break;
			}
		}

	
	int i;
	for(i=as.size()-2;i>=0;i--){
	res += as.get(i);
	if(i>0){
		res+= "\\\\\\\\";
		}
	res += "\n";
	}
	return res;
	}


}
