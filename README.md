DevOps Challenge

This repo contains an app comprised of multiple services with two data dependencies. Each directory contains a README with details for deploying the individual app. They are as follows:

* A front-end web app in [Python](/vote) which lets you vote between two options
* A [Java](/worker) worker which consumes votes and stores them in…
* A [Node.js](/result) webapp which reads from the db shows the results of the voting in real time

And they depend on a

* A [Redis](https://redis.io/) queue which collects new votes
* A [Postgres](https://www.postgresql.org/) database

In addition there is a diagram in the root of this repository that you can use to visualize the architecture (with graphviz installed on your machine) using the following command:

~~~
dot -Tps architecture.dot -o architecture.png
~~~

To complete this challenge, include a script or series of scripts to deploy the application that has working data flow and also satisfies the following criteria:

1. Applications should also be behind a reverse proxy (Nginx, HAProxy, Envoy, etc)
2. Both Applications should be accessible behind a single load balancer using path-based routing in the reverse proxy.
3. SSL should be terminated at either the load balancer or the proxy level
4. Any provisioned compute should not be accessible from the open internet

Deliverables for cloud resources can be in any form (Terraform, Ansible, Cloudformation, Boto scripts, etc.) and should be able to be run without (a reasonable amount of) manual intervention against the chosen cloud provider to get the stack up in a real environment. Any orchestration layer for running the application is acceptable. Containerizing the services is encouraged. Please follow instructions for each service for build dependencies and relevant commands for running.

Once initial setup is complete, include any additional components and/or notes you would want as if it was your job to maintain this in a production environment.
