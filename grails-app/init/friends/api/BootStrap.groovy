package friends.api

class BootStrap {

    def init = { servletContext ->
        //Adding dummy data
        def perla = new Person(name:"Perla").save()
        def raquel = new Person(name:"Raquel").save()
        def israel = new Person(name:"Israel").save()
        def adam = new Person(name:"Adam").save()
        def patricia = new Person(name:"Patricia").save()
        def juan = new Person(name:"Juan").save()
        def hayde = new Person(name:"Hayde").save()
        
        new Relationship(person1:perla, person2: raquel).save()
        new Relationship(person1:perla, person2: israel).save()
        new Relationship(person1:adam, person2: israel).save()
        new Relationship(person1:adam, person2: patricia).save()
        new Relationship(person1:hayde, person2: raquel).save()
    }
    def destroy = {
    }
}