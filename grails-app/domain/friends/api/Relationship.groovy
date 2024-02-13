package friends.api
import grails.rest.*

/*
    Relationship class
    Here we create the relationship for any two persons.

    REST support enabled

    TODO: Support to avoid duplicate relationships
*/
@Resource(uri='/relationship', formats=['json', 'xml'])
class Relationship { 
    Person person1
    Person person2

    static constraints = {
        person1 nullable: false
        person2 nullable: false
    }

// Ensure person1's ID is always lower than person2's ID to maintain consistency
    def beforeValidate() {
        if (person1.id > person2.id) {
            Person temp = person1
            person1 = person2
            person2 = temp
        }
    }
}