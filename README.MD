endpoints @ api/YouTubeController:

crawl playlist from a channel id  
crawl/playlist/{id}

crawl video items from a playlist id  
crawl/playlist/items/{id}

get playlist id from our db  
playlist/{id}

get video items from our db  
playlist/items/{id}

services/beans @ service/..  
ApiAuth: connect to the youtube's api  
YouTubeApiService: service methods that YouTubeController uses