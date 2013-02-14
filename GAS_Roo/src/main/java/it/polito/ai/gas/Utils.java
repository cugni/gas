package it.polito.ai.gas;

import java.util.HashSet;
import java.util.Set;

public class Utils {
	public static <T> Set<T> merge(Set<T> one,Set<T> two){
		if(one==null&&two==null){
			return new HashSet<T>();
		}else if( one ==null){
			return two;
		}else if(two==null){
			return one;
		}else{
			HashSet<T> s=new HashSet<T>(one);
			s.addAll(two);
			return s;
		}
	}
	public static <T> Set<T> merge(Set<T> one){
		if(one==null){
			return new HashSet<T>();		 
		}else{
			HashSet<T> s=new HashSet<T>(one);		 
			return s;
		}
	}

}
