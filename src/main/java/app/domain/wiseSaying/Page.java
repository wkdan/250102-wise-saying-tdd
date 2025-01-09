package app.domain.wiseSaying;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Page<T> {

    @Getter
    public List<T> content;
    @Getter
    private int totalItems;
    private int itemsPerPage;
    @Getter
    private int page;

    public int getTotalPages() {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }
}