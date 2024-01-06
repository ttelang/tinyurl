# Problem
Design a URL Shortening Service, similar to bit.ly or tinyURL?

# Restating the Problem
Assuming that we would like to design a URL shortener service, various considerations need to be taken into account. To start with, we need to decide on the functionality that our service will offer. 

For instance, will users be able to customize their shortened URLs? 
Alternatively, will the service simply generate a random string of characters for each URL?

In terms of scalability, one important question is how we will store the data associated with each shortened URL. For example, will we use a database? If so, which type of database would be best suited for our needs? In addition, we need to consider how often data will be accessed and whether or not we will need to support real-time lookups.

Finally, we need to think about how our service will be used. Will users be interacting with it via a web interface? Or will they be using an API? Depending on our answer, we may need to design our system accordingly.

In summary, designing a URL shortener service requires careful planning and consideration of various factors. By taking the time to think about our requirements upfront, we can create a system that is both scalable and secure.

# Introduction 
A simple implementation of tiny URL using the minimalistic approach, it shortening a long original URL into a shorter and manageable URL.

This project is about system design of a URL shortener service like bit.ly, goo.gl or tinyurl.com. We'll look into the various design aspects and trade-offs involved in creating such a service. A URL shortener service is a web service that provides shortened URLs for long URLs. The shortened URL is easier to remember and share than the long URL. The service also provides a way to track clicks on shortened URLs.

There are many existing URL shortener services, such as bit.ly, goo.gl, and tinyurl.com. In this project, we will design our own URL shortener service.

**Existing Services:** 
tinyurl.com, bit.ly, ow.ly, short.io, bl.ink, Rebrandly, shorby, sniply

## Clarifications
- Can users create private URLs or allow a particular set of users to access a URL?
- How many times a short URL has been used, what were user locations, etc.? 
- How would we store these statistics? 
- Customization: Users should be able to choose their own custom URL instead of a randomly generated URL. 
- Tracking: The service should be able to track the number of clicks on each shortened URL. 
- Storage: We will use a database to store the data associated with each shortened URL. 
- Access Time: Data will be accessed in real-time. 
- Security: We will rate limit requests and implement some type of filtering to prevent abuse. 
- Usage: Users will be interacting with the service via an API. 

If it is part of a DB row that gets updated on each view, what will happen when a popular URL is slammed with a large number of concurrent requests?

## User Stories or Use Cases

1. URL Shortening (MVP):  As a user, I want to enter a long URL and receive a unique shortened URL so that I can easily share it across platforms where space is limited or a shorter URL is more convinient. 
Details: The system should ensure that 2 URLs never map to the same shortened URL.
2. Custom Short Links (Optional): As a user, I want the option to customize the short link when creating it so that I can make it memorable, meaningful, or branded for my specific needs. 
3. URL Redirection (MVP): As a user I can click on the shortened URL into the browser to get redirected to the original long URL so that I can access the content intended by the short link without needing to know the full URL.
4. Link Expiration: As a user, I want my short links to have an expiration date so that they automatically become inactive after a certain period, ensuring they're only used within the timeframe I deem relevant.
5. Specifying Expiration Time: As a user, I want the ability to specify the expiration time for my short links so that I can control their longevity based on my requirements or preferences.

* User Analytics: As a marketing analyst, I want to access detailed analytics on URL usage, including click-through rates, geographical data, and device information, so that I can understand user engagement and refine marketing strategies.
* Data Privacy Compliance: As a legal compliance officer, I want the system to adhere to data privacy laws and regulations like GDPR, ensuring that user data is handled and stored in compliance with legal requirements.
* Security Monitoring: As a security specialist, I want to monitor the system for any unusual activities or potential security threats, such as brute-force attacks or unauthorized access attempts, so that I can safeguard the system against cyber threats.
* Monitoring for System Health: As a system administrator, I want to continuously monitor the health of the system, including uptime, response times, and server load, so that I can proactively address issues and maintain high system availability.
* Telemetry for System Performance: As a system engineer, I want to collect real-time telemetry data on system performance, including request rates, latency, and error rates, so that I can ensure the system is performing optimally and identify areas for improvement.
* Authentication User Story for Users: As a user, I want to have the ability to create an account and log in securely, so that I can track and manage my created short URLs and access detailed analytics.
* Security: As a system administrator, I want to ensure that all data transfer within the system is securely encrypted, both in transit and at rest, so that user data and URL information are protected from unauthorized access or breaches.

