package test;

import java.util.Optional;

public class optinal {
    public static void main(String args[]){
        Person person  =new Person();
        person.setAge(2);
        String name  = Optional.ofNullable(person).map(p -> p.getName()).orElse("name is null");
        System.out.println(name);
    }
}
