import java.util.*;
public class PowerEx extends Ex{

public PowerEx(){
	reportSeparator = '^';
	exList.add(new VoidEx());
	exList.add(new VoidEx());
	}

public PowerEx(Ex e){
	reportSeparator = '^';
	exList.add(e);
	exList.add(new PlainEx(1));
	}

public PowerEx(Ex e, Ex p){
	reportSeparator = '^';
	exList.add(e);
	exList.add(p);
	}
@Override
public Ex toPower(Ex e){
	if(getSubEx(1) instanceof VoidEx){
		replaceTarget(1, e);
		return this;
		}
	if((e instanceof PlainEx)&&(((PlainEx)e).value==1)){
		return this;
		}
	getSubEx(1).multi(e);
	return this;
	}

@Override
public ArrayList<Operator> suggestCrunchers(){
	ArrayList<Operator> crs = new ArrayList<Operator>();
	crs.add(new PowerCruncherExpand());
	return crs;
	}

@Override
public ArrayList<Alterator> suggestAlterators(){
	ArrayList<Alterator> alts = new ArrayList<Alterator>();
	return alts;
	}

@Override
public Ex processCopy(Ex theCopy){
	Ex base = exList.get(0);
	Ex power = exList.get(1);
	theCopy.replaceTarget(0, base);
	theCopy.replaceTarget(1, power);
	return theCopy;
	}

@Override
public void sort(){
	
	}
}