Other examples
* As a user, I want to be able to shorten a URL so that I can share it more easily. 
* As a user, I want to be able to view statistics about how many people have clicked on my shortened URL so that  I can track its effectiveness. 
* As a user, I want to be able to choose my own short code for a URL so that I can remember it more easily. 
* As a user, I want the service to be secure so that my data is not compromised. 
* As a user, I want the service to be fast so that I can shorten and share my URLs as quickly as possible. 
* As a user, I want the service to be free so that I don't have to pay anything to use it. 

Below is the use case diagram for this system.
![image](https://github.com/ttelang/tinyurl/assets/133903/c2501f0f-f31c-4be8-a7dc-9978fb77cd1f)

**Actors:**
* _User:_ Represents the end user of the URL shortening service.

**Use Cases:**
* _Enter Long URL (UC1):_ Users input a long URL they wish to shorten.
* _Receive Short URL (UC2):_ The system generates a short URL.
* _Redirect to Long URL (UC3):_ Users are redirected to the original URL when accessing the short URL.
* _Customize Short URL (UC4):_ Users request a custom short URL.
* _Specify Expiration Time (UC5):_ Users set an expiration time for the short URL.

## Non-Functional Requirements

- The system should have very low latency (URL redirection should happen in real-time with minimal latency.)
- the system should be highly available even if the machine storing the URL mapping goes down due to Power outage, Hard Disk crashes etc.
- The system should stay consistent if a user have shortened a URL, given the shortened URL, 
the system should always return the original URL no matter when the URL is requested from the system. 
- the service should never go down. (No outage). The machines may die. 
the system should make sure your service is unaffected by these incidents (unless all of your machines die at once - that can happen very rarely).

For designing a scalable URL Shortening Service, particularly in the context of a high-demand environment like that of a major tech company, it's crucial to consider specific scalability and user metrics. Here are key metrics to keep in mind:
* User Base Size and Growth Rate: Anticipate scaling for up to 1 billion users, with a growth rate projection of 10-20% per year.
* Daily Active Users (DAU): Plan for a scenario where up to 100 million users are actively using the service daily.
* URL Shortening Requests: The system should be able to handle millions of URL shortenings per day, peaking at 10,000 to 20,000 requests per minute during high-traffic periods.
* URL Redirection Requests: Expect a higher volume of redirection requests than shortening requests, potentially in the range of tens of millions per day.
* Data Storage Growth: Plan for exponential growth in data storage, as each URL shortened and its metadata need to be stored. Assume a yearly data growth rate of 50-100%.
* Response Time: Aim for sub-200 millisecond response times for both creating short URLs and redirecting to the original URLs.
* System Availability: Target 99.99% uptime to ensure high availability, which translates to no more than 52.56 minutes of downtime per year.
* Load Variability: Be prepared for variable loads, with potential spikes during specific events or viral content peaks. The system should handle up to 3x the average load.
* Concurrent Users: Design for tens of thousands of concurrent users performing URL shortenings and redirections.
* Bandwidth and Throughput: Ensure sufficient bandwidth and throughput to handle peak traffic loads, considering both incoming URL shortening requests and outgoing redirection traffic.
* Cache Hit Ratio:  Aim for a high cache hit ratio (e.g., > 90%) for URL redirection to reduce database load and improve response time.
* Data Backup and Recovery: Plan for regular data backups and a robust recovery strategy to handle data loss scenarios, with a recovery time objective (RTO) of a few hours.

Below is the table of non-functional requiremetns for a URL Shortening Service

| Requirement Category | Specific Requirement                              | Target Metric                                      |
|----------------------|---------------------------------------------------|----------------------------------------------------|
| Availability         | System availability                               | 99.99% uptime                                      |
| Latency              | URL redirection latency                           | < 200 milliseconds for 95% of requests             |
| Throughput           | Request handling capacity                         | At least 10,000 URL shortenings per minute         |
| Scalability          | User support                                      | Up to 1 billion registered users                   |
|                      | Daily active user support                         | Handle 100 million daily active users              |
| Data Durability      | Reliability of data storage                       | 99.999% durability                                 |
| Security             | Data security                                     | Encryption in transit and at rest, GDPR compliance |
| Load Handling        | Traffic surge management                          | Handle 3x average traffic without performance loss  |
| System Monitoring    | Real-time system health check                     | Monitoring metrics with < 1-minute data lag        |
| Disaster Recovery    | Service restoration capability                    | Restore service within 2 hours of major incident   |
| Maintenance          | Scheduled downtime for updates and improvements  | Not exceed 4 hours per month                       |

## Capactiy Estimation and Constraints

### Assumptions
#### CPU (Compute)
- For this **read heavy system**, Let's assume **100:1** ratio between read and write.
- We store a URL for **5 years**.
- Traffic received would be around **500 million new URL shortenings per month**. 
- redirections per month would be **100 * 500 million = 50 billion**
- Queries per second 500 million / (30 days * 24 hours * 3600 s) = **20000 requests / sec**

#### Storage 
- Total number of URLs to be stored - 500 million * 5 year * 12 months = 30 Billion URLs
- Let's assume, storage used per URL is around 1Kb
- Total Storage needed would be 30 Billion URLs * 1Kb = **30 TB** 

#### Network Bandwidth estimates 
Input or Write requests
 200 * 1 Kb = 200 Kb/s

Output or Read Requests
20000 * 1Kb / sec = 20 MB/s

#### Cache
80-20 rule, 20% of URLs would generate 80% of traffice
We would need to cahche 20% of URLs using LRU (least recently used) policy. (Linked Hash Map data structure is used to store URLs and Hashes)

20000 * 3600 seconds * 24 hours = 1.7 billion  
Cache storage = 1.7 * 20% * 1Kb = **340 GB**. (We would have many duplicate requests, so actual cache required could be lessor)

## APIs

1. Create short URL

```
POST /api/link HTTP/1.1
Host: urlshrtnr.taruntelang.repl.co
Content-Type: application/x-www-form-urlencoded
link= <URL>

Response: 
<shortURL>
```
for e.g. link = `https://www.linkedin.com/in/taruntelang/`
shortURL =  6U8Ahx9

2. Get Original URL

```
GET /<shortURL> HTTP/1.1
Host: urlshrtnr.taruntelang.repl.co
```

Short links are easy to display, print, share via message or tweet. They are also used to analyze audience, measure ad campaigns performance or hide affiliated URLs 

## Components
At a high level, the service would need an efficient way to store and retrieve the mappings between shortened URLs and the original URLs. This involves a reliable database. We'll also need a front-end interface for users to input their URLs, and a backend service that handles the business logic - including generating the short URL, storing it, and redirecting users.

Let's dive deeper into the URL generation logic. How would you ensure that the URLs are unique and not easily guessable?
We can use a hash function to generate a short URL from the original URL. To avoid collisions, we can append an incremental counter or a timestamp. For non-guessability, we can use a random string generator along with hashing. But we need to ensure that the same URL doesn't get different shortened URLs, to maintain consistency.

Following are the key components of a TinyURL service: 
1. **Load Balancer:**
- Effectively distributes incoming network traffic across multiple servers.
- Enhances availability and prevents any single server from becoming a bottleneck.
2. **Web Server:**
- Manages HTTP requests from users and clients.
- Serves as the face of the application, handling all user interactions.
3. **API Gateway:**
- Acts as a single entry point for all API requests.
- Manages API routing, security (like API keys and rate limiting), and authorization processes.
4. **App Servers:**
- Host the application logic including:
  - Redirection Logic: Manages the redirection of short URLs to the original long URLs.
  - URL Shortening Service: Contains the core logic for generating short URLs, typically using hashing algorithms or unique ID generators.
5. **Database:**
- Stores critical data such as mappings between short and original URLs, user account information, custom URL requests, and expiration details.
6. **Cache:**
- Implements caching solutions like Redis or Memcached to enhance performance.
- Reduces the load on the database by caching frequent queries.
7. **Monitoring and Alerting System:**
- Continuously monitors system health and performance.
- Triggers alerts and notifications in case of system failures or performance degradation.

![TinyURL Architecture](https://github.com/ttelang/tinyurl/assets/133903/b10ec3ff-4d35-4dc4-a50b-8eff07c4b32d)

This architecture provides a robust framework for a TinyURL service, ensuring efficiency, scalability, and reliability. Each component plays a vital role in the system's overall functionality and user experience.
Each of these components plays a specific role in the system:

- **User: **Initiates the interaction with the TinyURL service.
- **Load Balancer (ELB):** Distributes incoming traffic to web servers to balance the load.
- **Web Servers (Nginx): **Handle HTTP requests and serve the web application.
- **API Gateway:** Manages API requests, providing routing, security, and other cross-cutting concerns.
- **URL Shortener Servers (EC2):** Dedicated servers for handling URL shortening logic.
- **Redirection Servers (EC2):** Servers that handle redirecting short URLs to the original URLs.
- **Cache (Redis):** Provides fast access to frequently requested data, reducing load on the database.
- **Database (RDS):** Stores all persistent data, including URL mappings and user information.
- **Monitoring System (Prometheus):** Monitors the health and performance of the entire system.

How would you handle the data storage for this many URLs and requests?
We would need a highly scalable database. A NoSQL database like Cassandra, known for its high write throughput, would be suitable here. We also need to consider data partitioning and replication for high availability and fast read/write access.

Considering the massive scale, how would you ensure low latency and high availability of the system?
Caching frequently accessed URLs at the edge using a service like Redis can significantly reduce latency. For high availability, we should deploy our service across multiple data centers and use load balancing to distribute traffic.

Let's touch on analytics and monitoring. How would you implement these in your design?
For analytics, we can log each URL access with its metadata, like timestamp and user info, and process these logs asynchronously to generate insights. For monitoring, we’d use a combination of system metrics and application-level metrics to monitor the health and performance of our service.

how would you approach security and authorization for creating short links?
We should implement API keys for users to authenticate requests. Rate limiting is also crucial to prevent abuse. For sensitive URLs, we can add an option for users to create private links, which are only accessible to users with the right permissions.

### Telemetry
Some statistics worth tracking: 
- country of the visitor, 
- date and time of access, 
- web page that referred the click, 
- browser, 
- platform from where the page was accessed.

### Health Check System
### Load Balancer 
### Application Server
### Cache
We can also use a load balancer to distribute traffic among multiple web servers. This will help to ensure that the system can handle a large number of requests. 

### Database
- URL Table
   UrlId: NUMBER <PRIMARY KEY>
   OriginalURL: VARCHAR
   CreationDate: DATETIME
   ExpiryDate: DATETIME
   UserId: NUMBER <FOREIGN KEY>
 
 - User
   UserId: NUMBER <PRIMARY KEY>
   Name: VARCHAR
   Email: VARCHAR
   CreationDate: DATETIME
   LastLoginDate: DATETIME

We need to store billions of rows we can go with NoSQL database like Cassandara. This will be easy to scale. Also there are not many relationships.
  
## Design
shortURL consists of 7 character in base64 format. Characterset for shortURL [a-z A-Z 0-9 - _]  

## Implementation
Currently this project is only implemented using NodeJS. Kindly email me at (tarun.telang@gmail.com) if you are interested in implementing this using .NET, Python, Spring Boot or any other framework.

