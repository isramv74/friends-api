## Friends-api 1.0 Documentation
Here is where the magic happens
[DistanceController.groovy](grails-app%2Fcontrollers%2Ffriends%2Fapi%2FDistanceController.groovy)

<p>Create build: <br>
./gradlew build
</p>

Docker
<p>
Dockerhub<br>
docker pull isramv74/friends-api:v1.0<br>
docker run -p 0.0.0.0:80:8080/tcp isramv74/friends-api:v1.0
</p>
<p>
local image build:<br>
docker buildx build --load -t friends-api:latest .<br>
docker run -p 0.0.0.0:80:8080/tcp friends-api<br>
</p>

Testing in docker


<p>
Local enviroment<br>
Published port in docker is: 80<br>
http://localhost/<br>
Here you will see the controllers available, in general must produce browser output

</p>

Testing with curl
<p>
Get all persons:<br>
curl -i -H "Accept: application/json" http://localhost/person

Get distance from person1 to person2 using ids:<br> 
distance/person1.id/person2.id<br>
curl -i -X GET -H "Content-Type: application/json" http://localhost/distance/1/5

Get all the relationships<br>
curl -i -X GET -H "Content-Type: application/json" http://localhost/relationship

Create a Person<br>
curl -i -X POST -H "Content-Type: application/json" -d '{"name":"Raul"}' http://localhost/person

Create a Relationship<br>
Change the persons ids<br>
curl -i -X POST -H "Content-Type: application/json" -d '{"person1":{"id":4},"person2":{"id":3}}' http://localhost/relationship

</p>