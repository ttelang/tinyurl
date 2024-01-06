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

1. (MVP) As a user you can enter a URL to shorten in the system to get back a unique shortened URL. 
Details: The system should ensure that 2 URLs never map to the same shortened URL.

2. (Optional) As a user you can enter a URL to shorten and a custom string, so that The system returns shortened URL with the custom string as suffix as inputted by user provided this shortened URL with the custom string as suffix is available. 

3. (MVP) As a user you can type the shortened URL into the browser to get redirected to the original URL. 

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

We need to store billions of rows we can go with NoSQL database. This will be easy to scale. Also there are not many relationships.
  
## Design
shortURL consists of 7 character in base64 format. Characterset for shortURL [a-z A-Z 0-9 - _]  

## Implementation
Currently this project is only implemented using NodeJS. Kindly email me at (tarun.telang@gmail.com) if you are interested in implementing this using .NET, Python, Spring Boot or any other framework.

