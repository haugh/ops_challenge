## Voting Submission App

This app is written in Python Flask and submits votes for one of two categories to a Redis DB. Voting is only allowed once per client. This application requires the following env vars to run:

~~~
# The hostname of the running Redis db
REDIS_HOST=

# (Optional) The port the server listens on. Defaults to 80
PORT=

# (Optional) Voting category one. Defaults to 'Cats'
OPTION_A=

# (Optional)
OPTION_B= Voting category one. Defaults to 'Dogs'
~~~

This app is tested in **Python 2.7**. To build its dependencies run the following:

~~~
pip install -r requirements.txt
~~~

To run this app, issue the following command:

~~~
python app.py
~~~
