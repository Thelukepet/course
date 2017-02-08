Start a container running Spark Notebook, and start a shell inside the container:

    # DC = docker run ...
    DC=3b6cc93fb24d

    docker exec -it $DC bash

Download sample server log data.

    cd /tmp
    wget http://s3.amazonaws.com/hw-sandbox/tutorial12/serverfiles.zip


