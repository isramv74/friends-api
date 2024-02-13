package friends.api

/*
    DistanceController class
    Controller to get relationship distance between 2 persons
    Using personId of each person
    Ex: curl -i -X GET -H "Content-Type: application/json" http://localhost:8080/distance/1/2
*/
class DistanceController {
    static responseFormats = ['json', 'xml']
    
    /*
        Dummy method, empty response.
    */
    def index() {
        List myFriends = new ArrayList<Person>();
        respond myFriends
    }

    /*
        GET method for getting the distance:
        distance/<personId1>/<personId2>
        distance/5/6
    */
    def show(Person person) {
        def distanceMap = [:]
        int distanceInt = -1
        
        Person person2 = Person.findById(params.id2)

        if( person && person2){
            log.debug 'person1.name: '+person.name
            log.debug 'person2.name: '+person2.name

            distanceInt = searchForFriendsDistance(person.id,person2.id)
        }
        
        distanceMap = [distance:distanceInt]
        log.debug 'distance:' + distanceInt 

        respond( distanceMap )
    }

    /*
        searchForFriendsDistance: helper method to search both ways
        TODO: move it to service layer
    */
    int searchForFriendsDistance(Long startPersonId, Long targetPersonId){
        int distanceInt = findFriendshipDistance(startPersonId,targetPersonId)
            if( distanceInt == -1)
                distanceInt = findFriendshipDistance(targetPersonId,startPersonId)
        // Return -1 if there is no path between startPersonId and targetPersonId
        return distanceInt   
    }


    /*
        getFriendIds: helper method to get all the ids of of a person into a list.
        TODO: move it to service layer
        TODO: avoid duplicates
    */
    List<Long> getFriendIds(Long personId){
        List myFriends = new ArrayList();
        Person person = Person.findById(personId)

        if (person){
            def relationships = Relationship.findAllByPerson1(person)
            relationships.each { 
                myFriends.add(it.person2.id)
            }
            relationships = Relationship.findAllByPerson2(person)
            relationships.each { 
                myFriends.add(it.person2.id)
            }
        }
        myFriends
    }


    /*
        findFriendshipDistance helper method to get the distance between to persons
        from startPersonId to targetPersonId
        based on BFS algorithm

        TODO: move it to service layer
    */
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
        // Return -1 if there is no path between startPersonId and targetPersonId
        return -1 
    }
}