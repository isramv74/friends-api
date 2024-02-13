package friends.api
import grails.rest.*

/*
    FriendsController class
    Controller to get friend for a given person.
    Ex: curl -i -H "Accept: application/json" localhost:8080/friends/1
*/
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
        List myFriends = new ArrayList();
        def relationships = Relationship.findAllByPerson1(person)
        relationships.each { 
            myFriends.add(it.person2)
        }
        respond myFriends
    }
    
}