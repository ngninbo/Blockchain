class Box<T extends Animal> {

    private Animal animal;

    void add(T t) {
        this.animal = t;
    }
}

// Don't change the code below
class Animal { }