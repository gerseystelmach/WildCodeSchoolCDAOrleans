import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.*;

public class KataSquareSum {
    private final Set<Integer> squareSet;
    private final int n;
    private Map<Integer, Set<Integer>> neighboursByElement;
    private Map<Integer, ArrayList<Integer>> elementByNbNeighbours;

    // https://www.codewars.com/kata/5a667236145c462103000091/train/java
    public static void main(String[] args) {
        varDump(buildUpTo(5));
        varDump(buildUpTo(15));
    }

    public static List<Integer> buildUpTo(int n) {
        return new KataSquareSum(n).buildUp();
    }

    public KataSquareSum(int n) {
        this.n = n;
        this.squareSet = constructSquareSet(n);
    }

    public List<Integer> buildUp() {
        neighboursByElement = getNeighboursMap();
        if(neighboursByElement == null) return null;

        elementByNbNeighbours = getElementByNbNeighbours(neighboursByElement);

        if(elementByNbNeighbours.get(1) != null
                && elementByNbNeighbours.get(1).size() > 2) {
            return null;
        }

        int[] initialArray = getInitialArray();

        return solve(initialArray);
    }

    private List<Integer> solve(int[] initialArray) {
        return null;
    }

    private int[] getInitialArray() {
        int[] result = new int[n];
        List<Integer> lonelyInteger = elementByNbNeighbours.get(1);

        if(lonelyInteger.size() > 0) {
            result[0] = lonelyInteger.get(0);
            if(lonelyInteger.size() == 2) {
                result[n-1] = lonelyInteger.get(1);
            }
        }

        return result;
    }

    private Map<Integer, ArrayList<Integer>> getElementByNbNeighbours(Map<Integer, Set<Integer>> neighboursByElement) {
        Map<Integer, ArrayList<Integer>> elementByNbNeighbours = new TreeMap<Integer, ArrayList<Integer>>();

        neighboursByElement.forEach( (k, v) -> {
            int nbElements = v.size();
            if(!elementByNbNeighbours.containsKey(nbElements)) {
                elementByNbNeighbours.put(
                        nbElements,
                        new ArrayList<>()
                        );
            }
            elementByNbNeighbours.get(nbElements).add(k);
        });
        return elementByNbNeighbours;
    }

    private Map<Integer, Set<Integer>> getNeighboursMap() {
        Map<Integer, Set<Integer>> nbNeighboursByElement = new TreeMap<>();

        for(int i = 1; i <= n; ++i) {
            Set<Integer> possibleNeighbours = getPotentialNeighbours(i);
            if(possibleNeighbours.isEmpty()) return null;
            nbNeighboursByElement.put(i, possibleNeighbours);
        }

        return nbNeighboursByElement;
    }

    public Set<Integer> getPotentialNeighbours(int i) {
        Set<Integer> result = new TreeSet<>();

        for(Integer s: squareSet) {
            if(s <= i || s == 2*i) continue;
            if(s - i > n) break;

            result.add(s - i);
        }

        return result;
    }

    private Set<Integer> constructSquareSet(int n) {
        Set<Integer> squareSet = new TreeSet<>();

        for(int i = 2; i*i < 2*n; ++i) {
            squareSet.add(i*i);
        }

        return squareSet;
    }

    public static void varDump(Object o) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            System.out.println(ow.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
