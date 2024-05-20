package org.sam.api.common;

import java.util.Collection;

public class Page<T> {

    private int totalPage;

    private int totalCount;

    private Collection<T> items;

    public Page(int size, int totalCount, Collection<T> items) {
        this.totalPage = totalCount / size;;
        if (totalCount % size > 0) {
            this.totalPage++;
        }
        this.totalCount = totalCount;
        this.items = items;
    }

}
