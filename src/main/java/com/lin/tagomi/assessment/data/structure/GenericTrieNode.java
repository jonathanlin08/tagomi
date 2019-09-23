package com.lin.tagomi.assessment.data.structure;

import java.util.*;

public class GenericTrieNode<T> {
    private Map<T, GenericTrieNode<T>> children = new HashMap<>();
    private List<Integer> matchIndices = new ArrayList<>();

    public Map<T, GenericTrieNode<T>> getChildren() {
        return children;
    }

    public List<Integer> getMatchIndices() {
        return matchIndices;
    }

    public void addToMatchIndices(List<Integer> newMatchIndices) {
        this.matchIndices.addAll(newMatchIndices);
    }

    public void addToMatchIndices(int newMatchIndex) {
        this.matchIndices.add(newMatchIndex);
    }
}