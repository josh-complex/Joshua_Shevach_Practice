package com.company;

import java.util.HashSet;
import java.util.Iterator;

public class SetIterator {

    public void printSet(int a, int b, int c, int d, int e){

        HashSet<Integer> set = new HashSet<Integer>()
        {{
            add(a);
            add(b);
            add(c);
            add(d);
            add(e);
        }};

        Iterator iter = set.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
}
