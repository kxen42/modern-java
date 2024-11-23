package org.fotm.collections;

import org.fotm.model.Car;
import org.fotm.model.CarGenerator;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CommonCollectionMethods {

    private static final Collection<Car> unmodifiableCarList = CarGenerator.createCars();

    public static void main(String[] args) {
        addAllAndClear();
        equalsAddAndRemove();
        removeAll();
        removeIf();
        retainAll();
        toArrayVanilla();
        toArrayWithArrayArg();
        toArrayWithIntFunction();
    }

    public static void addAllAndClear() {
        /* boolean addAll(Collection<E>)
         The behavior of this operation is undefined if the specified collection is modified while the operation is
         in progress. (This implies that the behavior of this call is undefined if the specified collection is this
         collection, and this collection is nonempty.)
         */
        System.out.println(" ----- addAllAndClear");
        Collection<Car> modifiableCarList = createModifiableCarList();
        before(modifiableCarList, unmodifiableCarList);
        boolean addedAll = modifiableCarList.addAll(unmodifiableCarList);
        System.out.println("addedAll: " + addedAll);
        after(modifiableCarList, unmodifiableCarList);
        modifiableCarList.clear();
        System.out.println("after clear");
        after(modifiableCarList, unmodifiableCarList);
    }

    public static void equalsAddAndRemove() {
        System.out.println(" ----- equalsAddAndRemove");
        Collection<Car> cars1 = createModifiableCarList();
        Collection<Car> cars2 = createModifiableCarList();
        before(cars1, cars2);
        spewAddresses(cars1);
        spewAddresses(cars2);
        System.out.println("identical? " + cars1.equals(cars2));

        Car extraCar = duplicateCar();
        cars2.add(extraCar);
        after(cars1, cars2);
        spewAddresses(cars2);
        System.out.println("identical after adding car? " + !cars1.equals(cars2));

        /*
        Returns true if this collection contained the specified element (or equivalently, if this collection
        changed as a result of the call).
        You don't know the order - this example does not remove that one just added, it remove the first for
        which 'equals' was true.
         */
        boolean removed = cars2.remove(extraCar);
        after(cars1, cars2);
        spewAddresses(cars2);
        System.out.println("extraCar removed? " + removed);
        System.out.println("is it equal again? " + cars1.equals(cars2));
    }

    public static void removeAll() {
        System.out.println(" ----- removeAll");
        Collection<Car> cars1 = createModifiableCarList();
        before(cars1, unmodifiableCarList);
        spewGuts(cars1);
        spewGuts(unmodifiableCarList);
        boolean removed = cars1.removeAll(unmodifiableCarList);
        System.out.println("were any removed? " + removed);
        after(cars1, unmodifiableCarList);
        spewGuts(cars1);
        System.out.println("is collection empty? " + cars1.isEmpty());
    }

    public static void removeIf() {
        System.out.println(" ----- removeIf");
        Predicate<Car> isAudi = c -> c.getMake()
                                      .equals("Audi");

        /*
        Parameterized constructor call saves you from newing then adding all.
         */
        Collection<Car> cars = new ArrayList<>(unmodifiableCarList);

        spewGuts(cars);
        boolean b = cars.removeIf(isAudi);
        System.out.println("where all Audis removed? " + b);
        spewGuts(cars);

    }

    public static void retainAll() {
        System.out.println(" ----- retainAll");

        Collection<Car> cars = createModifiableCarList();
        before(cars, unmodifiableCarList);

        boolean b = cars.retainAll(unmodifiableCarList);
        System.out.println("did collection change? " + b);
        after(cars, unmodifiableCarList);
    }

    public static void toArrayVanilla() {
        System.out.println(" ----- toArrayVanilla");
        Collection<Car> modifiableCarList = createModifiableCarList();
        Object[] array = modifiableCarList.toArray();
        System.out.println(VM.current().addressOf(modifiableCarList));
        spewAddresses(modifiableCarList);
        System.out.println(VM.current().addressOf(array));
        spewAddresses(array);
    }

    public static void toArrayWithArrayArg() {
        System.out.println(" ----- toArrayWithArrayArg");
        Collection<Car> modifiableCarList = createModifiableCarList();
        Car[] carListArray = modifiableCarList.toArray(new Car[0]);
        System.out.println(VM.current()
                             .addressOf(modifiableCarList));
        spewAddresses(modifiableCarList);
        System.out.println(VM.current()
                             .addressOf(carListArray));
        spewAddresses(carListArray);

        Car[] carListArray1 = modifiableCarList.toArray(new Car[modifiableCarList.size()]);
        System.out.println(VM.current()
                             .addressOf(carListArray1));
        spewAddresses(carListArray1);

        // newed with method reference
        Car[] carListArray2 = modifiableCarList.toArray(Car[]::new);
        System.out.println(VM.current()
                             .addressOf(carListArray2) + " carListArray2 newed with method reference");

    }

    /**
     * A[]  toArray(IntFunction<A[]> generator). Returns an array of values emitted by the stream/collection, using the
     * provided generator function to allocate the returned array, as well as any additional arrays that might be
     * required for a partitioned execution or for resizing.
     */
    public static void toArrayWithIntFunction() {
        System.out.println(" ----- toArrayWithIntFunction");
        IntFunction<Car[]> generatorLambda = i -> new Car[i];
        Car[] carListArray = unmodifiableCarList.toArray(generatorLambda);

        IntFunction<Car[]> generatorMethodReference = Car[]::new;
        Car[] carListArray1 = unmodifiableCarList.toArray(generatorMethodReference);
        System.out.println(VM.current().addressOf(carListArray) + " IntFunction 1");
        System.out.println(VM.current().addressOf(carListArray1) + " IntFunction 2");
    }

    private static void spewAddresses(Object[] array) {
        String collect = Arrays.stream(array)
                               .map(x -> VM.current()
                                           .addressOf(x))
                               .map(Long::toHexString)
                               .collect(Collectors.joining(", "));
        System.out.println("addresses: " + collect);
    }


    public static Collection<Car> createModifiableCarList() {
        Collection<Car> cars = new ArrayList<>();

        /* boolean Collection.add(E)
        Ensures that this collection contains the specified element (optional operation). Returns true if this
        collection changed as a result of the call.
        (Returns false if this collection does not permit duplicates and already contains the specified element.)
         */
        cars.add(Car.builder()
                    .make("Audi")
                    .year(2001)
                    .mileage(90000.5)
                    .defects(Arrays.asList("torn driver side seat", "AM radio not working", "front springs kaput"))
                    .build());

        cars.add(Car.builder()
                    .make("Audi")
                    .year(2015)
                    .mileage(50150.4)
                    .defects(Collections.singletonList("chip in windshield"))
                    .build());

        cars.add(Car.builder()
                    .make("Genesis")
                    .year(2024)
                    .mileage(0.0)
                    .defects(Collections.emptyList())
                    .build());

        return cars;
    }

    public static Car duplicateCar() {
        return Car.builder()
                  .make("Audi")
                  .year(2015)
                  .mileage(50150.4)
                  .defects(Collections.singletonList("chip in windshield"))
                  .build();
    }

    private static void spewSize(String preamble, Collection<Car> collection1, Collection<Car> collection2) {
        System.out.println(preamble + " - collection1.size: " + collection1.size() + ", collection2.size: " + collection2.size());
    }

    private static void before(Collection<Car> collection1, Collection<Car> collection2) {
        spewSize("Before", collection1, collection2);
    }

    private static void after(Collection<Car> collection1, Collection<Car> collection2) {
        spewSize("After", collection1, collection2);
    }

    private static void spewGuts(Collection<Car> collection) {
        collection.forEach(System.out::println);
    }

    public static void spewAddresses(Collection<Car> collection) {
        String collect = collection.stream()
                                   .map(x -> VM.current()
                                               .addressOf(x))
                                   .map(Long::toHexString)
                                   .collect(Collectors.joining(", "));
        System.out.println("addresses: " + collect);
    }
}