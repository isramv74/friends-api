package friends.api

class BootStrap {

    def init = { servletContext ->
        //Adding dummy data
        def perla = new Person(name:"Perla").save()
        def raquel = new Person(name:"Raquel").save()
        def israel = new Person(name:"Israel").save()
        def adam = new Person(name:"Adam").save()
        def patricia = new Person(name:"Patricia").save()
        
        new Relationship(person1:perla, person2: raquel).save()
        new Relationship(person1:perla, person2: israel).save()
    }
    def destroy = {
    }
}