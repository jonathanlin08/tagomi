package com.lin.tagomi.assessment.data.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class GenericTrie<T> {

    private GenericTrieNode<T> root = new GenericTrieNode<>();

    public void insert(Collection<T> input, int index) {
        GenericTrieNode<T> current = this.root;
        for (T t : input) {
            current = current.getChildren().computeIfAbsent(t, c -> new GenericTrieNode<>());
        }
        current.addToMatchIndices(index);
    }

    public int count() {
        return this.getAllMatchWithPrefix(this.root).size();
    }

    public Collection<Integer> searchWithPrefix(Collection<T> prefix) {
        GenericTrieNode<T> current = this.root;
        for (T t : prefix) {
            if (!current.getChildren().containsKey(t)) {
                return Collections.emptyList();
            }
            current = current.getChildren().get(t);
        }
        return this.getAllMatchWithPrefix(current);
    }

    public Collection<Integer> getAllMatchWithPrefix(GenericTrieNode<T> node) {
        Collection<Integer> result = new ArrayList<>();
        if (node.getMatchIndices().size() > 0) {
            result.addAll(node.getMatchIndices());
        }
        for (Map.Entry<T, GenericTrieNode<T>> entry : node.getChildren().entrySet()){
            GenericTrieNode<T> childNode = entry.getValue();
            Collection<Integer> childAllMatchWithPrefix = this.getAllMatchWithPrefix(childNode);
            result.addAll(childAllMatchWithPrefix);
        }
        return result;
    }
}
