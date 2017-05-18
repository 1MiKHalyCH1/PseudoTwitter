package ru.urfu.controllers;

import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.urfu.entities.Message;
import ru.urfu.model.InMemoryMessageDao;
import ru.urfu.model.InMemoryUserDao;

import javax.inject.Inject;
import java.util.*;

import static java.util.stream.Collectors.*;

@RestController
public class StatisticController {
    @Inject InMemoryMessageDao messagesStorage;
    @Inject InMemoryUserDao userStorage;

    @RequestMapping(value = "/top100all")
    Map<String, Integer> get_all_messages() {
        return getSortedMapOfWords(messagesStorage.findAll());
    }

    @RequestMapping(value = "/top100/{user}")
    Map<String, Integer> get_user_messages(@PathVariable("user") String user) {
        return getSortedMapOfWords(messagesStorage.findAll(user));
    }

    @RequestMapping(value = "/topWord/{word}")
    Map<String, Integer> get_word_stat(@PathVariable("word") String word){
        return messagesStorage.findAll()
                .parallelStream()
                .collect(groupingBy(Message::getAuthorName, HashMap::new, mapping(s -> s, toList())))
                .entrySet()
                .parallelStream()
                .collect(toConcurrentMap(s -> s.getKey(), s -> getSortedMapOfWords(s.getValue()).getOrDefault(word, 0)))
                .entrySet()
                .parallelStream()
                .filter(s -> s.getValue() != 0)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new)
                );
    }

    private Map<String, Integer> getSortedMapOfWords(List<Message> messages){
        return messages
                .parallelStream()
                .map(Message::getMessage)
                .map(String::toLowerCase)
                .flatMap(s -> Arrays.stream(s.split(" "))
                        .map(x -> x.replaceAll("[^a-zA-Z0-9]", ""))
                        .filter(x -> !x.isEmpty()))
                .collect(toConcurrentMap(s -> s, w -> 1, Integer::sum))
                .entrySet()
                .parallelStream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(100)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new)
                );
    }
}
