import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int personsUnder18 = (int) persons.stream().filter(p -> p.getAge() < 18).count();

        List<String> personsForArmy = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(p -> p.getSex() == Sex.MAN)
                .map(Person::getName).toList();

        List<Person> personsWorkAvailable = persons.stream().filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.MAN && (p.getAge() >= 18 && p.getAge() <= 65))
                        || p.getSex() == Sex.WOMAN && (p.getAge() >= 18 && p.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily)).toList();
    }
}