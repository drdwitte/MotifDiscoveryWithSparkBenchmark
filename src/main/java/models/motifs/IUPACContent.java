package models.motifs;

import toolbox.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ddewitte on 29.09.15.
 */
public class IUPACContent implements PatternContent {

    private Map<Character,Integer> contentFreq = new HashMap<>();

    public IUPACContent(IUPACPattern p){
        for (int i=0; i<p.length(); i++){
            this.add(p.charAt(i));
        }

    }

    private IUPACContent(Map<Character,Integer> contentFreq){
        this.contentFreq=contentFreq;
    }

    @Override
    public PatternContent cloneContent() {
        Map<Character,Integer> clonedContentFreq = new HashMap<>();
        contentFreq.forEach(clonedContentFreq::put);
        return new IUPACContent(clonedContentFreq);
    }

    @Override
    public void add(Character c) {
        int count = contentFreq.getOrDefault(c,0);
        contentFreq.put(c, count + 1);
    }

    @Override
    public void pop(Character c) {
        int f = contentFreq.get(c);
        if (f==1){
            contentFreq.remove(c);
        } else {
            contentFreq.put(c,f-1);
        }

    }

    @Override
    public String toString() {
        return contentFreq.entrySet().stream()
                .map(e -> "" + e.getKey() + e.getValue())
                .sorted()
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public Set<Character> chars() {
        return contentFreq.keySet();
    }




}
