package com.lottery.ticket.application.exception;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MergeNames {
    
    public static String[] uniqueNames(String[] names1, String[] names2) {
        
		/*
		 * Set<String> mySet1 = new HashSet<String>(Arrays.asList(names1)); Set<String>
		 * mySet2 = new HashSet<String>(Arrays.asList(names2)); Set<String> union = new
		 * HashSet<String>(mySet1); union.addAll(mySet2); return union.toArray(new
		 * String[union.size()]);
		 */
    	
    	Stream<String> stream1 = Stream.of(names1);
    	Stream<String> stream2 = Stream.of(names2);
    	Stream<String> resultStream = Stream.concat(stream1, stream2).distinct();
    	return resultStream.toArray(size -> new String[size]);
    }
    
    public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
    }
}


