## VacFlixBackend
Crawl YouTube's channel playlists and the playlist videos to store them in our db.  
Then interact with the data through a RESTful API

## Instructions
Get YouTube v3 API and save it in resources/application.properties    
youtube.apikey=your_api_key  
add datasource cfg  
spring.datasource.url=jdbc:mysql://localhost:3306/vacflix
spring.datasource.username=root
spring.datasource.password=password  

Execute the db.sql from /sql


### endpoints @ api/YouTubeController:

crawl playlist from a channel id  
crawl/playlist/{id}

crawl video items from a playlist id  
crawl/playlist/items/{id}

crawl a single video id
crawl/video/{id}

get playlist id from our db  
playlist/{id}

get video items from our db  
playlist/items/{id}

get single video for testing purposes
video/{id}

get videos from a search query  
search/{query}

TODO: update, delete, etc

### services/beans @ service/.. 
**YouTubeApiService**: service methods that YouTubeController uses
ApiAuth: connect to the youtube's api  
rest are beans and interfaces for easier modification

### TODOs
- spread service layer into better/more microservices (almost done)
- dockerize
- cleanup