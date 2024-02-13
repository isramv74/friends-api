package friends.api
import grails.rest.*

/*
    Person class
    For now only name attribute.
    REST support enabled
*/
@Resource(uri='/person', formats=['json', 'xml'])
class Person {
    String name


    static constraints = {
        name nullable: false
    }
}