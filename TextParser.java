
import java.util.*;

public class TextParser{

public Ex parse(String inputString){

int depth = 0;
int i = 0;
boolean parsingVar = false;
String val = "";

DirtyEx grandseer= new DirtyEx();
DirtyEx overseer = new DirtyEx();
grandseer.add(overseer);
DirtyEx currEx = overseer;
ArrayList<Ex> toBeNegated = new ArrayList<Ex>();
boolean negateNext = false;

for(i=0;i<inputString.length();i++){
	char c = inputString.charAt(i);
	switch(c){
		case '(' :	System.out.println("TP:Start of new ex.");
				DirtyEx nw = new DirtyEx();
				currEx.add(nw);
				currEx = nw;
				if(negateNext){
					negateNext = false;
					toBeNegated.add(nw);
					}
				break;
		case ')' :	System.out.println("TP: end of ex of " +val);
				currEx.value = val;
				currEx = (DirtyEx)currEx.master;
				val = "";
				break;
		case '-' :	negateNext = true;
				c = '+';
		default	 :	System.out.println("TP:default " +c);
				if(isSeparator(c)){
					currEx.reportSeparator = c;
					}
				else{
				val += String.valueOf(c);
				}
		}
	}
	if(currEx!=overseer){
		System.out.println("Warning ! TextParser did not end with overseer. Risk of ambiguity.");
		}
	grandseer.getSubEx(0).polish();
	Ex result = grandseer.getSubEx(0);
	return result;
}

public static boolean isSeparator(char s){
	char[] sepArr = {'+','*','/','='}; //TODO remove magic
	for(char sep : sepArr){
		if(s==sep){
			return true;
			}
		}
	return false;
	}

}