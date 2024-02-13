package friends.api
import grails.rest.*

class FriendsController extends RestfulController<Person>{
    static responseFormats = ['json', 'xml']
    FriendsController() {
        super(Person)
    }
    

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Person.list(params), model:[personCount: Person.count()]
    }


    def show(Person person) {
        log.error 'person.name: '+person.name
        log.error 'show'

        List myFriends = new ArrayList();
        def relationships = Relationship.findAllByPerson1(person)
        relationships.each { 
            myFriends.add(it.person2)
        }
        respond myFriends
    }
    
}