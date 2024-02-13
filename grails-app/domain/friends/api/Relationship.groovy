package friends.api
import grails.rest.*

@Resource(uri='/relationship', formats=['json', 'xml'])
class Relationship { 
    Person person1
    Person person2

    static constraints = {
        person1 nullable: false
        person2 nullable: false
    }

}