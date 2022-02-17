# Latest commit hash
GIT_SHA=$(shell git rev-parse HEAD)

# If working copy has changes, append `-local` to hash
GIT_DIFF=$(shell git diff -s --exit-code || echo "-local")
GIT_REV=$(GIT_SHA)$(GIT_DIFF)

# To flag when the working copy had changes, use GIT_REV. To just use
# the latest commit hash, use GIT_SHA.
build:
	cd result && docker build -t chaugh04/result:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile .
	cd vote && docker build -t chaugh04/vote:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile .
	cd worker && docker build -t chaugh04/worker:${GIT_SHA} --build-arg GIT_SHA=$(GIT_REV) --file Dockerfile .

run:
