# Stwitter Challenge

## Configurations and Ports  
Configuration locations [can be found here](https://github.com/BentAllenDesign/U2-M9-Challenge-Properties/)  
  
**Post service port:** 1777  
**Comment service port:** 6868  
**Comment queue port:** 3861  
**Stwitter service port:** 8889

## Notes  
Updating comments through the stwitter service must use a comment with both a postId and commentId  
Different services validate different things  
Both the comment service and the stwitter service (post-related requests only) implement caching  
  
The queue consumer sets up queues, routes, and exchanges using Declarables rather than individual Bean declarations  
There are queues for:  
- comment create
- comment delete
- comment update
- post delete
- post update
