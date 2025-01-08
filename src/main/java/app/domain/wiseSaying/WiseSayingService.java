package app.domain.wiseSaying;

import app.domain.wiseSaying.repository.RepositoryProvider;
import app.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        wiseSayingRepository = RepositoryProvider.provide();
    }

    public WiseSaying write(String content, String author) {

        WiseSaying wiseSaying = new WiseSaying(content, author);
        return wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> getAllItems() {
        return wiseSayingRepository.findAll();
    }

    public boolean delete(int id) {
        return wiseSayingRepository.deleteById(id);
    }

    public Optional<WiseSaying> getItem(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String newContent, String newAuthor) {
        wiseSaying.setContent(newContent);
        wiseSaying.setAuthor(newAuthor);

        wiseSayingRepository.save(wiseSaying);
    }

    public void build() {
        wiseSayingRepository.build();
    }

    public List<WiseSaying> search(String kType, String kW) {

        return wiseSayingRepository.findAll().stream()
                .filter( w-> {
                    if(kType.equals("content")) {
                        return w.getContent().contains(kW);
                    } else
                        return w.getAuthor().contains(kW);
                })
                .toList();
    }

    public void makeSampleData(int cnt) {
        wiseSayingRepository.makeSampleData(cnt);
    }

    public int count() {
        return wiseSayingRepository.count();
    }
}
