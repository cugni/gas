package it.polito.ai.gas;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.List;
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
	public static <T> Set<T> merge(Set<T> one,List<T> two){
		if(one==null&&two==null){
			return new HashSet<T>();
		}else if( one ==null){
			return new HashSet<T>(two);
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
	public static <T> Set<T> merge(Set<T> set, T obj) {
		HashSet<T> s;
		if (set == null)
			s = new HashSet<T>();
		else
			s = new HashSet<T>(set);
		if (obj != null)
			s.add(obj);
		return s;
	}

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static <T> T checkRights(T checkThis) {

        Integer checkId = -1;

        if (checkThis instanceof PurchaseRequest)
            checkId = ((PurchaseRequest) checkThis).getAcquirer().getId();
        else if (checkThis instanceof Product)
            checkId = ((Product) checkThis).getProducer().getId();
        else if (checkThis instanceof Proposal)
            checkId = ((Proposal) checkThis).getDelegate().getId();

        if(getCurrentUser().getId().intValue() != checkId.intValue())
            throw new SecurityException("Cause: "+checkThis.toString());

        return checkThis;
    }
}
