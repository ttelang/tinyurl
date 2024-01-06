from diagrams import Diagram, Cluster
from diagrams.aws.compute import EC2
from diagrams.aws.database import RDS
from diagrams.aws.network import ELB
from diagrams.onprem.network import Nginx
from diagrams.onprem.inmemory import Redis
from diagrams.onprem.monitoring import Prometheus
from diagrams.aws.network import APIGateway
from diagrams.onprem.client import User

with Diagram("TinyURL Layered Architecture", show=False):
    user = User("User")

    with Cluster("Presentation Layer"):
        lb = ELB("Load Balancer")
        with Cluster("Web Servers"):
            web_servers = [Nginx("Web Server 1"), Nginx("Web Server 2")]

    with Cluster("Business Logic Layer"):
        api_gateway = APIGateway("API Gateway")
        with Cluster("URL Shortener Servers"):
            url_shorteners = [EC2("URL Shortener 1"), EC2("URL Shortener 2")]

        with Cluster("Redirection Servers"):
            redirections = [EC2("Redirection 1"), EC2("Redirection 2"), EC2("Redirection 3")]

    with Cluster("Data Storage Layer"):
        cache = Redis("Cache")
        db = RDS("Database")

    monitoring = Prometheus("Monitoring System")

    user >> lb >> web_servers >> api_gateway
    api_gateway >> url_shorteners >> cache >> db
    api_gateway >> redirections >> cache
    api_gateway >> monitoring
