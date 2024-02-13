package friends.api


class DistanceController {
    static responseFormats = ['json', 'xml']
    
    def index() {
        List myFriends = new ArrayList<Person>();
        respond myFriends
    }

    def show(Person person) {
        def distance = [:]
        log.error 'person1.name: '+person.name
        log.error 'person2.id: '+params.id2
        Person person2 = Person.findById(params.id2)
        log.error 'person2.name: '+person2.name

        def array = [1]
        array[0]=findFriendshipDistance(person.id,person2.id)
        distance = [distance:array[0]]
        log.error 'distance:' +array[0] 

        respond( distance )
    }


    List<Long> getFriendIds(Long personId){
        List myFriends = new ArrayList();
        Person person = Person.findById(personId)

        if (person){
            def relationships = Relationship.findAllByPerson1(person)
            relationships.each { 
                myFriends.add(it.person2.id)
            }
        }
        myFriends
    }

    int findFriendshipDistance(Long startPersonId, Long targetPersonId) {
        if (startPersonId == targetPersonId) {
            return 0
        }

        Queue queue = new LinkedList()
        queue.add(startPersonId)

        Map<Long, Integer> visited = [:]
        visited.put(startPersonId, 0)

        while (!queue.isEmpty()) {
            Long currentUserId = queue.poll()
            int currentDistance = visited.get(currentUserId)

            List<Long> friends = getFriendIds(currentUserId)
            for (Long friendId : friends) {
                if (friendId == targetPersonId) {
                    return currentDistance + 1
                }

                if (!visited.containsKey(friendId)) {
                    queue.add(friendId)
                    visited.put(friendId, currentDistance + 1)
                }
            }
        }

        return -1 // Return -1 if there is no path between startPersonId and targetPersonId
    }
}