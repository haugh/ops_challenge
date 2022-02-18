# Latest commit hash
GIT_SHA=$(shell git rev-parse HEAD)

# If working copy has changes, append `-local` to hash
GIT_DIFF=$(shell git diff -s --exit-code || echo "-local")
GIT_REV=$(GIT_SHA)$(GIT_DIFF)

# To flag when the working copy had changes, use GIT_REV. To just use
# the latest commit hash, use GIT_SHA.
build:
	cd result && docker build -t kahiki.jfrog.io/kahiki-stable/result:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile . && docker push kahiki.jfrog.io/kahiki-stable/result:${GIT_SHA}
	cd vote && docker build --target python -t kahiki.jfrog.io/kahiki-stable/vote-python:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile . && docker push kahiki.jfrog.io/kahiki-stable/vote-python:${GIT_SHA} && docker build --target nginx -t kahiki.jfrog.io/kahiki-stable/vote-nginx:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile . && docker push kahiki.jfrog.io/kahiki-stable/vote-nginx:${GIT_SHA}
	cd worker && docker build -t kahiki.jfrog.io/kahiki-stable/worker:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile . && docker push kahiki.jfrog.io/kahiki-stable/worker:${GIT_SHA}