package tasks;

import common.Person;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {

    return persons.stream().skip(0).map(Person::getFirstName).collect(Collectors.toList()); //спользован api skip вместо remove
//    if (persons.size() == 0) {
//      return Collections.emptyList();
//    }
//    persons.remove(0);
//    return persons.stream().map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().collect(Collectors.toSet()); //убран лишний stream api distinct, set уже предполагает уникальность
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {

    return Stream.of(person.getFirstName(), person.getMiddleName(), person.getSecondName())
            .reduce("", (accum, name) -> accum + name + " ")
            .trim(); //Переписано в виде стрима

//
//    String result = "";
//
//    if (person.getFirstName() != null) {
//      result += person.getFirstName();
//    }
//
//    if (person.getMiddleName() != null) {
//      result += " " + person.getSecondName();
//    }
//    if (person.getSecondName() != null) {
//      result += " " + person.getSecondName();
//    }
//    //Изменен порядок, вместо SecondName  -> MiddleName
//    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(Person::getId,
            this::convertPersonToString,(existingValue, newValue) -> existingValue, HashMap::new)); //переписано в виде стримов, интерфейс HashMap как реализация Set
//    Map<Integer, String> map = new HashMap<>(1);
//    for (Person person : persons) {
//      if (!map.containsKey(person.getId())) {
//        map.put(person.getId(), convertPersonToString(person));
//      }
//    }
//    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {

    return persons1.stream().anyMatch(person1 -> persons2.stream().anyMatch(person2 -> person1.equals(persons2)));

//    for (Person person1 : persons1) {
//      for (Person person2 : persons2) {
//        if (person1.equals(person2)) {
//          return true;                         //добавлен выход из цикла при первом удовлетворении условия
//        }
//      }
//    }
//    return false;
  }

  //...
  public long countEven(Stream<Integer> numbers) {
//    count = 0;
    return numbers.filter(num -> num % 2 == 0).count(); //forEach(num -> count++);
//    return count;
  }
}
