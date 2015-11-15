
```
import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityFactory;
import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;

class Main {

    interface Person {
        EntityProperty<String> NAME = EntityPropertyFactory.create("name",
                String.class);
        EntityProperty<Integer> AGE = EntityPropertyFactory.create("age",
                Integer.class);
    }
    
    public static void main(String[] args) {
        Entity person = EntityFactory.create();
        person.set(Person.NAME, "Mike");
        person.set(Person.AGE, 20);
        System.out.println(person.get(Person.NAME)); // print "Mike"
        System.out.println(person.get(Person.AGE)); // print 20
    }
}
```