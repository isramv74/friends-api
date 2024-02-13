package friends.api
import grails.rest.*

@Resource(uri='/person', formats=['json', 'xml'])
class Person {
    String name


    static constraints = {
        name nullable: false
    }
}