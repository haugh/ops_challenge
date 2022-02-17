#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG PYTHON_DIGEST=sha256:b9ca0d225d42fd188bd636148229d121586fe7dcf3dcccfb23237b6401df4951
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
ENTRYPOINT [ "python app.py" ]