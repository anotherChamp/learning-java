package sd.learningjava.collection.processor;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModification {

    public void demoRun() {
        List<String> unmodifiableList = List.of("Soumojit", "Sumit", "Pratik", "Sudipta", "Tuhin");
        List<String> l = new ArrayList<>(unmodifiableList);

        System.out.println("List of names - ");
        l.forEach(System.out::println);
        System.out.println("Trying to remove names which do not start with 'S'");
        System.out.println("Using enhanced for loop");
        try {
            for (String s : l) {
                if (!s.startsWith("S"))
                    l.remove(s);
            }
        } catch(ConcurrentModificationException e) {
            System.out.println("Ohh! We got an exception");
            e.printStackTrace(System.out);
        }
        System.out.println("List after iterating using enhanced for loop");
        l.forEach(System.out::println);

        System.out.println("To remove while iterating we need Iterator or ListIterator");
        System.out.println("Re-generating list");
        l = new ArrayList<>(unmodifiableList);
        l.forEach(System.out::println);
        System.out.println("Using iterator");
        for (Iterator<String> it = l.iterator(); it.hasNext(); ) {
            if (!it.next().startsWith("S"))
                it.remove();
        }
        System.out.println("After iterating using Iterator - ");
        l.forEach(System.out::println);

        System.out.println("Re-generating list");
        l = new ArrayList<>(unmodifiableList);
        l.forEach(System.out::println);
        System.out.println("Same operation using Java 8+");
        // Just a one liner
        l.removeIf(s -> !s.startsWith("S"));
        l.forEach(System.out::println);
    }
}
