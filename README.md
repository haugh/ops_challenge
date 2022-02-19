# Overview of what was done to set this up
I have setup three different repositories in play with this setup. There is helm-app-files (These are the helm files that are used to deploy to kuberentes in a Dev,Staging,Production Enviornment), ops_challenege (This is the repo that was used for the actual applicaiton level code), and terraform-gcp (This is the terraform code that would be used to spin up a GKE cluster inside of GCP)

I have setup the cat-dog voting app to run based on contanerization. I have spun application up on my home Kubernetes cluster. The overall archicture is as follows: 3 node Kubernetes cluster with the following microservices: mettalb (This is the load balancer used), istio (Service Mesh), longhorn (this is used for mainiting persistent volumes accross the cluster), cloudflare (this is used as a proxy/CDN for my applicaitons), jfrog (This is used to store the docker image).

For this specific application, i have setup a namespace called dev, and used helm to create the following resources in the dev namespace: peek-results (Deployment), peek-vote (Deployment), peek-worker (Deployment), postgres (Statefulset), and redis (Statefulset).

# How to access voting and view results
You Can access the site by going to the following URLs:

voting-peek.kahiki.io
results-peek.kahiki.io

# How to bring up on a kubernetes cluster
To spin this up you can run from ops_challenge folder the following:

sudo make build 

This will actaully build the docker image and tag it with a sha and upload the image to jfrog. From there if you go into the helm-apps-files and go into the values.yaml file you will change the image.tag with the new sha. (This would be a automated step if there was a pipeline in play). Then you would run the following command to get the kubernetes resources on the cluster:

helm upgrade peek-voting --debug -f ../helm-app-files/values.yaml ../helm-app/files/ --install --namespace=dev
helm upgrade postgres install  bitnami/postgresql --install --namespace=dev

Now you have a working cat-dog voting app up and running

# DevOps Challenge

![](peek-squarelogo.png)


This repo contains an app comprised of multiple services with two data dependencies. Each directory contains a README with details for deploying the individual app.

The services are as follows:

* A front-end web app in [Python](/vote) which lets you vote between two options
* A [Java](/worker) worker which consumes votes and stores them in a DB
* A [Node.js](/result) webapp which reads from the db shows the results of the voting in real time

And they depend on a:

* A [Redis](https://redis.io/) queue which collects new votes
* A [Postgres](https://www.postgresql.org/) database

In addition there is a diagram in the root of this repository that you can use to visualize the architecture (with graphviz installed on your machine) using the following command:

~~~
dot -Tps architecture.dot -o architecture.png
~~~

### To complete this challenge

Create a script or series of scripts to deploy the application that has working data flow and also satisfies the following criteria:

1. Applications should also be behind a reverse proxy (Nginx, HAProxy, Envoy, etc)
2. Both Applications should be accessible behind a single load balancer using path-based routing in the reverse proxy.
3. SSL should be terminated at either the load balancer or the proxy level
4. Any provisioned compute should not be accessible from the open internet

Deliverables for cloud resources can be in any form (Terraform, Ansible, Cloudformation, Boto scripts, etc.) and should be able to be run without (a reasonable amount of) manual intervention against the chosen cloud provider to get the stack up in a real environment. Any orchestration layer for running the application is acceptable. Containerizing the services is encouraged. Please follow instructions for each service for build dependencies and relevant commands for running.

### Submission Process
Fork this repo and create your implementation files. Be sure to include any additional components and/or notes you would want as if it was your job to maintain this in a production environment. Submit a link to your forked repository and/or branch. *The estimated effort of this project is approx 4hrs.*
