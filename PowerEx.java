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
public void sort(){
	return;
	}

}
