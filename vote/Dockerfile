#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG PYTHON_DIGEST=sha256:c934af72b8bd03b9804d5bde2569c320926e70392d708d113a2e71bcf98c8a20
ARG NGINX_DIGEST=sha256:050385609d832fae11b007fbbfba77d0bba12bf72bc0dca0ac03e09b1998580f

FROM python@${PYTHON_DIGEST} as python

WORKDIR /app

COPY static /app/static
COPY templates /app/templates
COPY app.py /app/
COPY requirements.txt /app/

RUN pip install -r requirements.txt

FROM nginx@${NGINX_DIGEST} as nginx
COPY --from=python /app/ /app
ENTRYPOINT [ "python /app/app.py" ]