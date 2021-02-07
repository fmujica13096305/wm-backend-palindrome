#!/bin/bash

is_local=true

if $is_local; then
    # run mongo so we can import data
    mongod &
    pid=$!
    echo "Started mongod to import data, pid: ${pid}"
    server="localhost"
fi
echo $(ls /database)

echo "Importing sample datasets into $server"

mongoimport --host localhost --db promotions --collection products --file /database/products.json --jsonArray -v

#cat mongoimportoutput.txt
#    mongoimport --host ${HOST} --username productListUser --password productListPassword --authenticationDatabase admin --db promotions --collection ${COLLECTION} --mode upsert --file ${AFILE}


echo "Data Imported $server"

#if $is_local; then
#    # kill and re-run mongo, so we make sure we substitute the mongod process as the container PID 1, maybe a way around this?
#    kill $pid
#    wait # wait for kill to go through or we'll try to launch two instances of mongod
#    exec mongod --bind_ip_all
#fi